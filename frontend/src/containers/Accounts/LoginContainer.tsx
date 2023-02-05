import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';

// mui
import { Button, TextField } from '@mui/material';

import { async } from 'q';
import styles from './LoginContainer.module.scss';
import { UserSignInType } from '../../types/user';
import { requestSignIn } from '../../api/user';
import { useAppDispatch, useAppSelector } from '../../store/hooks';
import { closeModal, openModal } from '../../store/slices/modalSlice';
import { setUserLoginState, setUserType } from '../../store/slices/userSlice';
import KakaoLogin from '../../assets/images/kakao.png';
import { http } from '../../api/axios';

function LoginContainer() {
  const navigate = useNavigate();
  const dispatch = useAppDispatch();
  const params = useParams();
  const [userInfo, setUserInfo] = useState<UserSignInType>({
    email: '',
    password: '',
  });

  // 로 그인 정보 입력
  const onChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    setUserInfo({ ...userInfo, [name]: value });
    console.log('Get userinfo', userInfo);
  };

  // Enter키를 입력으로 로그인 요청
  const onKeyDownHandler = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') {
      requestEmailLogin();
    }
  };

  const handleModal = () => {
    dispatch(closeModal());
  };

  const requestEmailLogin = async () => {
    try {
      const response = await requestSignIn(userInfo);
      if (response.status === 200) {
        const { data } = response;
        console.log(data);

        localStorage.setItem('accessToken', data.token.accessToken);
        localStorage.setItem('refreshToken', data.token.refreshToken);
        sessionStorage.setItem('user', JSON.stringify(data));

        dispatch(setUserLoginState(true));
        dispatch(setUserType(data.userType));

        navigate('/');
      }
    } catch (error) {
      console.error(error);
      dispatch(openModal({ isOpen: true, title: '로그인 실패', content: '비밀번호가 틀림요', handleModal }));
    }
  };

  // KAKAO 로그인 요청
  const OAuth = async () => {
    try {
      // const REST_API_KEY = process.env.REACT_APP_KAKAO_LOGIN_API;
      // const REDIRECT_URI = `http://localhost:3000`;
      const url = `https://i8e204.p.ssafy.io/api/v1/oauth2/authorization/kakao`;
      // const url = ` kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;
      window.location.href = url;

      // const res = await http.get('oauth2/authorization/kakao');
      // console.log(res);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    console.log(params);
  }, []);

  return (
    <div>
      <div className={styles.container}>
        <div className={styles.area}>
          <div className={styles.contents}>
            <div className={styles['login-box']}>
              <p className={styles.title}>로그인</p>

              <p>이메일</p>
              <TextField onChange={onChangeHandler} name="email" margin="normal" placeholder="이메일을 입력해주세요." variant="outlined" onKeyPress={onKeyDownHandler} />

              <p>비밀번호</p>
              <TextField
                onChange={onChangeHandler}
                name="password"
                type="password"
                margin="normal"
                placeholder="비밀번호를 입력해주세요."
                onKeyPress={onKeyDownHandler}
                variant="outlined"
              />

              <div className={styles['findInfo-box']}>
                <span>
                  <Link to="/findEmail">이메일 찾기</Link>
                </span>
                <span>
                  <Link to="/findPassword">비밀번호 찾기</Link>
                </span>
              </div>

              <Button className={styles['login-button']} variant="contained" onClick={requestEmailLogin}>
                이메일로 로그인하기
              </Button>
              <img src={KakaoLogin} alt="aaa" onClick={OAuth} aria-hidden="true" />
            </div>
          </div>

          <ul className={styles.circles}>
            <li />
            <li />
            <li />
            <li />
            <li />
            <li />
            <li />
            <li />
            <li />
            <li />
          </ul>
        </div>
      </div>
    </div>
  );
}
export default LoginContainer;
