import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { BsPiggyBankFill, BsBookmarkHeartFill, BsFillHeartFill, BsBookmarkHeart, BsFillTelephoneFill, BsHeart } from 'react-icons/bs';
import { FaMoneyBillWaveAlt } from 'react-icons/fa';
import Button from '@mui/material/Button';
import EmailIcon from '@mui/icons-material/Email';
import SettingsIcon from '@mui/icons-material/Settings';
import { CircularProgress } from '@mui/material';
import { requestTeamProfileInfo } from '../../../api/team';
import TeamSideBarList from '../../../components/TeamPageSideBar/TeamSideBarList';
import { teamProfileType } from '../../../types/user';
import { requestFollow } from '../../../api/user';
import styles from './TeamProfileContainer.module.scss';
import defaultImage from '../../../assets/images/default-profile-img.svg';
import { useAppSelector } from '../../../store/hooks';
import FundingListElement from '../../../components/Funding/FundingListElement';
import { FundingElementType } from '../../../types/funding';
import Footer from '../../../components/Footer/Footer';

function TeamProfileContainer() {
  const navigate = useNavigate();
  /** 현재 프로필 페이지 정보의 팀 ID */
  const { teamId } = useParams();
  /** 유저 ID */
  const userId = useAppSelector((state) => state.userSlice.userId);
  /** 유저 타입 */
  const userType = useAppSelector((state) => state.userSlice.userType);
  const [isFollowing, setIsFollowing] = useState<boolean>(false);
  const [followerCnt, setFollowerCnt] = useState<number>(0);
  const [teamProfileInfo, setTeamProfileInfo] = useState<teamProfileType>({
    profileImgUrl: '',
    name: '',
    email: '',
    phone: '',
    money: 0,
    description: '',
    fundingList: [],
    totalFundingAmount: 0,
  });

  useEffect(() => {
    requestTeamInfo();
  }, []);

  /** 단체 프로필 정보 요청 */
  const requestTeamInfo = async () => {
    try {
      const response = await requestTeamProfileInfo(teamId);
      console.log('단체 프로필 정보', response);
      setTeamProfileInfo((prev) => ({ ...prev, ...response.data }));
      setIsFollowing(response.data.followBtn);
      setFollowerCnt(response.data.followerCnt);
    } catch (error) {
      console.error(error);
    }
  };

  /** 팔로우 요청 */
  const onClickFollowBtn = async () => {
    try {
      const response = await requestFollow(teamId);
      console.log(response);
      if (response.status === 200) {
        if (isFollowing) {
          setFollowerCnt(followerCnt - 1);
        } else {
          setFollowerCnt(followerCnt + 1);
        }
        setIsFollowing(!isFollowing);
      }
    } catch (error) {
      console.error(error);
    }
  };

  /** 프로필 수정 버튼 */
  const onClickSetting = async () => {
    navigate(`../teamedit/${teamId}`, {
      state: {
        teamId,
        profileImgUrl: teamProfileInfo.profileImgUrl,
        name: teamProfileInfo.name,
        description: teamProfileInfo.description,
      },
    });
  };

  return (
    <div className={styles.container}>
      {userId.toString() === teamId && <TeamSideBarList teamId={teamId} />}
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
                {userType === ('NORMAL' || 'KAKAO') && (
                  <div className={styles['follow-btn-div']}>
                    {isFollowing && <BsFillHeartFill color="red" onClick={onClickFollowBtn} className={styles['follow-btn']} />}
                    {!isFollowing && <BsHeart color="red" onClick={onClickFollowBtn} className={styles['follow-btn']} />}
                  </div>
                )}
                {userId.toString() === teamId && <SettingsIcon className={styles.setting} onClick={onClickSetting} />}
              </h1>
              <div className={styles['profile-card-info-content']}>
                <div className={styles['profile-card-info-left']}>
                  <div className={styles['profile-card-info-item']}>
                    {userId.toString() === teamId && (
                      <div style={{ display: 'flex', alignItems: 'center' }}>
                        <BsPiggyBankFill color="rgba(236, 153, 75, 1)" />
                        <p className={styles.money}>
                          마일리지: <span>{teamProfileInfo.money.toLocaleString('ko-KR')}</span> 원
                        </p>
                      </div>
                    )}
                  </div>
                  <div className={styles['profile-card-info-item']}>
                    <FaMoneyBillWaveAlt color="rgba(236, 153, 75, 1)" />
                    <p>
                      누적 액수: <span>{teamProfileInfo.totalFundingAmount.toLocaleString('ko-KR')}</span> 원
                    </p>
                  </div>
                  <div className={styles['profile-card-info-item']}>
                    <BsFillHeartFill color="rgba(236, 153, 75, 1)" />
                    <p>
                      팔로워: <span>{followerCnt.toLocaleString('ko-KR')}</span> 명
                    </p>
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
            <p>단체 설명</p>
            <pre>{teamProfileInfo.description}</pre>
          </div>
          {/* 진행한 펀딩 프로젝트 */}
          <div className={styles['funding-div']}>
            <h1>
              프로젝트 <span style={{ color: 'orange', fontWeight: 'bold' }}>{Object.keys(teamProfileInfo.fundingList).length}</span>
            </h1>
            <div className={styles['funding-list']}>
              {teamProfileInfo.fundingList?.map((funding: FundingElementType) => (
                <FundingListElement {...funding} key={funding.id} />
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default TeamProfileContainer;
