import React from 'react';
import { Link } from 'react-router-dom';
import { useAppSelector } from '../../store/hooks';
import styles from './MainPageContainer.module.scss';

export function MainPageContainer() {
  console.log(useAppSelector((state) => state.userSlice.userType));

  return (
    <div className={styles.container}>
      <div className={styles.bannerContainer}>
        <div className={styles.typoBox}>
          <p className={styles.logoTypo}>
            당신의 착한 마음을 <br /> <span className={styles.logoStrong}>FUNTEER</span>가 응원합니다{' '}
          </p>
          <p className={styles.subLogoTypo}>펀딩을 통해 접하는 새로운 봉사</p>
          <button className={styles.serviceBtn} type="button">
            서비스 상세보기
          </button>
          <p>
            <Link to="/funding/create" style={{ fontSize: '3rem', color: 'white' }}>
              펀딩 페이지
            </Link>
          </p>
          <p>
            <Link to="/charge" style={{ fontSize: '3rem', color: 'white' }}>
              충전 페이지
            </Link>
          </p>
          <p>
            <Link to="/admin" style={{ fontSize: '3rem', color: 'white' }}>
              관리자 페이지
            </Link>
          </p>
          <p>
            <Link to="/team/88" style={{ fontSize: '3rem', color: 'white' }}>
              단체 프로필
            </Link>
          </p>
        </div>
        <div className={styles.bannerImg}>배너 이미지 공간</div>
      </div>
    </div>
  );
}

export default MainPageContainer;
