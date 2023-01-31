import React, { useMemo } from 'react';
import styled from 'styled-components';
import { useLocation, useNavigate } from 'react-router-dom';
import styles from './FundingListElement.module.scss';
import { FundingElementType } from '../../types/funding';
import { diffDay } from '../../utils/day';
import defaultFundingThumbnail from '../../assets/images/funding/funding_thumbnail.png';

const ProgressBar = styled.div<{ gage: number }>`
  width: 100%;
  height: 5px;
  background-color: rgba(217, 217, 217, 1);

  &::after {
    content: '';
    display: block;
    width: ${(props) => props.gage}%;
    height: 5px;
    background-color: red;
  }
`;

function FundingListElement(funding: FundingElementType) {
  const { id, thumbnail, title, startDate, postType, postDate, fundingDescription, endDate, currentFundingAmount, amount } = funding;
  const { pathname } = useLocation();
  const navigate = useNavigate();

  const enoughMoney = useMemo(() => {
    return Math.floor((currentFundingAmount / amount) * 100);
  }, [currentFundingAmount, amount]);

  const moveFundingDetail = () => {
    navigate(`${pathname}/detail/${id}`);
  };

  return (
    <div className={styles['funding-box']} onClick={moveFundingDetail} aria-hidden="true">
      <img src={thumbnail || defaultFundingThumbnail} alt="???" />
      <p className={styles.title}>{title}</p>
      <p className={styles.content}>{fundingDescription}</p>

      <div className={styles['progress-info-box']}>
        <p className={styles.money}>
          <span>{enoughMoney}%</span>&nbsp;
          <span>{currentFundingAmount}원</span>
        </p>
        <p className={styles.date}>{diffDay(endDate)}일남음</p>
      </div>
      <ProgressBar gage={enoughMoney} />
    </div>
  );
}

export default FundingListElement;
