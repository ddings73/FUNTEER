package com.yam.funteer.live.service;

import com.yam.funteer.common.code.UserType;
import com.yam.funteer.exception.UserNotFoundException;
import com.yam.funteer.funding.entity.Funding;
import com.yam.funteer.funding.repository.FundingRepository;
import com.yam.funteer.live.dto.CreateConnectionRequest;
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
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service @Slf4j
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

    private Long userId = 81L;

    @PostConstruct
    public void init(){
        this.openVidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    @Override
    public JSONObject initializeSession(CreateConnectionRequest request) {
        Long userId = this.userId;//SecurityUtil.getCurrentUserId();
        this.userId = this.userId == 81L ? 83L : 81L;

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        String sessionName = request.getSessionName();

        UserType userType = user.getUserType();
        OpenViduRole role = userType.getOpenviduRole();
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                .role(role).build();

        if(mapSessions.containsKey(sessionName)){
            log.info("이미 생성된 세션 참여 => {}", sessionName);
            return joinExistingSession(sessionName, role, connectionProperties);
        }else {
            log.info("새로운 세션 생성 => {}", sessionName);

            Funding funding = fundingRepository.findById(request.getFundingId())
                .orElseThrow(IllegalArgumentException::new);

            Team fundingTeam = funding.getTeam();
            if(!fundingTeam.equals(user)) throw new IllegalArgumentException("비정상적인 접근입니다");

            return createNewSession(sessionName, role, connectionProperties, funding);
        }
    }

    @Override
    public void leaveSession(String sessionName, String token) {
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

                    mapSessions.remove(sessionName);
                    tokenRoleMap.clear();
                }else if(tokenRoleMap.isEmpty()){
                    mapSessions.remove(sessionName);
                }
                return;
            }

            log.error("토큰값이 이상함");
            return;
        }
        log.error("sessionId가 이상함");
    }

    @Override
    public Recording startRecording(StartRecordingRequest request) {
        RecordingProperties properties = request.toRecordingProperties();

        try {
            String sessionId = request.getSessionId();
            this.sessionRecordings.put(sessionId, true);
            Recording recording = this.openVidu.startRecording(sessionId, properties);
            return recording;
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Recording getRecording(String recordingId) {
        try {
            return this.openVidu.getRecording(recordingId);
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Recording stopRecording(String recordingId) {
        try {
            return this.openVidu.stopRecording(recordingId);
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
    }

    private JSONObject createNewSession(String sessionName, OpenViduRole role
            , ConnectionProperties connectionProperties, Funding funding){
        try {
            Session session = openVidu.createSession();

            Live live = Live.of(session.getSessionId(), funding);
            liveRepository.save(live);

            mapSessions.put(sessionName, session);
            String token = mapSessions.get(sessionName).createConnection(connectionProperties).getToken();

            mapSessionNamesTokens.put(sessionName, new ConcurrentHashMap<>());
            mapSessionNamesTokens.get(sessionName).put(token, role);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", token);
            return jsonObject;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private JSONObject joinExistingSession(String sessionName, OpenViduRole role, ConnectionProperties connectionProperties){
        try{
            String token = mapSessions.get(sessionName).createConnection(connectionProperties).getToken();
            mapSessionNamesTokens.get(sessionName).put(token, role);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", token);
            return jsonObject;
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
}
