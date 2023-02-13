import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { requestLiveDonation } from '../../api/live';
import { requestTeamProfile, requestUserProfile } from '../../api/user';
import VideoRoomComponent from '../../components/Live/VideoRoomComponent';
import { useAppSelector } from '../../store/hooks';

function PublisherLiveRoomContainer() {
  const { username } = useParams();
  const [token, setToken] = useState<string | null>('');
  const [userCurrentMoney, setUserCurrentMoney] = useState<number>(0);
  const userName = useAppSelector((state) => state.userSlice.username);
  const userProfileImg = useAppSelector((state) => state.userSlice.profileImgUrl);
  const userType = useAppSelector((state) => state.userSlice.userType);
  const userId = useAppSelector((state) => state.userSlice.userId);

  const getUserMoney = async () => {
    try {
      const { data } = await requestTeamProfile(userId);
      console.log(data);
      setUserCurrentMoney(data.money);
    } catch (error) {
      console.error(error);
    }
  };

  const liveDonation = async (amount: number) => {
    console.log('뺴야할 금액', amount);
    try {
      const response = await requestLiveDonation(amount as number, userName as string);
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
    if (localStorage.getItem('liveToken')) {
      setToken(localStorage.getItem('liveToken'));
    }
    getUserMoney();
  }, []);

  useEffect(() => {
    console.log('token', token);
  }, [token]);

  // return (<h1>킄킄</h1>)
  return (
    <>
      {' '}
      {username && token && (
        <VideoRoomComponent
          userProfileImg={userProfileImg}
          userCurrentMoney={userCurrentMoney}
          sessionName={username}
          user={userName}
          token={token}
          liveDonation={liveDonation}
          userType={userType}
        />
      )}
    </>
  );
}

export default PublisherLiveRoomContainer;
