import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import { useParams } from 'react-router-dom';
import SendIcon from '@mui/icons-material/Send';
import styles from './CommentCardSubmit.module.scss';
import profileTemp from '../../assets/images/default-profile-img.svg';
import { postFundingComment } from '../../api/funding';

export function CommentCardSubmit() {
  const { fundIdx } = useParams();

  const [comment, setComment] = useState('');
  const commentOnChange = (e: any) => {
    setComment(e.target.value);
  };
  const fetchData = async () => {
    try {
      const response = await postFundingComment(comment, fundIdx);
      console.log('댓글res', response);
    } catch (error) {
      console.log(error);
    }
  };
  const onSubmit = (e: any) => {
    e.preventDefault();
    fetchData();
    alert('댓글 등록 완료!');
    setComment('');
  };
  const handleKeyPress = (e: any) => {
    if (e.key === 'Enter') {
      if (!comment) {
        e.preventDefault();
      } else {
        e.preventDefault();
        fetchData();
        alert('댓글 등록 완료!');
        setComment('');
      }
    }
  };
  return (
    <div className={styles.cardContainer}>
      <div className={styles.commentHead}>응원 댓글 등록</div>
      <form onKeyUp={handleKeyPress} onSubmit={onSubmit} style={{ width: '80%', marginTop: '20px', display: 'flex' }}>
        <div className={styles.commentMid}>
          <img className={styles.commentImg} alt="프로필" src={profileTemp} />
          <TextField
            className={styles.tesxtField}
            placeholder="댓글을 입력해주세요"
            variant="filled"
            sx={{ width: '80%' }}
            value={comment}
            onChange={commentOnChange}
            size="small"
            color="warning"
          />
        </div>
        <div className={styles.BtnGrop}>
          <button className={styles.commentBtn} type="submit">
            <SendIcon />
            <p>댓글 등록</p>
          </button>
        </div>
      </form>
    </div>
  );
}

export default CommentCardSubmit;
