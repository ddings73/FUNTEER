import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './AdminFaqContainer.module.scss'; // <- css 코드 여기서 작성
import AdminFaqContainerItem from './AdminFaqContainerItem';

function AdminFaqContainer() {
  const navigate=useNavigate();

  const onClickFaqItemHandler = () => {
    console.log('도네이션 관리 상세 페이지 이동');
  };

  const onClickFaqRegister=()=>{
    console.log("작성페이지로");
    // admin/faq/creat
    navigate('create');
  }
  return (
    <div className={styles.container}>
    <div className={styles.contents}>
      <h1 className={styles.title}>faq 관리</h1>
      <button type="button" onClick={onClickFaqRegister} style={{margin:'0rem 0rem 1rem auto'}}>faq 작성</button>

      <ul className={styles['title-line']}>
        <li>번호</li>
        <li className={styles['title-col']}>제목</li>
        <li>등록일</li>
       
      </ul>
      {AdminFaqContainerItem.map((data) => (
        <div className={styles['list-line']}>
          <li>
            <p>{data.id}</p>
          </li>
          <button type="button" className={styles['title-col-btn']} onClick={onClickFaqItemHandler}>
            <li>
              <p>{data.title}</p>
            </li>
          </button>
          <li>
            <p>{data.postDate}</p>
          </li>
        </div>
      ))}
    </div>
  </div>
  );
}

export default AdminFaqContainer;
