import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';
import { requestNoticeList } from '../../../api/admin';
import styles from './NoticeContainer.module.scss';

export type NoticeContainerItemType = {
  id: number;
  title: string;
  localDate: string;
};

function NoticeContainer() {
  const navigate = useNavigate();
  const size = 10;
  /** 페이지 */
  const [page, setPage] = useState<number>(1);
  /** 최대 페이지 */
  const [maxPage, setMaxPage] = useState<number>(0);
  /** 현재 페이지 공지사항 리스트 */
  const [noticeList, setNoticeList] = useState<NoticeContainerItemType[]>([]);

  useEffect(() => {
    if (!maxPage) {
      requestMaxPage();
    }
    requestNotice();
  }, [maxPage, page]);

  /** 페이지 교체 */
  const handleChangePage = (e: React.ChangeEvent<any>, selectedPage: number) => {
    setPage(selectedPage);
  };

  /** 공지사항 상세 페이지로 */
  const onClickNoticeHandler = (data: NoticeContainerItemType) => {
    navigate(`./${data.id}`, { state: { data } });
  };

  /** 최대 페이지 설정용 요청 */
  const requestMaxPage = async () => {
    try {
      const response = await requestNoticeList(0, 10000);
      console.log('공지사항 최대 페이지 설정', response);
      const len = response.data.length;
      setMaxPage(len % size ? Math.floor(len / size) + 1 : len / size);
    } catch (error) {
      console.error(error);
    }
  };

  /** 페이지 공지사항 리스트 요청 */
  const requestNotice = async () => {
    try {
      const response = await requestNoticeList(page - 1, size);
      console.log('페이지 공지사항 리스트 요청', response);
      setNoticeList(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className={styles.container}>
      <ul className={styles['title-line']}>
        <li className={styles['mobile-none']}>번호</li>
        <li>제목</li>
        <li>작성일</li>
      </ul>
      {noticeList.map((data) => (
        <button
          type="button"
          key={data.id}
          className={styles['list-line']}
          onClick={() => {
            onClickNoticeHandler(data);
          }}
        >
          <li className={styles['mobile-none']}>
            <p>{data.id}</p>
          </li>
          <li>
            <p>{data.title}</p>
          </li>
          <li>
            <p>{data.localDate}</p>
          </li>
        </button>
      ))}
      <div className={styles['page-bar']}>
        <Stack spacing={2}>
          <Pagination showFirstButton showLastButton count={maxPage} variant="outlined" page={page} onChange={handleChangePage} />
        </Stack>
      </div>
    </div>
  );
}

export default NoticeContainer;
