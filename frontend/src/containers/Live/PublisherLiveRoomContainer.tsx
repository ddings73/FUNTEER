import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import VideoRoomComponent from '../../components/Live/VideoRoomComponent';
import { useAppSelector } from '../../store/hooks';

function PublisherLiveRoomContainer() {
  const { username } = useParams();
  const [token, setToken] = useState<string | null>('');
  const userName = useAppSelector((state) => state.userSlice.username);
  const userProfileImg = useAppSelector(state=>state.userSlice.profileImgUrl)
  console.log(userProfileImg)

  useEffect(() => {
    if (localStorage.getItem('liveToken')) {
      setToken(localStorage.getItem('liveToken'));
    }
  }, []);

  useEffect(() => {
    console.log('token', token);
  }, [token]);

  // return (<h1>킄킄</h1>)
  return <> {username && token && <VideoRoomComponent userProfileImg={userProfileImg} sessionName={username} user={userName} token={token} />}</>;
}

export default PublisherLiveRoomContainer;
