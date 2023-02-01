import { Button, TextField } from '@mui/material';
import React, { useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { secondsToMinutes, secondsToSeconds } from '../../utils/timer';
import styles from './FindPasswordContainer.module.scss';

type UserLoginType = {
  email: string;
  password: string;
};

function FindPasswordContainer() {
  const navigate = useNavigate();
  const { pathname } = useLocation();

  const [userInfo, setUserInfo] = useState<UserLoginType>({
    email: '',
    password: '',
  });
  // 이메일 인증 버튼에 표시되는 텍스트
  const [buttonText, setButtonText] = useState<string>('이메일 인증하기');

  // 이메일 체크가 완료되었는지 체크해주는 상태
  const [checkEmailAuth, setCheckEmailAuth] = useState<boolean>(false);
  // 로 그인 정보 입력
  const onChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
  };

  // 이메일 인증 시간 카운딩
  const handleClickAuthEmail = () => {
    let time = 180;
    let minute;
    let second;
    const timer = setInterval(() => {
      minute = Math.floor(secondsToMinutes(time));
      second = secondsToSeconds(time);

      setButtonText(`${minute} 분 ${second}초`);
      time -= 1;

      if (time < 0) {
        setButtonText('입력 시간 초과');
        clearInterval(timer);
      }
    }, 1000);
  };

  const handleResetPassword = () => {
    navigate('/resetPassword');
  };
  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <div className={styles['login-box']}>
          <p className={styles.title}>비밀번호 찾기</p>

          <p>이름</p>
          <TextField onChange={onChangeHandler} name="email" margin="normal" id="outlined-basic" placeholder="이름을 입력해주세요." variant="outlined" />

          <p>이메일</p>
          <TextField
            onChange={onChangeHandler}
            name="email"
            type="email"
            margin="normal"
            id="outlined-basic"
            placeholder="가입한 이메일을 입력해주세요."
            variant="outlined"
          />
          <div className={styles['email-auth-box']}>
            <Button className={styles['email-auth-button']} variant="contained" onClick={handleClickAuthEmail}>
              {buttonText}
            </Button>
          </div>

          <p>인증번호</p>
          <TextField onChange={onChangeHandler} name="email" type="email" margin="normal" id="outlined-basic" placeholder="인증번호를 입력해주세요." variant="outlined" />

          <Button onClick={handleResetPassword} className={styles['login-button']} variant="contained">
            비밀번호 재설정하기
          </Button>
        </div>
      </div>
    </div>
  );
}

export default FindPasswordContainer;
