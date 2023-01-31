import React from 'react';
import HomeIcon from '@mui/icons-material/Home';
import EmailIcon from '@mui/icons-material/Email';
import PhoneInTalkIcon from '@mui/icons-material/PhoneInTalk';
import thumbNail from '../../assets/images/funding/funding_thumbnail.png';
import styles from './FundSummary.module.scss';
import ProfileSvg from '../../assets/images/default-profile-img.svg';

export interface ResponseInterface {
  id: number;
  title: string;
  startDate: string;
  endDate: string;
  postDate: string;
  thumbnail: string;
  postType: string;
  content: string;
  targetMonies: targetType;
  currentFundingAmount: number;
}
type targetType = {
  amount: number;
  description: string;
  targetMoneyType: string;
};

export function FundSummary(board: ResponseInterface) {
  const { title, startDate, endDate, content, targetMonies } = board;
  console.log('title', title);
  console.log('amount', targetMonies);
  console.log('amount2', targetMonies.amount);

  return (
    <div className={styles.cardContainer}>
      <div className={styles.blogCard}>
        <img className={styles.cardImage} src={thumbNail} alt="altImg" />
        <div className={styles.cardDetail}>
          <div className={styles.fundTitle}>{title}</div>
          <div className={styles.fundSubTitle}>{content}</div>
          <div className={styles.barWrapper}>
            <div className={styles.object}> 목표금액: {targetMonies.amount}원</div>
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
          <p className={styles.fundPeriod}>
            모금 기간 {startDate} ~ {endDate}
          </p>
        </div>
      </div>
    </div>
  );
}

export default FundSummary;
