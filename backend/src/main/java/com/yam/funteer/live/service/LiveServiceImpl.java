package com.yam.funteer.live.service;

import com.yam.funteer.alarm.service.AlarmService;
import com.yam.funteer.attach.FileType;
import com.yam.funteer.attach.FileUtil;
import com.yam.funteer.attach.entity.Attach;
import com.yam.funteer.attach.repository.AttachRepository;
import com.yam.funteer.common.aws.AwsS3Uploader;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.exception.DuplicateInfoException;
import com.yam.funteer.exception.SessionNotFoundException;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.live.dto.*;
import com.yam.funteer.live.entity.Gift;
import com.yam.funteer.live.entity.Live;
import com.yam.funteer.live.repository.GiftRepository;
import com.yam.funteer.live.repository.LiveRepository;
import com.yam.funteer.user.entity.Team;
import com.yam.funteer.user.entity.User;

import com.yam.funteer.user.entity.Wish;
import com.yam.funteer.user.repository.UserRepository;
import com.yam.funteer.user.repository.WishRepository;

import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service @Slf4j
@Transactional
@RequiredArgsConstructor
public class LiveServiceImpl implements LiveService{
    @Value("${OPENVIDU.URL}")
    private String OPENVIDU_URL;
    @Value("${OPENVIDU.SECRET}")
    private String OPENVIDU_SECRET;
    private OpenVidu openVidu;
    private final AwsS3Uploader awsS3Uploader;
    private final AlarmService alarmService;

    private final UserRepository userRepository;
    private final LiveRepository liveRepository;
    private final GiftRepository giftRepository;
    private final FundingRepository fundingRepository;
    private final AttachRepository attachRepository;
    private final WishRepository wishRepository;

    @PostConstruct
    public void init(){
        this.openVidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    @Override
    @Transactional(noRollbackFor= SessionNotFoundException.class)
    public CreateConnectionResponse initializeSession(CreateConnectionRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        String sessionName = request.getSessionName();


        if(liveRepository.findByFundingTeamNameAndEndTimeIsNull(sessionName).isPresent()){
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

        liveRepository.findByFundingTeamNameAndEndTimeIsNull(sessionName).ifPresent(live -> {
            String sessionId = live.getSessionId();
            Session session = this.openVidu.getActiveSession(sessionId);

            Optional<Connection> optConn = session.getConnections().stream()
                    .filter(conn -> conn.getServerData().equals(String.valueOf(userId))).findFirst();
            optConn.ifPresent(connection -> {
                if(connection.getRole().equals(OpenViduRole.PUBLISHER)) {
                    live.end();
                    log.info("라이브 종료 => {}", live.getEndTime());

                    // 녹화 종료
                    if(session.isBeingRecorded()) {
                        Attach attach = recordSaveThisSession(live.getTeam(), sessionId);
                        live.saveFile(attach);
                    }
                }

                openviduFetch();
            });

        });
    }


    private CreateConnectionResponse createNewSession(String sessionName, Funding funding, User user,
                                                      RecordingProperties recordingProperties) {

        liveRepository.findByFunding(funding).ifPresent(live -> {
            throw new DuplicateInfoException(live.getFunding().getTitle());
        });

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

            // DB에 저장
            Live live = Live.of(session.getSessionId(), funding);
            liveRepository.save(live);

            List<Wish> wishList = wishRepository.findAllByFundingAndChecked(funding, true);
            List<String> emailList = wishList.stream()
                .map(wish -> wish.getMember().getEmail())
                .collect(Collectors.toList());

            log.info("이메일 리스트 => {}", emailList);
            alarmService.sendList(emailList, sessionName + " 단체의 라이브 방송이 시작되었습니다.", "/subscribeLiveRoom/" + sessionName);
            return new CreateConnectionResponse(token);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    protected CreateConnectionResponse joinExistingSession(CreateConnectionRequest request, User user) {

        String sessionName = request.getSessionName();
        Live live = liveRepository.findByFundingTeamNameAndEndTimeIsNull(sessionName).orElseThrow(SessionNotFoundException::new);
        try {
            openviduFetch();
            Session session = this.openVidu.getActiveSession(live.getSessionId());
            if(session == null){
                log.warn("OpenVidu 서버에 동작중인 세션이 없음 in try");
                live.end();
                Team prevTeam = live.getFunding().getTeam();
                if(prevTeam.getId().equals(user.getId())) {
                    return initializeSession(request);
                }
                throw new SessionNotFoundException();
            }

            if(!session.isBeingRecorded()){
                this.openVidu.startRecording(session.getSessionId());
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
            log.warn("OpenVidu 서버에 동작중인 세션이 없음 in catch");
            live.end();

            Team prevTeam = live.getFunding().getTeam();
            if(prevTeam.getId().equals(user.getId())) {
                return initializeSession(request);
            }
            throw new SessionNotFoundException();
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
    private Attach recordSaveThisSession(Team team, String sessionId){
        log.info("녹화 저장 시작");

        log.info("sessionId => {}", sessionId);
        Recording recording = getSessionRecording(sessionId);

        String fileUrl = recording.getUrl();
        log.info(fileUrl);

        File file = FileUtil.downloadFromUrl(fileUrl);
        String path = awsS3Uploader.upload(file, "live");

        Attach attach = Attach.of(file.getName(), path, FileType.LIVE);
        attachRepository.save(attach);


        removeRecordingInServer(recording);

        return attach;
    }

    private void removeRecordingInServer(Recording recording) {
        try{
            this.openVidu.deleteRecording(recording.getId());
        }catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ActiveSessionsResponse getCurrentActiveSessions(Pageable pageable) {
        openviduFetch();
        Page<Live> livePage = liveRepository.findByEndTimeIsNull(pageable);

        return ActiveSessionsResponse.of(livePage);
    }

    @Override
    public void giftToFundingTeam(GiftRequest request) {
        String sessionName = request.getSessionName();
        liveRepository.findByFundingTeamNameAndEndTimeIsNull(sessionName).ifPresentOrElse(live -> {
            Session session = this.openVidu.getActiveSession(live.getSessionId());
            openviduFetch();

            if(session == null){
                log.warn("세션이 없음");
                throw new SessionNotFoundException();
            }

            Long userId = SecurityUtil.getCurrentUserId();
            User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
            Long amount = request.getAmount();

            user.checkMoney(amount);

            Team team = live.getTeam();
            team.updateMoney(amount);
            user.updateMoney(-amount);

            Gift gift = Gift.from(live, user, amount);
            giftRepository.save(gift);
        }, () -> {
            throw new SessionNotFoundException();
        });
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
