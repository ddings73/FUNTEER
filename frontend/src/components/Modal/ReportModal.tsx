import React, { useEffect, useState } from 'react';
import { AxiosError } from 'axios';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import { useDispatch } from 'react-redux';
import { useAppSelector } from '../../store/hooks';
import { closeModal } from '../../store/slices/reportModalSlice';
import { fundingReportPost } from '../../api/funding';
import requiredIcon from '../../assets/images/funding/required.svg';
import styles from './ReportModal.module.scss';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: '40%',
  height: '35vh',
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
  overflow: 'scroll',
  padding: '3%',
};

export function ReportModal() {
  // Hook, tools 선언
  const dispatch = useDispatch();
  const reportModalState = useAppSelector((state) => state.reportModalSlice);
  const fundingId = useAppSelector((state) => state.reportModalSlice.fundingId);

  // 컴포넌트 state 관리
  const [reportFile, setReportFile] = useState<any>();
  // 컴포넌트 function 관리

  const createNotice = async () => {
    try {
      await fundingReportPost(fundingId, reportFile);
      alert('파일 등록 완료');
      dispatch(closeModal());
    } catch (error: unknown) {
      if (error instanceof AxiosError) {
        alert(error.response?.data.message);
        console.error(error);
      }
    }
  };

  function initModalClose() {
    dispatch(closeModal());
  }
  useEffect(() => {
    console.log('use', reportFile);
  }, [reportFile]);

  const onChangeFiles = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      const file = e.target.files[0];
      console.log('파일 정보', file);
      setReportFile(e.target.files[0]);
      console.log('파일 정보 후', reportFile);
    }
  };

  return (
    <Modal open={reportModalState.isOpen} onClose={() => initModalClose()} aria-labelledby="modal-modal-title" aria-describedby="modal-modal-description">
      <Box sx={style}>
        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
          <Typography id="modal-modal-title" variant="h2" component="h2">
            펀딩 상세 보고서 제출
          </Typography>
          <Typography id="modal-modal-title" variant="h6" component="h6" sx={{ margin: '30px 0', fontSize: '1em', color: 'gray' }}>
            완료된 펀딩에 대한 보고서를 pdf 형식으로 제출해주세요.
          </Typography>
          <div className={styles.labelDiv} style={{ margin: '1.5rem 0' }}>
            <p style={{ marginBottom: '1rem' }}>파일첨부</p>
            <img style={{ marginRight: '1rem' }} src={requiredIcon} alt="required icon" />
            <input type="file" multiple onChange={onChangeFiles} />
          </div>
          <div style={{ marginTop: '5%', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
            <Button variant="contained" size="large" color="warning">
              등록하기
            </Button>
            <Button variant="outlined" size="large" color="warning" onClick={() => initModalClose()} sx={{ ml: 3 }}>
              취소
            </Button>
          </div>
        </div>
      </Box>
    </Modal>
  );
}

export default ReportModal;
