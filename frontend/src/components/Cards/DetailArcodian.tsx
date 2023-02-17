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
  return (
    <div className={styles.fundingPlanner}>
      <p className={styles.planTitle}>펀딩 금액에 따른 봉사 진행 상황</p>
      <div className={styles.planTag}>
        <BeenhereIcon className={styles.iconTag} sx={{ visibility: 'hidden' }} />
        <BeenhereIcon className={styles.iconTag} color={Number(board.currentFundingAmount) >= Number(board.targetMoneyListLevelOne.amount) ? 'warning' : 'disabled'} />
        <BeenhereIcon className={styles.iconTag} color={Number(board.currentFundingAmount) >= Number(board.targetMoneyListLevelTwo.amount) ? 'warning' : 'disabled'} />
        <BeenhereIcon className={styles.iconTag} color={Number(board.currentFundingAmount) >= Number(board.targetMoneyListLevelThree.amount) ? 'warning' : 'disabled'} />
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
