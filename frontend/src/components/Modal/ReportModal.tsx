import React, { useCallback, useEffect, useRef, useState } from 'react';
import { AxiosError } from 'axios';
import { Editor as ToastEditor } from '@toast-ui/react-editor';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { IconButton, TextField } from '@mui/material';
import { UploadFile } from '@mui/icons-material';
import HighlightOffIcon from '@mui/icons-material/HighlightOff';
import Modal from '@mui/material/Modal';
import { AiOutlineClose } from 'react-icons/ai';
import AddBoxIcon from '@mui/icons-material/AddBox';
import { useDispatch } from 'react-redux';
import { useAppSelector } from '../../store/hooks';
import { closeModal } from '../../store/slices/reportModalSlice';
import { FundingReportInterface, responseListType } from '../../types/funding';
import { fundingReportPost } from '../../api/funding';
import requiredIcon from '../../assets/images/funding/required.svg';
import styles from './ReportModal.module.scss';
import { customTextOnlyAlert, noTimeSuccess, noTimeWarn } from '../../utils/customAlert';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: '60%',
  height: '80vh',
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
  overflow: 'scroll',
};
const ALLOW_FILE_EXTENSION = 'jpg,jpeg,png';
const FILE_SIZE_MAX_LIMIT = 5 * 1024 * 1024;

