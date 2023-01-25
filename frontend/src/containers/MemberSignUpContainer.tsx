import React, { useState } from 'react';
import { Button, TextField } from '@mui/material';
// import axios from 'axios';
import styles from './MemberSignUpContainer.module.scss';

type MemberSingUpType = {
  name: string;
  email: string;
  emailIsCertificated: boolean;
  password: string;
  passwordCheck: string;
  nickname: string;
  phone: string;
  phoneIsCertificated: boolean;
  accountNumber: string;
};

function MemberSignUpContainer() {
  /** 회원가입 정보 */
  const [memberSignUpInfo, setMemberSignUpInfo] = useState<MemberSingUpType>({
    name: '',
    email: '',
    emailIsCertificated: false,
    password: '',
    passwordCheck: '',
    nickname: '',
    phone: '',
    phoneIsCertificated: false,
    accountNumber: '',
  });

  const onChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    setMemberSignUpInfo({ ...memberSignUpInfo, [name]: value });
  };

  const requestEmailCertification = () => {
    console.log('이메일 인증 요청');
  };

  const requestPhoneCertification = () => {
    console.log('휴대폰 인증 요청');
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
            <TextField name="email" margin="dense" placeholder="이메일을 입력해주세요." variant="outlined" onChange={onChangeHandler} />
            <Button className={styles['certification-button']} variant="contained" onClick={requestEmailCertification}>
              인증
            </Button>

            <p>비밀번호</p>
            <TextField name="password" margin="dense" placeholder="비밀번호을 입력해주세요." variant="outlined" onChange={onChangeHandler} />

            <p>비밀번호 확인</p>
            <TextField name="passwordCheck" margin="dense" placeholder="비밀번호을 입력해주세요." variant="outlined" onChange={onChangeHandler} />

            <p>닉네임</p>
            <TextField name="nickname" margin="dense" placeholder="닉네임을 입력해주세요." variant="outlined" onChange={onChangeHandler} />

            <p>휴대폰 번호</p>
            <TextField name="phone" margin="dense" placeholder="휴대폰 번호를 입력해주세요." variant="outlined" onChange={onChangeHandler} />
            <Button className={styles['certification-button']} variant="contained" onClick={requestPhoneCertification}>
              인증
            </Button>

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
