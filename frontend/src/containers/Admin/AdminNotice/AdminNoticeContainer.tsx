import React, { useState, useEffect } from 'react';
import Pagination from '@mui/material/Pagination';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { AiOutlineClose } from 'react-icons/ai';
import Swal from 'sweetalert2';
import { requestNoticeList, requestDeleteNotice } from '../../../api/admin';
import styles from './AdminNoticeContainer.module.scss';
import { customAlert, s1000 } from '../../../utils/customAlert';

export type AdminNoticeContainerItemType = {
  id: number;
  title: string;
  content: string;
  localDate: string;
  files: string;
};

function AdminNoticeContainer() {
  const navigate = useNavigate();
  const { pathname } = useLocation();
  const size = 8;
  const [page, setPage] = useState<number>(1);
  const [maxPage, setMaxPage] = useState<number>(0);
  const [noticeList, setNoticeList] = useState<AdminNoticeContainerItemType[]>([]);
  const [onDelete, setOnDelete] = useState<boolean>(false);

  useEffect(() => {
    if (!maxPage) {
      getMaxPage();
    } else {
      requestNotice();
    }
  }, [page, maxPage]);

  /** 페이지 교체 */
  const handleChangePage = (e: React.ChangeEvent<any>, selectedPage: number) => {
    setPage(selectedPage);
  };

  /** 최대 페이지 */
  const getMaxPage = async () => {
    try {
      const response = await requestNoticeList(0, 10000);
      const pageCalc = response.data.length % size ? Math.floor(response.data.length / size) + 1 : response.data.length / size;
      console.log('최대 페이지 요청', response);
      setMaxPage(pageCalc);
    } catch (error) {
      console.error(error);
    }
  };

  /** 리스트 요청 */
  const requestNotice = async () => {
    setNoticeList([]);
    try {
      const response = await requestNoticeList(page - 1, size);
      console.log('공지사항 리스트 요청', response);
      setNoticeList(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  /** 공지사항 작성 버튼 */
  const onClickCreateBtn = () => {
    navigate(`${pathname}/noticecreate`);
  };

  /** 공지사항 삭제 버튼 */
  const onClickDeletebtn = (id: number) => {
    Swal.fire({
      text: '공지사항을 삭제하시겠습니까?',
      showConfirmButton: false,
      showDenyButton: true,
      showCancelButton: true,
      denyButtonText: `삭제`,
      denyButtonColor: 'rgba(211, 79, 4, 1)',
      cancelButtonText: '취소',
    }).then((result) => {
      if (result.isDenied) {
        deleteNotice(id);
      }
    });
  };

  const deleteNotice = async (id: number) => {
    try {
      const response = await requestDeleteNotice(id.toString());
      console.log(response);
      customAlert(s1000, '공지사항이 삭제되었습니다.');
      setPage(1);
      requestNotice();
    } catch (err) {
      console.error(err);
    }
  };

  const onClickNoticeHandler = (data: AdminNoticeContainerItemType, e: React.MouseEvent<HTMLButtonElement>) => {
    navigate(`../../notice/${data.id}`, { state: { data } });
  };

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>공지사항 관리</h1>
        <div className={styles['create-div']}>
          <button type="button" onClick={onClickCreateBtn} className={styles.create}>
            공지사항 작성
          </button>
        </div>
        <ul className={styles['title-line']}>
          <li>번호</li>
          <li className={styles.wide}>제목</li>
          <li>작성날짜</li>
          <li> </li>
        </ul>
        {noticeList.map((data) => (
          <ul key={data.id} className={styles['list-line']}>
            <li className={styles['mobile-none']}>
              <p>{data.id}</p>
            </li>
            <button
              type="button"
              className={styles['title-btn']}
              onClick={(e) => {
                onClickNoticeHandler(data, e);
              }}
            >
              {data.title}
            </button>
            <li>
              <p>{data.localDate}</p>
            </li>
            <li>
              <AiOutlineClose onClick={() => onClickDeletebtn(data.id)} className={styles['withdraw-btn']} />
            </li>
          </ul>
        ))}
        <Pagination sx={{ marginTop: '2rem' }} count={maxPage} page={page} onChange={handleChangePage} />
      </div>
    </div>
  );
}

export default AdminNoticeContainer;
