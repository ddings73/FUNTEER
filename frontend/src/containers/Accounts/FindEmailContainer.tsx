import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, TextField } from '@mui/material';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { requestFindEmail } from '../../api/user';
import styles from './FindEmailContainer.module.scss';

type UserLoginType = {
  name: string;
  phone: string;
};

function FindEmailContainer() {
  const navigate = useNavigate();
  const [onModal, setOnModal] = useState(false);
  const [userInfo, setUserInfo] = useState<UserLoginType>({
    name: '',
    phone: '',
  });
  const [email,setEmail] = useState<string>("")

  const onChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    if(name ==='phone'){
     const newPhone = value.replace(/[^0-9]/g, '') // 숫자를 제외한 모든 문자 제거
      .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);

      setUserInfo({...userInfo,phone:newPhone})
    }
    else 
       setUserInfo({...userInfo,name:value})

  };

  const findEmail = async () => {
    try{
      const response = await requestFindEmail(userInfo.name,userInfo.phone)
      console.log(response)

      setEmail(response.data.email)
    }
    catch(error){
      console.log(error)
    }
    setOnModal(!onModal);
  };

  const handleClose = () => {
    setOnModal(false);
  };
  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <div className={styles['login-box']}>
          <p className={styles.title}>이메일 찾기</p>
          <p>이름</p>
          <TextField onChange={onChangeHandler} value={userInfo.name} name="name" margin="normal" id="outlined-basic" placeholder="가입시 입력한 회원님의 이름을 입력해주세요." variant="outlined" />
          <p>전화번호</p>
          <TextField
            onChange={onChangeHandler}
            name="phone"
            type="text"
            margin="normal"
            value={userInfo.phone}
            id="outlined-basic"
            placeholder="가입시 등록한 전화번호를 입력해주세요."
            variant="outlined"
          />
          <Button onClick={findEmail} className={styles['login-button']} variant="contained">
            이메일 찾기
          </Button>

          <Dialog open={onModal} onClose={handleClose} aria-labelledby="alert-dialog-title" aria-describedby="alert-dialog-description">
            <DialogTitle id="alert-dialog-title">이메일 찾기</DialogTitle>
            <DialogContent>
              <DialogContentText id="alert-dialog-description">회원님의 이메일은 {email}입니다</DialogContentText>
            </DialogContent>
            <DialogActions>
              <Button onClick={handleClose}>닫기</Button>
              <Button
                onClick={() => {
                  navigate('/login');
                }}
                autoFocus
              >
                로그인하기
              </Button>
            </DialogActions>
          </Dialog>
        </div>
      </div>
    </div>
  );
}

export default FindEmailContainer;
