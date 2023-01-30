import React, { useState, useEffect } from 'react';
import FundSummary from '../../components/Cards/FundSummary';
import styles from './FundingDetailContainer.module.scss';

export function FundingDetailContainer() {
  return (
    <div className={styles.bodyContainer}>
      <div className={styles.banner}>
        <div className={styles.bannerContent}>
          <h1 className={styles.bannerTitle}>펀딩 상세 제목</h1>
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
          <FundSummary />
        </div>
        <div className={styles.mainContent}>상세 사진</div>
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
