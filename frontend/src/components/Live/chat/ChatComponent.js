/* eslint-disable no-empty */
/* eslint-disable react/no-array-index-key */
/* eslint-disable no-undef */
/* eslint-disable react/destructuring-assignment */
import React, { Component } from 'react';
import Fab from '@material-ui/core/Fab';
import Send from '@material-ui/icons/Send';

import './ChatComponent.css';
import { Tooltip } from '@material-ui/core';

export default class ChatComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      messageList: [],
      message: '',
    };
    this.chatScroll = React.createRef();

    this.handleChange = this.handleChange.bind(this);
    this.handlePressKey = this.handlePressKey.bind(this);
    this.sendMessage = this.sendMessage.bind(this);
  }

  componentDidMount() {
    this.props.user.getStreamManager().stream.session.on('signal:chat', (event) => {
      const data = JSON.parse(event.data);
      const { messageList } = this.state;
      messageList.push({ connectionId: event.from.connectionId, nickname: data.nickname, message: data.message });
      const { document } = window;
      setTimeout(() => {
        const userImg = document.getElementById(`userImg-${this.state.messageList.length - 1}`);
        const video = document.getElementById(`video-${data.streamId}`);
        const avatar = userImg.getContext('2d');
        avatar.drawImage(video, 200, 120, 285, 285, 0, 0, 60, 60);
      }, 50);
      this.setState({ messageList });
      this.scrollToBottom();
    });
  }

  handleChange(event) {
    this.setState({ message: event.target.value });
  }

  handlePressKey(event) {
    if (event.key === 'Enter') {
      this.sendMessage();
    }
  }

  sendMessage() {
    if (this.props.user && this.state.message) {
      const message = this.state.message.replace(/ +(?= )/g, '');
      if (message !== '' && message !== ' ') {
        const data = { message, nickname: this.props.user.getNickname(), streamId: this.props.user.getStreamManager().stream.streamId };
        this.props.user.getStreamManager().stream.session.signal({
          data: JSON.stringify(data),
          type: 'chat',
        });
      }
    }
    this.setState({ message: '' });
  }

  scrollToBottom() {
    setTimeout(() => {
      try {
        this.chatScroll.current.scrollTop = this.chatScroll.current.scrollHeight;
      } catch (err) {}
    }, 20);
  }

  render() {
    return (
      <div id="chatContainer">
        <div id="chatComponent">
          <div id="chatToolbar">
            <span>{this.props.user.getStreamManager().stream.session.sessionId}의 채팅방</span>
          </div>
          <div className="message-wrap" ref={this.chatScroll}>
            {this.state.messageList.map((data, i) => (
              <div key={i} id="remoteUsers" className={`message${data.connectionId !== this.props.user.getConnectionId() ? ' left' : ' right'}`}>
                <div className="msg-detail">
                  <div className="msg-info">
                    <p> {data.nickname}</p>
                  </div>
                  <div className="msg-content">
                    <span className="triangle" />
                    <p className="text">{data.message}</p>
                  </div>
                </div>
              </div>
            ))}
          </div>

          <div id="messageInput">
            <input placeholder="채팅 메세지를 입력해주세요." id="chatInput" value={this.state.message} onChange={this.handleChange} onKeyPress={this.handlePressKey} />
            <Tooltip title="채팅 메세지를 입력해주세요.">
              <Fab size="middle" id="sendButton" onClick={this.sendMessage}>
                <Send color="secondary" />
              </Fab>
            </Tooltip>
          </div>
        </div>
      </div>
    );
  }
}
