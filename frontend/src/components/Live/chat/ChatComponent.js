/* eslint-disable no-empty */
/* eslint-disable react/no-array-index-key */
/* eslint-disable no-undef */
/* eslint-disable react/destructuring-assignment */
import React, { Component } from 'react';
import Fab from '@material-ui/core/Fab';
import Send from '@material-ui/icons/Send';

import './ChatComponent.css';
import { Tooltip } from '@material-ui/core';
import { Button, IconButton } from '@mui/material';
import PaidOutlinedIcon from '@mui/icons-material/PaidOutlined';
import cn from 'classnames'
import defaultProfile from '../../../assets/images/default-profile-img.svg';


export default class ChatComponent extends Component {

  constructor(props) {
    super(props);
    this.state = {
      messageList: [],
      message: '',
      // eslint-disable-next-line react/no-unused-state
      toggle:false,
      // eslint-disable-next-line react/no-unused-state
      amount:0
    };
    this.chatScroll = React.createRef();
    this.handleChange = this.handleChange.bind(this);
    this.handlePressKey = this.handlePressKey.bind(this);
    this.sendMessage = this.sendMessage.bind(this);
    this.toggleDonation=this.toggleDonation.bind(this)
    this.onClickDonation=this.onClickDonation.bind(this)
    this.onChangeMoney=this.onChangeMoney.bind(this)
  }

  componentDidMount() {
    this.props.user.getStreamManager().stream.session.on('signal:chat', (event) => {
      const data = JSON.parse(event.data);
      const { messageList } = this.state;
      messageList.push({ connectionId: event.from.connectionId, nickname: data.nickname, message: data.message ,userProfileImg:data.userProfileImg});
      const { document } = window;
      setTimeout(() => {
        const userImg = document.getElementById(`userImg-${this.state.messageList.length - 1}`);
        const video = document.getElementById(`video-${data.streamId}`);
        // const avatar = userImg.getContext('2d');
        // avatar.drawImage(video, 200, 120, 285, 285, 0, 0, 60, 60);
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

  onChangeMoney(e){
    // eslint-disable-next-line react/no-unused-state
    this.setState({amount:e.target.value})

  }

  onClickDonation(){
    this.props.liveDonation(this.state.amount)
  }
  
  sendMessage() {
    if (this.props.user && this.state.message) {
      const message = this.state.message.replace(/ +(?= )/g, '');
      if (message !== '' && message !== ' ') {
        const data = { message, nickname: this.props.user.getNickname(), streamId: this.props.user.getStreamManager().stream.streamId,userProfileImg:this.props.user.getUserProfileImg() };
        console.log(data)
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

  toggleDonation(){
    // eslint-disable-next-line react/no-unused-state, react/no-access-state-in-setstate
    this.setState({toggle:!this.state.toggle})
  }

 

  render() {
    console.log(this.props)
    // console.log(this.props.user)
    // console.log(this.state.messageList)
    return (
      <div id="chatContainer">
        <div id="chatComponent">
          <div id="chatToolbar">
            <span> 채팅방</span>
            <div className='pulse-button'>
            <Tooltip title="후원하기" placement='right'>
            <IconButton sx={{color:"rgba(236, 153, 75, 1) !important"}} onClick={this.toggleDonation} >
            <PaidOutlinedIcon color='inherit' sx={{fontSize:28}}/>
            </IconButton>
            </Tooltip>
            </div>
          </div>

          <div className={cn('donation-box',this.state.toggle?"toggle":"")}>
            <div className='donation-contents'>
            <p className='title'>라이브 방송 후원하기</p>
            <div className='current-money-box'>
              <p>현재 마일리지</p>
              <p>{this.props.userCurrentMoney}</p>
            </div>

            <div className='donation-money-box'>
              <p>후원할 금액</p>
              <input className='donation-money-input' type="number" name="money" onChange={this.onChangeMoney} />
            </div>
            <Button className='donation-button' variant='contained' onClick={this.onClickDonation} >후원하기</Button>
            </div>
          </div>
          <div className="message-wrap" ref={this.chatScroll}>
            {this.state.messageList.map((data, i) => (
              <div key={i} id="remoteUsers" className={`message${data.connectionId !== this.props.user.getConnectionId() ? ' left' : ' left'}`}>
                <div className="msg-detail">
                  <img src={data.userProfileImg ? data.userProfileImg : defaultProfile} className="userProfileImage" alt="userprofile" />
                  <div className="message-contents-box">
                    <span className="uername">{data.nickname}</span>
                    <span className="chat-content">{data.message}</span>
                  </div>
                  {/* <div className="msg-info">
                    <p> {data.nickname}</p>
                  </div>
                  <div className="msg-content">
                    <p className="text">{data.message}</p>
                  </div> */}
                </div>
              </div>
            ))}
          </div>

          <div id="messageInput">
            <input placeholder="채팅 메세지를 입력해주세요." id="chatInput" value={this.state.message} onChange={this.handleChange} onKeyPress={this.handlePressKey} />
            <IconButton size="large" id="sendButton" onClick={this.sendMessage}>
              <Send color="inherit" />
            </IconButton>
          </div>
        </div>
      </div>
    );
  }
}
