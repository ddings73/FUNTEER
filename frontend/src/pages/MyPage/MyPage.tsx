import React, { useEffect } from 'react';
import { useNavigate } from 'react-router';
import MyPageContainer from '../../containers/MyPage/MyPageContainer';
import { useAppSelector } from '../../store/hooks';

function MyPage() {
  const isLogin = useAppSelector((state) => state.userSlice.isLogin);
  const navigate = useNavigate();
  useEffect(() => {
    if (!isLogin) navigate('/login');
  }, []);
  return <MyPageContainer />;
}

export default MyPage;
