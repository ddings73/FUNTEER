import React from 'react';
import TextField from '@mui/material/TextField';
import styles from './CommentCardSubmit.module.scss';
import profileTemp from '../../assets/images/default-profile-img.svg';

export function CommentCardSubmit() {
  return (
    <div className={styles.cardContainer}>
      <div className={styles.commentHead}>응원 댓글 등록</div>
      <div className={styles.commentMid}>
        <img className={styles.commentImg} alt="프로필" src={profileTemp} />
        <TextField className={styles.tesxtField} placeholder="댓글 입력..." variant="filled" sx={{ width: '80%' }} />
      </div>
      <div className={styles.BtnGrop}>
        <button className={styles.commentBtn} type="button">
          댓글 등록
        </button>
      </div>
    </div>
  );
}

export default CommentCardSubmit;
