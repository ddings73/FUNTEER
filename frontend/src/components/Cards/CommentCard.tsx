import React from 'react';
import TextField from '@mui/material/TextField';
import styles from './CommentCard.module.scss';
import profileTemp from '../../assets/images/default-profile-img.svg';

export function CommentCard() {
  return (
    <div className={styles.cardContainer}>
      <div className={styles.commentMid}>
        <img className={styles.commentImg} alt="프로필" src={profileTemp} />
        <div className={styles.commentWrapper}>
          <div className={styles.commentName}>가가가가 (abcd***)</div>
          <div className={styles.commentBox}>
            내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용
          </div>
        </div>
      </div>
    </div>
  );
}

export default CommentCard;
