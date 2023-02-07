import React from 'react';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Typography from '@mui/material/Typography';
import FAQContainerMemberItem from './FAQContainerMemberItem';
import FAQContainerTeamItem from './FAQContainerTeamItem';
import styles from './FAQContainer.module.scss';

export default function FAQContainer() {
  return (
    <div className={styles.container}>
      <div className={styles['user-type-div']}>
        <h1 className={styles.title}>후원자 분들이 많이 문의했어요.</h1>
        {FAQContainerMemberItem.map((data) => (
          <Accordion key={data.ques} sx={{ boxShadow: 'none' }}>
            <AccordionSummary expandIcon={<ExpandMoreIcon />} aria-controls="panel1a-content" id="panel1a-header">
              <Typography sx={{ fontSize: '1.125rem', fontFamily: 'NanumSquareRound' }}>{data.ques}</Typography>
            </AccordionSummary>
            <AccordionDetails sx={{ backgroundColor: 'rgb(255, 254, 252)', padding: '2rem', boxShadow: '0px 0px 20px rgba(255, 132, 0, 0.04) inset' }}>
              <Typography sx={{ fontSize: '1rem', lineHeight: '2rem', fontFamily: 'NanumSquareRound' }}>{data.ans}</Typography>
            </AccordionDetails>
          </Accordion>
        ))}
      </div>
      <div className={styles['user-type-div']}>
        <h1 className={styles.title}>봉사 단체에서 많이 문의했어요.</h1>
        {FAQContainerTeamItem.map((data) => (
          <Accordion key={data.ques} sx={{ boxShadow: 'none' }}>
            <AccordionSummary expandIcon={<ExpandMoreIcon />} aria-controls="panel1a-content" id="panel1a-header">
              <Typography sx={{ fontSize: '1.125rem', fontFamily: 'NanumSquareRound' }}>{data.ques}</Typography>
            </AccordionSummary>
            <AccordionDetails sx={{ backgroundColor: 'rgb(255, 254, 252)', padding: '2rem', boxShadow: '0px 0px 20px rgba(0, 0, 0, 0.04) inset' }}>
              <Typography sx={{ fontSize: '1rem', lineHeight: '2rem', fontFamily: 'NanumSquareRound' }}>{data.ans}</Typography>
            </AccordionDetails>
          </Accordion>
        ))}
      </div>
    </div>
  );
}
