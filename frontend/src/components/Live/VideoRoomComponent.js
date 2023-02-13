/* eslint-disable class-methods-use-this */
/* eslint-disable no-return-await */
/* eslint-disable react/sort-comp */
/* eslint-disable no-undef */
/* eslint-disable react/destructuring-assignment */
/* eslint-disable react/no-unused-class-component-methods */
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import React, { Component } from 'react';
import { browserHistory } from 'react-router-dom';
import StreamComponent from './stream/StreamComponent';
import './VideoRoomComponent.css';

import OpenViduLayout from './layout/openvidu-layout';
import UserModel from './models/user-model';
import ToolbarComponent from './toolbar/ToolbarComponent';
import ChatComponent from './chat/ChatComponent';
import { http } from '../../api/axios';

const localUser = new UserModel();

console.log('localUser', localUser);
class VideoRoomComponent extends Component {
  constructor(props) {
    super(props);
    this.history = this.props;
    this.hasBeenUpdated = false;
    this.layout = new OpenViduLayout();
    const sessionName = this.props.sessionName ? this.props.sessionName : 'A';
    const userName = this.props.user ? this.props.user : Math.floor(Math.random() * 100);

    this.remotes = [];
    this.localUserAccessAllowed = false;
    this.state = {
      mySessionId: sessionName,
      myUserName: userName,
      session: undefined,
      localUser: undefined,
      subscribers: [],
      currentVideoDevice: undefined,
      userCount: 0,
      allAmount: 0,
    };

    this.joinSession = this.joinSession.bind(this);
    this.leaveSession = this.leaveSession.bind(this);
    this.onbeforeunload = this.onbeforeunload.bind(this);
    this.updateLayout = this.updateLayout.bind(this);
    this.camStatusChanged = this.camStatusChanged.bind(this);
    this.micStatusChanged = this.micStatusChanged.bind(this);
    this.switchCamera = this.switchCamera.bind(this);
  }

  componentDidMount() {
    // const openViduLayoutOptions = {
    //   maxRatio: 5 / 2, // The narrowest ratio that will be used (default 2x3)
    //   minRatio: 9 / 16, // The widest ratio that will be used (default 16x9)
    //   fixedRatio: false, // If this is true then the aspect ratio of the video is maintained and minRatio and maxRatio are ignored (default false)
    //   bigClass: 'OV_big', // The class to add to elements that should be sized bigger
    //   bigPercentage: 0.7, // The maximum percentage of space the big ones should take up
    //   bigFixedRatio: false, // fixedRatio for the big ones
    //   bigMaxRatio: 5 / 7, // The narrowest ratio to use for the big elements (default 2x3)
    //   bigMinRatio: 9 / 21, // The widest ratio to use for the big elements (default 16x9)
    //   bigFirst: true, // Whether to place the big one in the top left (true) or bottom right
    //   animate: false, // Whether you want to animate the transitions
    // };

    const openViduLayoutOptions = {
      maxRatio: 3 / 1, // The narrowest ratio that will be used (default 2x3)
      minRatio: 9 / 16, // The widest ratio that will be used (default 16x9)
      fixedRatio: false, // If this is true then the aspect ratio of the video is maintained and minRatio and maxRatio are ignored (default false)
      bigClass: 'OV_big', // The class to add to elements that should be sized bigger
      bigPercentage: 0.7, // The maximum percentage of space the big ones should take up
      bigFixedRatio: false, // fixedRatio for the big ones
      bigMaxRatio: 3 / 1, // The narrowest ratio to use for the big elements (default 2x3)
      bigMinRatio: 9 / 16, // The widest ratio to use for the big elements (default 16x9)
      bigFirst: true, // Whether to place the big one in the top left (true) or bottom right
      animate: false, // Whether you want to animate the transitions
    };

    this.layout.initLayoutContainer(document.getElementById('layout'), openViduLayoutOptions);
    window.addEventListener('beforeunload', this.onbeforeunload);
    window.addEventListener('resize', this.updateLayout);
    this.joinSession();
  }

  // updateAllAmount(amount) {
  //   console.log('업데이트!!!!!!!!!!!!!!!', this.state);
  //   // eslint-disable-next-line react/no-access-state-in-setstate

  // }

