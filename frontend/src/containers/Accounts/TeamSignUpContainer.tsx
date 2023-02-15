import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router';
import { useInterval } from 'usehooks-ts';
import { Button, TextField } from '@mui/material';
import { Visibility, VisibilityOff } from '@mui/icons-material';
import MenuItem from '@mui/material/MenuItem';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import { s1000, s1500, w1500, customAlert } from '../../utils/customAlert';
import { teamSignUpType } from '../../types/user';
import { secondsToMinutes, secondsToSeconds } from '../../utils/timer';
import { requestCheckEmailAuthCode, requestEmailDuplConfirm, requestNameDuplConfirm, requestSendEmailAuthCode } from '../../api/user';
import { requestTeamSignUp } from '../../api/team';
import styles from './TeamSignUpContainer.module.scss';

const bankKind = [
  '카카오뱅크',
  '농협',
  '신한',
  'IBK기업',
  '하나',
  '우리',
  '국민',
  'SC제일',
  '대구',
  '부산',
  '광주',
  '새마을금고',
  '경남',
  '전북',
  '제주',
  '산업',
  '우체국',
  '신협',
  '수협',
  '씨티',
  '케이뱅크',
  '토스뱅크',
  '도이치',
  'BOA',
  'BNP',
  'HSBC',
  'JP모간',
  '산립조합',
  '저축은행',
];

