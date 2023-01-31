import React, { useState } from 'react';
import { useNavigate } from 'react-router';
import { useInterval } from 'usehooks-ts';
import { Button, TextField } from '@mui/material';
import { memberSignUpType } from '../../types/user';
import { secondsToMinutes, secondsToSeconds } from '../../utils/timer';
import { requestEmailDuplConfirm, requestMemberSignUp, requestNicknameDuplConfirm, requestPhoneDuplConfirm } from '../../api/user';
import styles from './MemberSignUpContainer.module.scss';

function MemberSignUpContainer() {
  const navigate = useNavigate();

  /** 회원가입 정보 */
  const [memberSignUpInfo, setMemberSignUpInfo] = useState<memberSignUpType>({
    name: '',
    email: '',
    password: '',
    passwordCheck: '',
    nickname: '',
    phone: '',
  });

  /** 이메일 인증 버튼에 표시되는 텍스트 */
  const [buttonText, setButtonText] = useState<string>('이메일 인증하기');
  /** 닉네임 중복 검사 여부 */
  const [nicknameDuplConfirmed, setNicknameDuplConfirmed] = useState<boolean>(false);
  /** 이메일 중복 검사 여부 */
  const [emailDuplConfirmed, setEmailDuplConfirmed] = useState<boolean>(false);
  /** 휴대폰 중복 검사 여부 */
  const [phoneDuplConfirmed, setPhoneDuplConfirmed] = useState<boolean>(false);
  /** 이메일 인증 버튼을 이미 눌렀는지 확인 */
  const [emailAuthButtonPushed, setEmailAuthButtonPushed] = useState<boolean>(false);
  /** 이메일 인증 시간 제한 */
  const initTime = 300;
  const [time, setTime] = useState<number>(initTime);
  /** 인증 번호 */
  const [authNumber, setAuthNumber] = useState<string>('');
  /** 이메일 인증이 완료되었는지 체크해주는 상태 */
  const [checkEmailAuth, setCheckEmailAuth] = useState<boolean>(false);

  /** 회원가입 정보 입력 */
  const onChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    /** 닉네임을 바꿀 경우 중복 체크 다시 필요 */
    if (name === 'nickname') {
      setNicknameDuplConfirmed(false);
    }
    /** 이메일을 바꿀 경우 중복 체크 다시 필요 => 인증까지 완료되면 바꿀 수 없음 */
    if (name === 'email') {
      setEmailDuplConfirmed(false);
    }
    /** 휴대폰 번호를 바꿀 경우 중복 체크 다시 필요 */
    if (name === 'phone') {
      setPhoneDuplConfirmed(false);
    }

    setMemberSignUpInfo({ ...memberSignUpInfo, [name]: value });
  };

  /** 닉네임 중복 검사 */
  const onClickNicknameDuplBtnHandler = async (e: React.MouseEvent<HTMLAnchorElement>) => {
    e.preventDefault();

    if (!memberSignUpInfo.nickname) {
      alert('닉네임을 입력해주세요.');
      return;
    }

    try {
      const response = await requestNicknameDuplConfirm(memberSignUpInfo.nickname);
      alert('닉네임 중복 체크 완료');
      setNicknameDuplConfirmed(true);
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };

  /** 이메일 중복 검사 */
  const onClickEmailDuplBtnHandler = async (e: React.MouseEvent<HTMLAnchorElement>) => {
    e.preventDefault();

    if (!memberSignUpInfo.email) {
      alert('이메일을 입력해주세요.');
      return;
    }

    /** 이메일 정규식 검사 */
    const validEmail = /^[A-Za-z0-9]+@[A-Za-z]+\.[A-Za-z]+/; // (알파벳, 숫자)@(알파벳).(알파벳)

    if (validEmail.test(memberSignUpInfo.email) === false) {
      alert('이메일 주소가 올바르지 않습니다.');
      return;
    }

    try {
      const response = await requestEmailDuplConfirm(memberSignUpInfo.email);
      alert('이메일 중복 체크 완료');
      setEmailDuplConfirmed(true);
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };

  /** 휴대폰 중복 검사 */
  const onClickPhoneDuplBtnHandler = async (e: React.MouseEvent<HTMLAnchorElement>) => {
    e.preventDefault();

    if (!memberSignUpInfo.phone) {
      alert('휴대폰 번호를 입력해주세요.');
      return;
    }

    try {
      const response = await requestPhoneDuplConfirm(memberSignUpInfo.email);
      alert('휴대폰 번호 중복 체크 완료');
      setPhoneDuplConfirmed(true);
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };

  /** 이메일 인증하기 버튼 */
  const handleClickAuthEmail = () => {
    if (!emailDuplConfirmed) {
      alert('이메일 중복 체크를 먼저 완료해주세요.');
      return;
    }

    if (emailAuthButtonPushed) {
      return;
    }

    setEmailAuthButtonPushed(true);
  };

  /** 이메일 인증 타이머 */
  let minute;
  let second;
  useInterval(
    () => {
      /** logic */
      setTime(time - 1);
      minute = Math.floor(secondsToMinutes(time));
      second = secondsToSeconds(time);
      setButtonText(`${minute}분 ${second}초`);

      if (time === 0) {
        alert('인증번호 입력 시간이 초과되었습니다.');
        setButtonText('이메일 인증하기');
        setEmailAuthButtonPushed(false);
        setTime(initTime);
      }
    },
    /** Delay in milliseconds or null to stop it */
    emailAuthButtonPushed && !checkEmailAuth ? 1000 : null,
  );

  /** 인증 번호 입력 */
  const onAuthNumberChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setAuthNumber(e.target.value);
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
  const requestSignUp = async () => {
    // ========================== 유효성 검사 ==============================
    /** 중복 검사 했는지 */
    if (!nicknameDuplConfirmed || !emailDuplConfirmed || !phoneDuplConfirmed) {
      alert('모든 중복 체크를 완료해주세요.');
      return;
    }
    /** 이메일 인증 여부 */
    if (!checkEmailAuth) {
      alert('이메일 인증을 완료해주세요.');
      return;
    }
    /** 모든 정보를 입력 했는지 */
    const isEmpty = Object.values(memberSignUpInfo).some((value) => value === '' || value === null);
    if (isEmpty) {
      alert('모든 정보를 입력해주세요.');
      return;
    }
    /** 비밀번호와 비밀번호 확인 값이 같은지 */
    if (memberSignUpInfo.password !== memberSignUpInfo.passwordCheck) {
      alert('비밀번호와 비밀번호 확인 값이 다릅니다.');
      return;
    }
    // ===================================================================

    console.log('개인 회원가입 정보', memberSignUpInfo);

    try {
      const response = await requestMemberSignUp(memberSignUpInfo);
      console.log(response);
      navigate('/');
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>개인 회원가입</h1>
        <div className={styles['form-div']}>
          <div id="form-div-inner">
            <p>이름</p>
            <TextField name="name" margin="dense" placeholder="이름을 입력해주세요." variant="outlined" onChange={onChangeHandler} />

            <p>
              이메일
              {!emailDuplConfirmed && (
                <a href="./" onClick={onClickEmailDuplBtnHandler} className={styles['dupl-btn']}>
                  중복체크
                </a>
              )}
            </p>
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
            <TextField name="password" margin="dense" placeholder="비밀번호를 입력해주세요." variant="outlined" onChange={onChangeHandler} />

            <p>비밀번호 확인</p>
            <TextField name="passwordCheck" margin="dense" placeholder="비밀번호를 입력해주세요." variant="outlined" onChange={onChangeHandler} />

            <p>
              닉네임
              {!nicknameDuplConfirmed && (
                <a href="./" onClick={onClickNicknameDuplBtnHandler} className={styles['dupl-btn']}>
                  중복체크
                </a>
              )}
            </p>
            <TextField name="nickname" margin="dense" placeholder="닉네임을 입력해주세요." variant="outlined" onChange={onChangeHandler} />

            <p>
              휴대폰 번호
              {!phoneDuplConfirmed && (
                <a href="./" onClick={onClickPhoneDuplBtnHandler} className={styles['dupl-btn']}>
                  중복체크
                </a>
              )}
            </p>
            <TextField name="phone" margin="dense" placeholder="휴대폰 번호를 입력해주세요." variant="outlined" onChange={onChangeHandler} />

            {/* <p>계좌번호</p>
            <TextField name="accountNumber" margin="dense" placeholder="계좌번호를 입력해주세요." variant="outlined" onChange={onChangeHandler} /> */}

            <Button className={styles['signup-button']} variant="contained" onClick={requestSignUp}>
              회원가입
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default MemberSignUpContainer;
