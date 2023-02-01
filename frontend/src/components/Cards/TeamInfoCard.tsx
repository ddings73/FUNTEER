import React from 'react';
import HomeIcon from '@mui/icons-material/Home';
import EmailIcon from '@mui/icons-material/Email';
import PhoneInTalkIcon from '@mui/icons-material/PhoneInTalk';
import styles from './TeamInfoCard.module.scss';
import ProfileSvg from '../../assets/images/default-profile-img.svg';

export function TeamInfo() {
  return (
    <div className={styles.bodyContainer}>
      {' '}
      <div className={styles.teamBadge}>
        <img src={ProfileSvg} alt="BdgAlt" className={styles.teamPic} />
        <div className={styles.badgeWrapper}>
          <p className={styles.badgeName}>봉사단체명</p>
          <p className={styles.badgeTeamName}>영알못</p>
        </div>
      </div>
      <div className={styles.buttonGroup}>
        <HomeIcon className={styles.buttonInfo} />
        <EmailIcon className={styles.buttonInfo} />
        <PhoneInTalkIcon className={styles.buttonInfo} />
      </div>
    </div>
  );
}
export default TeamInfo;
