import React, { useEffect, useState } from 'react';
import { ResponseInterface } from '../../containers/Funding/FundingDetailContainer';
import styles from './FundSummary.module.scss';
import TeamInfo from './TeamInfoCard';

export function FundSummary(board: ResponseInterface) {
  const { title, startDate, endDate, content, targetMoneyListLevelThree, thumbnail, fundingDescription, currentFundingAmount, team } = board;

  const sub = (fundingDescription: string) => {
    if (fundingDescription.length > 50) {
      return fundingDescription.substring(0, 50).concat('...');
    }
    return fundingDescription;
  };

  function calc(tar: string, cur: string) {
    const newTar = Number(tar?.replaceAll(',', ''));
    const newCur = Number(cur?.replaceAll(',', ''));

    return Math.round((newCur / newTar) * 100);
  }

  return (
    <div className={styles.cardContainer}>
      <div className={styles.blogCard}>
        <img className={styles.cardImage} src={thumbnail} alt="altImg" />
        <div className={styles.cardDetail}>
          <div className={styles.fundTitle}>{title}</div>
          <div className={styles.fundSubTitle}>{sub(fundingDescription)}</div>
          <div className={styles.barWrapper}>
            <div className={styles.object}> 목표금액: {targetMoneyListLevelThree.amount}원</div>
            <div className={styles.progressBar}>
              <div className={styles.status} style={{ width: `${calc(targetMoneyListLevelThree.amount as string, currentFundingAmount)}%` }}>
                <p className={styles.statusNum}>{calc(targetMoneyListLevelThree.amount as string, currentFundingAmount)}%</p>
              </div>
            </div>
          </div>
          <div className={styles.teamArea}>
            <TeamInfo {...team} />
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
