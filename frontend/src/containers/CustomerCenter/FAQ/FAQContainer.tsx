import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Typography from '@mui/material/Typography';
import styles from './FAQContainer.module.scss';
import { useAppSelector } from '../../../store/hooks';
import { FaqInterface } from '../../../types/faq';
import { requestFaqDelete, requestFaqList } from '../../../api/faq';
import { customAlert, s1000 } from '../../../utils/customAlert';

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

  const onClickDelete = async (postId: number) => {
    requestDeleteFAQ(postId);
  };

  const onClickEdit = async (postId: number, groupOrPerson: number, title: string, content: string) => {
    navigate(`${postId}/edit`, {
      state: {
        postId,
        groupOrPerson,
        title,
        content,
      },
    });
  };

  const requestDeleteFAQ = async (postId: number) => {
    try {
      const response = await requestFaqDelete(postId);
      console.log('FAQ 삭제 요청', response);
      customAlert(s1000, '해당 FAQ가 삭제되었습니다.');
      requestAllFAQ();
    } catch (err) {
      console.error(err);
    }
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
                  <Typography sx={{ fontSize: '1.125rem', fontFamily: 'NanumSquare' }}>{data.title}</Typography>
                </AccordionSummary>
                <AccordionDetails sx={{ backgroundColor: 'rgb(255, 254, 252)', padding: '2rem', boxShadow: '0px 0px 20px rgba(255, 132, 0, 0.04) inset' }}>
                  <Typography sx={{ fontSize: '1rem', lineHeight: '2rem', fontFamily: 'NanumSquare' }}>{data.content}</Typography>
                  {userType === 'ADMIN' && (
                    <div className={styles['item-btn-div']}>
                      <button type="button" onClick={() => onClickDelete(data.id as number)}>
                        삭제
                      </button>
                      <button type="button" onClick={() => onClickEdit(data.id as number, data.groupOrPerson, data.title, data.content as string)}>
                        수정
                      </button>
                    </div>
                  )}
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
                  <Typography sx={{ fontSize: '1.125rem', fontFamily: 'NanumSquare' }}>{data.title}</Typography>
                </AccordionSummary>
                <AccordionDetails sx={{ backgroundColor: 'rgb(255, 254, 252)', padding: '2rem', boxShadow: '0px 0px 20px rgba(255, 132, 0, 0.04) inset' }}>
                  <Typography sx={{ fontSize: '1rem', lineHeight: '2rem', fontFamily: 'NanumSquare' }}>{data.content}</Typography>
                  {userType === 'ADMIN' && (
                    <div className={styles['item-btn-div']}>
                      <button type="button" onClick={() => onClickDelete(data.id as number)}>
                        삭제
                      </button>
                      <button type="button" onClick={() => onClickEdit(data.id as number, data.groupOrPerson, data.title, data.content as string)}>
                        수정
                      </button>
                    </div>
                  )}
                </AccordionDetails>
              </Accordion>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}
