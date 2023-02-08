import React from 'react';
import styles from './AdminMainContainer.module.scss';

function AdminMainContainer() {
  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1>FUNTEER 관리자 페이지입니다.</h1>
        <h2>개발팀에 대한 문의사항은 becoding96@gmail.com으로 연락해주세요.</h2>
      </div>
    </div>
  );
}

export default AdminMainContainer;
