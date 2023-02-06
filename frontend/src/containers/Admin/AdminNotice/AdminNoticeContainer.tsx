import { Button } from '@mui/material';
import React from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import styles from './AdminNoticeContainer.module.scss'; // <- css 코드 여기서 작성
import AdminNoticeContainerItem, { AdminNoticeContainerItemType } from './AdminNoticeContainerItem';

function AdminNoticeContainer() {
  const navigate = useNavigate();
  const {pathname} =useLocation()

  /** 여기서 함수, 변수 선언하거나 axios 요청 */
  const onClickCreateBtn = () => {
    navigate(`${pathname}/noticecreate`)
  }

  const onClickDeletebtn = () => {
    // 삭제 요청인데 삭제 할건지 모달 띄워줘 
  } 

  const onClickNoticeHandler = (data: AdminNoticeContainerItemType, e: React.MouseEvent<HTMLButtonElement>) => {
    navigate(`./${data.id}`, { state: { data } });
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
        {AdminNoticeContainerItem.map((data) => (
          <button
          type="button"
          key={data.id}
          className={styles['list-line']}
          onClick={(e) => {
            onClickNoticeHandler(data, e);
          }}
        >
          <li className={styles['mobile-none']}>
            <p>{data.id}</p>
          </li>
          <li>
            <p>{data.title}</p>
          </li>
          <li>
            <p>{data.regDate}</p>
          </li>
          <button type="button" onClick={onClickDeletebtn} className={styles['withdraw-btn']}>
            X
          </button>
        </button>
        ))}
      </div>
    </div>
  );
}

export default AdminNoticeContainer;