function TeamSignUpContainer() {
  const navigate = useNavigate();

  /** 회원가입 정보 */
  const [teamSignUpInfo, setTeamSignUpInfo] = useState({
    name: '',
    email: '',
    password: '',
    passwordCheck: '',
    phone: '',
    accountNumber: '',
  });

  /** 이메일 인증 버튼에 표시되는 텍스트 */
  const [btnText, setButtonText] = useState<string>('이메일 인증하기');
  /** 단체명 중복 검사 여부 */
  const [nameDuplConfirmed, setNameDuplConfirmed] = useState<boolean>(false);
  /** 이메일 중복 검사 여부 */
  const [emailDuplConfirmed, setEmailDuplConfirmed] = useState<boolean>(false);
  /** 이메일 인증 버튼을 이미 눌렀는지 확인 */
  const [emailAuthButtonPushed, setEmailAuthButtonPushed] = useState<boolean>(false);
  /** 이메일 인증 시간 제한 */
  const initTime = 180;
  const [time, setTime] = useState<number>(initTime);
  /** 인증 번호 */
  const [authNumber, setAuthNumber] = useState<string>('');
  /** 이메일 인증이 완료되었는지 체크해주는 상태 */
  const [checkEmailAuth, setCheckEmailAuth] = useState<boolean>(false);
  /** 비밀번호 가시 여부 */
  const [passwordVisibility, setPasswordVisibility] = useState<boolean>(false);
  /** 비밀번호 확인 가시 여부 */
  const [passwordCheckVisibility, setPasswordCheckVisibility] = useState<boolean>(false);
  /** vms 파일 */
  const [vmsFile, setVmsFile] = useState<Blob | null>(null);
  /** 실적 파일 */
  const [performFile, setPerformFile] = useState<Blob | null>(null);
  /** 하이픈 전화번호 */
  const [inputValue, setInputValue] = useState<string>('');

  /** 하이픈 자동 완성 */
  useEffect(() => {
    if (inputValue.length === 10) {
      setInputValue(inputValue.replace(/(\d{3})(\d{4})(\d{3})/, '$1-$2-$3'));
    }
    if (inputValue.length === 11) {
      setInputValue(inputValue.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3'));
    }
  }, [inputValue]);

  /** 회원가입 정보 입력 */
  const onChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    /** 단체명을 바꿀 경우 중복 체크 다시 필요 */
    if (name === 'name') {
      setNameDuplConfirmed(false);
    }
    /** 이메일을 바꿀 경우 중복 체크 다시 필요 => 인증까지 완료되면 바꿀 수 없음 */
    if (name === 'email') {
      setEmailDuplConfirmed(false);
    }
    /** 하이픈 */
    if (name === 'phone') {
      setInputValue(e.target.value);
    }

    setTeamSignUpInfo({ ...teamSignUpInfo, [name]: value });
  };

  /** 단체명 중복 검사 */
  const onClickNameDuplBtnHandler = async (e: React.MouseEvent<HTMLAnchorElement>) => {
    e.preventDefault();

    if (!teamSignUpInfo.name) {
      customAlert(w1500, '단체명을 입력해주세요.');
      return;
    }

    try {
      const response = await requestNameDuplConfirm(teamSignUpInfo.name);
      customAlert(s1000, '단체명 중복 체크 완료');
      setNameDuplConfirmed(true);
      console.log(response);
    } catch (error) {
      customAlert(w1500, '이미 가입된 단체명입니다.');
      console.log(error);
    }
  };

  /** 이메일 중복 검사 */
  const onClickEmailDuplBtnHandler = async (e: React.MouseEvent<HTMLAnchorElement>) => {
    e.preventDefault();

    if (!teamSignUpInfo.email) {
      customAlert(w1500, '이메일을 입력해주세요.');
      return;
    }

    /** 이메일 정규식 검사 */
    const validEmail = /^[A-Za-z0-9]+@[A-Za-z]+\.[A-Za-z]+/; // (알파벳, 숫자)@(알파벳).(알파벳)

    if (validEmail.test(teamSignUpInfo.email) === false) {
      customAlert(w1500, '이메일 주소가 올바르지 않습니다.');
      return;
    }

    try {
      const response = await requestEmailDuplConfirm(teamSignUpInfo.email);
      customAlert(s1000, '이메일 중복 체크 완료');
      setEmailDuplConfirmed(true);
      console.log(response);
    } catch (error) {
      customAlert(w1500, '이미 가입된 이메일입니다.');
      console.log(error);
    }
  };

  /** 이메일 인증하기 버튼 */
  const handleClickAuthEmail = async () => {
    if (!emailDuplConfirmed) {
      customAlert(w1500, '먼저 이메일 중복 체크를 완료해주세요.');
      return;
    }

    if (emailAuthButtonPushed) {
      return;
    }

    setEmailAuthButtonPushed(true);
    try {
      const response = await requestSendEmailAuthCode(teamSignUpInfo.email);
      console.log('이메일 인증 코드 요청', response);
    } catch (err) {
      console.error(err);
    }
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
        customAlert(w1500, '인증 번호 입력 시간이 초과되었습니다.');
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

  /** 인증 번호 검증 */
  const checkAuthNumber = async () => {
    try {
      const response = await requestCheckEmailAuthCode(authNumber, teamSignUpInfo.email);
      console.log('이메일 인증 요청', response);
      customAlert(s1500, '이메일 인증이 완료되었습니다.');
      setCheckEmailAuth(true);
      setEmailAuthButtonPushed(false);
    } catch (err) {
      console.error(err);
      customAlert(w1500, '인증 번호가 일치하지 않습니다.');
    }
  };

  /** 비밀번호 가시 */
  useEffect(() => {
    if (passwordVisibility) {
      console.log();
    }
  }, [passwordVisibility]);

  /** 계좌 인증 알림, 구현 패스 */
  const onClickAccountBtn = () => {
    if (teamSignUpInfo.accountNumber.length <= 5) {
      customAlert(w1500, '올바른 계좌 번호를 입력해주세요.');
      return;
    }
    customAlert(s1000, '계좌 인증이 완료되었습니다.');
  };

  /** vms 파일 업로드 */
  const onVmsChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setVmsFile(e.target.files[0]);
    }
  };

  /** 실적 파일 업로드 */
  const onPerformChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setPerformFile(e.target.files[0]);
    }
  };

  /** 단체 회원가입 요청 */
  const requestSignUp = async () => {
    // ========================== 유효성 검사 ==============================
    /** 중복 검사 했는지 */
    if (!nameDuplConfirmed || !emailDuplConfirmed) {
      customAlert(w1500, '모든 중복 체크를 완료해주세요.');
      return;
    }
    /** 비밀번호와 비밀번호 확인 값이 같은지 */
    if (teamSignUpInfo.password !== teamSignUpInfo.passwordCheck) {
      customAlert(w1500, '비밀번호와 비밀번호 확인 값이 다릅니다.');
      return;
    }
    /** 비밀번호 정규식: 8 ~ 15자, 하나 이상의 문자와 숫자 */
    const validPW = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z]).*$/;
    if (!validPW.test(teamSignUpInfo.password)) {
      customAlert(w1500, '적합하지 않은 비밀번호입니다.');
      return;
    }
    /** 이메일 인증 여부 */
    if (!checkEmailAuth) {
      customAlert(w1500, '이메일 인증을 완료해주세요.');
      return;
    }
    /** 모든 정보를 입력 했는지 */
    const isEmpty = Object.values(teamSignUpInfo).some((value) => value === '' || value === null);
    if (isEmpty) {
      customAlert(w1500, '모든 정보를 입력해주세요.');
      return;
    }
    /** 필수 파일들을 업로드 했는지 */
    if (!vmsFile || !performFile) {
      customAlert(w1500, '필수 파일을 첨부해주세요.');
      return;
    }

    // ===================================================================

    const newTeamSignUpInfo: teamSignUpType = { ...teamSignUpInfo, vmsFile, performFile };
    console.log('단체 회원가입 정보', newTeamSignUpInfo);

    try {
      const response = await requestTeamSignUp(newTeamSignUpInfo);
      console.log(response);
      customAlert(s1500, '단체 회원가입이 완료되었습니다.');
      navigate('/');
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.area}>
        <div className={styles.contents}>
          <h1 className={styles.title}>단체 회원가입</h1>
          <div className={styles['form-div']}>
            <div className={styles['form-div-inner']}>
              <p>
                단체명
                {!nameDuplConfirmed && (
                  <a href="./" onClick={onClickNameDuplBtnHandler} className={styles['dupl-btn']}>
                    중복체크
                  </a>
                )}
              </p>
              <TextField
                color="warning"
                name="name"
                margin="dense"
                placeholder="이름을 입력해주세요."
                variant="outlined"
                sx={{ background: 'white' }}
                onChange={onChangeHandler}
              />
              <p>
                이메일
                {!emailDuplConfirmed && (
                  <a href="./" onClick={onClickEmailDuplBtnHandler} className={styles['dupl-btn']}>
                    중복체크
                  </a>
                )}
              </p>
              {checkEmailAuth && <p className={styles['authed-email']}>{teamSignUpInfo.email}</p>}
              {!checkEmailAuth && (
                <div className={styles['not-shadow']}>
                  <TextField
                    color="warning"
                    name="email"
                    margin="dense"
                    placeholder="이메일을 입력해주세요."
                    variant="outlined"
                    sx={{ background: 'white' }}
                    onChange={onChangeHandler}
                  />
                  <Button className={styles['auth-btn']} variant="contained" onClick={handleClickAuthEmail}>
                    {btnText}
                  </Button>
                </div>
              )}
              {emailAuthButtonPushed && (
                <div className={styles['auth-input-div']}>
                  <input
                    type="text"
                    name="authNumber"
                    placeholder="인증 번호를 입력해주세요."
                    className={styles['auth-number-input']}
                    onChange={onAuthNumberChangeHandler}
                  />
                  <Button className={styles['auth-check-btn']} variant="contained" onClick={checkAuthNumber}>
                    인증
                  </Button>
                </div>
              )}
              <p>비밀번호</p>
              <div className={styles['pw-div']}>
                <TextField
                  color="warning"
                  name="password"
                  type={!passwordVisibility ? 'password' : ''}
                  margin="dense"
                  placeholder="비밀번호를 입력해주세요."
                  variant="outlined"
                  sx={{ background: 'white' }}
                  onChange={onChangeHandler}
                />{' '}
                {passwordVisibility && (
                  <Visibility
                    onClick={() => {
                      setPasswordVisibility(!passwordVisibility);
                    }}
                    className={styles['pwv-btn']}
                  />
                )}
                {!passwordVisibility && (
                  <VisibilityOff
                    onClick={() => {
                      setPasswordVisibility(!passwordVisibility);
                    }}
                    className={styles['pwv-btn']}
                  />
                )}
              </div>
              <p className={styles.comment}>8~15자, 하나 이상의 문자와 숫자가 포함되어야 합니다.</p>
              <p>비밀번호 확인</p>
              <div className={styles['pw-div']}>
                <TextField
                  color="warning"
                  name="passwordCheck"
                  type={!passwordCheckVisibility ? 'password' : ''}
                  margin="dense"
                  placeholder="비밀번호를 입력해주세요."
                  variant="outlined"
                  sx={{ background: 'white' }}
                  onChange={onChangeHandler}
                />
                {passwordCheckVisibility && (
                  <Visibility
                    onClick={() => {
                      setPasswordCheckVisibility(!passwordCheckVisibility);
                    }}
                    className={styles['pwv-btn']}
                  />
                )}
                {!passwordCheckVisibility && (
                  <VisibilityOff
                    onClick={() => {
                      setPasswordCheckVisibility(!passwordCheckVisibility);
                    }}
                    className={styles['pwv-btn']}
                  />
                )}
              </div>
              <p>대표자 연락처</p>
              <TextField
                color="warning"
                name="phone"
                margin="dense"
                placeholder="휴대폰 번호를 입력해주세요."
                variant="outlined"
                sx={{ background: 'white' }}
                value={inputValue}
                onChange={onChangeHandler}
              />
              <p>단체 계좌 정보</p>
              <select name="accountBank">
                {bankKind.map((bank) => (
                  <option value={bank}>{bank}</option>
                ))}
              </select>
              <TextField
                color="warning"
                name="accountNumber"
                margin="dense"
                placeholder="계좌 번호를 입력해주세요."
                variant="outlined"
                sx={{ background: 'white' }}
                value={inputValue}
                onChange={onChangeHandler}
              />
              <Button className={styles['auth-btn']} variant="contained" onClick={onClickAccountBtn}>
                계좌 인증하기
              </Button>
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
              <Button className={styles['signup-btn']} variant="contained" onClick={requestSignUp}>
                회원가입
              </Button>
            </div>
          </div>
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
  );
}

export default TeamSignUpContainer;
