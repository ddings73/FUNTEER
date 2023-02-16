import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Fade, Tooltip } from '@mui/material';
import Lottie from 'lottie-react';
import styles from './MainPageContainer.module.scss';
import InfoCard from '../../components/Main/InfoCard';
import FunList from '../../components/Main/funList';
import ast from '../../assets/images/mainPage/ast.webp';
import planet from '../../assets/images/mainPage/planet_funteer.webp';
import background from '../../assets/images/mainPage/background.webp';
import spaceLottie from '../../lotties/107693-space.json';

export function MainPageContainer() {
  const navigate = useNavigate();
  const [scrollPosition, setScrollPosition] = useState(0);
  const updateScroll = () => {
    setScrollPosition(window.scrollY || document.documentElement.scrollTop);
  };

  useEffect(() => {
    window.addEventListener('scroll', updateScroll);

    return () => {
      window.removeEventListener('scroll', updateScroll);
    };
  }, []);

  const goServiceDetail = () => {
    navigate('/service');
  };

  // 우주인에 손을 올려보세요
  const tooltipText = () => (
    <p style={{ lineHeight: '200%', fontSize: '1.1em', whiteSpace: 'pre-line', textAlign: 'center' }}>
      {`우주여행을 하는 우주선과 우주비행사들은 
        모두를 대신해 그들의 염원과 희망을 품고 
        우주로 떠납니다.
        우리 Funteer도 여러분을 대신해 
        나눔을 실천하는 봉사자분들을 돕고 응원합니다.`}
    </p>
  );

  return (
    <div className={styles.container}>
      <div
        style={{
          width: '100%',
          padding: '0 100px',
        }}
        className={styles.bannerContainer}
      >
        {/* <div className={styles.bannerContainer}> */}
        <div className={styles.typoBox} style={{ position: 'absolute', zIndex: '100' }}>
          <p className={styles.logoTypo}>
            당신의 착한 마음을 <br /> <span className={styles.logoStrong}>FUNTEER</span>가 응원합니다{' '}
          </p>
          <p className={styles.subLogoTypo}>펀딩을 통해 접하는 새로운 봉사</p>
          <button className={styles.serviceBtn} type="button" onClick={goServiceDetail}>
            서비스 상세보기
          </button>
        </div>

        <div className={styles.bannerImg} style={{ opacity: scrollPosition < 700 ? '1' : '0', position: 'absolute', zIndex: '100', right: '-8%' }}>
          <div className={styles.planets}>
            <img src={planet} alt="planet" className={styles.planet} />
            <Tooltip TransitionComponent={Fade} title={tooltipText()} placement="top" followCursor>
              <div className={styles.astWrap}>
                <img src={ast} alt="ast" className={styles.ast} />
                <img src={ast} alt="ast" className={styles.astCnt} />
              </div>
            </Tooltip>
          </div>
        </div>
        <img className={styles.backgroundImg} src={background} style={{ opacity: scrollPosition < 1500 ? '1' : '0' }} alt="back" />
      </div>
      <div className={styles.infoBanner}>
        <Lottie className={styles.lottieSpace} animationData={spaceLottie} />
        <InfoCard />
      </div>
      <div className={styles.fundLists}>
        <FunList />
      </div>
      <div className={styles.donate}> </div>
    </div>
  );
}

export default MainPageContainer;
