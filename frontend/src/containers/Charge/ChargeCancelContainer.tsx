import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { Button } from '@mui/material';
import requireIcon from '../../assets/images/funding/required.svg';
import { YYYYMMDDHHMMSS } from '../../utils/day';
import styles from './ChargeCancelContainer.module.scss';
import { requestCancelIamport } from '../../api/payment';
import { customAlert, customTextOnlyAlert, noTimeSuccess, noTimeWarn, s1000, w1500 } from '../../utils/customAlert';

function ChargeCancelContainer() {
  const location = useLocation();
  const navigate = useNavigate();
  const { userId, amount, impUid, money, chargeDate } = location.state;
  const [reason, setReason] = useState<string>('');

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  const onChangeReason = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setReason(e.target.value);
  };

  const onClickCancelBtn = () => {
    navigate(-1);
  };

  const onClickDecideBtn = () => {
    if (reason.length > 100) {
      customTextOnlyAlert(noTimeWarn, '환불 사유가 100자를 초과했습니다.');
      return;
    }

    requestCancelCharge();
  };

  const requestCancelCharge = async () => {
    try {
      const response = await requestCancelIamport(amount, impUid, reason);
      console.log('충전 취소 요청 응답', response);
      customTextOnlyAlert(noTimeSuccess, '충전 취소 완료');
      navigate(-1);
    } catch (err) {
      console.error('충전 취소 요청 에러', err);
      customTextOnlyAlert(noTimeWarn, '충전 취소 실패');
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.content}>
        <div className={styles.banner}>
          <h1 className={styles.title}>충전 취소 정보</h1>
        </div>
        <div className={styles['charge-info']}>
          <p className={styles.label}>충전 일시</p>
          <p className={styles.value}>{YYYYMMDDHHMMSS(chargeDate)}</p>
          <p className={styles.label}>취소 금액</p>
          <p className={styles.value}>
            <span>{amount}</span>
          </p>
          <p className={styles.label}>취소 후 마일리지</p>
          <p className={styles.value}>
            {money} - {amount} = <span>{money - amount}</span>
          </p>
        </div>
        <hr />
        <div className={styles['refund-info']}>
          <p className={styles.label}>
            <img src={requireIcon} alt="require" /> 환불 사유를 입력해주세요.
          </p>
          <textarea rows={10} placeholder="최대 100자 이내로 입력해주세요." onChange={onChangeReason} />
          <p className={styles.alarm}>
            <span className={reason.length > 100 ? styles['alarm-warn'] : ''}>{reason.length}</span> / 100
          </p>
        </div>
        <div className={styles['btn-div']}>
          <Button sx={{ color: 'grey' }} onClick={onClickCancelBtn}>
            취소
          </Button>
          <Button color="warning" onClick={onClickDecideBtn}>
            결정
          </Button>
        </div>
      </div>
    </div>
  );
}

export default ChargeCancelContainer;
