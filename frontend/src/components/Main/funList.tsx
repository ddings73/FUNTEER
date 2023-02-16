import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import styles from './funList.module.scss';
import CarouselCard from '../../components/Main/CarouselCard';

export function funList(scrollPosition: { scrollPosition: number }) {
  const [fundingLength, setFundingLength] = useState<number>(0);
  const scroll = scrollPosition.scrollPosition;

  return (
    <div className={styles.listContainer}>
      <div className={styles.TypoWrapper}>
        <p className={styles.subTitle}>
          펀딩을 통해
          <br />
          특별한 봉사활동을 경험하세요!
        </p>
        <p className={styles.Title}>펀딩 봉사</p>
      </div>
      <div className={styles.contentWrapper}>
        <div className={styles.menuHeads}>
          <p className={styles.curStatus} style={scroll >= 1000 ? { color: 'black' } : { color: 'white' }}>
            총 <span>{fundingLength}</span>건의 펀딩이 진행 중 입니다.
          </p>
          <Link to="/funding" className={styles.linkTo}>
            전체보기
          </Link>
        </div>
        <CarouselCard setFundingLength={setFundingLength} />
      </div>
    </div>
  );
}

export default funList;
