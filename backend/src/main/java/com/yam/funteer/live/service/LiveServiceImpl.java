package com.yam.funteer.live.service;

import com.yam.funteer.attach.FileUtil;
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

import com.yam.funteer.user.repository.UserRepository;
import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

    private final UserRepository userRepository;
    private final LiveRepository liveRepository;
    private final FundingRepository fundingRepository;

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
        if(mapSessions.containsKey(sessionName)){
            log.info("이미 생성된 세션 참여 => {}", sessionName);
            return joinExistingSession(sessionName, OpenViduRole.SUBSCRIBER);
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

    private boolean canPublish(User user) {
        return user != null && user.getUserType().doPublish();
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

                    closeThisSession(session);

                    mapSessions.remove(sessionName);
                    mapSessionNamesTokens.remove(sessionName);
                    
                    // 녹화 종료
                    String sessionId = session.getSessionId();
                    if(sessionRecordings.containsKey(sessionId)) {
                        sessionRecordings.remove(sessionId);
                        Recording recording = getSessionRecording(sessionId);
                        String fileUrl = recording.getUrl();
                        log.info(fileUrl);

                        File file = FileUtil.downloadFromUrl(fileUrl);
                        MultipartFile multipartFile = FileUtil.fileToMultipart(file);

                        System.out.println();
                    }
                }

                return;
            }
            log.error("토큰값이 이상함 => {}", token);
            return;
        }

        log.error("sessionName이 이상함 => {}", sessionName);
    }

    private Recording getSessionRecording(String sessionId) {
        try {
            return this.openVidu.getRecording(sessionId);
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeThisSession(Session session) {
        try {
            session.close();
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private CreateConnectionResponse createNewSession(String sessionName, OpenViduRole role
        ,  Funding funding) {
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

    private CreateConnectionResponse joinExistingSession(String sessionName, OpenViduRole role) {
        try {
            Session session = mapSessions.get(sessionName);

            ConnectionProperties connectionProperties = new ConnectionProperties.Builder().role(role).build();
            String token = session.createConnection(connectionProperties).getToken();
            mapSessionNamesTokens.get(sessionName).put(token, role);

            if (!session.isBeingRecorded()) {
                log.info("녹화 시작 ===========> ");
                String sessionId = session.getSessionId();
                RecordingProperties recordingProperties = new RecordingProperties.Builder()
                        .outputMode(Recording.OutputMode.COMPOSED).hasAudio(true).hasVideo(true).name(sessionName).frameRate(30).build();
                Recording recording = this.openVidu.startRecording(sessionId, recordingProperties);
                log.info("{}에 대한 녹화 시작, outputMode = {}, hasAudio = {}, hasVideo = {}"
                        , sessionName, recording.getOutputMode(), recording.hasAudio(), recording.hasVideo());
                sessionRecordings.put(sessionId, true);
            }

            return new CreateConnectionResponse(token);
        } catch (OpenViduJavaClientException e) {
            log.error(e.getMessage());
        } catch (OpenViduHttpException e) {
            log.error(e.getMessage());
            if (e.getStatus() == 404) {
                mapSessions.remove(sessionName);
                mapSessionNamesTokens.remove(sessionName);
            }
        }
        throw new RuntimeException();
    }
}
