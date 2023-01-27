import React from 'react';
import styles from './adminRoot.module.scss';

function AdminRoot() {
  return (
    <div className={styles.container}>
      <div className={styles.left} />
      <div className={styles.right} />
    </div>
  );
}

export default AdminRoot;
