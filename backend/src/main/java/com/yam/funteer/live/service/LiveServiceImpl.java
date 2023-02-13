package com.yam.funteer.live.service;

import com.yam.funteer.attach.FileType;
import com.yam.funteer.attach.FileUtil;
import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.attach.entity.TeamAttach;
import com.yam.funteer.attach.repository.AttachRepository;
import com.yam.funteer.attach.repository.TeamAttachRepository;
import com.yam.funteer.common.aws.AwsS3Uploader;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.exception.DuplicateInfoException;
import com.yam.funteer.exception.SessionNotFoundException;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.live.dto.CreateConnectionRequest;
import com.yam.funteer.live.dto.CreateConnectionResponse;
import com.yam.funteer.live.dto.GiftRequest;
import com.yam.funteer.live.dto.SessionLeaveRequest;
import com.yam.funteer.live.entity.Gift;
import com.yam.funteer.live.entity.Live;
import com.yam.funteer.live.repository.GiftRepository;
import com.yam.funteer.live.repository.LiveRepository;
import com.yam.funteer.user.entity.Member;
import com.yam.funteer.user.entity.Team;
import com.yam.funteer.user.entity.User;

import com.yam.funteer.user.repository.MemberRepository;
import com.yam.funteer.user.repository.TeamRepository;
import com.yam.funteer.user.repository.UserRepository;
import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service @Slf4j
@Transactional
@RequiredArgsConstructor
public class LiveServiceImpl implements LiveService{
    @Value("${OPENVIDU.URL}")
    private String OPENVIDU_URL;
    @Value("${OPENVIDU.SECRET}")
    private String OPENVIDU_SECRET;
    private OpenVidu openVidu;
    private final Map<String, Session> mapSessions = new ConcurrentHashMap<>();

    private final AwsS3Uploader awsS3Uploader;

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final LiveRepository liveRepository;
    private final GiftRepository giftRepository;
    private final FundingRepository fundingRepository;
    private final AttachRepository attachRepository;
    private final TeamAttachRepository teamAttachRepository;

