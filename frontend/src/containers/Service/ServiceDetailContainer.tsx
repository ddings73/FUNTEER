import React, { useEffect, useState } from 'react';
import styles from './ServiceDetailContainer.module.scss';

function ServiceDetailContainer() {
  const [showP, setShowP] = useState(0);

  useEffect(() => {
    let interval: NodeJS.Timeout;
    // eslint-disable-next-line
    interval = setInterval(() => {
      setShowP((prev) => prev + 1);
    }, 1000);

    return () => clearInterval(interval);
  }, []);

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <div className={styles.totalContainer}>
          <div className={styles.serviceHeader}>FUNTEER</div>
          <div className={styles.serviceComment}>기부형 크라우드 펀딩을 통한 봉사활동 중계 플랫폼</div>
          <div className={styles.serviceContents}>
            {[
              <p className={styles.serviceContent} key={1}>
                봉사활동 라이브 중계를 통하여 봉사 단체, 기부에 대한 투명성과 신뢰성을 보장합니다.
              </p>,
              <p className={styles.serviceContent} key={2}>
                펀딩 참여자에게는 만족감을, 봉사 단체에게는 자금을 전달하여 기부와 봉사를 활성화합니다.
              </p>,
              <p className={styles.serviceContent} key={3}>
                플랫폼을 통해 기부와 봉사에 대한 정보 제공과 접근성을 높입니다.
              </p>,
            ].slice(0, showP)}
          </div>

          {/* <div className={styles.callhead}>담당자 연락처 : 010-2992-6824</div> */}
        </div>
      </div>
    </div>
  );
}

export default ServiceDetailContainer;
