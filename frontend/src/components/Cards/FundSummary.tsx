import React from 'react';
import HomeIcon from '@mui/icons-material/Home';
import EmailIcon from '@mui/icons-material/Email';
import PhoneInTalkIcon from '@mui/icons-material/PhoneInTalk';
import thumbNail from '../../assets/images/funding/funding_thumbnail.png';
import styles from './FundSummary.module.scss';
import ProfileSvg from '../../assets/images/default-profile-img.svg';

export function FundSummary() {
  return (
    <div className={styles.cardContainer}>
      <div className={styles.blogCard}>
        <img className={styles.cardImage} src={thumbNail} alt="altImg" />
        <div className={styles.cardDetail}>
          <div className={styles.fundTitle}>펀딩 제목</div>
          <div className={styles.fundSubTitle}>
            지친 사람들의 쉴 곳, 동식물들의 생명이 피어나는 곳 등 다양한 모습으로 우리 곁에 있으며 사계절의 변화를 보여주는 곳 바로 &#39;지역 하천&#39;입니다.
          </div>
          <div className={styles.barWrapper}>
            <div className={styles.object}> 목표금액: 1,000,000원</div>
            <div className={styles.progressBar}>
              <div className={styles.status} />
            </div>
          </div>
          <div className={styles.teamArea}>
            <div className={styles.teamBadge}>
              <img src={ProfileSvg} alt="BdgAlt" className={styles.teamPic} />
              <div className={styles.badgeWrapper}>
                <p className={styles.badgeName}>봉사단체명</p>
                <p className={styles.badgeTeamName}>영알못</p>
              </div>
            </div>
            <div className={styles.buttonGroup}>
              <HomeIcon className={styles.buttonInfo} />
              <EmailIcon className={styles.buttonInfo} />
              <PhoneInTalkIcon className={styles.buttonInfo} />
            </div>
          </div>
          <p className={styles.fundPeriod}>모금 기간 2023-01-06 ~ 2023-01-21</p>
        </div>
      </div>
    </div>
  );
}

export default FundSummary;
