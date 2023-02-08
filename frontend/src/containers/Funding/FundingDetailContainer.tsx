import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
import FavoriteIcon from '@mui/icons-material/Favorite';
import { useParams } from 'react-router-dom';
import FundSummary from '../../components/Cards/FundSummary';
import styles from './FundingDetailContainer.module.scss';
import { requestFundingDetail } from '../../api/funding';
import TeamInfo from '../../components/Cards/TeamInfoCard';
import DetailArcodian from '../../components/Cards/DetailArcodian';
import CommentCardSubmit from '../../components/Cards/CommentCardSubmit';
import CommentCard from '../../components/Cards/CommentCard';
import './FundingDetailContainer.scss';

export interface ResponseInterface {
  id: number;
  title: string;
  start: string;
  end: string;
  postDate: string;
  thumbnail: string;
  category: string;
  content: string;
  targetMoneyListLevelThree: targetType;
  currentFundingAmount: string;
  wishCount: number;
  fundingDescription: string;
  comments: commentType[];
  team: teamType;
}
export type commentType = {
  memberNickName: string;
  content: string;
  memberProfileImg: string;
  regDate: string;
};
export type teamType = {
  email: string;
  name: string;
  phone: string;
  profileImgUrl: string;
  // performFileUrl: string;
  // vmsFileUrl: string;
};
type targetType = {
  amount?: string;
  targetMoneyType?: string;
  description?: string;
};

export function FundingDetailContainer() {
  const [commentList, setCommentList] = useState([
    {
      memberNickName: '',
      content: '',
      memberProfileImg: '',
      regDate: '',
    },
  ]);
  const [users, setUsers] = useState(null);
  const { fundIdx } = useParams();
  const [board, setBoard] = useState<ResponseInterface>({
    id: 0,
    title: '',
    start: '',
    end: '',
    postDate: '',
    thumbnail: '',
    category: '',
    content: '',
    fundingDescription: '',
    targetMoneyListLevelThree: {},
    currentFundingAmount: '',
    wishCount: 0,
    comments: [],
    team: {
      email: '',
      name: '',
      phone: '',
      profileImgUrl: '',
    },
  });
  const handleLikeClick = () => {
    console.log('눌림');
    console.log(board.comments);
  };

  // 펀딩 상세 게시물 로드
  const fetchData = async () => {
    try {
      const response = await requestFundingDetail(fundIdx);
      console.log('res: ', response);
      console.log('data res: ', response.data);
      setBoard(response.data);
      setCommentList([...board.comments]);
    } catch (error) {
      console.log(error);
    }
  };

  // 게시물 댓글 로드
  useEffect(() => {
    fetchData();
  }, []);

  // 정렬 체크박스
  const [isChecked, setIsChecked] = useState<boolean>(false);
  function checkHandler(e: React.ChangeEvent<HTMLInputElement> | undefined) {
    setIsChecked(!isChecked);
  }

  console.log('commentList', commentList);
  return (
    <div className={styles.bodyContainer}>
      <div className={styles.banner}>
        <div className={styles.bannerContent}>
          <h1 className={styles.bannerTitle}>{board.title}</h1>
          <div className={styles.bannerButtonGroup}>
            <button className={styles.bannerGrpBtn} type="button">
              보고서 제출
            </button>
            <button className={styles.bannerGrpBtn} type="button">
              라이브 시작
            </button>
            <button className={styles.bannerBtn} type="button">
              펀딩 수정하기
            </button>
          </div>
        </div>
      </div>
      <div className={styles.mainContainer}>
        <div className={styles.mainSum}>
          {' '}
          <FundSummary {...board} />
        </div>
        <div className={styles.mainContent}>
          <div dangerouslySetInnerHTML={{ __html: board.content }} className={styles.mainContentInner} />
        </div>
        <hr style={{ borderTop: '3px solid #bbb', borderRadius: '3px', opacity: '0.5' }} />
        <div className={styles.teamInfoCard}>
          {/* <TeamInfo {...board.team} /> */}
          123
        </div>
        <DetailArcodian />

        <div className={styles.mainFooterAttatch}>
          <p className={styles.attachTitle}>첨부파일</p>
          <p className={styles.attachItem}>인증서.hwp</p>
          <p className={styles.attachItem}>증명서.pdf</p>
        </div>
        <div className={styles.mainFooterLikeWrapper}>
          <button className={styles.mainFooterLikeButton} onClick={handleLikeClick} type="button">
            <FavoriteIcon className={styles.mainFooterLike} />
          </button>
          <div className={styles.Likebox}>
            <div className={styles.mainFooterLikeTest}> 펀딩 찜</div>
            <div className={styles.mainFooterLikeTestSub}> 찜 수 {board.wishCount}</div>
          </div>
        </div>
        <hr style={{ borderTop: '3px solid #bbb', borderRadius: '3px', opacity: '0.5' }} />
        <div className={styles.mainCommentSubmit}>
          <CommentCardSubmit />
        </div>
        <div className={styles.toggles}>
          <p>정렬 기준</p>
          <div className="toggle-button-cover">
            <div className="button-cover">
              <div className="button r" id="button-3">
                <input type="checkbox" className="checkbox" checked={isChecked} onChange={(e) => checkHandler(e)} />
                <div className="knobs" />
                <div className="layer" />
              </div>
            </div>
          </div>
        </div>
        <div className={styles.mainComments}>
          {commentList.reverse().map((comment) => {
            return (
              <CommentCard
                memberNickName={comment.memberNickName}
                content={comment.content}
                memberProfileImg={comment.memberProfileImg}
                regDate={comment.regDate}
                key={comment.regDate}
              />
            );
          })}
        </div>
      </div>
    </div>
  );
}
export default FundingDetailContainer;
