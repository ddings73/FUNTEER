import React, { useEffect } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { http } from '../../api/axios';
import { useAppDispatch } from '../../store/hooks';
import { setUserLoginState, setUserType } from '../../store/slices/userSlice';

function KakaoContainer() {
  const [searchParams, setSearchParams] = useSearchParams();
  const navigate = useNavigate();
  const dispatch = useAppDispatch();

  const email = searchParams.get('email');
  const data = {
    email,
  };
  const kakaoLogin = async () => {
    try {
      const response = await http.post('login/kakao', data, {
        headers: {
          'Content-Type': 'application/json',
        },
      });
      console.log(response);
      localStorage.setItem('accessToken', response.data.token.accessToken);
      localStorage.setItem('refreshToken', response.data.token.refreshToken);
      dispatch(setUserLoginState({ isLogin: true, userType: response.data.userType, userId: response.data.userId, username: response.data.username ,profileImgUrl:response.data.profileImgUrl}));
      navigate('/', { replace: true });
    } catch (Error) {
      console.log(Error);
    }
  };
  useEffect(() => {
    console.log('ㅎㅇㅎㅇㅎㅇ');

    kakaoLogin();
  }, []);
  return <h1>H!</h1>;
}
export default KakaoContainer;