export function ReportModal() {
  // Hook, tools 선언
  const dispatch = useDispatch();
  const reportModalState = useAppSelector((state) => state.reportModalSlice);
  const fundingId = useAppSelector((state) => state.reportModalSlice.fundingId);
  const editorRef = useRef<ToastEditor>(null);

  // 컴포넌트 state 관리
  const [responseList, setResponseList] = useState<responseListType[]>([
    {
      amount: '',
      description: '',
    },
  ]);
  const [reportData, setReportData] = useState<FundingReportInterface>({
    content: '',
    fundingDetailRequests: [
      {
        amount: '',
        description: '',
      },
    ],
    receiptFile: new Blob(),
  });
  const [amountText, setAmount] = useState('');
  const [descriptionText, setDescription] = useState('');
  const [contentText, setContentText] = useState('');
  // 컴포넌트 function 관리

  const editorChangeHandler = () => {
    if (!editorRef.current) {
      return;
    }
    setContentText(editorRef.current.getInstance().getHTML());
    setReportData({ ...reportData, content: editorRef.current.getInstance().getHTML() });
  };
  const onChangeAmount = useCallback(
    (e: React.ChangeEvent<HTMLInputElement>) => {
      setAmount(e.target.value);
    },
    [amountText],
  );
  const onChangeDescription = useCallback(
    (e: React.ChangeEvent<HTMLInputElement>) => {
      setDescription(e.target.value);
    },
    [descriptionText],
  );

  function submitHandler() {
    if (descriptionText !== '' && amountText !== '') {
      setResponseList([
        ...responseList,
        {
          amount: amountText,
          description: descriptionText,
        },
      ]);
    } else {
      customTextOnlyAlert(noTimeWarn, '빈칸이 있습니다. 양식을 모두 채워주세요');
    }
    setAmount('');
    setDescription('');
  }

  function deleteHandler(delIdx: number) {
    setResponseList(responseList.filter((data, idx) => idx !== delIdx));
  }

  // const createNotice = async () => {
  //   responseList.shift();
  //   setResponseList(responseList);
  //   setReportData({ ...reportData, content: contentText, fundingDetailRequests: responseList });
  // };

  // const test = async () => {
  //   await createNotice();

  //   try {
  //     const response = await fundingReportPost(fundingId, reportData);

  async function closeReportModal() {
    if (responseList.length < 2) {
      customTextOnlyAlert(noTimeWarn, '설명과 금액 보고를 작성해주세요');
      return;
    }
    responseList.shift(); // 맨 앞 빈 객체 제거
    try {
      // await fundingReportPost(fundingId, reportContent);
      // setReportContent({
      //   content: contentText,
      //   reportDetailResponseList: responseList,
      // });
      customTextOnlyAlert(noTimeSuccess, '보고서가 성공적으로 등록되었습니다. 등록창이 닫힙니다.');
      dispatch(closeModal());
    } catch (error: unknown) {
      if (error instanceof AxiosError) {
        alert(error.response?.data.message);
        console.error(error);
      }
    }
  }

  function initModalClose() {
    setAmount('');
    setDescription('');
    setResponseList([
      {
        amount: '',
        description: '',
      },
    ]);
    setContentText('');
    dispatch(closeModal());
  }

  const onChangeFiles = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      const file = e.target.files[0];
      // setReportData({ ...reportData, content: contentText, fundingDetailRequests: responseList, receiptFile: [...prev, ...temp] });
      setReportData({ ...reportData, receiptFile: file });
    }
  };

  // const onClickDeleteFile = (index: number) => {
  //   setReportData({ ...reportData, receiptFile: reportData.receiptFile.filter((_, i) => i !== index) });
  // };

  return (
    <Modal open={reportModalState.isOpen} onClose={() => initModalClose()} aria-labelledby="modal-modal-title" aria-describedby="modal-modal-description">
      <Box sx={style}>
        <>
          <Typography id="modal-modal-title" variant="h2" component="h2" sx={{ margin: '30px 0' }}>
            펀딩 상세 보고서 제출
          </Typography>
          <ToastEditor
            ref={editorRef}
            placeholder="진행한 펀딩 내용에 대해 자세히 설명해주세요."
            height="500px"
            useCommandShortcut
            initialEditType="wysiwyg"
            onChange={editorChangeHandler}
            language="ko-KR"
            hideModeSwitch // 하단의 타입 선택 탭 숨기기
          />
          <div className={styles.labelDiv} style={{ marginTop: '3rem' }}>
            <p>파일첨부</p> <img style={{ marginRight: '1rem' }} src={requiredIcon} alt="required icon" />
            <input type="file" multiple onChange={onChangeFiles} />
          </div>
          {/* <div className={styles.fileList}>
            {reportData.receiptFile.map((file, index) => (
              // eslint-disable-next-line
              <p key={index}>
                {file.name} <AiOutlineClose className={styles.withdrawBtn} onClick={() => onClickDeleteFile(index)} />
              </p>
            ))}
          </div> */}
          {responseList.map((data, idx) => {
            return responseList.length > 1 && idx > 0 ? (
              // eslint-disable-next-line
              <div style={{ display: 'flex', marginTop: '10px' }} key={idx}>
                <Typography variant="h5" component="h4" sx={{ marginTop: '20px', marginRight: '20px' }}>
                  - {data.description}
                </Typography>
                <Typography variant="h5" component="h4" sx={{ marginTop: '20px' }}>
                  {data.amount}원
                </Typography>
                <HighlightOffIcon fontSize="large" color="error" sx={{ marginLeft: '1%', paddingTop: '0.5em', cursor: 'pointer' }} onClick={() => deleteHandler(idx)} />
              </div>
            ) : (
              ''
            );
          })}

          <form style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', height: '60px' }}>
            <TextField
              id="outlined-basic"
              label="펀딩 보고 내용을 60자 이내로 작성해주세요"
              variant="outlined"
              sx={{ marginTop: '5%', width: '73%' }}
              onChange={onChangeDescription}
              color="warning"
              value={descriptionText}
            />
            <TextField
              id="outlined-basic"
              label="금액 입력"
              variant="outlined"
              sx={{ marginTop: '5%', width: '23%', marginLeft: '3%' }}
              onChange={onChangeAmount}
              color="warning"
              value={amountText}
            />
            <AddBoxIcon fontSize="large" color="warning" sx={{ width: '4%', paddingTop: '1.5em', cursor: 'pointer' }} onClick={() => submitHandler()} />
          </form>

          <div style={{ marginTop: '5%', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
            <Button variant="contained" size="large" color="warning" onClick={() => test()}>
              등록하기
            </Button>
            <Button variant="outlined" size="large" color="warning" onClick={() => initModalClose()} sx={{ ml: 3 }}>
              취소
            </Button>
          </div>
        </>
      </Box>
    </Modal>
  );
}

export default ReportModal;
