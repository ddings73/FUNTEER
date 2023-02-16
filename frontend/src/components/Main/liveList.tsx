import React from 'react';
import styles from './liveList.module.scss';
import CarouselCard from './CarouselCard';

export function liveList() {
  return (
    <div className={styles.listContainer}>
      <div className={styles.TypoWrapper}>
        <p className={styles.subTitle}>
          조금 더 신뢰가는
          <br />
          나의 봉사 지원을 위해
        </p>
        <p className={styles.Title}>봉사 라이브</p>
      </div>
      <div className={styles.contentWrapper}>
        <div className={styles.filterDrop}> 달성률 ▼</div>
        <div className={styles.menuHeads}>
          <p className={styles.linkTo}>전체보기</p>
          <p className={styles.curStatus}>
            현재 <span>10</span>개의 라이브가 진행중이에요.
          </p>
        </div>
        {/* <CarouselCard /> */}
      </div>
    </div>
  );
}

export default liveList;
