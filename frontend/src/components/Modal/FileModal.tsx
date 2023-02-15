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
import { requestAcceptTeam } from '../../api/admin';
import { customAlert, s1000 } from '../../utils/customAlert';

function FileModal({ isOpen, userId, vmsFileUrl, performFileUrl, teamState }: FileModalType) {
  const dispatch = useAppDispatch();

  const onClickCloseBtnHandler = () => {
    dispatch(closeModal());
  };

  const onClickApproveBtnHandler = async () => {
    try {
      const response = await requestAcceptTeam(parseInt(userId, 10));
      console.log(response);
      customAlert(s1000, '단체 승인 완료');
      dispatch(closeModal());
      window.location.reload();
    } catch (error) {
      console.error(error);
    }
    dispatch(closeModal());
  };

  const onClickDenyBtnHandler = () => {
    console.log('단체 가입 승인 거부');
    dispatch(denyTeam({ isOpen: false, userId, vmsFileUrl, performFileUrl, deniedNum: userId.toString(), teamState }));
  };

  return (
    <Dialog fullWidth maxWidth="sm" open={isOpen} aria-labelledby="alert-dialog-title" aria-describedby="alert-dialog-description" className={styles.dialog}>
      <button type="button" className={styles['close-btn']} onClick={onClickCloseBtnHandler}>
        X
      </button>
      <DialogTitle id="alert-dialog-title">유저 번호: {userId}</DialogTitle>
      <DialogContent>
        <DialogContentText id="alert-dialog-description">
          VMS 파일: <a href={vmsFileUrl}>{`${userId}_vms_file`}</a>
        </DialogContentText>
        <DialogContentText id="alert-dialog-description">
          봉사 실적 파일: <a href={performFileUrl}>{`${userId}_perform_file`}</a>
        </DialogContentText>
      </DialogContent>
      <DialogActions>
        {(teamState === 'TEAM_EXPIRED' || teamState === 'TEAM_WAIT') && (
          <>
            <Button variant="contained" onClick={onClickApproveBtnHandler} className={styles['approve-btn']}>
              승인
            </Button>
            <Button variant="contained" onClick={onClickDenyBtnHandler} className={styles['deny-btn']}>
              거부
            </Button>
          </>
        )}
      </DialogActions>
    </Dialog>
  );
}

export default FileModal;
