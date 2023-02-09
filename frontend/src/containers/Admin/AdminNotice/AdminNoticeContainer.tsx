import { Button } from '@mui/material';
import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import styles from './AdminNoticeContainer.module.scss'; // <- css 코드 여기서 작성
import { requestNoticeList } from '../../../api/admin';

export type AdminNoticeContainerItemType = {
  id: number;
  title: string;
  content: string;
  localDate: string;
  files: string;
};

function AdminNoticeContainer() {


  const navigate = useNavigate();
  const {pathname} = useLocation()
  const [noticeList, setNoticeList] = useState<AdminNoticeContainerItemType[]>([]);

  const requestNotice = async () => {
    try {
      const response = await requestNoticeList();
      console.log(response)
      setNoticeList(response.data)
    } catch (error) {
      console.error(error)
    }
  }

  useEffect(() => {
    requestNotice();
  }, [])

  /** 여기서 함수, 변수 선언하거나 axios 요청 */
  const onClickCreateBtn = () => {
    navigate(`${pathname}/noticecreate`)
  }

  const onClickDeletebtn = () => {
    // 삭제 요청인데 삭제 할건지 모달 띄워줘 
  } 

  const onClickNoticeHandler = (data: AdminNoticeContainerItemType, e: React.MouseEvent<HTMLButtonElement>) => {
    navigate(`../../cc/${data.id}`, { state: { data } });
  };

  /** 아래는 TSX 문법, HTML 코드 작성 */
  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>공지사항 관리</h1>
        <div className={styles['search-div']}>
          <Button variant="contained" className={styles.submit} onClick={onClickCreateBtn}>공지사항 작성</Button>
        </div>
        <ul className={styles['title-line']}>
          <li>번호</li>
          <li className={styles.wide}>제목</li>
          <li>작성날짜</li>
          <li> </li>

        </ul>
        {noticeList.map((data) => (
          <ul
          key={data.id}
          className={styles['list-line']}
        >
          <li className={styles['mobile-none']}>
            <p>{data.id}</p>
          </li>
            <button
            type="button"
            style={{ width: '250px',
            display: 'flex',
            justifyContent: 'center',
            border: 'none',
            backgroundColor: 'inherit',
            fontFamily: 'NanumSquareRound',
            fontWeight: 'bold',
            fontSize: '1rem', }}
            onClick={(e) => {
            onClickNoticeHandler(data, e);
          }}>{data.title}</button>
          <li>
            <p>{data.localDate}</p>
          </li>
          <li>
          <button type="button" onClick={onClickDeletebtn} className={styles['withdraw-btn']}>
            X
          </button>
          </li>
        </ul>
     ))}
      </div>
    </div>
  );
}

export default AdminNoticeContainer;