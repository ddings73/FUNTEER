import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { requestLiveDonation } from '../../api/live';
import { requestTeamProfile, requestUserProfile } from '../../api/user';
import VideoRoomComponent from '../../components/Live/VideoRoomComponent';
import { useAppSelector } from '../../store/hooks';

function PublisherLiveRoomContainer() {
  const { username } = useParams();
  const [token, setToken] = useState<string | null>('');
  const userName = useAppSelector((state) => state.userSlice.username);
  const userProfileImg = useAppSelector((state) => state.userSlice.profileImgUrl);
  const userType = useAppSelector((state) => state.userSlice.userType);
  const userId = useAppSelector((state) => state.userSlice.userId);

  useEffect(() => {
    if (localStorage.getItem('liveToken')) {
      setToken(localStorage.getItem('liveToken'));
    }
  }, []);

  useEffect(() => {
    console.log('token', token);
  }, [token]);

  // return (<h1>킄킄</h1>)
  return <> {username && token && <VideoRoomComponent userProfileImg={userProfileImg} sessionName={username} user={userName} token={token} userType={userType} />}</>;
}

export default PublisherLiveRoomContainer;
