import React, { useState, useEffect } from 'react';
import axios from 'axios';
import FundSummary from '../../components/Cards/FundSummary';
import styles from './FundingDetailContainer.module.scss';
// import { http } from '../../api/axios';
import { ResponseInterface } from '../../components/Cards/FundSummary';

export function FundingDetailContainer() {
  const [users, setUsers] = useState(null);
  const [board, setBoard] = useState<ResponseInterface>({
    id: 0,
    title: '',
    startDate: '',
    endDate: '',
    postDate: '',
    thumbnail: '',
    postType: '',
    content: '',
    targetMonies: { amount: 0, description: '', targetMoneyType: '' },
    currentFundingAmount: 0,
  });

  useEffect(() => {
    const params = { fundingId: 1 };
    axios.get('https://i8e204.p.ssafy.io/api/v1/funding', { params }).then((response) => {
      setBoard(response.data.fundingListResponses[`${params}`]);
    });
  }, []);

  return (
    <div className={styles.bodyContainer}>
      <div className={styles.banner}>
        <div className={styles.bannerContent}>
          <h1 className={styles.bannerTitle}>{board?.title}여기에 타이틀</h1>
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
      <div className={styles.mainContiainer}>
        <div className={styles.mainSum}>
          {' '}
          <FundSummary {...board} />
        </div>
        <div className={styles.mainContent}>123</div>
        <div className={styles.mainFooter}>푸터</div>
        <div className={styles.mainFooterSum}>▼ 모금액, 이렇게 사용됩니다.</div>
        <div className={styles.mainFooterdiv} />
        <div className={styles.mainFooterAttatch}>첨부공간</div>
        <div className={styles.mainFooterLike}>찜 공간</div>
        <div className={styles.mainFooterdiv} />
        <div className={styles.mainCommentSubmit}>댓글 등록 공간</div>
        <div className={styles.mainFooterdiv} />
        <div className={styles.mainComments}>댓글 공간</div>
      </div>
    </div>
  );
}
export default FundingDetailContainer;
