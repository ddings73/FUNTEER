import React from 'react'

// mui
import { Button } from '@mui/material';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogActions from '@mui/material/DialogActions';
import { ConfirmModalType } from '../../types/modal';
import { useAppDispatch } from '../../store/hooks';
import { closeModal } from '../../store/slices/modalSlice';





function ConfirmModal ({isOpen,title,content}:ConfirmModalType){
    const dispatch = useAppDispatch();
    const handleModal  = ()=>{
        dispatch(closeModal())
    }
    return (
        <Dialog fullWidth maxWidth="sm" open={isOpen}     aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description">
        <DialogTitle  id="alert-dialog-title">
          {title}
        </DialogTitle>
        <DialogContent >
          <DialogContentText  id="alert-dialog-description">
            {content}
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button variant="contained" onClick={handleModal}>
           닫기
          </Button>
        </DialogActions>
      </Dialog>
    )
}

export default ConfirmModal