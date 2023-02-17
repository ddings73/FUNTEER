/* eslint-disable */
import React, { useEffect } from 'react';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Tooltip, { TooltipProps, tooltipClasses } from '@mui/material/Tooltip';
import BeenhereIcon from '@mui/icons-material/Beenhere';
import styles from './DetailArcodian.module.scss';
import { ResponseInterface } from '../../containers/Funding/FundingDetailContainer';

export function DetailArcodian(board: ResponseInterface) {
  // 단계별 펀딩 정보
  const levelOneData = () => (
    <p style={{ whiteSpace: 'pre-line' }}>
      {`1단계 금액: ${board.targetMoneyListLevelOne.amount}원
      펀딩 설명: ${board.targetMoneyListLevelOne?.descriptions?.map((data) => data.description)}
      `}
    </p>
  );
  const levelTwoData = () => (
    <p style={{ whiteSpace: 'pre-line' }}>
      {`2단계 금액: ${board.targetMoneyListLevelTwo.amount}원
      펀딩 설명: ${board.targetMoneyListLevelTwo?.descriptions?.map((data) => data.description)}
      `}
    </p>
  );
  const levelThreeData = () => (
    <p style={{ whiteSpace: 'pre-line' }}>
      {`3단계 금액: ${board.targetMoneyListLevelThree.amount}원
      펀딩 설명: ${board.targetMoneyListLevelThree?.descriptions?.map((data) => data.description)}
      `}
    </p>
  );

  return (
    <div className={styles.fundingPlanner}>
      <p className={styles.planTitle}>펀딩 금액에 따른 봉사 진행 상황</p>
      <div className={styles.planTag}>
        <BeenhereIcon className={styles.iconTag} sx={{ visibility: 'hidden' }} />
        <Tooltip title={levelOneData()} placement="top">
          <BeenhereIcon className={styles.iconTag} color={Number(board.currentFundingAmount) >= Number(board.targetMoneyListLevelOne.amount) ? 'warning' : 'disabled'} />
        </Tooltip>
        <Tooltip title={levelTwoData()} placement="top">
          <BeenhereIcon className={styles.iconTag} color={Number(board.currentFundingAmount) >= Number(board.targetMoneyListLevelTwo.amount) ? 'warning' : 'disabled'} />
        </Tooltip>
        <Tooltip title={levelThreeData()} placement="top">
          <BeenhereIcon
            className={styles.iconTag}
            color={Number(board.currentFundingAmount) >= Number(board.targetMoneyListLevelThree.amount) ? 'warning' : 'disabled'}
          />
        </Tooltip>
      </div>
      <div className={styles.progressBar}>
        <div
          className={styles.status}
          style={{
            width:
              Number(board.currentFundingAmount) >= Number(board.targetMoneyListLevelThree.amount)
                ? '100%'
                : Number(board.currentFundingAmount) >= Number(board.targetMoneyListLevelTwo.amount)
                ? '60%'
                : Number(board.currentFundingAmount) >= Number(board.targetMoneyListLevelOne.amount)
                ? '30%'
                : Number(board.currentFundingAmount) > 0
                ? '15%'
                : '0%',
          }}
        />
      </div>
    </div>
  );
}

export default DetailArcodian;
