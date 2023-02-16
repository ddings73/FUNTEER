import React from 'react';
import styles from './InfoCard.module.scss';
import img1 from '../../assets/images/mainPage/fund.webp';
import img2 from '../../assets/images/mainPage/live.webp';
import img3 from '../../assets/images/mainPage/volunteer.webp';

export function infoCard(scrollPosition: { scrollPosition: number }) {
  const scroll = scrollPosition.scrollPosition;

  return (
    <div className={styles.cardContainer}>
      <div className={styles.cardElement}>
        <div className={styles.cardCircle}>
          <img src={img1} className={styles.cardImg} alt="card" style={{ bottom: '-20px' }} />
        </div>
        <div className={styles.cardTypo}>
          <p style={scroll >= 1000 ? { color: 'black' } : { color: 'white' }} className={styles.cardTitle}>
            펀딩을 통한 특별한 봉사활동
          </p>
          <p style={scroll >= 1000 ? { color: 'black' } : { color: 'white' }} className={styles.cardContent}>
            시간과 공간적인 제약을 뛰어넘어,
            <br />
            펀딩 통해 특별한 봉사를 경험하세요.
          </p>
        </div>
      </div>

      <div className={styles.cardElement}>
        <div className={styles.cardCircle}>
          <img src={img2} className={styles.cardImg} alt="card" style={{ right: '-60px' }} />
        </div>
        <div className={styles.cardTypo}>
          <p style={scroll >= 1000 ? { color: 'black' } : { color: 'white' }} className={styles.cardTitle}>
            눈으로 확인하는 라이브 봉사
          </p>
          <p style={scroll >= 1000 ? { color: 'black' } : { color: 'white' }} className={styles.cardContent}>
            봉사활동에 조금 더 신뢰를 드리기 위해
            <br />
            라이브로 참여하고 응원도 할 수 있어요.
          </p>
        </div>
      </div>
      <div className={styles.cardElement}>
        <div className={styles.cardCircle}>
          <img src={img3} className={styles.cardImg} alt="card" />
        </div>
        <div className={styles.cardTypo}>
          <p style={scroll >= 1000 ? { color: 'black' } : { color: 'white' }} className={styles.cardTitle}>
            펀티어에서 함께하는 기부활동
          </p>
          <p style={scroll >= 1000 ? { color: 'black' } : { color: 'white' }} className={styles.cardContent}>
            펀티어에서도 따뜻한 세상을 위해
            <br />
            기부를 모아 사회에 전달하는 기부활동을 해요
          </p>
        </div>
      </div>
    </div>
  );
}
export default infoCard;