  componentWillUnmount() {
    window.removeEventListener('beforeunload', this.onbeforeunload);
    window.removeEventListener('resize', this.updateLayout);
    this.leaveSession();
  }

  onbeforeunload(event) {
    this.leaveSession();
  }

  joinSession() {
    console.log('joinSession!!!!!!!!!!!!!!!!!!!!!!!!');
    this.OV = new OpenVidu();
    this.OV.enableProdMode();

    this.setState(
      {
        session: this.OV.initSession(),
      },
      async () => {
        console.dir(this.state.session);
        this.subscribeToStreamCreated();
        this.subscribeToConnectionCreated();
        this.subscribeToSessionDisconnected();
        this.subscribeToLiveDonation();
        this.subscribeToUpdateAmount();
        await this.connectToSession();
      },
    );
  }

  subscribeToLiveDonation() {
    this.state.session.on('signal:liveDonation', (event) => {
      console.log(event);
      const data = JSON.parse(event.data);
      console.log(data);
      this.props.liveDonation(data);
    });
  }

  subscribeToUpdateAmount() {
    this.state.session.on('signal:updateAmount', (event) => {
      // eslint-disable-next-line react/no-access-state-in-setstate
      const prev = this.state.allAmount;
      console.log('UPDATE!!!!!!!!!!!!!!!!');
      console.log(JSON.parse(event.data), prev);
      // this.setState({ allAmount: prev + amount });
      this.setState({ allAmount: prev + JSON.parse(event.data) });
    });
  }

  subscribeToSessionDisconnected() {
    this.state.session.on('connectionDestroyed', async (event) => {
      event.preventDefault();
      console.log('어디가노 돌아온나!!!!!!!!!!!!!!', event);
      // eslint-disable-next-line react/no-access-state-in-setstate
      await this.setState({ userCount: this.state.session.remoteConnections.size });
    });
  }

  subscribeToConnectionCreated() {
    this.state.session.on('connectionCreated', async (event) => {
      console.log('event!!!!!!!!!!!!!!!!!!!!', event);
      localUser.setUserProfileImg(this.props.userProfileImg);
      // eslint-disable-next-line react/no-access-state-in-setstate
      await this.setState({ userCount: this.state.session.remoteConnections.size });
      //  console.log(this.state.userCount)
    });
  }

  async connectToSession() {
    if (this.props.token !== undefined) {
      this.connect(this.props.token);
    } else {
      try {
        const token = await this.getToken();
        this.connect(token);
      } catch (error) {
        console.error('There was an error getting the token:', error.code, error.message);
        if (this.props.error) {
          this.props.error({
            error: error.error,
            messgae: error.message,
            code: error.code,
            status: error.status,
          });
        }
        alert('There was an error getting the token:', error.message);
      }
    }
  }

  connect(token) {
    this.state.session
      .connect(token, { clientData: this.state.myUserName })
      .then(() => {
        console.log('connect-Sucess', this.state);
        this.connectWebCam();
      })
      .catch((error) => {
        if (this.props.error) {
          this.props.error({
            error: error.error,
            messgae: error.message,
            code: error.code,
            status: error.status,
          });
        }
        alert('There was an error connecting to the session:', error.message);
        console.log('There was an error connecting to the session:', error.code, error.message);
      });
  }

  async connectWebCam() {
    await this.OV.getUserMedia({
      audioSource: undefined,
      videoSource: undefined,
    });
    const devices = await this.OV.getDevices();
    const videoDevices = devices.filter((device) => device.kind === 'videoinput');

    const publisher = this.OV.initPublisher(undefined, {
      audioSource: undefined,
      videoSource: videoDevices[0].deviceId,
      publishAudio: localUser.isAudioActive(),
      publishVideo: localUser.isVideoActive(),
      resolution: '640x480',
      frameRate: 30,
      insertMode: 'APPEND',
    });

    console.log(this.state.session.capabilities.publish);
    console.log(publisher.openvidu.role);

    this.updateSubscribers();
    if (publisher.openvidu.role === 'PUBLISHER') {
      publisher.on('accessAllowed', () => {
        this.state.session.publish(publisher).then(() => {
          this.localUserAccessAllowed = true;
          if (this.props.joinSession) {
            this.props.joinSession();
          }
        });
      });
      localUser.setStreamManager(publisher);
    } else {
      localUser.setStreamManager(this.state.subscribers[0].getStreamManager());
    }

    localUser.setNickname(this.state.myUserName);
    localUser.setConnectionId(this.state.session.connection.connectionId);
    localUser.setUserProfileImg(this.props.userProfileImg);
    localUser.setAllAmount(this.state.allAmount);

    this.subscribeToUserChanged();
    this.subscribeToStreamDestroyed();

    this.setState({ currentVideoDevice: videoDevices[0], localUser }, () => {
      this.state.localUser.getStreamManager().on('streamPlaying', (e) => {
        this.updateLayout();
      });
    });
  }

