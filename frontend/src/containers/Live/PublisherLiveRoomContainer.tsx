import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { requestLiveDonation } from '../../api/live';
import VideoRoomComponent from '../../components/Live/VideoRoomComponent';
import { useAppSelector } from '../../store/hooks';

function PublisherLiveRoomContainer() {
  const { username } = useParams();
  const [token, setToken] = useState<string | null>('');
  const [userCurrentMoney,setUserCurrentMoney] = useState<number>(0);
  const userName = useAppSelector((state) => state.userSlice.username);
  const userProfileImg = useAppSelector(state=>state.userSlice.profileImgUrl)


  const liveDonation = async(amount:number)=>{
    console.log(amount)
    try{
      const response = await requestLiveDonation(amount as number,userName as string)
      console.log(response)
    }catch(error){
      console.error(error)
    }

  }


  useEffect(() => {
    if (localStorage.getItem('liveToken')) {
      setToken(localStorage.getItem('liveToken'));
    }
  }, []);

  useEffect(() => {
    console.log('token', token);
  }, [token]);

  // return (<h1>킄킄</h1>)
  return <> {username && token && <VideoRoomComponent userProfileImg={userProfileImg}  userCurrentMoney={userCurrentMoney} sessionName={username} user={userName} token={token} liveDonation={liveDonation} />}</>;
}

export default PublisherLiveRoomContainer;
