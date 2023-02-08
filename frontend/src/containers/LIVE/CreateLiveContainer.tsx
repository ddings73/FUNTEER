import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAppSelector } from '../../store/hooks';
import { requestCreateSession } from '../../api/live';
import styles from './CreateLiveContainer.module.scss';

function CreateLiveContainer() {
  const username = useAppSelector((state) => state.userSlice.username);
  const [checkRoom, setCheckRoom] = useState<boolean>(false);
  const navigate = useNavigate();

  const createSession = async () => {
    try {
      const response = await requestCreateSession(username, 308);
      localStorage.setItem('liveToken', response.data.token);
      navigate(`../liveRoom/${username}`);
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <div className={styles.container}>
      <button type="button" onClick={createSession}>
        라이브 생성하기
      </button>
    </div>
  );
}

export default CreateLiveContainer;
