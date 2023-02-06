import { Button } from '@mui/material';
import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import styles from './AdminNoticeContainer.module.scss'; // <- css 코드 여기서 작성
import AdminNoticeContainerItem from './AdminNoticeContainerItem';


function AdminNoticeContainer() {
  const navigate = useNavigate();

  /** 여기서 함수, 변수 선언하거나 axios 요청 */
  const onClickCreateBtn = () => {
    navigate('../noticecreate')
  }

  const onClickDeletebtn = () => {
    // 삭제 요청인데 삭제 할건지 모달 띄워줘 
  } 

  const onClickNoticeDetail = () => {
    // 공지사항
  }

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
          <ul key={data.id} className={styles['list-line']}>
            <li>
              <p>{data.id}</p>
            </li>
          
            <li className={styles.wide}>
              <Link to="/">{data.title}</Link>
            </li>
            <li className={styles.wide}>
              <p>{data.regDate}</p>
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
