import React from 'react';
import TextField from '@mui/material/TextField';
import styles from './CommentCard.module.scss';
import profileTemp from '../../assets/images/default-profile-img.svg';
import { commentType } from '../../containers/Funding/FundingDetailContainer';
import { displayedAt } from '../../utils/day';

/** 필요 데이터
 *  등록 유저 프로필 사진, 닉네임, 내용, 등록 시간
 */
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
    </div>
  );
}

export default CommentCard;
