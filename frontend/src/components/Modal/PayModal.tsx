import React, { useState, useEffect } from 'react';

import { Button } from '@mui/material';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import FormControl from '@mui/material/FormControl';
import MenuItem from '@mui/material/MenuItem';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import { useAppDispatch } from '../../store/hooks';
import { payModalType } from '../../types/modal';
import { closeModal } from '../../store/slices/payModalSlice';

import styles from './FileModal.module.scss';
import { payment } from '../../payment';
import { PayParams } from '../../types/payment';

function PayModal({ isOpen }: payModalType) {
  const dispatch = useAppDispatch();

  const tmpPayInfo: PayParams = {
    pg: 'html5_inicis',
    pay_method: '',
    merchant_uid: `mid_${new Date().getTime()}`,
    amount: 50000,
    buyer_name: '방봉빔',
    buyer_tel: '01012341234',
    buyer_email: 'bangbongbim@gmail.com',
  };

  const onPayChange = (e: SelectChangeEvent<any>) => {
    payment({ ...tmpPayInfo, pay_method: e.target.value });
  };

  const onClickCloseBtn = () => {
    dispatch(closeModal());
  };

  return (
    <Dialog fullWidth maxWidth="sm" open={isOpen}>
      <DialogTitle>결제 정보 선택</DialogTitle>
      <DialogContent>
        <DialogContentText>원하는 결제 방식을 선택하세요.</DialogContentText>
        <FormControl color="warning" sx={{ mt: 2, width: '100%' }}>
          <Select autoFocus onChange={onPayChange}>
            <MenuItem id="card" value="card">
              카드 결제
            </MenuItem>
            <MenuItem id="vbank" value="vbank">
              가상계좌 결제
            </MenuItem>
          </Select>
        </FormControl>
      </DialogContent>
      <Button color="warning" variant="contained" sx={{ margin: '0.5rem 2rem 1rem auto' }} onClick={onClickCloseBtn}>
        취소
      </Button>
    </Dialog>
  );
}

export default PayModal;
