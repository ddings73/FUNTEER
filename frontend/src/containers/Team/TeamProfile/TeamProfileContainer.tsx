import React, { useEffect, useState } from 'react';
import { BsFillStarFill, BsFillHeartFill, BsFillTelephoneFill } from 'react-icons/bs';
import { FaMoneyBillWaveAlt } from 'react-icons/fa';
import Button from '@mui/material/Button';
import EmailIcon from '@mui/icons-material/Email';
import { testRequestTeamAccountInfo, testRequestTeamProfileInfo } from '../../../api/team';
import TeamSideBarList from '../../../components/TeamPageSideBar/TeamSideBarList';
import styles from './TeamProfileContainer.module.scss';
import defaultImage from '../../../assets/images/default-profile-img.svg';
import { teamProfileType } from '../../../types/user';

function TeamProfileContainer() {
  // const [teamProfileInfo, setTeamProfileInfo] = useState<teamProfileType>({});
  const user = localStorage.getItem('user');
  console.log('유저', user);

  const test1 = async () => {
    try {
      const response = await testRequestTeamAccountInfo();
      console.log('단체 정보', response);
      const { name, email, phone } = response.data;
      // setTeamProfileInfo({ ...teamProfileInfo, name, email, phone });
      // console.log(teamProfileInfo);
    } catch (error) {
      console.error(error);
    }
  };

  const test2 = async () => {
    try {
      const response = await testRequestTeamProfileInfo();
      console.log('단체 프로필 정보', response);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    test1();
    test2();
  }, []);

  return (
    <div className={styles.container}>
      <TeamSideBarList />
      <div className={styles.content}>
        <div className={styles['content-inner']}>
          {/* 프로필 카드 */}
          <div className={styles['profile-card']}>
            <div className={styles['profile-card-img-div']}>
              <img src={defaultImage} alt="프로필 이미지" className={styles['profile-img']} />
            </div>
            <div className={styles['profile-card-info-div']}>
              <h1 className={styles.name}>단체명</h1>
              <div className={styles['profile-card-info-content']}>
                <div className={styles['profile-card-info-left']}>
                  {/* 평점 */}
                  {/* <div className={styles['profile-card-info-item']}>
                    <BsFillStarFill color="rgba(236, 153, 75, 1)" />
                    <p>평점: 4.57</p>
                  </div> */}
                  <div className={styles['profile-card-info-item']}>
                    <FaMoneyBillWaveAlt color="rgba(236, 153, 75, 1)" />
                    <p>누적 액수: 4,570,000 원</p>
                  </div>
                  <div className={styles['profile-card-info-item']}>
                    <BsFillHeartFill color="rgba(236, 153, 75, 1)" />
                    <p>팔로워: 257 명</p>
                  </div>
                </div>
                <div className={styles['profile-card-info-right']}>
                  <Button variant="contained" color="warning" className={styles['profile-card-info-btn']}>
                    <EmailIcon />
                    <p>email@email.com</p>
                  </Button>
                  <Button variant="contained" color="warning" className={styles['profile-card-info-btn']}>
                    <BsFillTelephoneFill />
                    <p>010-1234-1234</p>
                  </Button>
                </div>
              </div>
            </div>
          </div>
          {/* 단체 소개 */}
          <div className={styles['description-div']}>
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
              프로젝트 <span style={{ color: 'orange', fontWeight: 'bold' }}>7</span>
              <div style={{ marginTop: '2rem', color: 'grey' }}>... 펀딩 리스트꺼 가져올 예정 ...</div>
            </h1>
          </div>
        </div>
      </div>
    </div>
  );
}

export default TeamProfileContainer;
