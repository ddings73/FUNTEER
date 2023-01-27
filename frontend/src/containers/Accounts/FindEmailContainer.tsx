import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, TextField } from '@mui/material';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './FindEmailContainer.module.scss';

type UserLoginType = {
  email: string;
  phone: string;
};

function FindEmailContainer() {
  const navigate = useNavigate();
  const [onModal, setOnModal] = useState(false);
  const [userInfo, setUserInfo] = useState<UserLoginType>({
    email: '',
    phone: '',
  });

  const onChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    setUserInfo({ ...userInfo, [name]: value });
  };

  const findEmail = async () => {
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
          <p>이메일</p>
          <TextField onChange={onChangeHandler} name="email" margin="normal" id="outlined-basic" placeholder="가입시 등록한 이메일을 입력해주세요." variant="outlined" />
          <p>전화번호</p>
          <TextField
            onChange={onChangeHandler}
            name="phone"
            type="text"
            margin="normal"
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
              <DialogContentText id="alert-dialog-description">회원님의 이메일은 ddd@ddd.com입니다</DialogContentText>
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
