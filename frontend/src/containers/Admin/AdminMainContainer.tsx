import React from 'react';
import styles from './AdminMainContainer.module.scss';

function AdminMainContainer() {
  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1>관리자 메인페이지</h1>
      </div>
    </div>
  );
}

export default AdminMainContainer;