  updateSubscribers() {
    console.log('업데이트 다다다다다다다');
    const subscribers = this.remotes;
    this.setState(
      {
        subscribers,
      },
      () => {
        if (this.state.localUser) {
          this.sendSignalUserChanged({
            isAudioActive: this.state.localUser.isAudioActive(),
            isVideoActive: this.state.localUser.isVideoActive(),
            nickname: this.state.localUser.getNickname(),
            isScreenShareActive: this.state.localUser.isScreenShareActive(),
          });
        }
        this.updateLayout();
      },
    );
  }

  leaveSession() {
    const mySession = this.state.session;

    if (mySession) {
      mySession.disconnect();
      console.dir(mySession);
      this.leaveThisSession(this.state.mySessionId, mySession.token);
      console.log('세션 종료 성공띠!!!');
      // window.history.
    }

    // Empty all properties...
    this.OV = null;
    this.setState({
      session: undefined,
      subscribers: [],
      mySessionId: 'A',
      myUserName: `OpenVidu_User${Math.floor(Math.random() * 100)}`,
      localUser: undefined,
    });
    if (this.props.leaveSession) {
      this.props.leaveSession();
    }

    console.log('LEAVE_OV', this.OV);
    console.log('LEAVE_STATE', this.state);
  }

  camStatusChanged() {
    localUser.setVideoActive(!localUser.isVideoActive());
    localUser.getStreamManager().publishVideo(localUser.isVideoActive());
    this.sendSignalUserChanged({ isVideoActive: localUser.isVideoActive() });
    this.setState({ localUser });
  }

  micStatusChanged() {
    localUser.setAudioActive(!localUser.isAudioActive());
    localUser.getStreamManager().publishAudio(localUser.isAudioActive());
    this.sendSignalUserChanged({ isAudioActive: localUser.isAudioActive() });
    this.setState({ localUser });
  }

  deleteSubscriber(stream) {
    const remoteUsers = this.state.subscribers;
    const userStream = remoteUsers.filter((user) => user.getStreamManager().stream === stream)[0];
    const index = remoteUsers.indexOf(userStream, 0);
    if (index > -1) {
      remoteUsers.splice(index, 1);
      this.setState({
        subscribers: remoteUsers,
      });
    }
  }

  subscribeToStreamCreated() {
    this.state.session.on('streamCreated', (event) => {
      const subscriber = this.state.session.subscribe(event.stream, undefined);
      console.log('subscriber!!!!!!!!!!!!!!', subscriber);

      const newUser = new UserModel();
      newUser.setStreamManager(subscriber);
      newUser.setConnectionId(event.stream.connection.connectionId);
      newUser.setType('remote');

      const nickname = event.stream.connection.data.split('%')[0];
      newUser.setNickname(JSON.parse(nickname).clientData);

      this.remotes.push(newUser);

      this.updateSubscribers();
      if (this.localUserAccessAllowed) {
        console.log('뜨루루루루');
      } else {
        console.log('왜 뽈스노');
      }
    });
  }

  subscribeToStreamDestroyed() {
    // On every Stream destroyed...
    this.state.session.on('streamDestroyed', (event) => {
      // Remove the stream from 'subscribers' array
      this.deleteSubscriber(event.stream);
      // setTimeout(() => {
      //     this.checkSomeoneShareScreen();
      // }, 20);
      event.preventDefault();
      this.updateLayout();
    });
  }

