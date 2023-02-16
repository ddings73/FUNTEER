/* eslint-disable react/no-array-index-key */
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Lottie from 'lottie-react';
import { requestLiveList } from '../../api/live';
import styles from './LiveListContainer.module.scss';
import liveLottie from '../../lotties/78810-live-animation.json';
import emptyLottie from '../../lotties/85557-empty.json'

import sudal from '../../assets/images/sudal.jpg';

type liveListElementType = {
  sessionName: string;
  thumbnail: string;
};
function LiveListContainer() {
  const navigate = useNavigate();
  const [liveList, setLiveList] = useState<liveListElementType[]>([]);

  const initLiveList = async () => {
    try {
      const response = await requestLiveList();
      setLiveList([...response.data.sessionInfoList]);
      console.log(response);
    } catch (error) {
      console.error(error);
    }
  };

  const onClickMoveLiveSession = (sessionName: string) => {
    navigate(`../subscribeLiveRoom/${sessionName}`);
  };

  useEffect(() => {
    initLiveList();
  }, []);
  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <div className={styles['title-box']}>
          <div className={styles['description-box']}>
            <p>현재 진행중인 봉사활동을<br/>라이브로 시청해보세요.</p>
          </div>
        </div>

        <div className={styles['live-list-box']}>
          {liveList.length===0 ?<div className={styles.lottieBox}><Lottie animationData={emptyLottie}/> <p className={styles.emptyText}>현재 진행중인 라이브 방송이 없어요</p> </div>  :(liveList.map((element, index) => (
            <div className={styles['live-element']} key={index + 1} onClick={() => onClickMoveLiveSession(element.sessionName)} aria-hidden="true">
              <div className={styles['live-thumbnail-box']}>
                <img src={element.thumbnail} alt="" className={styles['live-thumbnail']} />
              </div>
              <p>{element.sessionName}의 봉사 라이브</p>
              <Lottie animationData={liveLottie} className={styles.liveButton}>
                {' '}
              </Lottie>
            </div>
          )))}
          {}
        </div>
      </div>
    </div>
  );
}

export default LiveListContainer;
