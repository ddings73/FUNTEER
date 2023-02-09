import React, { useEffect, useState, useRef } from 'react';
import { Link } from 'react-router-dom';
import { Parallax, ParallaxLayer } from '@react-spring/parallax';
import { useAppSelector } from '../../store/hooks';
import styles from './MainPageContainer.module.scss';
import InfoCard from '../../components/Main/InfoCard';
import FunList from '../../components/Main/funList';
import LiveList from '../../components/Main/liveList';
import ast from '../../assets/images/mainPage/ast.png';
import planet from '../../assets/images/mainPage/planet_funteer.png';
import wave from '../../assets/images/mainPage/wave.svg';
import wave2 from '../../assets/images/mainPage/wave2.svg';

export function MainPageContainer() {
  console.log(useAppSelector((state) => state.userSlice.userType));

  const [scrollPosition, setScrollPosition] = useState(0);
  const updateScroll = () => {
    setScrollPosition(window.scrollY || document.documentElement.scrollTop);
  };

  useEffect(() => {
    window.addEventListener('scroll', updateScroll);
    console.log(scrollPosition);
    return () => {
      window.removeEventListener('scroll', updateScroll);
    };
  });

  return (
    <div className={scrollPosition < 800 ? styles.container : styles.container_scrolled}>
      <div style={{ width: '100%', padding: '0 100px' }} className={styles.bannerContainer}>
        {/* <div className={styles.bannerContainer}> */}
        <div className={styles.typoBox}>
          <p className={styles.logoTypo}>
            당신의 착한 마음을 <br /> <span className={styles.logoStrong}>FUNTEER</span>가 응원합니다{' '}
          </p>
          <p className={styles.subLogoTypo}>펀딩을 통해 접하는 새로운 봉사</p>
          <button className={styles.serviceBtn} type="button">
            서비스 상세보기
          </button>
          <p>
            <Link to="/funding/create" style={{ fontSize: '3rem', color: 'white' }}>
              펀딩 페이지
            </Link>
          </p>
          <p>
            <Link to="/charge" style={{ fontSize: '3rem', color: 'white' }}>
              충전 페이지
            </Link>
          </p>
          <p>
            <Link to="/admin" style={{ fontSize: '3rem', color: 'white' }}>
              관리자 페이지
            </Link>
          </p>
          <p>
            <Link to="/team/88" style={{ fontSize: '3rem', color: 'white' }}>
              단체 프로필
            </Link>
          </p>
        </div>
        <div className={styles.bannerImg} style={{ opacity: scrollPosition < 700 ? '1' : '0' }}>
          <div className={styles.planets}>
            <img src={planet} alt="planet" className={styles.planet} />
            <div className={styles.astWrap}>
              <img src={ast} alt="ast" className={styles.ast} />
              <img src={ast} alt="ast" className={styles.astCnt} />
            </div>
          </div>
        </div>
        {/* </div> */}
      </div>
      <div className={styles.infoBanner}>
        <InfoCard />
      </div>
      <div className={styles.fundLists}>
        <FunList />
      </div>
      <div className={styles.volunLists}>
        <LiveList />
      </div>
      <div className={styles.donate}>
        <img src={wave} alt="wave" className={styles.waveBack} />
        <img src={wave2} alt="wave2" className={styles.waveBack2} />
      </div>
    </div>
  );
}

export default MainPageContainer;
