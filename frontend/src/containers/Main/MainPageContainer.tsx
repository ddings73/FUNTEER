import React from 'react';
import styles from './MainPageContainer.module.scss';

export function MainPageContainer() {
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
        </div>
        <div className={styles.bannerImg}>배너 이미지 공간</div>
      </div>
    </div>
  );
}

export default MainPageContainer;
