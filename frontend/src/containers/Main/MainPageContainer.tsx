import React, { useEffect, useState, useRef } from 'react';
import { Link } from 'react-router-dom';
import { EventListener, EventSourcePolyfill } from 'event-source-polyfill';
import { Fade, Tooltip } from '@mui/material';
import styles from './MainPageContainer.module.scss';
import InfoCard from '../../components/Main/InfoCard';
import FunList from '../../components/Main/funList';
import LiveList from '../../components/Main/liveList';
import ast from '../../assets/images/mainPage/ast.png';
import planet from '../../assets/images/mainPage/planet_funteer.png';
import wave from '../../assets/images/mainPage/wave.svg';
import wave2 from '../../assets/images/mainPage/wave2.svg';

export function MainPageContainer() {
  const [scrollPosition, setScrollPosition] = useState(0);
  const updateScroll = () => {
    setScrollPosition(window.scrollY || document.documentElement.scrollTop);
  };

  const token = localStorage.getItem('accessToken');
  const [listening, setListening] = useState(false);
  const [sseData, setSseData] = useState({});
  const [respon, setRespon] = useState(false);
  let eventSource: EventSourcePolyfill | undefined;

  useEffect(() => {
    window.addEventListener('scroll', updateScroll);
    console.log(scrollPosition);
    return () => {
      window.removeEventListener('scroll', updateScroll);
    };
  });

  // sse
  useEffect(() => {
    if (!listening && token && !eventSource) {
      // sse 연결
      // http://localhost:8080/api/v1/subscribe
      // https://i8e204.p.ssafy.io/api/v1/subscribe
      eventSource=new EventSourcePolyfill("https://i8e204.p.ssafy.io/api/v1/subscribe",{
        headers:{
          "Content-Type":"text/event-stream",
          "Access-Control-Allow-Origin": "*",
          "Authorization":`Bearer ${token}`,
          "Cache-Control": "no-cache",
        },
        heartbeatTimeout: 86400000,
        withCredentials: true,
      });

      console.log(eventSource);
      // 최초 연결
      eventSource.onopen = (event) => {
        setListening(true);
      };

      // 서버에서 메시지 날릴 때
      eventSource.onmessage = (event) => {
        setSseData(event.data);
        setRespon(true);
        console.log(event.data);
        console.log('onmessage');
        if (event.data !== undefined) alert(event.data);
      };

      eventSource.addEventListener('sse', ((event: CustomEvent) => {
        console.log(event);
      }) as EventListener);

      eventSource.onerror = (error) => {
        if (eventSource) {
          eventSource.close();
          setListening(false);
          console.log(error);
        }
      };
    } else {
      console.log('logout');
      eventSource?.close();
    }
    return () => {
      if (!token && eventSource !== undefined) {
        eventSource.close();
        setListening(false);
      }
    };
  }, [token]);

  // 우주인에 손을 올려보세요
  const tooltipText = () => (
    <p style={{ lineHeight: '200%', fontSize: '1.1em', whiteSpace: 'pre-line', textAlign: 'center' }}>
      {`우주여행을 하는 우주선과 우주비행사들은 
      모두를 대신해 그들의 염원과 희망을 품고 
      우주로 떠납니다.
 우리 Funteer도 여러분을 대신해 
 나눔을 실천하는 봉사자분들을 돕고 응원합니다.      
      `}
    </p>
  );

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
        </div>
        <div className={styles.bannerImg} style={{ opacity: scrollPosition < 700 ? '1' : '0' }}>
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
