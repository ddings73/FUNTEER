import React from 'react';
import HomeIcon from '@mui/icons-material/Home';
import EmailIcon from '@mui/icons-material/Email';
import PhoneInTalkIcon from '@mui/icons-material/PhoneInTalk';
import thumbNail from '../../assets/images/funding/funding_thumbnail.png';
import styles from './FundSummary.module.scss';
import ProfileSvg from '../../assets/images/default-profile-img.svg';
import TeamInfo from './TeamInfoCard';

export interface ResponseInterface {
  id: number;
  title: string;
  start: string;
  end: string;
  postDate: string;
  thumbnail: string;
  category: string;
  content: string;
  targetMoneyListLevelThree: Array<targetType>;
  currentFundingAmount: number;
  wishCount: number;
  fundingDescription: string;
}
type targetType = {
  amount?: number;
  targetMoneyType?: string;
  description?: string;
};

export function FundSummary(board: ResponseInterface) {
  // const [description]
  const { title, start, end, content, targetMoneyListLevelThree, thumbnail, fundingDescription } = board;
  // console.log('타겟 머니', typeof targetMoneyListLevelThree[0]?.amount);
  // console.log('타겟 머니 amount', targetMonies[2].amount);

  return (
    <div className={styles.cardContainer}>
      <div className={styles.blogCard}>
        <img className={styles.cardImage} src={thumbnail} alt="altImg" />
        <div className={styles.cardDetail}>
          <div className={styles.fundTitle}>{title}</div>
          <div className={styles.fundSubTitle}>{}</div>
          <div className={styles.barWrapper}>
            <div className={styles.object}> 목표금액: {targetMoneyListLevelThree[0]?.amount}원</div>
            <div className={styles.progressBar}>
              <div className={styles.status} />
              <p className={styles.statusNum}>10%</p>
            </div>
          </div>
          <div className={styles.teamArea}>
            <TeamInfo />
          </div>
          <p className={styles.fundPeriod}>
            모금 기간 {start} ~ {end}
          </p>
        </div>
      </div>
    </div>
  );
}

export default FundSummary;
