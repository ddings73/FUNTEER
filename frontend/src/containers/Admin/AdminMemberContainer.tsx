import React from 'react';
import styles from './AdminMemberContainer.module.scss';

function AdminMemberContainer() {
  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>개인 회원관리</h1>
      </div>
    </div>
  );
}

export default AdminMemberContainer;
