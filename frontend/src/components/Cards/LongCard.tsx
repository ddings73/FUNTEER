import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './LongCard.module.scss';
import thumbNail from '../../assets/images/funding/funding_thumbnail.png';
import { fundListType } from '../../containers/MyPage/MyFundingContainer';

export function LongCard(data: fundListType) {
  const navigate = useNavigate();
  const { amount, payDate, postId, postName } = data;

  const goFundingDetail = (e: React.MouseEvent<HTMLAnchorElement>, postId: number) => {
    e.preventDefault();

    navigate(`/funding/detail/${postId}`);
  };

  return (
    <div className={styles.cardContainer}>
      <div className={styles.blogCard}>
        <img className={styles.cardImage} src={thumbNail} alt="altImg" />
        <div className={styles.cardDetail}>
          <h4 className={styles.fundGroup}>펀딩 단체명</h4>
          <div className={styles.fundInfo}>
            <p className={styles.fundTitle}>
              {/* eslint-disable-next-line */}
              <a href="." onClick={(e) => goFundingDetail(e, data.postId)} className={styles.go}>
                {postName.length > 10 ? postName.substring(0, 9).concat('...') : postName}
              </a>
            </p>
            <p className={styles.fundPeriod}>(2023-01-06 ~ 2023-01-21)</p>
          </div>
          <div className={styles.fundGauge}>
            <p className={styles.fundLevel}>진행중 (1단계 달성)</p>
          </div>
          <div className={styles.barWrapper}>
            <div className={styles.progressBar}>
              <div className={styles.status} />
            </div>
            <p className={styles.funStatusNum}>40%</p>
          </div>
        </div>
        <div className={styles.joinDetail}>
          <p className={styles.fundAmount}>
            후원 금액 <br /> <span>{amount}원</span>
          </p>
          <p className={styles.joinDate}>{payDate}</p>
        </div>
      </div>
    </div>
  );
}

export default LongCard;
