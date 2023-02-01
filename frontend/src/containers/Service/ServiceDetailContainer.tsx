import React from 'react';
import styles from './ServiceDetailContainer.module.scss';

function ServiceDetailContainer() {
  return (
    <div className={styles.bodyContainer}>
      <div className={styles.bodyCover}>
        <div className={styles.totalContainer}>
          <div className={styles.serviceHeader}>FUNTEER 서비스 소개</div>
          <div className={styles.serviceContents}>
            헌법재판소 재판관의 임기는 6년으로 하며, 법률이 정하는 바에 의하여 연임할 수 있다. 국가는 지역간의 균형있는 발전을 위하여 지역경제를 육성할 의무를 진다.
            감사원은 원장을 포함한 5인 이상 11인 이하의 감사위원으로 구성한다. 모든 국민은 고문을 받지 아니하며, 형사상 자기에게 불리한 진술을 강요당하지 아니한다.{' '}
          </div>
          <div className={styles.callhead}>
            담당자 연락처 <br /> 010-1234-5678
          </div>
        </div>
      </div>
    </div>
  );
}

export default ServiceDetailContainer;
