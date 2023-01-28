import React from 'react';
import ConfirmModal from '../components/Modal/ConfirmModal';
import styles from './adminRoot.module.scss';

function AdminRoot() {
  return (
    <div className={styles.container}>
      <div className={styles.left} />
      <div className={styles.right} />
      {/* <ConfirmModal isOpen title="제목" content="모달 내용"/> */}
    </div>
  );
}

export default AdminRoot;
