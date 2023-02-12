import React, { useEffect, useState } from 'react';
import TextField from '@mui/material/TextField';
import DeleteOutlineIcon from '@mui/icons-material/DeleteOutline';
import styles from './CommentCard.module.scss';
import profileTemp from '../../assets/images/default-profile-img.svg';
import { commentType } from '../../containers/Funding/FundingDetailContainer';
import { displayedAt } from '../../utils/day';
import { requestTeamAccountInfo } from '../../api/team';
import { useAppSelector } from '../../store/hooks';
import { requestUserInfo, requestUserProfile } from '../../api/user';
import { userProfileInterface } from '../../types/user';
import { requestDeleteComment } from '../../api/funding';

// 본인이면 삭제
// const [isSameUser, setIsSameUser] = useState(false);

export function CommentCard({ memberNickName, content, memberProfileImg, regDate, commentId }: commentType) {
  const userId = useAppSelector((state) => state.userSlice.userId);
  const [userProfile, setUserProfile] = useState<userProfileInterface>({
    nickname: '',
    profileImgUrl: '',
    money: 0,
    wishCnt: 0,
    followingCnt: 0,
  });
  const getRequestUserInfo = async () => {
    try {
      const response = await requestUserProfile(userId);
      console.log(response.data);
      setUserProfile({ ...response.data });
    } catch (error) {
      console.log(error);
    }
  };

  // 댓글 삭제
  async function handleDeleteBtn() {
    console.log('clicked');
    try {
      await requestDeleteComment(commentId);
      alert('댓글이 삭제되었습니다!');
    } catch (e) {
      console.log(e);
    }
  }

  useEffect(() => {
    getRequestUserInfo();
  }, []);

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
      {userProfile.nickname === memberNickName && (
        <button className={styles.deleteBtn} onClick={handleDeleteBtn} type="button" style={{ cursor: 'pointer', border: 'none', background: 'transparent' }}>
          <DeleteOutlineIcon />
        </button>
      )}
    </div>
  );
}

export default CommentCard;
