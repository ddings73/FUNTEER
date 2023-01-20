import { Button, TextField } from '@mui/material';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';
import React, { useState } from 'react';
import styles from './LoginContainer.module.scss';

type LoginType = {
  email: string;
  password: string;
};

function LoginContainer() {
  const [loginInfo, setLoginInfo] = useState<LoginType>();

  const onChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    console.log(e.target.value);
  };

  const OAuth = () => {
    const REST_API_KEY = process.env.REACT_APP_KAKAO_LOGIN_API;
    const REDIRECT_URI = `http://localhost:3000`;
    const url = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;
    window.location.href = url;
  };

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <div className={styles['login-box']}>
          <p className={styles.title}>로그인</p>

          <p>이메일</p>
          <TextField onChange={onChangeHandler} name="email" margin="normal" id="outlined-basic" placeholder="이메일을 입력해주세요." variant="outlined" />

          <p>비밀번호</p>
          <TextField
            onChange={onChangeHandler}
            name="password"
            type="password"
            margin="normal"
            id="outlined-basic"
            placeholder="비밀번호를 입력해주세요."
            variant="outlined"
          />

          <Button className={styles['login-button']} variant="contained">
            이메일로 로그인하기
          </Button>

          <Button className={styles['login-button']} variant="contained" onClick={OAuth}>
            카카오로 로그인 하기
          </Button>
        </div>
      </div>
    </div>
  );
}
export default LoginContainer;
