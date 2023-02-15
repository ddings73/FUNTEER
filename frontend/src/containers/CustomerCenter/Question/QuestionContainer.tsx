import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Button from '@mui/material/Button';
import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import swal from 'sweetalert2';
import styles from './QuestionContainer.module.scss';
import requiredIcon from '../../../assets/images/funding/required.svg';
import { customAlert, w1500 } from '../../../utils/customAlert';
import { useAppSelector } from '../../../store/hooks';
import { requestQNAList } from '../../../api/qna';
import { QNAListInterface } from '../../../types/qna';

export default function QuestionContainer() {
  const navigate = useNavigate();
  /** 유저 ID */
  const userId = useAppSelector((state) => state.userSlice.userId);
  const size = 8;
  const [page, setPage] = useState<number>(1);
  const [maxPage, setMaxPage] = useState<number>(0);
  const [QNAList, setQNAList] = useState<QNAListInterface[]>([]);

  useEffect(() => {
    if (!userId) {
      navigate('/login');
    }
  }, []);

  useEffect(() => {
    requestMaxPage();
  }, []);

  useEffect(() => {
    if (maxPage) {
      requestPageQNA();
    }
  }, [maxPage, page]);

  const onClickCreateQuesBtnHandler = () => {
    navigate('create');
  };

  const onClickQNAItem = (qnaId: number) => {
    navigate(`${qnaId}`);
  };

  /** 페이지 교체 */
  const handleChangePage = (e: React.ChangeEvent<any>, selectedPage: number) => {
    setPage(selectedPage);
  };

  /** 최대 페이지 설정 */
  const requestMaxPage = async () => {
    try {
      const response = await requestQNAList(0, 10000);
      console.log('1:1문의 최대 페이지 설정', response);
      const len = response.data.length;
      setMaxPage(len % size ? Math.floor(len / size) + 1 : len / size);
    } catch (error) {
      console.error(error);
    }
  };

  /** 페이지 QNA 설정 */
  const requestPageQNA = async () => {
    try {
      const response = await requestQNAList(page - 1, size);
      console.log('1:1문의 리스트 요청', response);
      setQNAList(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className={styles.container}>
      {/* 문의 목록 */}
      <div className={styles.content}>
        <div className={styles['ques-btn-div']}>
          <Button variant="outlined" className={styles['ques-btn']} onClick={onClickCreateQuesBtnHandler}>
            문의하기
          </Button>
        </div>

        {QNAList.map((data) => (
          <button
            key={data.id}
            type="button"
            className={styles.line}
            onClick={() => {
              onClickQNAItem(data.id);
            }}
          >
            <p className={styles['item-title']}>{data.title}</p>
            <p className={styles['item-respond']}>{data.respond ? '답변 완료' : '대기중'}</p>
          </button>
        ))}

        <div className={styles['page-bar']}>
          <Stack spacing={2}>
            <Pagination showFirstButton showLastButton count={maxPage} variant="outlined" page={page} onChange={handleChangePage} />
          </Stack>
        </div>
      </div>
    </div>
  );
}
