/* eslint-disable react/no-array-index-key */
import React, { useEffect, useState } from 'react';
import cn from 'classnames';
import { Tooltip } from '@mui/material';
import styles from './MyBadgesContainer.module.scss';
import SideBarList from '../../components/MyPageSideBar/SideBarList';
import { requestUserProfile } from '../../api/user';
import { useAppSelector } from '../../store/hooks';

type badgeType = {
  achieve: boolean;
  badgeImgPath: string;
  description: string;
  name: string;
};

export function MyPageContainer() {
  const [badges, setBadges] = useState<badgeType[]>([]);
  const userId = useAppSelector((state) => state.userSlice.userId);

  const getRequestUserInfo = async () => {
    try {
      const response = await requestUserProfile(userId);
      console.log(response.data);

      setBadges([...response.data.myBadges]);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getRequestUserInfo();
  }, []);

  useEffect(() => {
    console.log(badges);
  }, [badges]);
  return (
    <div className={styles.bodyContainer}>
      <SideBarList />
      <div className={styles.contentContainer}>
        <div className={styles.contentBox}>
          <div className={styles.badgeBox}>
            <p className={styles.badgeTitle}>획득한 뱃지 현황</p>

            <div className={styles.badgeContentsBox}>
              {badges.map((badge, index) => (
                <Tooltip title={badge.description} placement="top">
                  <div className={styles.badgeStyle}>
                    <img src={badge.badgeImgPath} alt="뱃지" key={index + 1} className={cn(badge.achieve ? styles.achieve : '')} />
                    <p className={styles['badge-description']}>{badge.name}</p>
                  </div>
                </Tooltip>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default MyPageContainer;
