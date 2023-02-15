import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router';
import { Button } from '@mui/material';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import { FaHandHoldingHeart } from 'react-icons/fa';
import { useAppDispatch } from '../../store/hooks';
import { donateModalType } from '../../types/modal';
import { customAlert, s1000 } from '../../utils/customAlert';
import { closeModal } from '../../store/slices/donateModalSlice';
import styles from './DonateModal.module.scss';
import { requestPayDonation } from '../../api/donation';
import logo from '../../assets/images/logo.png';

function DonateModal({ isOpen, postId, userId, mileage }: donateModalType) {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  const [amount, setAmount] = useState<number>(0);
  const [less1000, setLess1000] = useState<boolean>(false);
  const [lessMileage, setLessMileage] = useState<boolean>(false);

  useEffect(() => {
    return () => {
      setAmount(0);
      setLess1000(false);
      setLessMileage(false);
    };
  }, []);

  /** 모달 닫기 */
  const onClickClose = () => {
    dispatch(closeModal());
  };

  /** 결제 금액 변경 */
  const onChangeAmount = async (e: React.ChangeEvent<HTMLInputElement>) => {
    setLess1000(false);
    setLessMileage(false);

    const enteredAmount = parseInt(e.target.value, 10);
    const roundedAmount = Math.floor(enteredAmount / 100) * 100;

    if (enteredAmount > mileage && roundedAmount < 1000) {
      setLessMileage(true);
      setLess1000(true);
      return;
    }
    if (enteredAmount > mileage) {
      setLessMileage(true);
      return;
    }
    if (roundedAmount < 1000) {
      setLess1000(true);
      return;
    }

    setAmount(roundedAmount);
  };

  /** 충전 버튼 클릭 */
  const onClickCharge = (e: React.MouseEvent<HTMLAnchorElement>) => {
    e.preventDefault();
    dispatch(closeModal());
    navigate('/charge');
  };

  /** 결제 버튼 클릭 */
  const onClickPay = () => {
    if (less1000 || lessMileage) {
      return;
    }

    requestPay();
  };

  /** 자체 기부 참여 요청 */
  const requestPay = async () => {
    try {
      const response = await requestPayDonation(postId, amount.toString());
      console.log('자체 기부 참여 요청', response);
      dispatch(closeModal());
      customAlert(s1000, '기부 참여가 완료되었습니다.');
      setTimeout(() => {
        window.location.reload();
      }, 1000);
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
        <img src={logo} alt="로고" className={styles.logo} /> FUNTEER 기부 참여
      </DialogTitle>
      <DialogContent>
        <DialogContentText className={styles['text-label']}>현재 마일리지</DialogContentText>
        <DialogContentText className={styles['text-content']}>{mileage.toLocaleString('ko-KR')} 원</DialogContentText>
        <DialogContentText className={styles['text-label']}>기부 금액</DialogContentText>
        <input type="number" placeholder="1000원 이상, 100원 단위로 입력해주세요." onChange={onChangeAmount} />
        {amount >= 0 && (
          <p className={styles.alarm}>
            {amount.toLocaleString('ko-KR')} 원 <span>차감 예정</span>
          </p>
        )}
        {lessMileage && <p className={styles.warn}>마일리지가 부족합니다.</p>}
        {less1000 && <p className={styles.warn}>1000원 이상의 금액만 입력 가능합니다.</p>}
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
