import React from 'react';
import styles from './MyPageContainer.module.scss';
import SideBarList from '../../components/MyPageSideBar/SideBarList';

export function MyPageContainer() {
  return (
    <div className={styles.bodyContainer}>
      <SideBarList />
    </div>
  );
}

export default MyPageContainer;
