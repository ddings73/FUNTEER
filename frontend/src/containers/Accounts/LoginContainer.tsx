import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

// mui
import { Button, TextField } from '@mui/material';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogActions from '@mui/material/DialogActions';
import Modal from '@mui/material/Modal';

import styles from './LoginContainer.module.scss';
import { UserSignInType } from '../../types/user';
import { requestSignIn } from '../../api/user';
import { useAppDispatch } from '../../store/hooks';
import { openModal } from '../../store/slices/modalSlice';







function LoginContainer() {
  const navigate = useNavigate();
  const dispatch = useAppDispatch()
  const [userInfo, setUserInfo] = useState<UserSignInType>({
    email: '',
    password: '',
    type:"NORMAL"
  });


  // 로 그인 정보 입력
  const onChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    setUserInfo({ ...userInfo, [name]: value });
  };

  // Enter키를 입력으로 로그인 요청
  const onKeyDownHandler = (e: React.KeyboardEvent<HTMLInputElement>)=>{
    if(e.key==="Enter"){
      requestEmailLogin()
    }
  }

  const requestEmailLogin = async () => {
    try{
       const response = await requestSignIn(userInfo)
       if(response.status === 200){
        const {data} = response
        localStorage.setItem("token",JSON.stringify(data.token))        
       }
    }
    catch(error){
      console.error(error);
      dispatch(openModal({isOpen:true,title:"로그인 실패",content:"비밀번호가 틀림요"}))
    }
  };



  // KAKAO 로그인 요청
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
          <TextField 
          onChange={onChangeHandler} 
          name="email" 
          margin="normal" 
          placeholder="이메일을 입력해주세요." 
          variant="outlined" 
          onKeyPress={onKeyDownHandler} 
          />

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

          <Button className={styles['login-button']} variant="contained" onClick={requestEmailLogin} >
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
