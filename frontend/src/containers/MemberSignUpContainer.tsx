import React, { useState } from 'react';
import { Button, TextField } from '@mui/material';
// import axios from 'axios';
import styles from './MemberSignUpContainer.module.scss';
import { secondsToMinutes, secondsToSeconds } from '../utils/timer';

type MemberSingUpType = {
  name: string;
  email: string;
  password: string;
  passwordCheck: string;
  nickname: string;
  phone: string;
  accountNumber: string;
  authNumber: string;
};

function MemberSignUpContainer() {
  /** 회원가입 정보 */
  const [memberSignUpInfo, setMemberSignUpInfo] = useState<MemberSingUpType>({
    name: '',
    email: '',
    password: '',
    passwordCheck: '',
    nickname: '',
    phone: '',
    accountNumber: '',
    authNumber: '',
  });

  const onChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    setMemberSignUpInfo({ ...memberSignUpInfo, [name]: value });
  };

  /** 이메일 인증 버튼에 표시되는 텍스트 */
  const [buttonText, setButtonText] = useState<string>('이메일 인증하기');

  /** 이메일 인증 버튼을 이미 눌렀는지 확인 */
  const [emailAuthButtonPushed, setEmailAuthButtonPushed] = useState<boolean>(false);

  /** 이메일 인증이 완료되었는지 체크해주는 상태 */
  const [checkEmailAuth, setCheckEmailAuth] = useState<boolean>(false);

  /** 이메일 인증 시간 카운팅 */
  const handleClickAuthEmail = () => {
    if (emailAuthButtonPushed) {
      return;
    }

    setEmailAuthButtonPushed(true);

    let time = 180;
    let minute;
    let second;
    const timer = setInterval(() => {
      minute = Math.floor(secondsToMinutes(time));
      second = secondsToSeconds(time);

      setButtonText(`${minute}분 ${second}초`);
      time -= 1;

      if (time < 0) {
        alert('인증번호 입력 시간이 초과되었습니다.');
        clearInterval(timer);
        setButtonText('이메일 인증하기');
        setEmailAuthButtonPushed(false);
      }
    }, 1000);
  };

  const checkAuthNumber = () => {
    alert('이메일 인증이 완료되었습니다.');
    setCheckEmailAuth(true);
    setEmailAuthButtonPushed(false);

    /** if (백엔드에서 온 이메일 인증번호 === 유저가 입력한 인증번호) {
     *    alert('이메일 인증이 완료되었습니다.')
     *    setEmailAuthButtonPushed(false);
     *    setCheckEmailAuth(true)
     * } else {
     *    alert(이메일 인증 번호가 틀렸습니다.)
     *    setButtonText('이메일 인증하기')
     *    setEmailAuthButtonPushed(false)
     * } */
  };

  /** 개인 회원가입 요청 */
  const requestMemberSignUp = () => {
    console.log('개인 회원가입 정보', memberSignUpInfo);
    console.log('개인 회원가입 요청');
  };

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>개인 회원가입</h1>
        <div className={styles['form-div']}>
          <div id="form-div-inner">
            <p>이름</p>
            <TextField name="name" margin="dense" placeholder="이름을 입력해주세요." variant="outlined" onChange={onChangeHandler} />

            <p>이메일</p>
            {checkEmailAuth && <p className={styles['authed-email']}>{memberSignUpInfo.email}</p>}
            {!checkEmailAuth && (
              <div className={styles['not-shadow']}>
                <TextField name="email" margin="dense" placeholder="이메일을 입력해주세요." variant="outlined" onChange={onChangeHandler} />
                <Button className={styles['auth-button']} variant="contained" onClick={handleClickAuthEmail}>
                  {buttonText}
                </Button>
              </div>
            )}
            {emailAuthButtonPushed && (
              <div className={styles['auth-input-div']}>
                <input type="text" name="authNumber" placeholder="인증번호를 입력해주세요." className={styles['auth-number-input']} onChange={onChangeHandler} />
                <Button className={styles['auth-check-button']} variant="contained" onClick={checkAuthNumber}>
                  인증
                </Button>
              </div>
            )}

            <p>비밀번호</p>
            <TextField name="password" margin="dense" placeholder="비밀번호을 입력해주세요." variant="outlined" onChange={onChangeHandler} />

            <p>비밀번호 확인</p>
            <TextField name="passwordCheck" margin="dense" placeholder="비밀번호을 입력해주세요." variant="outlined" onChange={onChangeHandler} />

            <p>닉네임</p>
            <TextField name="nickname" margin="dense" placeholder="닉네임을 입력해주세요." variant="outlined" onChange={onChangeHandler} />

            <p>휴대폰 번호</p>
            <TextField name="phone" margin="dense" placeholder="휴대폰 번호를 입력해주세요." variant="outlined" onChange={onChangeHandler} />

            <p>계좌번호</p>
            <TextField name="accountNumber" margin="dense" placeholder="계좌번호를 입력해주세요." variant="outlined" onChange={onChangeHandler} />

            <Button className={styles['signup-button']} variant="contained" onClick={requestMemberSignUp}>
              회원가입
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default MemberSignUpContainer;