  subscribeToUserChanged() {
    this.state.session.on('signal:userChanged', (event) => {
      const remoteUsers = this.state.subscribers;
      remoteUsers.forEach((user) => {
        if (user.getConnectionId() === event.from.connectionId) {
          const data = JSON.parse(event.data);
          console.log('EVENTO REMOTE: ', event.data);
          if (data.isAudioActive !== undefined) {
            user.setAudioActive(data.isAudioActive);
          }
          if (data.isVideoActive !== undefined) {
            user.setVideoActive(data.isVideoActive);
          }
          if (data.nickname !== undefined) {
            user.setNickname(data.nickname);
          }
          if (data.isScreenShareActive !== undefined) {
            user.setScreenShareActive(data.isScreenShareActive);
          }
        }
      });
    });
  }

  updateLayout() {
    setTimeout(() => {
      this.layout.updateLayout();
    }, 20);
  }

  sendSignalUserChanged(data) {
    const signalOptions = {
      data: JSON.stringify(data),
      type: 'userChanged',
    };
    this.state.session.signal(signalOptions);
  }

  async switchCamera() {
    try {
      const devices = await this.OV.getUserMedia();
      const videoDevices = devices.filter((device) => device.kind === 'videoinput');

      if (videoDevices && videoDevices.length > 1) {
        const newVideoDevice = videoDevices.filter((device) => device.deviceId !== this.state.currentVideoDevice.deviceId);

        if (newVideoDevice.length > 0) {
          // Creating a new publisher with specific videoSource
          // In mobile devices the default and first camera is the front one
          const newPublisher = this.OV.initPublisher(undefined, {
            audioSource: undefined,
            videoSource: newVideoDevice[0].deviceId,
            publishAudio: localUser.isAudioActive(),
            publishVideo: localUser.isVideoActive(),
            mirror: true,
          });

          // newPublisher.once("accessAllowed", () => {
          await this.state.session.unpublish(this.state.localUser.getStreamManager());
          await this.state.session.publish(newPublisher);
          this.state.localUser.setStreamManager(newPublisher);
          this.setState({
            currentVideoDevice: newVideoDevice,
            localUser,
          });
        }
      }
    } catch (e) {
      console.error(e);
    }
  }

  render() {
    // console.log('state', this.state);
    const { mySessionId } = this.state;
    const { localUser } = this.state;
    // console.log('최근잔고 !!!!!!!!', this.props.userCurrentMoney);
    console.log(this.state);
    // console.log(localUser)
    // const {remoteConnections} = this.state.session
    // console.log(remoteConnections)

    return (
      <>
        <ToolbarComponent
          sessionId={mySessionId}
          user={localUser}
          camStatusChanged={this.camStatusChanged}
          micStatusChanged={this.micStatusChanged}
          switchCamera={this.switchCamera}
          leaveSession={this.leaveSession}
          userCount={this.state.userCount}
        />

        <div id="layout" className="bounds">
          {localUser !== undefined && localUser.getStreamManager() !== undefined && (
            <div className="OT_root OV_big OT_publisher custom-class" id="localUser">
              <StreamComponent user={localUser} sessionId={mySessionId} userCount={this.state.userCount} allAmount={this.state.allAmount} />
            </div>
          )}
          {localUser !== undefined && localUser.getStreamManager() !== undefined && (
            <div className="OT_root OV_small OT_publisher custom-class chat-box">
              <ChatComponent
                user={localUser}
                userCount={this.state.userCount}
                userCurrentMoney={this.props.userCurrentMoney}
                liveDonation={this.props.liveDonation}
                updateAllAmount={this.updateAllAmount}
                userType={this.props.userType}
              />
            </div>
          )}
        </div>
      </>
    );
  }

  async getToken() {
    return await this.createSession(this.state.mySessionId);
  }

  async createSession(sessionId) {
    console.log('sessionId', sessionId);
    const response = await http.post(
      `openvidu/sessions`,
      { sessionName: sessionId, fundingId: 308 },
      {
        headers: { 'Content-Type': 'application/json' },
      },
    );
    return response.data.token; // The sessionId
  }

  async leaveThisSession(sessionId, token) {
    const response = await http.post(
      `openvidu/sessions/leave`,
      { sessionName: sessionId, token },
      {
        headers: { 'Content-Type': 'application/json', Authorization: `Bearer ${localStorage.getItem('accessToken')}` },
      },
    );
  }
}
export default VideoRoomComponent;
