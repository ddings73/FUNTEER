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
          <div className={styles.contentBox}>
            <LongCard />
          </div>
        </div>
      </div>
    </div>
  );
}

export default MyFundingContainer;
