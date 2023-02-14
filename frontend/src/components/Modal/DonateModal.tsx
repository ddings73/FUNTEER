import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router';
import { Button } from '@mui/material';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogActions from '@mui/material/DialogActions';
import { FaHandHoldingHeart } from 'react-icons/fa';
import { useAppDispatch, useAppSelector } from '../../store/hooks';
import { donateModalType } from '../../types/modal';
import { customAlert, s1000 } from '../../utils/customAlert';
import { closeModal } from '../../store/slices/donateModalSlice';
import styles from './DonateModal.module.scss';
import { requestPayDonation } from '../../api/donation';

function DonateModal({ isOpen, postId, userId, mileage }: donateModalType) {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  const [amount, setAmount] = useState<number>(0);

  /** 모달 닫기 */
  const onClickClose = () => {
    dispatch(closeModal());
  };

  /** 결제 금액 변경 */
  const onChangeAmount = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (parseInt(e.target.value, 10) > mileage) {
      setAmount(mileage);
    } else {
      setAmount(parseInt(e.target.value, 10));
    }
  };

  /** 충전 버튼 클릭 */
  const onClickCharge = (e: React.MouseEvent<HTMLAnchorElement>) => {
    e.preventDefault();
    dispatch(closeModal());
    navigate('/charge');
  };

  /** 결제 버튼 클릭 */
  const onClickPay = () => {
    requestPay();
  };

  /** 자체 기부 참여 요청 */
  const requestPay = async () => {
    try {
      const response = await requestPayDonation(postId, amount.toString());
      console.log('자체 기부 참여 요청', response);
      dispatch(closeModal());
      customAlert(s1000, '기부 참여가 완료되었습니다.');
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <Dialog fullWidth maxWidth="sm" open={isOpen} className={styles.dialog}>
      <button type="button" onClick={onClickClose} className={styles['close-btn']}>
        X
      </button>
      <DialogTitle className={styles.title}>
        <FaHandHoldingHeart /> FUNTEER 기부 참여
      </DialogTitle>
      <DialogContent>
        <DialogContentText className={styles['text-label']}>현재 마일리지</DialogContentText>
        <DialogContentText className={styles['text-content']}>{mileage.toLocaleString('ko-KR')} 원</DialogContentText>
        <DialogContentText className={styles['text-label']}>기부 금액</DialogContentText>
        <input type="number" value={amount} onChange={onChangeAmount} /> {amount > 0 && <span>{amount.toLocaleString('ko-KR')} 원</span>}
      </DialogContent>
      <a href="." onClick={onClickCharge} className={styles.charge}>
        마일리지 충전
      </a>
      <Button variant="contained" onClick={onClickPay} className={styles.pay}>
        결제
      </Button>
    </Dialog>
  );
}

export default DonateModal;
