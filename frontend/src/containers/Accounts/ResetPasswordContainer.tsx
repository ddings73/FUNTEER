import { Button, TextField } from '@mui/material';
import React, { useState } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import cn from 'classnames'
import { style } from '@mui/system';
import { ResetPassword } from '../../pages';
import styles from './ResetPasswordContainer.module.scss';
import { requestResetPassword } from '../../api/user';
import { useAppDispatch } from '../../store/hooks';
import { closeModal, openModal } from '../../store/slices/modalSlice';


type ResetPasswordType = {
  name:string | null,
  email:string | null,
  password:string
}
function ResetPasswordContainer() {
  const dispatch = useAppDispatch()
  const navigate = useNavigate();
  const [searchParams,setSearchParams] = useSearchParams();
  const name = searchParams.get('name')
  const email = searchParams.get('email')

  const [userInfo ,setUserInfo] =useState<ResetPasswordType>({
    name,
    email,
    password:""
  })

  const [checkPassword,setCheckPassword] = useState<boolean>(false)

  const onChangePassword = (e:React.ChangeEvent<HTMLInputElement>)=>{
    const {value} =e.target

    setUserInfo({...userInfo,password:value})
  }

  const onChangeCheckPassword = (e:React.ChangeEvent<HTMLInputElement>)=>{
    const {value} =e.target
    if(value === userInfo.password)
    setCheckPassword(true)
    else
    setCheckPassword(false)    
  }

  const successHandleModal = ()=>{
    dispatch(closeModal())
    navigate("/Login",{replace:true})
  }

  const failHandleModal = ()=>{
    dispatch(closeModal())
  }

  

  const onClickResetPassword = async()=>{
    try{
      const response= await requestResetPassword(userInfo.email as string,userInfo.name as string,userInfo.password)
      dispatch(openModal({isOpen:true,title:"비밀번호 재설정 성공",content:"비밀번호 재설정에 성공했습니다. 새로운 비밀번호로 로그인해주세요",handleModal:successHandleModal}))
    }
    catch(error){
      console.error(error)
      dispatch(openModal({isOpen:true,title:"비밀번호 재설정 실패",content:"비밀번호 재설정에 실패했습니다. 관리자에게 문의주세요.",handleModal:failHandleModal}))
    }
  }
  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <div className={styles['login-box']}>
          <p className={styles.title}>비밀번호 재설정</p>

          <p>비밀번호 입력</p>
          <TextField name="password"onChange={onChangePassword} value={userInfo.password} type="password" margin="normal" id="outlined-basic" placeholder="변경할 비밀번호를 입력해주세요" variant="outlined" />

          <p>비밀번호 확인 {!checkPassword && (<span className={cn(!checkPassword?styles.toggle:"")}>비밀번호가 일치하지 않습니다.</span>)}</p>
          <TextField
            type="password"
            margin="normal"
            id="outlined-basic"
            placeholder="변경할 비밀번호를 다시 한번 입력해주세요."
            variant="outlined"
            onChange={onChangeCheckPassword}
          />

          <Button onClick={onClickResetPassword} className={styles['login-button']} variant="contained">
            비밀번호 재설정
          </Button>
        </div>
      </div>
    </div>
  );
}

export default ResetPasswordContainer;
