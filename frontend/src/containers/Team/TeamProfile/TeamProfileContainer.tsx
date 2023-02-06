import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { BsPiggyBankFill, BsBookmarkHeartFill, BsFillHeartFill, BsBookmarkHeart, BsFillTelephoneFill } from 'react-icons/bs';
import FavoriteTwoToneIcon from '@mui/icons-material/FavoriteTwoTone';
import { FaMoneyBillWaveAlt } from 'react-icons/fa';
import Button from '@mui/material/Button';
import EmailIcon from '@mui/icons-material/Email';
import { requestTeamAccountInfo, requestTeamProfileInfo } from '../../../api/team';
import TeamSideBarList from '../../../components/TeamPageSideBar/TeamSideBarList';
import styles from './TeamProfileContainer.module.scss';
import defaultImage from '../../../assets/images/default-profile-img.svg';
import { teamProfileType } from '../../../types/user';
import { requestFollow } from '../../../api/user';

function TeamProfileContainer() {
  const { teamId } = useParams();
  const [userId, setUserId] = useState<number>(0);
  const [userType, setUserType] = useState<string>('');
  const [teamProfileInfo, setTeamProfileInfo] = useState<teamProfileType>({
    profileImgUrl: '',
    name: '',
    email: '',
    phone: '',
    money: 0,
    followerCnt: 0,
    description: '',
    fundingList: [],
    followBtn: false,
    totalFundingAmount: 0,
  });

  /** 단체 프로필 정보 요청 */
  const requestTeamInfo = async () => {
    try {
      const response = await requestTeamProfileInfo(teamId);
      console.log('단체 프로필 정보', response);
      setTeamProfileInfo((prev) => ({ ...prev, ...response.data }));
    } catch (error) {
      console.error(error);
    }
  };

  /** 로그인한 유저의 id, type 세팅 */
  useEffect(() => {
    if (!userId || !userType) {
      const userData = sessionStorage.getItem('user');
      setUserId(userData ? JSON.parse(userData).userId : null);
      setUserType(userData ? JSON.parse(userData).userType : null);
    }
    requestTeamInfo();
  }, []);

  /** 팔로우 요청 */
  const onClickFollowBtn = async () => {
    try {
      const response = await requestFollow(teamId);
      console.log(response);
    } catch (error) {
      console.error(error);
    }
  };

  /** 팔로우 반영 */
  // useEffect(() => {}, [teamProfileInfo.followBtn]);

  return (
    <div className={styles.container}>
      <TeamSideBarList />
      <div className={styles.content}>
        <div className={styles['content-inner']}>
          {/* 프로필 카드 */}
          <div className={styles['profile-card']}>
            <div className={styles['profile-card-img-div']}>
              <div className={styles['profile-card-img-inner']}>
                <img src={teamProfileInfo.profileImgUrl ? teamProfileInfo.profileImgUrl : defaultImage} alt="프로필 이미지" className={styles['profile-img']} />
              </div>
            </div>
            <div className={styles['profile-card-info-div']}>
              <h1 className={styles.name}>
                {teamProfileInfo.name}
                {userType === 'NORMAL' && (
                  <div className={styles['follow-btn-div']}>
                    {teamProfileInfo.followBtn && <BsBookmarkHeartFill color="red" onClick={onClickFollowBtn} className={styles['follow-btn']} />}
                    {!teamProfileInfo.followBtn && <BsBookmarkHeart color="red" onClick={onClickFollowBtn} className={styles['follow-btn']} />}
                  </div>
                )}
              </h1>
              <div className={styles['profile-card-info-content']}>
                <div className={styles['profile-card-info-left']}>
                  <div className={styles['profile-card-info-item']}>
                    <BsPiggyBankFill color="rgba(236, 153, 75, 1)" />
                    <p className={styles.money}>저금통: {teamProfileInfo.money.toLocaleString('ko-KR')} 원</p>
                  </div>
                  <div className={styles['profile-card-info-item']}>
                    <FaMoneyBillWaveAlt color="rgba(236, 153, 75, 1)" />
                    <p>누적 액수: {teamProfileInfo.totalFundingAmount.toLocaleString('ko-KR')} 원</p>
                  </div>
                  <div className={styles['profile-card-info-item']}>
                    <BsFillHeartFill color="rgba(236, 153, 75, 1)" />
                    <p>팔로워: {teamProfileInfo.followerCnt.toLocaleString('ko-KR')} 명</p>
                  </div>
                </div>
                <div className={styles['profile-card-info-right']}>
                  <Button variant="contained" color="warning" className={styles['profile-card-info-btn']}>
                    <EmailIcon />
                    <p>{teamProfileInfo.email}</p>
                  </Button>
                  <Button variant="contained" color="warning" className={styles['profile-card-info-btn']}>
                    <BsFillTelephoneFill />
                    <p>{teamProfileInfo.phone}</p>
                  </Button>
                </div>
              </div>
            </div>
          </div>
          {/* 단체 소개 */}
          <div className={styles['description-div']}>
            {/* <p>{teamProfileInfo.description}</p> */}
            <p>
              갑 얼음에 아니더면, 노래하며 그들에게 속잎나고, 뜨거운지라, 것이다. 꽃이 공자는 황금시대를 옷을 아름다우냐? 위하여, 무엇이 행복스럽고 있다. 이것을
              유소년에게서 예가 피어나기 아니다. 되려니와, 찾아다녀도, 위하여 피가 듣기만 때까지 하여도 원질이 보배를 있으랴? 찾아 무엇을 예가 뭇 이상의 전인 가슴에 피고,
              청춘을 사막이다. 하여도 그러므로 청춘에서만 만물은 살 길지 철환하였는가? 그들에게 있는 그들을 아니한 밥을 이 철환하였는가? 이상은 이상을 날카로우나 청춘을
              쓸쓸하랴? 날카로우나 있으며, 풍부하게 남는 가치를 것이다.
            </p>
          </div>
          {/* 진행한 펀딩 프로젝트 */}
          <div className={styles['funding-div']}>
            <h1>
              프로젝트 <span style={{ color: 'orange', fontWeight: 'bold' }}>{Object.keys(teamProfileInfo.fundingList).length}</span>
              <div style={{ marginTop: '2rem', color: 'grey' }}>... 펀딩 리스트에서 가져올 예정 ...</div>
            </h1>
          </div>
        </div>
      </div>
    </div>
  );
}

export default TeamProfileContainer;
