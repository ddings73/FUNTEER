import React from 'react';
import HomeIcon from '@mui/icons-material/Home';
import EmailIcon from '@mui/icons-material/Email';
import PhoneInTalkIcon from '@mui/icons-material/PhoneInTalk';
import { Link } from 'react-router-dom';
import styles from './TeamInfoCard.module.scss';
import ProfileSvg from '../../assets/images/default-profile-img.svg';
import { teamType } from '../../containers/Funding/FundingDetailContainer';

export function TeamInfo(team: teamType) {
  const { name, profileImgUrl, email, phone, id } = team;
  const handleCopyClipBoard = async (text: string, div: string) => {
    try {
      await navigator.clipboard.writeText(text);
      alert(`${div} 복사했음 굿굿.`);
    } catch (e) {
      alert('복사에 실패하였습니다');
    }
  };

  return (
    <div className={styles.bodyContainer}>
      {' '}
      <div className={styles.teamBadge}>
        <img src={profileImgUrl} alt="BdgAlt" className={styles.teamPic} />
        <div className={styles.badgeWrapper}>
          <p className={styles.badgeName}>봉사단체명</p>
          <p className={styles.badgeTeamName}>{name}</p>
        </div>
      </div>
      <div className={styles.buttonGroup}>
        <button className={styles.contact} type="button" style={{ margin: '0 5px', cursor: 'pointer', border: 'none', background: 'transparent' }}>
          <Link to={`/team/${id}`}>
            <HomeIcon className={styles.buttonInfo} />
          </Link>
        </button>
        <button
          className={styles.contact}
          type="button"
          style={{ margin: '0 5px', cursor: 'pointer', border: 'none', background: 'transparent' }}
          onClick={() => handleCopyClipBoard(email, '이메일')}
        >
          <EmailIcon className={styles.buttonInfo} />
        </button>
        <button
          className={styles.contact}
          type="button"
          style={{ margin: '0 5px', cursor: 'pointer', border: 'none', background: 'transparent' }}
          onClick={() => handleCopyClipBoard(phone, '전화번호')}
        >
          <PhoneInTalkIcon className={styles.buttonInfo} />
        </button>
      </div>
    </div>
  );
}
export default TeamInfo;
