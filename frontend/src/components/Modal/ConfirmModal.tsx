import React, { useEffect, useState } from 'react';

// mui
import { Button } from '@mui/material';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogActions from '@mui/material/DialogActions';
import { ConfirmModalType } from '../../types/modal';

function ConfirmModal({ isOpen, title, content, handleModal }: ConfirmModalType) {
  const [open, setOpen] = useState<boolean>(false);
  const onClickHandler = () => {
    setOpen(false);
    handleModal();
  };

  const onCloseModal = () => {
    setOpen(false);
    handleModal();
  };

  useEffect(() => {
    if (isOpen) setOpen(true);
  }, [isOpen]);

  return (
    <Dialog fullWidth maxWidth="sm" open={open} onClose={onCloseModal} aria-labelledby="alert-dialog-title" aria-describedby="alert-dialog-description">
      <DialogTitle id="alert-dialog-title">{title}</DialogTitle>
      <DialogContent>
        <DialogContentText id="alert-dialog-description">{content}</DialogContentText>
      </DialogContent>
      <DialogActions>
        <Button variant="contained" onClick={onClickHandler}>
          닫기
        </Button>
      </DialogActions>
    </Dialog>
  );
}

export default ConfirmModal;
