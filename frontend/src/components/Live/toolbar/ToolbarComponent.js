/* eslint-disable react/destructuring-assignment */
import React, { Component } from 'react';
import './ToolbarComponent.css';

import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';

import Mic from '@material-ui/icons/Mic';
import MicOff from '@material-ui/icons/MicOff';
import Videocam from '@material-ui/icons/Videocam';
import VideocamOff from '@material-ui/icons/VideocamOff';
import SwitchVideoIcon from '@material-ui/icons/SwitchVideo';
import PowerSettingsNew from '@material-ui/icons/PowerSettingsNew';

import IconButton from '@material-ui/core/IconButton';
import { Link } from 'react-router-dom';
import Logo from '../../../assets/images/FunteerLogo.png';

export default class ToolbarComponent extends Component {
  constructor(props) {
    super(props);
    this.camStatusChanged = this.camStatusChanged.bind(this);
    this.micStatusChanged = this.micStatusChanged.bind(this);
    this.switchCamera = this.switchCamera.bind(this);
    this.leaveSession = this.leaveSession.bind(this);
  }

  micStatusChanged() {
    this.props.micStatusChanged();
  }

  camStatusChanged() {
    this.props.camStatusChanged();
  }

  switchCamera() {
    this.props.switchCamera();
  }

  leaveSession() {
    this.props.leaveSession();
  }

  render() {
    const mySessionId = this.props.sessionId;
    const localUser = this.props.user;
    return (
      <header className="header">
        <div className="toolbar">
          <div className="navSessionInfo">
            {this.props.sessionId && (
              <div id="titleContent">
                <span id="session-title">{mySessionId}</span>
              </div>
            )}
          </div>

          <div className="buttonsContent">
            {localUser !== undefined && localUser.getStreamManager().openvidu !== undefined && (
              <IconButton color="inherit" className="navButton" id="navMicButton" onClick={this.micStatusChanged}>
                {localUser !== undefined && localUser.isAudioActive() ? <Mic /> : <MicOff color="secondary" />}
              </IconButton>
            )}

            {localUser !== undefined && localUser.getStreamManager().openvidu !== undefined && (
              <IconButton color="inherit" className="navButton" id="navCamButton" onClick={this.camStatusChanged}>
                {localUser !== undefined && localUser.isVideoActive() ? <Videocam /> : <VideocamOff color="secondary" />}
              </IconButton>
            )}

            {localUser !== undefined && localUser.getStreamManager().openvidu !== undefined && (
              <IconButton color="inherit" className="navButton" onClick={this.switchCamera}>
                <SwitchVideoIcon />
              </IconButton>
            )}

            <Link to="/">
              <IconButton color="secondary" className="navButton" onClick={this.leaveSession} id="navLeaveButton">
                <PowerSettingsNew />
              </IconButton>
            </Link>
          </div>
        </div>
      </header>
    );
  }
}
