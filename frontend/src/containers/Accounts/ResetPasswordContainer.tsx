import { Button, TextField } from '@mui/material';
import React from 'react';
import styles from './ResetPasswordContainer.module.scss';

function ResetPasswordContainer() {
  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <div className={styles['login-box']}>
          <p className={styles.title}>비밀번호 재설정</p>

          <p>비밀번호 입력</p>
          <TextField name="password" type="password" margin="normal" id="outlined-basic" placeholder="변경할 비밀번호를 입력해주세요" variant="outlined" />

          <p>비밀번호 확인</p>
          <TextField
            name="passwordCheck"
            type="password"
            margin="normal"
            id="outlined-basic"
            placeholder="변경할 비밀번호를 다시 한번 입력해주세요."
            variant="outlined"
          />

          <Button className={styles['login-button']} variant="contained">
            비밀번호 재설정
          </Button>
        </div>
      </div>
    </div>
  );
}

export default ResetPasswordContainer;
