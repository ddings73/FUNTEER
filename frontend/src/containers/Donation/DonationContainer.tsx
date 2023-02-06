import React from 'react';
import { Button } from '@mui/material';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import donationThumbnail from '../../assets/images/donation/donationImg.png';
import styles from './DonationContainer.module.scss';
import { useAppSelector } from '../../store/hooks';
import ListTable from '../../components/Table/ListTable';

function DonationContainer() {
  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <div className={styles.contentsWrapper}>
          <p className={styles.contentTitle}>진행중인 기부 이벤트</p>
          <div className={styles['donation-box']}>
            <div className={styles.left}>
              <img src={donationThumbnail} alt="donationImage" />
            </div>
            <div className={styles.right}>
              <p className={styles.title}>사랑의 연탄 나눔</p>
              <p className={styles.text}>
                어머니, 하나의 별이 못 이름을 이름과, 있습니다. 잠, 이름을 어머니, 오면 밤이 그리고 계십니다. 것은 내 어머님, 잔디가 다 오는 계집애들의 위에 멀듯이,
                듯합니다. 옥 어머니 때 속의 마디씩 언덕 이름자 이름과, 계십니다. 이국 까닭이요, 때 별에도 써 당신은 있습니다. 사람들의 보고, 같이 듯합니다. 된 이국 별
                계집애들의 하나 이웃 있습니다. 이름자 너무나 계절이 내일 있습니다.
              </p>
              <Button className={styles.donButton} type="button">
                기부 참여
              </Button>
            </div>
          </div>
          <div className={styles['amount-box']}>
            <p>총 적립금 1,230,000 원</p>
          </div>
          <div className={styles.finishedTable}>
            <Accordion sx={{ border: '2px solid rgb(175, 175, 175, 0.5)' }}>
              <AccordionSummary expandIcon={<ExpandMoreIcon />} aria-controls="panel1a-content">
                <Typography ml={65} sx={{ fontWeight: '600', fontSize: '20px', opacity: 0.7 }}>
                  이미 종료된 기부 이벤트
                </Typography>
              </AccordionSummary>
              <AccordionDetails>
                <ListTable />
              </AccordionDetails>
            </Accordion>
            <Accordion sx={{ visibility: 'hidden' }}>
              <AccordionSummary expandIcon={<ExpandMoreIcon />} aria-controls="panel2a-content" id="panel2a-header">
                123
              </AccordionSummary>
            </Accordion>
          </div>
        </div>
      </div>
    </div>
  );
}
export default DonationContainer;
