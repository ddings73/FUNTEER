import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import DeleteOutlineIcon from '@mui/icons-material/DeleteOutline';
import styles from './CommentCard.module.scss';
import profileTemp from '../../assets/images/default-profile-img.svg';
import { commentType } from '../../containers/Funding/FundingDetailContainer';
import { displayedAt } from '../../utils/day';

// 본인이면 삭제
// const [isSameUser, setIsSameUser] = useState(false);

export function CommentCard({ memberNickName, content, memberProfileImg, regDate }: commentType) {
  console.log('이미지 경로: ', memberProfileImg);
  return (
    <div className={styles.cardContainer}>
      <div className={styles.commentMid}>
        <img className={styles.commentImg} alt="프로필" src={memberProfileImg} />
        <div className={styles.commentWrapper}>
          <div className={styles.commentName}>
            {memberNickName} <span>{displayedAt(regDate)}</span>
          </div>
          <div className={styles.commentBox}>{content}</div>
        </div>
      </div>
      {/* {isSameUser ? ( */}
      {/* <div className={styles.deleteBtn}> */}
      <DeleteOutlineIcon />
      {/* </div>
      ) : (
        ''
      )} */}
    </div>
  );
}

export default CommentCard;
