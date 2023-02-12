import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Typography from '@mui/material/Typography';
import FAQContainerMemberItem from './FAQContainerMemberItem';
import FAQContainerTeamItem from './FAQContainerTeamItem';
import styles from './FAQContainer.module.scss';
import { useAppSelector } from '../../../store/hooks';
import { FaqInterface } from '../../../types/faq';
import { requestFaqList } from '../../../api/faq';

export default function FAQContainer() {
  const navigate = useNavigate();
  const userType = useAppSelector((state) => state.userSlice.userType);
  const [isTeamMode, setIsTeamMode] = useState<boolean>(false);
  const [FAQList, setFAQList] = useState<FaqInterface[]>([]);

  useEffect(() => {
    requestAllFAQ();
  }, []);

  const memberFAQList = FAQList.filter((faq) => {
    return faq.groupOrPerson === 0;
  });

  const teamFAQList = FAQList.filter((faq) => {
    return faq.groupOrPerson === 1;
  });

  const onClickToggle = () => {
    setIsTeamMode(!isTeamMode);
  };

  const requestAllFAQ = async () => {
    try {
      const response = await requestFaqList(100000);
      console.log('모든 FAQ 요청', response);
      setFAQList(response.data);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.content}>
        <div className={styles.line}>
          <button type="button" onClick={onClickToggle}>
            <p>{!isTeamMode ? '단체' : '개인'} 회원들의 FAQ가 궁금하신가요?</p>
          </button>
          {userType === 'ADMIN' && (
            <button
              type="button"
              onClick={() => {
                navigate('create');
              }}
              className={styles.create}
            >
              FAQ 생성
            </button>
          )}
        </div>
        {!isTeamMode && (
          <div className={styles['user-type-div']}>
            <h1 className={styles.title}>후원자 분들이 많이 문의했어요.</h1>
            {memberFAQList.map((data) => (
              <Accordion key={data.id} sx={{ boxShadow: 'none' }}>
                <AccordionSummary expandIcon={<ExpandMoreIcon />} aria-controls="panel1a-content" id="panel1a-header">
                  <Typography sx={{ display: 'flex', justifyContent: 'space-between', fontSize: '1.125rem', fontFamily: 'NanumSquareRound' }}>
                    <p>{data.title}</p>
                    <div className={styles['item-btn-div']}>
                      <button type="button">수정</button>
                      <button type="button">삭제</button>
                    </div>
                  </Typography>
                </AccordionSummary>
                <AccordionDetails sx={{ backgroundColor: 'rgb(255, 254, 252)', padding: '2rem', boxShadow: '0px 0px 20px rgba(255, 132, 0, 0.04) inset' }}>
                  <Typography sx={{ display: 'flex', fontSize: '1rem', lineHeight: '2rem', fontFamily: 'NanumSquareRound' }}>{data.content}</Typography>
                </AccordionDetails>
              </Accordion>
            ))}
          </div>
        )}
        {isTeamMode && (
          <div className={styles['user-type-div']}>
            <h1 className={styles.title}>봉사 단체에서 많이 문의했어요.</h1>
            {teamFAQList.map((data) => (
              <Accordion key={data.id} sx={{ boxShadow: 'none' }}>
                <AccordionSummary expandIcon={<ExpandMoreIcon />} aria-controls="panel1a-content" id="panel1a-header">
                  <Typography sx={{ fontSize: '1.125rem', fontFamily: 'NanumSquareRound' }}>{data.title}</Typography>
                </AccordionSummary>
                <AccordionDetails sx={{ backgroundColor: 'rgb(255, 254, 252)', padding: '2rem', boxShadow: '0px 0px 20px rgba(255, 132, 0, 0.04) inset' }}>
                  <Typography sx={{ fontSize: '1rem', lineHeight: '2rem', fontFamily: 'NanumSquareRound' }}>{data.content}</Typography>
                </AccordionDetails>
              </Accordion>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}
