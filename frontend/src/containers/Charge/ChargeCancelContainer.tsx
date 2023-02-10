import React, { useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import styles from './ChargeCancelContainer.module.scss';

function ChargeCancelContainer() {
  const location = useLocation();
  const { userId, amount, impUid, money } = location.state;

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <div className={styles.container}>
      <div className={styles.content}>
        <div className={styles.banner}>
          <h1 className={styles.title}>취소 결제 정보</h1>
        </div>
        <div className={styles['charge-info']}>
          <p className={styles.label}>금액</p>
          <p className={styles.value}>
            <span>{amount}</span>
          </p>
          <p className={styles.label}>취소 후 마일리지</p>
          <p className={styles.value}>
            {money} - {amount} = <span>{money - amount}</span>
          </p>
        </div>
      </div>
    </div>
  );
}

export default ChargeCancelContainer;
