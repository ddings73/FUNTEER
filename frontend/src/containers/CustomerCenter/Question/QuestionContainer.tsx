import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Button from '@mui/material/Button';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import swal from 'sweetalert2';
import QuestionContainerItem from './QuestionContainerItem';
import styles from './QuestionContainer.module.scss';
import requiredIcon from '../../../assets/images/funding/required.svg';
import { customAlert, w1500 } from '../../../utils/customAlert';
import { useAppSelector } from '../../../store/hooks';

export default function QuestionContainer() {
  const navigate = useNavigate();
  /** 유저 ID */
  const userId = useAppSelector((state) => state.userSlice.userId);
  /** 유저 Type */
  const userType = useAppSelector((state) => state.userSlice.userType);

  useEffect(() => {
    if (!userId) {
      navigate('/login');
    }
  }, []);

  const [createMode, setCreateMode] = useState<boolean>(false);
  const [questionCreateInfo, setQuestionCreateInfo] = useState({
    title: '',
    content: '',
  });

  const onClickCreateQuesBtnHandler = () => {
    setCreateMode(true);
  };

  const onClickCancelBtnHandler = () => {
    setCreateMode(false);
  };

  const onChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    setQuestionCreateInfo({ ...questionCreateInfo, [name]: value });
  };

  const onClickPostBtnHandler = () => {
    if (!questionCreateInfo.title || !questionCreateInfo.content) {
      customAlert(w1500, '문의 정보를 입력해주세요.');
      return;
    }
    console.log('문의 등록 요청');
    setCreateMode(false);
  };

  return (
    <div className={styles.container}>
      {/* 문의 목록 */}
      {!createMode && (
        <div className={styles['ques-board']}>
          <div className={styles['ques-btn-div']}>
            <Button variant="outlined" sx={{ fontFamily: 'NanumSquareRound' }} className={styles['ques-btn']} onClick={onClickCreateQuesBtnHandler}>
              문의하기
            </Button>
          </div>
          <div>
            {QuestionContainerItem.map((data) => (
              <Accordion sx={{ boxShadow: 'none' }}>
                <AccordionSummary expandIcon={<ExpandMoreIcon />} aria-controls="panel1a-content" id="panel1a-header">
                  <div>
                    <Typography sx={{ fontSize: '1.125rem', fontFamily: 'NanumSquareRound' }}>{data.content}</Typography>
                    <p className={styles.state}>{data.state}</p>
                  </div>
                </AccordionSummary>
                {data.ans && (
                  <AccordionDetails sx={{ backgroundColor: 'rgb(255, 254, 253)', padding: '2rem', boxShadow: '0px 0px 20px rgba(255, 132, 0, 0.02) inset' }}>
                    <Typography sx={{ fontSize: '1rem', lineHeight: '2rem', fontFamily: 'NanumSquareRound' }}>{data.ans}</Typography>
                  </AccordionDetails>
                )}
              </Accordion>
            ))}
          </div>
        </div>
      )}
      {/* 문의 생성 */}
      {createMode && (
        <div className={styles['ques-create']}>
          <div className={styles['title-label']}>
            <p>제목</p>
            <img src={requiredIcon} alt="required icon" />
          </div>
          <TextField name="title" id="outlined-basic" variant="outlined" color="warning" className={styles['title-input']} onChange={onChangeHandler} />
          <div className={styles['content-label']}>
            <p>내용</p>
            <img src={requiredIcon} alt="required icon" />
          </div>
          <TextField name="content" id="outlined-multiline-static" multiline rows={10} color="warning" className={styles['content-input']} onChange={onChangeHandler} />
          <div className={styles['submit-btn-div']}>
            <Button variant="contained" onClick={onClickCancelBtnHandler}>
              취소
            </Button>
            <Button variant="contained" onClick={onClickPostBtnHandler}>
              등록
            </Button>
          </div>
        </div>
      )}
    </div>
  );
}
