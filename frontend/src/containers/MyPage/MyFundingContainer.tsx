import React from 'react';
import styles from './MyFundingContainer.module.scss';
import SideBarList from '../../components/MyPageSideBar/SideBarList';
import LongCard from '../../components/Cards/LongCard';

export function MyFundingContainer() {
  return (
    <div className={styles.bodyContainer}>
      <SideBarList />
      <div className={styles.contentContainer}>
        <div className={styles.backBox}>
          <h1 className={styles.title}>나의 펀딩 내역</h1>
          <div className={styles.contentBox}>
            <LongCard />
          </div>
        </div>
      </div>
    </div>
  );
}

export default MyFundingContainer;
