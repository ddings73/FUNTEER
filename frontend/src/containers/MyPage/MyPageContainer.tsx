import React, { useEffect, useState } from 'react';
import GitHubIcon from '@mui/icons-material/GitHub';
import EmailIcon from '@mui/icons-material/Email';
import SettingsIcon from '@mui/icons-material/Settings';
import Tooltip from '@mui/material/Tooltip';
import Alert from '@mui/material/Alert';
import AlertTitle from '@mui/material/AlertTitle';
import { useLocation } from 'react-router-dom';
import SideBarList from '../../components/MyPageSideBar/SideBarList';
import styles from './MyPageContainer.module.scss';
import ProfileSvg from '../../assets/images/default-profile-img.svg';
import { requestUserInfo, requestUserProfile } from '../../api/user';
import { useAppSelector } from '../../store/hooks';
import { userProfileInterface } from '../../types/user';

export function MyPageContainer() {
  const userId = useAppSelector((state) => state.userSlice.userId);
  const { pathname } = useLocation();
  const [userProfile, setUserProfile] = useState<userProfileInterface>({
    nickname: '',
    profileUrl: '',
    money: 0,
    wishCnt: 0,
    followingCnt: 0,
  });
  console.log(pathname);

  const handleCopyClipBoard = async (text: string, type: string) => {
    try {
      await navigator.clipboard.writeText(text);

      alert(`${type}가 복사되었습니다.`);
    } catch (error) {
      alert('복사에 실패했습니다');
    }
  };

  const getRequestUserInfo = async () => {
    try {
      const response = await requestUserProfile(userId);
      setUserProfile({ ...response.data });
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getRequestUserInfo();
  }, []);
  return (
    <div className={styles.bodyContainer}>
      <SideBarList />
      <div className={styles.contentContainer}>
        <div className={styles.backBox}>
          <div className={styles.contentBox}>
            <section className={styles.main}>
              <div className={styles.profileCard}>
                <div className={styles.profileEdit}>
                  {' '}
                  <Tooltip title="프로필 수정" placement="bottom">
                    <SettingsIcon sx={{ fontSize: '50px' }} />
                  </Tooltip>
                </div>
                <div className={styles.image}>
                  <img className={styles.profilePic} src={ProfileSvg} alt="" />
                </div>
                <div className={styles.data}>
                  <h2>{userProfile.nickname}</h2>
                </div>
                <div className={styles.row}>
                  <div className={styles.info}>
                    <h3>팔로우</h3>
                    <span>{userProfile.followingCnt}</span>
                  </div>
                  <div className={styles.info}>
                    <h3>찜한 펀딩</h3>
                    <span>{userProfile.followingCnt}</span>
                  </div>
                  <div className={styles.info}>
                    <h3>총 기부</h3>
                    <span>{userProfile.money}</span>
                  </div>
                </div>
                <div className={styles.buttons}>
                  <Tooltip title="깃허브 복사" placement="top">
                    <GitHubIcon className={styles.btn} onClick={() => handleCopyClipBoard('@github', '깃허브 정보')}>
                      GitHub
                    </GitHubIcon>
                  </Tooltip>
                  <Tooltip title="이메일 복사" placement="top">
                    <EmailIcon className={styles.btn} onClick={() => handleCopyClipBoard('Email@gmail.com', '이메일 주소')} />
                  </Tooltip>
                </div>
              </div>
            </section>
          </div>
        </div>
      </div>
    </div>
  );
}

export default MyPageContainer;
