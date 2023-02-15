import React from 'react';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Tooltip, { TooltipProps, tooltipClasses } from '@mui/material/Tooltip';
import BeenhereIcon from '@mui/icons-material/Beenhere';
import styles from './DetailArcodian.module.scss';

export function DetailArcodian() {
  return (
    <div className={styles.fundingPlanner}>
      <p className={styles.planTitle}>펀딩 금액에 따른 봉사계획</p>
      <p className={styles.planSubTitle}>마우스를 올려 단계별 계획을 확인하세요!</p>
      <div className={styles.planTag}>
        <BeenhereIcon className={styles.iconTag} sx={{ visibility: 'hidden' }} />
        <BeenhereIcon className={styles.iconTag} />
        <BeenhereIcon className={styles.iconTag} />
        <BeenhereIcon className={styles.iconTag} />
      </div>
      <div className={styles.progressBar}>
        <div className={styles.status} style={{ width: `50%` }} />
      </div>
    </div>
  );
}

export default DetailArcodian;
