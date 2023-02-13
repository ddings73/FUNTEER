import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { requestCreateSession, requestLiveDonation } from '../../api/live';
import { requestUserProfile } from '../../api/user';
import VideoRoomComponent from '../../components/Live/VideoRoomComponent';
import { useAppSelector } from '../../store/hooks';
import styles from './SubscribeLiveContainer.module.scss';

function SubscribeLiveRoomContainer() {
  const { sessionName } = useParams();
  const [check, setCheck] = useState<boolean>(false);
  const [token, setToken] = useState<string>('');
  const [userCurrentMoney, setUserCurrentMoney] = useState<number>(0);
  const userName = useAppSelector((state) => state.userSlice.username);
  const userProfileImg = useAppSelector((state) => state.userSlice.profileImgUrl);
  const userId = useAppSelector((state) => state.userSlice.userId);
  const userType = useAppSelector((state) => state.userSlice.userType);

  const getUserMoney = async () => {
    try {
      const { data } = await requestUserProfile(userId);
      console.log(data);
      setUserCurrentMoney(data.money);
    } catch (error) {
      console.error(error);
    }
  };

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

  const liveDonation = async (amount: number) => {
    console.log('뺴야할 금액', amount);
    try {
      const response = await requestLiveDonation(amount as number, sessionName as string);
      console.log('결과띠', response);

      await getUserMoney();
      alert('성공띠');
      console.log(response);
    } catch (error) {
      alert('실패띠');

      console.error(error);
    }
  };

  useEffect(() => {
    getUserMoney();
    createSession();
  }, []);
  // return(
  //   <div>11</div>
  // )
  return (
    <div className={styles.container}>
      {check && (
        <VideoRoomComponent
          sessionName={sessionName}
          userProfileImg={userProfileImg}
          user={userName}
          userCurrentMoney={userCurrentMoney}
          liveDonation={liveDonation}
          userType={userType}
        />
      )}
    </div>
  );
}

export default SubscribeLiveRoomContainer;
