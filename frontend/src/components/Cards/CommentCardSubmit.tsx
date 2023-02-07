import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import { useParams } from 'react-router-dom';
import styles from './CommentCardSubmit.module.scss';
import profileTemp from '../../assets/images/default-profile-img.svg';
// import { postFundingComment } from '../../api/funding';

export function CommentCardSubmit() {
  const { fundIdx } = useParams();

  const [comment, setComment] = useState('');
  const commentOnChange = (e: any) => {
    setComment(e.target.value);
  };
  const onSubmit = (e: any) => {
    e.preventDefault();
    if (comment === '') return;
    setComment('');
  };
  // const fetchData= async() =>{
  //   try{
  //     const response = await postFundingComment(fundIdx)
  //     console.log('res',response);
  //     console.log('data res: ', response.data);
  //   }catch(error){
  //     console.log(error);
  //   }
  // }
  console.log(comment);
  console.log(fundIdx);
  return (
    <div className={styles.cardContainer}>
      <div className={styles.commentHead}>응원 댓글 등록</div>
      <form onSubmit={onSubmit}>
        <div className={styles.commentMid}>
          <img className={styles.commentImg} alt="프로필" src={profileTemp} />
          <TextField className={styles.tesxtField} placeholder="댓글을 입력해주세요" variant="filled" sx={{ width: '80%' }} value={comment} onChange={commentOnChange} />
        </div>
        <div className={styles.BtnGrop}>
          <button className={styles.commentBtn} type="submit">
            댓글 등록
          </button>
        </div>
      </form>
    </div>
  );
}

export default CommentCardSubmit;
