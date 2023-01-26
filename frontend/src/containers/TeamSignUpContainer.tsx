import React, { useState } from 'react';
import { Button, TextField } from '@mui/material';
// import axios from 'axios';
import styles from './TeamSignUpContainer.module.scss';
import { secondsToMinutes, secondsToSeconds } from '../utils/timer';

type TeamSingUpType = {
  name: string;
  email: string;
  password: string;
  passwordCheck: string;
  phone: string;
};

function TeamSignUpContainer() {
  /** 회원가입 정보 */
  const [TeamSignUpInfo, setTeamSignUpInfo] = useState<TeamSingUpType>({
    name: '',
    email: '',
    password: '',
    passwordCheck: '',
    phone: '',
  });

  /** 인증 번호 */
  const [authNumber, setAuthNumber] = useState<string>('');

  const onChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    setTeamSignUpInfo({ ...TeamSignUpInfo, [name]: value });
  };

  const onAuthNumberChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setAuthNumber(e.target.value);
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

  const [vmsFiles, setVmsFiles] = useState<FileList | null>(null);
  const [performFiles, setPerformFiles] = useState<FileList | null>(null);

  const onVmsChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setVmsFiles(e.target.files);
    }
  };

  const onPerformChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setPerformFiles(e.target.files);
    }
  };

  /** 단체 회원가입 요청 */
  const requestTeamSignUp = () => {
    /** 유효성 검사 */
    const isEmpty = Object.values(TeamSignUpInfo).some((value) => value === '' || value === null);

    if (isEmpty) {
      alert('모든 정보를 입력해주세요.');
      return;
    }

    if (vmsFiles && performFiles) {
      const formData = new FormData();
      formData.append('file', vmsFiles[0]);
      formData.append('file', performFiles[0]);
    } else {
      alert('필수 파일을 첨부해주세요.');
      return;
    }

    if (TeamSignUpInfo.password !== TeamSignUpInfo.passwordCheck) {
      alert('비밀번호와 비밀번호 확인 값이 다릅니다.');
      return;
    } // 유효성 검사 끝

    console.log('단체 회원가입 정보', TeamSignUpInfo);
    console.log('단체 회원가입 요청');
  };

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>단체 회원가입</h1>
        <div className={styles['form-div']}>
          <div id="form-div-inner">
            <p>단체명</p>
            <TextField name="name" margin="dense" placeholder="이름을 입력해주세요." variant="outlined" onChange={onChangeHandler} />

            <p>이메일</p>
            {checkEmailAuth && <p className={styles['authed-email']}>{TeamSignUpInfo.email}</p>}
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
                <input
                  type="text"
                  name="authNumber"
                  placeholder="인증번호를 입력해주세요."
                  className={styles['auth-number-input']}
                  onChange={onAuthNumberChangeHandler}
                />
                <Button className={styles['auth-check-button']} variant="contained" onClick={checkAuthNumber}>
                  인증
                </Button>
              </div>
            )}

            <p>비밀번호</p>
            <TextField name="password" margin="dense" placeholder="비밀번호을 입력해주세요." variant="outlined" onChange={onChangeHandler} />

            <p>비밀번호 확인</p>
            <TextField name="passwordCheck" margin="dense" placeholder="비밀번호을 입력해주세요." variant="outlined" onChange={onChangeHandler} />

            <p>대표자 연락처</p>
            <TextField name="phone" margin="dense" placeholder="휴대폰 번호를 입력해주세요." variant="outlined" onChange={onChangeHandler} />

            <hr />

            <label htmlFor="file">
              <p>VMS 위촉 임명장</p>
              <input type="file" name="file" id="file" onChange={onVmsChangeHandler} />
            </label>
            <p className={styles.comment}>VMS에 인증된 봉사 단체인지 확인합니다.</p>

            <label htmlFor="file">
              <p>봉사 실적</p>
              <input type="file" name="file" id="file" onChange={onPerformChangeHandler} />
            </label>
            <p className={styles.comment}>펀딩 진행 시 1년 이내의 봉사 실적이 필요합니다.</p>

            <p className={styles['last-comment']}>가입 승인 시 인증된 이메일 주소로 메일을 보내드립니다.</p>
            <Button className={styles['signup-button']} variant="contained" onClick={requestTeamSignUp}>
              회원가입
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default TeamSignUpContainer;
