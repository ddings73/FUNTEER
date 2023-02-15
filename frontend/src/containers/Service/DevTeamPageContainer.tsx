import React from 'react';
import GitHubIcon from '@mui/icons-material/GitHub';
import styles from './DevTeamPageContainer.module.scss';
import f1 from '../../assets/images/teamProfile/김진호.jpg';
import f2 from '../../assets/images/teamProfile/백준봉.jpg';
import f3 from '../../assets/images/teamProfile/김승섭.jpg';
import b1 from '../../assets/images/teamProfile/김보경.jpg';
import b2 from '../../assets/images/teamProfile/안명수.jpg';
import b3 from '../../assets/images/teamProfile/김송빈.jpg';

type memberType = { name: string; dev: string; memo: string; profile: string };
const members: memberType[] = [
  {
    name: '김진호',
    dev: 'FRONT',
    memo: 'bangbongbim',
    profile: f1,
  },
  {
    name: '백준봉',
    dev: 'FRONT',
    memo: 'becoding96',
    profile: f2,
  },
  {
    name: '김승섭',
    dev: 'FRONT',
    memo: 'sub9707',
    profile: f3,
  },
  {
    name: '김보경',
    dev: 'BACK',
    memo: 'bbookng',
    profile: b1,
  },
  {
    name: '안명수',
    dev: 'BACK',
    memo: 'ddings73',
    profile: b2,
  },
  {
    name: '김송빈',
    dev: 'BACK',
    memo: 'dhyunee',
    profile: b3,
  },
];

export function TeamPageContainer() {
  return (
    <div className={styles.bodyContainer}>
      <div className={styles.totalWrapper}>
        <div className={styles.typoWrapper}>
          <div className={styles.typoHead}>개발팀 소개</div>
        </div>

        <div className={styles.cardWrapper}>
          <ul className={styles.team}>
            {members.map((item) => (
              <TeamCard item={item} />
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
}

function TeamCard({ item }: { item: memberType }) {
  return (
    <li className={styles.teamItem}>
      <div className={styles.profile}>
        <img className={styles.profileImg} src={item.profile} alt="teamImg" />
        <div className={styles.profileContents}>
          <h2>{item.name}</h2>
          <p className={styles.profileDev} style={{ color: item.dev === 'FRONT' ? '#9dcdff' : '#b9eca7' }}>
            {item.dev}
          </p>
          <div className={styles.profileMemo}>
            <GitHubIcon className={styles.iconGit} sx={{ color: 'rgb(167, 167, 167)', fontSize: 'medium', mr: 0.5 }} />
            {item.memo}
          </div>
        </div>
      </div>
    </li>
  );
}

export default TeamPageContainer;
