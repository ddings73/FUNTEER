import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import VideoRoomComponent from '../../components/Live/VideoRoomComponent';

function LiveRoomContainer() {
  const { username } = useParams();
  const [token, setToken] = useState<string | null>('');

  useEffect(() => {
    if (localStorage.getItem('liveToken')) {
      setToken(localStorage.getItem('liveToken'));
    }
  }, []);

  useEffect(() => {
    console.log('token', token);
  }, [token]);

  return <> {username && token && <VideoRoomComponent sessionName={username} token={token} />}</>;
}

export default LiveRoomContainer;