    @PostConstruct
    public void init(){
        this.openVidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    @Override
    public CreateConnectionResponse initializeSession(CreateConnectionRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        String sessionName = request.getSessionName();
        if(mapSessions.containsKey(sessionName)){
            log.info("이미 생성된 세션 참여 => {}", sessionName);
            return joinExistingSession(request, user);
        }else if(canPublish(user)){ // 방을 생성할 수 있다면
            log.info("새로운 세션 생성 => {}", sessionName);

            Long fundingId = request.getFundingId();
            Funding funding = fundingRepository.findByFundingId(fundingId)
                .orElseThrow(IllegalArgumentException::new);

            Team fundingTeam = funding.getTeam();
            if(!fundingTeam.equals(user)) throw new AccessDeniedException("펀딩을 개최한 단체와 다릅니다.");

            RecordingProperties recordingProperties = request.toRecordingProperties();
            return createNewSession(sessionName, funding, user, recordingProperties);
        }

        throw new AccessDeniedException("유저정보가 이상하거나 권한이 잘못 설정되었습니다.");
    }

    @Override
    public void leaveSession(SessionLeaveRequest request) {
        String sessionName = request.getSessionName();

        Long userId = SecurityUtil.getCurrentUserId();

        if(mapSessions.containsKey(sessionName)){
            String sessionId = mapSessions.get(sessionName).getSessionId();
            Session session = this.openVidu.getActiveSession(sessionId);

            Optional<Connection> optConn = session.getConnections().stream()
                    .filter(conn -> conn.getServerData().equals(String.valueOf(userId))).findFirst();
            optConn.ifPresent(connection -> {
                if(connection.getRole().equals(OpenViduRole.PUBLISHER)) {
                    Live live = liveRepository.findBySessionId(session.getSessionId()).orElseThrow(IllegalArgumentException::new);
                    live.end();
                    log.info("라이브 종료 => {}", live.getEndTime());

                    mapSessions.remove(sessionName);
                    log.info("map에서 {} 제거", sessionName);

                    // 녹화 종료
                    if(session.isBeingRecorded()) {
                        recordSaveThisSession(session.getSessionId());
                    }
                }

                openviduFetch();
            });

        }
    }


    private CreateConnectionResponse createNewSession(String sessionName, Funding funding, User user,
                                                      RecordingProperties recordingProperties) {
        try {
            log.info("세션 생성 ===========> {}", sessionName);

            SessionProperties sessionProperties = new SessionProperties.Builder()
                    .defaultRecordingProperties(recordingProperties).build();
            Session session = this.openVidu.createSession(sessionProperties);

            OpenViduRole role = user.getUserType().getOpenviduRole();

            ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                    .type(ConnectionType.WEBRTC).role(role).data(String.valueOf(user.getId()))
                    .build(); // PUBLISHER or MODERATOR

            String token = session.createConnection(connectionProperties).getToken();

            mapSessions.put(sessionName, session);

            // DB에 저장
            Live live = Live.of(session.getSessionId(), funding);
            liveRepository.save(live);

            return new CreateConnectionResponse(token);
        } catch (Exception e) {
            log.error(e.getMessage());
            mapSessions.remove(sessionName);
            throw new RuntimeException(e.getMessage());
        }
    }

    private CreateConnectionResponse joinExistingSession(CreateConnectionRequest request, User user) {

        String sessionName = request.getSessionName();
        String sessionId = mapSessions.get(sessionName).getSessionId();

        try {
            openviduFetch();
            Session session = this.openVidu.getActiveSession(sessionId);
            if(session == null){
                log.info("OpenVidu 서버에 동작중인 세션이 없음");
                mapSessions.remove(sessionName);
                return initializeSession(request);
            }

            if(!session.isBeingRecorded()){
                this.openVidu.startRecording(sessionId);
            }

            Long userId = user.getId();
            session.getActiveConnections().forEach(connection -> {
                log.info("클라이언트데이터 => {}",connection.getClientData());
                log.info("서버데이터 => {}", connection.getServerData());

                String connUserId = connection.getServerData();
                if(connUserId != null && connUserId.equals(String.valueOf(userId))){
                    throw new DuplicateInfoException("이미 세션에 참가한 회원입니다.");
                }
            });


            ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                    .role(OpenViduRole.SUBSCRIBER).data(String.valueOf(user.getId())).build();

            String token = session.createConnection(connectionProperties).getToken();


            return new CreateConnectionResponse(token);
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            log.error(e.getMessage());

            log.info("OpenVidu 서버에 동작중인 세션이 없음");
            mapSessions.remove(sessionName);
            return initializeSession(request);
        }
    }

    private Recording getSessionRecording(String sessionId) {
        try {
            Recording recording = this.openVidu.getRecording(sessionId);
            return this.openVidu.stopRecording(recording.getId());
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
    }
    private void recordSaveThisSession(String sessionId){
        log.info("녹화 저장 시작");
        Long teamId = SecurityUtil.getCurrentUserId();
        Team team = teamRepository.findById(teamId).orElseThrow(UserNotFoundException::new);

        log.info("sessionId => {}", sessionId);
        Recording recording = getSessionRecording(sessionId);

        String fileUrl = recording.getUrl();
        log.info(fileUrl);

        File file = FileUtil.downloadFromUrl(fileUrl);
        String path = awsS3Uploader.upload(file, "live");

        Attach attach = Attach.of(file.getName(), path, FileType.LIVE);
        attachRepository.save(attach);

        TeamAttach teamAttach = TeamAttach.of(team, attach);
        teamAttachRepository.save(teamAttach);

        removeRecordingInServer(recording);

    }

    private void removeRecordingInServer(Recording recording) {
        try{
            this.openVidu.deleteRecording(recording.getId());
        }catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getCurrentActiveSessions() {
        openviduFetch();
        List<Session> activeSessions = this.openVidu.getActiveSessions();
        List<String> response = new ArrayList();

        mapSessions.forEach((s, session) -> {
            Optional<Session> findSameSession = activeSessions.stream().filter(activeSession -> activeSession.getSessionId().equals(session.getSessionId())).findFirst();
            if(!findSameSession.isPresent()){
                mapSessions.remove(s);
            }else{
                response.add(s);
            }
        });
        return response;
    }

    @Override
    public void giftToFundingTeam(GiftRequest request) {
        String sessionName = request.getSessionName();
        if(mapSessions.containsKey(sessionName)){
            Session session = mapSessions.get(sessionName);
            openviduFetch();

            Session activeSession = this.openVidu.getActiveSession(session.getSessionId());
            if(activeSession == null){
                throw new SessionNotFoundException();
            }

            Live live = liveRepository.findBySessionId(activeSession.getSessionId()).orElseThrow(SessionNotFoundException::new);

            Long userId = SecurityUtil.getCurrentUserId();
            User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
            Long amount = request.getAmount();

            if(amount < 0) throw new IllegalArgumentException("음수는 입력하시면 안돼요");

            user.checkMoney(amount);

            Team team = live.getTeam();
            team.updateMoney(amount);
            user.updateMoney(-amount);

            Gift gift = Gift.from(live, user, amount);
            giftRepository.save(gift);
            return;
        }

        throw new SessionNotFoundException();
    }

    private void openviduFetch(){
        try {
            this.openVidu.fetch();
        } catch (OpenViduJavaClientException e) {
            throw new RuntimeException(e);
        } catch (OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean canPublish(User user) {
        return user != null && user.getUserType().doPublish();
    }
}
