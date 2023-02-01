import React from 'react';
import styles from './MyPageContainer.module.scss';
import SideBarList from '../../components/MyPageSideBar/SideBarList';

export function MyPageContainer() {
  return (
    <div className={styles.bodyContainer}>
      <SideBarList />
      <div className={styles.contentContainer}>
        <div className={styles.contentBox}>중앙</div>
      </div>
    </div>
  );
}

export default MyPageContainer;
