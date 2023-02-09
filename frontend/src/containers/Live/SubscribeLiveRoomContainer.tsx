import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { requestCreateSession } from '../../api/live';
import VideoRoomComponent from '../../components/Live/VideoRoomComponent';
import { useAppSelector } from '../../store/hooks';
import styles from './SubscribeLiveContainer.module.scss';

function SubscribeLiveRoomContainer() {
  const { sessionName } = useParams();
  const [check, setCheck] = useState<boolean>(false);
  const [token, setToken] = useState<string>('');
  const userName = useAppSelector((state) => state.userSlice.username);

  const createSession = async () => {
    try {
      const response = await requestCreateSession(sessionName as string);
      console.log(response);

      localStorage.setItem('liveToken', response.data.token);
      setToken(response.data.token);
      setCheck(true);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    createSession();
  }, []);

  return (
    <div className={styles.container}>
      <h1>시청자</h1>
      {check && <VideoRoomComponent sessionName={sessionName} user={userName} token={token} />}
    </div>
  );
}

export default SubscribeLiveRoomContainer;
