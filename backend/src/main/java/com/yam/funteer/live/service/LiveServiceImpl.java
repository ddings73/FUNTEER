package com.yam.funteer.live.service;

import com.yam.funteer.common.code.UserType;
import com.yam.funteer.common.security.SecurityUtil;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.live.dto.CreateConnectionRequest;
import com.yam.funteer.live.dto.CreateConnectionResponse;
import com.yam.funteer.live.dto.SessionLeaveRequest;
import com.yam.funteer.live.dto.StartRecordingRequest;
import com.yam.funteer.live.entity.Live;
import com.yam.funteer.live.repository.LiveRepository;
import com.yam.funteer.user.entity.Team;
import com.yam.funteer.user.entity.User;
import com.yam.funteer.user.repository.TeamRepository;

import com.yam.funteer.user.repository.UserRepository;
import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import java.util.List;
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
    private final Map<String, String> sessionRecordings = new ConcurrentHashMap<>();

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
        // if(SecurityUtil.isLogin()) {
            Long userId = 81L;// SecurityUtil.getCurrentUserId();
            user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        // }

        String sessionName = request.getSessionName();
        OpenViduRole role = user == null ? OpenViduRole.SUBSCRIBER : user.openViduRole();

        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
            .type(ConnectionType.WEBRTC)
            .record(true)
            .role(role).build();

        if(mapSessions.containsKey(sessionName)){
            log.info("이미 생성된 세션 참여 => {}", sessionName);
            return joinExistingSession(sessionName, role, connectionProperties);
        }else if(!role.equals(OpenViduRole.SUBSCRIBER)){ // 방을 생성할 수 있다면
            log.info("새로운 세션 생성 => {}", sessionName);

            Long fundingId = request.getFundingId();
            Funding funding = fundingRepository.findById(fundingId)
                .orElseThrow(IllegalArgumentException::new);

            Team fundingTeam = funding.getTeam();
            if(!fundingTeam.equals(user)) throw new AccessDeniedException("펀딩을 개최한 단체와 다릅니다.");

            return createNewSession(sessionName, role, connectionProperties, funding);
        }

        throw new AccessDeniedException("유저정보가 이상하거나 권한이 잘못 설정되었습니ㄷㅏ.");
    }

    @Override
    public void leaveSession(SessionLeaveRequest request) {
        String sessionName = request.getSessionName();
        String token = request.getToken();

        if(mapSessions.containsKey(sessionName) && mapSessionNamesTokens.containsKey(sessionName)){
            Map<String, OpenViduRole> tokenRoleMap = mapSessionNamesTokens.get(sessionName);
            if(tokenRoleMap.containsKey(token)){
                OpenViduRole role = tokenRoleMap.get(token);
                tokenRoleMap.remove(token);
                if(role.equals(OpenViduRole.PUBLISHER)){
                    Session session = mapSessions.get(sessionName);
                    Live live = liveRepository.findBySessionId(session.getSessionId())
                            .orElseThrow(IllegalArgumentException::new);
                    live.end();


                    disconnectAllConnections(session);

                    mapSessions.remove(sessionName);
                    mapSessionNamesTokens.remove(sessionName);
                    
                    // 녹화 종료
                    // String recordingId = sessionRecordings.get(sessionName);
                    // Recording recording = stopRecording(recordingId);
                    sessionRecordings.remove(sessionName);
                }
                return;
            }
            log.error("토큰값이 이상함 => {}", token);
            return;
        }

        log.error("sessionId가 이상함 => {}", sessionName);
    }

    private CreateConnectionResponse createNewSession(String sessionName, OpenViduRole role
        , ConnectionProperties connectionProperties, Funding funding){
        try {
            Session session = this.openVidu.createSession();

            mapSessions.put(sessionName, session);
            String token = session.createConnection(connectionProperties).getToken();

            mapSessionNamesTokens.put(sessionName, new ConcurrentHashMap<>());
            mapSessionNamesTokens.get(sessionName).put(token, role);

            // 녹화
            String sessionId = session.getSessionId();
            // Recording recording = this.openVidu.startRecording(sessionId, recordingProperties);
            // log.info("{}에 대한 녹화 시작, outputMode = {}, hasAudio = {}, hasVideo = {}"
            //     , sessionName , recording.getOutputMode(), recording.hasAudio(), recording.hasVideo());
            // sessionRecordings.put(sessionId, recording.getId());

            // DB에 저장
            Live live = Live.of(sessionId, funding);
            liveRepository.save(live);

            return new CreateConnectionResponse(token);
        }catch (Exception e){
            mapSessions.remove(sessionName);
            mapSessionNamesTokens.remove(sessionName);
            throw new RuntimeException(e.getMessage());
        }
    }

    private CreateConnectionResponse joinExistingSession(String sessionName, OpenViduRole role, ConnectionProperties connectionProperties){
        try{
            Session session = mapSessions.get(sessionName);
            String token = session.createConnection(connectionProperties).getToken();
            mapSessionNamesTokens.get(sessionName).put(token, role);

            return new CreateConnectionResponse(token);
        }catch(OpenViduJavaClientException e){
            log.error(e.getMessage());
        }catch(OpenViduHttpException e){
            log.error(e.getMessage());
            if(e.getStatus() == 404){
                mapSessions.remove(sessionName);
                mapSessionNamesTokens.remove(sessionName);
            }
        }
        throw new RuntimeException();
    }

    private Recording stopRecording(String recordingId) {
        try {
            return this.openVidu.stopRecording(recordingId);
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
    }

    private void disconnectAllConnections(Session session) {
        List<Connection> connections = session.getConnections();
        connections.forEach(connection -> {
            try {
                session.forceDisconnect(connection);
            } catch (OpenViduJavaClientException | OpenViduHttpException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @Override
    public Recording getRecording(String recordingId) {
        try {
            return this.openVidu.getRecording(recordingId);
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
    }
}
