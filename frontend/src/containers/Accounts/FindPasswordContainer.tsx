/* eslint-disable prefer-const */
import { Button, TextField } from '@mui/material';
import React, { useEffect, useRef, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import cn from 'classnames'
import CircularProgress from '@mui/material/CircularProgress';
import { useInterval } from 'usehooks-ts';
import { secondsToMinutes, secondsToSeconds } from '../../utils/timer';
import styles from './FindPasswordContainer.module.scss';
import { requestCheckEmailAuthCode, requestSendEmailAuthCode } from '../../api/user';
import { useAppDispatch } from '../../store/hooks';
import { closeModal, openModal } from '../../store/slices/modalSlice';

type UserLoginType = {
  name: string;
  email: string;
  code:string
};





function FindPasswordContainer() {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  const [userInfo, setUserInfo] = useState<UserLoginType>({
    name: '',
    email: '',
    code:""
  });
  // 이메일 인증 버튼에 표시되는 텍스트
  const [buttonText, setButtonText] = useState<string>('인증코드 보내기');


  // 이메일 인증코드를 성공적으로 보냈는지 체크
  const [checkSendEmailCode ,setCheckSendEmailCode] = useState<boolean>(false);

  // 이메일 체크가 완료되었는지 체크해주는 상태
  const [checkEmailAuth, setCheckEmailAuth] = useState<boolean>(false);


  const [startTimer,setStartTimer] =useState<boolean>(false)
  // 인증코드 시간초과 체크
  const [timeoutCheck,setTimeoutCheck] = useState<boolean>(false)
  // 로 그인 정보 입력

  // 이메일 인증 코드 보내는 동안 돌아가는 스피너 
  const [codeSendLoading,setCodeSendLoading] =useState<boolean>(false)

  const [time,setTime] =useState<number>(180)

  //  이름, 이메일 handler
  const onChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    setUserInfo({...userInfo,[name]:value})

  };

  const handleModal = ()=>{
    setCodeSendLoading(false)
    dispatch(closeModal())
  }
  // 이메일 인증 시간 카운딩
  const handleClickAuthEmail = async() => {
    try{
      setCodeSendLoading(true)
      const response = await requestSendEmailAuthCode(userInfo.email)
      setCodeSendLoading(false)
      setCheckSendEmailCode(true)

      handleCodeCheckTimer()
    }
    catch(error){
      dispatch(openModal({isOpen:true,title:"인증코드 전송 실패", content:"회원 정보가 존재하지 않습니다.",handleModal}))
      console.error(error)
    }

  };

  const timer = () => {
    let minute;
    let second;
    minute = Math.floor(secondsToMinutes(time));
    second = secondsToSeconds(time);

    setButtonText(`${minute} 분 ${second}초`);
    setTime(time-1);

    if (time < 0) {
      setButtonText('입력 시간 초과');
      setTimeoutCheck(true)
    }
  };

  const handleCodeCheckTimer = ()=>{
    setStartTimer(true)
  }

  const onClickCodeCheck = async()=>{
    try{
      const response = await requestCheckEmailAuthCode(userInfo.code,userInfo.email)
      console.log(response)
      setCheckEmailAuth(true);
      dispatch(openModal({isOpen:true,title:"인증 성공",content:"인증에 성공했습니다. 비밀번호 재설정을 진행해주세요.",handleModal}))
    }
    catch(error){
      dispatch(openModal({isOpen:true,title:"인증 실패",content:"인증코드가 일치하지 않습니다",handleModal}))
      console.error(error)
    }
  }



  

  useInterval(
    ()=>{
      timer()
  },
  checkSendEmailCode && !checkEmailAuth ? 1000:null,
  )

  useEffect(()=>{
    if(checkEmailAuth)
    setButtonText("인증이 완료되었습니다.")
  },[checkEmailAuth])


  useEffect(()=>{
    if(timeoutCheck){
      setTimeoutCheck(false)
      setCheckSendEmailCode(false)
      setButtonText("인증코드 보내기")

    }
  },[timeoutCheck])



  const handleResetPassword = () => {
    if(!checkEmailAuth){
      dispatch(openModal({isOpen:true,title:"인증 실패",content:"이메일 인증이 완료되지 않았습니다.",handleModal}))
    }
    else{
      navigate(`/resetPassword?name=${userInfo.name}&email=${userInfo.email}`,{replace:true});
    }
  };
  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <div className={styles['login-box']}>
          <p className={styles.title}>비밀번호 찾기</p>

          <p>이름</p>
          <TextField onChange={onChangeHandler} name="name" margin="normal" id="outlined-basic" placeholder="이름을 입력해주세요." variant="outlined" />

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
              {codeSendLoading ? ( <CircularProgress color='inherit' />):buttonText}
            </Button>
          </div>

          <div className={cn(styles['auth-check-box'],styles.hidden,checkSendEmailCode? styles.toggle:"")}>
          <TextField
          sx={{width:'60%'}}
            onChange={onChangeHandler}
            name="code"
            type="text"
            id="outlined-basic"
            placeholder="인증코드를 입력해주세요."
            variant="outlined"
         
          />
           <Button className={styles['auth-check-button']} variant="contained" onClick={onClickCodeCheck}>
            인증하기
            </Button>
          </div>


          <Button onClick={handleResetPassword} className={styles['login-button']} variant="contained">
          비밀번호 재설정하기
          </Button>
        </div>
      </div>
    </div>
  );
}

export default FindPasswordContainer;
