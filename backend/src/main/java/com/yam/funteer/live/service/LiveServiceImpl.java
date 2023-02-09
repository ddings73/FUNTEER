package com.yam.funteer.live.service;

import com.yam.funteer.attach.FileType;
import com.yam.funteer.attach.FileUtil;
import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.attach.entity.TeamAttach;
import com.yam.funteer.attach.repository.AttachRepository;
import com.yam.funteer.attach.repository.TeamAttachRepository;
import com.yam.funteer.common.aws.AwsS3Uploader;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.live.dto.CreateConnectionRequest;
import com.yam.funteer.live.dto.CreateConnectionResponse;
import com.yam.funteer.live.dto.SessionLeaveRequest;
import com.yam.funteer.live.entity.Live;
import com.yam.funteer.live.repository.LiveRepository;
import com.yam.funteer.user.entity.Team;
import com.yam.funteer.user.entity.User;

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
import java.util.Map;
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
    private final Map<String, Map<String, OpenViduRole>> mapSessionNamesTokens = new ConcurrentHashMap<>();
    private final Map<String, Boolean> sessionRecordings = new ConcurrentHashMap<>();

    private final AwsS3Uploader awsS3Uploader;

    private final UserRepository userRepository;
    private final LiveRepository liveRepository;
    private final FundingRepository fundingRepository;
    private final TeamRepository teamRepository;
    private final AttachRepository attachRepository;
    private final TeamAttachRepository teamAttachRepository;

    @PostConstruct
    public void init(){
        this.openVidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    @Override
    public CreateConnectionResponse initializeSession(CreateConnectionRequest request) {
        User user = null;
        if(SecurityUtil.isLogin()) {
            Long userId = SecurityUtil.getCurrentUserId();
            user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        }

        String sessionName = request.getSessionName();
        if(mapSessions.containsKey(sessionName) && mapSessionNamesTokens.containsKey(sessionName)){
            log.info("이미 생성된 세션 참여 => {}", sessionName);
            return joinExistingSession(request, OpenViduRole.SUBSCRIBER);
        }else if(canPublish(user)){ // 방을 생성할 수 있다면
            log.info("새로운 세션 생성 => {}", sessionName);

            Long fundingId = request.getFundingId();
            Funding funding = fundingRepository.findById(fundingId)
                .orElseThrow(IllegalArgumentException::new);

            Team fundingTeam = funding.getTeam();
            if(!fundingTeam.equals(user)) throw new AccessDeniedException("펀딩을 개최한 단체와 다릅니다.");

            OpenViduRole role = user.getUserType().getOpenviduRole();
            return createNewSession(sessionName, role, funding);
        }

        throw new AccessDeniedException("유저정보가 이상하거나 권한이 잘못 설정되었습니다.");
    }

    @Override
    public void leaveSession(SessionLeaveRequest request) {
        String sessionName = request.getSessionName();
        String token = request.getToken();

        if(mapSessions.containsKey(sessionName) && mapSessionNamesTokens.containsKey(sessionName)){ //session 이 존재하면서
            Map<String, OpenViduRole> tokenRoleMap = mapSessionNamesTokens.get(sessionName);
            if(mapSessionNamesTokens.get(sessionName).containsKey(token)){ // 토큰값도 존재하면
                OpenViduRole role = tokenRoleMap.get(token);
                tokenRoleMap.remove(token);

                log.info("{} 권한을 가진 사용자가 세션 {} 를 떠났습니다.", role, sessionName);

                if(role.equals(OpenViduRole.PUBLISHER)){ // publisher ( 방송주인 체크 )
                    Session session = mapSessions.get(sessionName);
                    Live live = liveRepository.findBySessionId(session.getSessionId()).orElseThrow(IllegalArgumentException::new);
                    live.end();
                    log.info("라이브 종료 => {}", live.getEndTime());


                    mapSessions.remove(sessionName);
                    mapSessionNamesTokens.remove(sessionName);
                    log.info("map에서 {} 제거", sessionName);

                    // 녹화 종료
                    String sessionId = session.getSessionId();
                    if(sessionRecordings.containsKey(sessionId)) {
                        log.info("녹화 저장 시작");
                        Long teamId = SecurityUtil.getCurrentUserId();
                        Team team = teamRepository.findById(teamId).orElseThrow(UserNotFoundException::new);

                        sessionRecordings.remove(sessionId);

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
                }

                return;
            }
            log.error("토큰값이 mapSessionNamesToken에 존재하지 않음 => {}", token);
            return;
        }

        log.error("sessionName에 해당하는 Session이 없음 => {}", sessionName);
    }

    private void removeRecordingInServer(Recording recording) {
        try{
            this.openVidu.deleteRecording(recording.getId());
        }catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
    }

    private Recording getSessionRecording(String sessionId) {
        try {
            return this.openVidu.getRecording(sessionId);
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
    }

    private CreateConnectionResponse createNewSession(String sessionName, OpenViduRole role,  Funding funding) {
        try {
            Session session = this.openVidu.createSession();

            ConnectionProperties connectionProperties = new ConnectionProperties.Builder().type(ConnectionType.WEBRTC)
                    .role(role).build(); // PUBLISHER or MODERATOR

            String token = session.createConnection(connectionProperties).getToken();

            mapSessions.put(sessionName, session);
            mapSessionNamesTokens.put(sessionName, new ConcurrentHashMap<>());
            mapSessionNamesTokens.get(sessionName).put(token, role);

            String sessionId = session.getSessionId();

            // DB에 저장
            Live live = Live.of(sessionId, funding);
            liveRepository.save(live);

            return new CreateConnectionResponse(token);
        } catch (Exception e) {
            log.error(e.getMessage());
            mapSessions.remove(sessionName);
            mapSessionNamesTokens.remove(sessionName);
            throw new RuntimeException(e.getMessage());
        }
    }

    private CreateConnectionResponse joinExistingSession(CreateConnectionRequest request, OpenViduRole role) {

        String sessionName = request.getSessionName();

        Session session = mapSessions.get(sessionName);
        try {
            Session activeSession = this.openVidu.getActiveSession(session.getSessionId());
            if(activeSession == null){
                log.info("OpenVidu 서버에 동작중인 세션이 없음");
                sessionRecordings.remove(session.getSessionId());
                mapSessions.remove(sessionName);
                mapSessionNamesTokens.remove(sessionName);
                return initializeSession(request);
            }

            ConnectionProperties connectionProperties = new ConnectionProperties.Builder().role(role).build();
            String token = session.createConnection(connectionProperties).getToken();
            mapSessionNamesTokens.get(sessionName).put(token, role);
            if (!session.isBeingRecorded()) {
                String sessionId = session.getSessionId();
                RecordingProperties recordingProperties = request.toRecordingProperties();


                log.info("녹화 시작 ===========> {}", sessionId);
                log.info("recordingProperties name => {}", recordingProperties.name());
                Recording recording = this.openVidu.startRecording(sessionId, recordingProperties);
                log.info("{}에 대한 녹화 시작, outputMode = {}, hasAudio = {}, hasVideo = {}"
                        , sessionName, recording.getOutputMode(), recording.hasAudio(), recording.hasVideo());
                sessionRecordings.put(sessionId, true);
            }

            return new CreateConnectionResponse(token);
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            log.error(e.getMessage());
        }
        throw new RuntimeException();
    }

    private boolean canPublish(User user) {
        return user != null && user.getUserType().doPublish();
    }
}
