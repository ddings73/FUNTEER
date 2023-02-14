import React, { useRef, useState } from 'react';
import { Editor as ToastEditor } from '@toast-ui/react-editor';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { TextField } from '@mui/material';
import Modal from '@mui/material/Modal';
import { useDispatch } from 'react-redux';
import { useAppSelector } from '../../store/hooks';
import { closeModalWith, closeModalPlain } from '../../store/slices/reportModalSlice';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: '60%',
  height: 'auto',
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};

export function ReportModal() {
  const [reportContent, isReportContent] = useState({
    content: '',
    reportDetailResponseList: [
      {
        amount: '',
        description: '',
      },
    ],
  });
  const dispatch = useDispatch();
  const reportModalState = useAppSelector((state) => state.reportModalSlice);
  const editorRef = useRef<ToastEditor>(null);

  function handleClose() {
    dispatch(closeModalWith({ isOpen: false, content: reportContent.content }));
  }
  const editorChangeHandler = (e: any) => {
    const text = editorRef.current?.getInstance().getHTML();
  };
  return (
    <Modal open={reportModalState.isOpen} onClose={() => handleClose()} aria-labelledby="modal-modal-title" aria-describedby="modal-modal-description">
      <Box sx={style}>
        <Typography id="modal-modal-title" variant="h2" component="h2" sx={{ margin: '30px 0' }}>
          펀딩 상세 보고서 제출
        </Typography>
        <ToastEditor
          ref={editorRef}
          placeholder="진행하시는 펀딩에 대해 자세히 설명해주세요."
          height="500px"
          useCommandShortcut
          initialEditType="wysiwyg"
          onChange={editorChangeHandler}
          language="ko-KR"
          hideModeSwitch // 하단의 타입 선택 탭 숨기기
        />
        <div style={{ display: 'flex' }}>
          <TextField id="outlined-basic" label="항목 설명" variant="outlined" sx={{ marginTop: '5%', width: '70%' }} />
          <TextField id="outlined-basic" label="금액 입력" variant="outlined" sx={{ marginTop: '5%', width: '20%', marginLeft: '3%' }} />
          <Button variant="text">계획 추가</Button>
        </div>

        <div style={{ marginTop: '5%', display: 'flex', justifyContent: 'center' }}>
          <Button variant="contained" size="large" color="warning">
            등록하기
          </Button>
          <Button variant="outlined" size="large" color="warning" onClick={() => dispatch(closeModalPlain())} sx={{ ml: 3 }}>
            취소
          </Button>
        </div>
      </Box>
    </Modal>
  );
}

export default ReportModal;
