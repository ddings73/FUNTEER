import React, { useState, useEffect } from 'react';

import { Button } from '@mui/material';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogActions from '@mui/material/DialogActions';

import { useNavigate } from 'react-router-dom';
import { useAppDispatch } from '../../store/hooks';
import { FileModalType } from '../../types/modal';
import { closeModal, denyTeam } from '../../store/slices/fileModalSlice';

import styles from './FileModal.module.scss';

function FileModal({ isOpen, vmsNum, vmsFile, performFile, deniedNum }: FileModalType) {
  const dispatch = useAppDispatch();

  const onClickCloseBtnHandler = () => {
    dispatch(closeModal());
  };

  const onClickApproveBtnHandler = () => {
    console.log('단체 가입 승인 요청');
    dispatch(closeModal());
  };

  const onClickDenyBtnHandler = () => {
    console.log('단체 가입 승인 거부');
    dispatch(denyTeam({ isOpen: false, vmsNum, vmsFile, performFile, deniedNum: vmsNum }));
  };

  return (
    <Dialog fullWidth maxWidth="sm" open={isOpen} aria-labelledby="alert-dialog-title" aria-describedby="alert-dialog-description" className={styles.dialog}>
      <button type="button" className={styles['close-btn']} onClick={onClickCloseBtnHandler}>
        X
      </button>
      <DialogTitle id="alert-dialog-title">위촉 번호: {vmsNum}</DialogTitle>
      <DialogContent>
        <DialogContentText id="alert-dialog-description">VMS 파일: {vmsFile}</DialogContentText>
        <DialogContentText id="alert-dialog-description">봉사 실적 파일: {performFile}</DialogContentText>
      </DialogContent>
      <DialogActions>
        <Button variant="contained" onClick={onClickApproveBtnHandler} className={styles['approve-btn']}>
          승인
        </Button>
        <Button variant="contained" onClick={onClickDenyBtnHandler} className={styles['deny-btn']}>
          거절
        </Button>
      </DialogActions>
    </Dialog>
  );
}

export default FileModal;
