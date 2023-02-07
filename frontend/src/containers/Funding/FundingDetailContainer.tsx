import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
import FavoriteIcon from '@mui/icons-material/Favorite';
import { useParams } from 'react-router-dom';
import FundSummary from '../../components/Cards/FundSummary';
import styles from './FundingDetailContainer.module.scss';
import { http } from '../../api/axios';
import { ResponseInterface } from '../../components/Cards/FundSummary';
import { requestFundingDetail } from '../../api/funding';
import TeamInfo from '../../components/Cards/TeamInfoCard';
import DetailArcodian from '../../components/Cards/DetailArcodian';
import CommentCardSubmit from '../../components/Cards/CommentCardSubmit';
import CommentCard from '../../components/Cards/CommentCard';

export function FundingDetailContainer() {
  const [users, setUsers] = useState(null);
  const { fundIdx } = useParams();
  // const [likePressed, setLikePressed] = useState(false);
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
    targetMoneyListLevelThree: [],
    currentFundingAmount: 0,
    wishCount: 0,
  });
  // function setLikeCount() {}

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await requestFundingDetail(fundIdx);
        console.log('res: ', response);
        console.log('data res: ', response.data);
        setBoard(response.data);
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, []);

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
        <div className={styles.mainContent}>123</div>
        <div className={styles.teamInfoCard}>
          <TeamInfo />
        </div>
        <DetailArcodian />
        <div className={styles.mainFooterdiv} />
        <div className={styles.mainFooterAttatch}>
          <p className={styles.attachTitle}>첨부파일</p>
          <p className={styles.attachItem}>인증서.hwp</p>
          <p className={styles.attachItem}>증명서.pdf</p>
        </div>
        <div className={styles.mainFooterLikeWrapper}>
          <FavoriteIcon className={styles.mainFooterLike} />
          <div className={styles.Likebox}>
            <div className={styles.mainFooterLikeTest}> 펀딩 찜</div>
            <div className={styles.mainFooterLikeTestSub}> 찜 수 {board.wishCount}</div>
          </div>
        </div>
        <div className={styles.mainFooterdiv} />
        <div className={styles.mainCommentSubmit}>
          <CommentCardSubmit />
        </div>
        <div className={styles.mainFooterdiv} />
        <div className={styles.mainComments}>
          <CommentCard />
          <CommentCard />
          <CommentCard />
        </div>
      </div>
    </div>
  );
}
export default FundingDetailContainer;
