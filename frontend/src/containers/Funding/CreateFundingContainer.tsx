import React, { useState, useRef, useEffect, useMemo, useCallback } from 'react';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor as ToastEditor } from '@toast-ui/react-editor';
import { Dayjs } from 'dayjs';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import TextField from '@mui/material/TextField';
import { Box } from '@mui/system';
import LinearProgress from '@mui/material/LinearProgress';
import Tab from '@mui/material/Tab';
import { Button } from '@mui/material';
import Icon from '@mui/material/Icon';
import { BsLockFill } from 'react-icons/bs';
import styles from './CreateFundingContainer.module.scss';
import { requestUploadImage } from '../../api/funding';
import { FundingInterface } from '../../types/funding';
import defaultThumbnail from '../../assets/images/default-profile-img.svg';

interface TabPanelProps {
  // eslint-disable-next-line react/require-default-props
  children?: React.ReactNode;
  index: number;
  value: number;
}

function TabPanel(props: TabPanelProps) {
  const { children, value, index, ...other } = props;

  return (
    <div role="tabpanel" hidden={value !== index} id={`simple-tabpanel-${index}`} aria-labelledby={`simple-tab-${index}`} {...other}>
      {value === index && <div>{children}</div>}
    </div>
  );
}

function CreateFundingContainer() {
  const editorRef = useRef<ToastEditor>(null);
  const [fundingData, setFundingData] = useState<FundingInterface>({
    thumbnail: '',
    title: '',
    fundingDescription: '',
    category: '',
    content: '',
    startDate: '',
    endDate: '',
    amount1: '',
    description1: '',
    amount2: '',
    description2: '',
    amount3: '',
    description3: '',
  });
  const [thunmbnailPreview, setThumbnailPreview] = useState<string>();
  const [startDate, setStartDate] = useState<Dayjs | null>(null);
  const [endDate, setEndDate] = useState<Dayjs | null>(null);
  const [progress, setProgress] = useState<number>(100 / 3);
  const [tabIdx, setTabIdx] = useState<number>(0);

  const editorChangeHandler = (e: any) => {
    console.log(editorRef.current?.getInstance().getHTML());
  };

  const onUploadImage = async (blob: Blob, callback: any) => {
    const url = await requestUploadImage(blob);

    callback(url, 'fundingContents이미지');
  };

  const prevStageHandler = () => {
    if (progress > 100 / 3) {
      setProgress(progress - 100 / 3);
      setTabIdx(tabIdx - (1 % 3));
    }
  };
  const nextStageHandler = () => {
    if (progress < 100) {
      setProgress(progress + 100 / 3);
      setTabIdx(tabIdx + (1 % 3));
    }
  };

  const onFileHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (!e.target.files) {
      return;
    }
    const file = e.target.files[0];
    console.log(file);
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = () => {
      console.log(typeof reader.result);
      setThumbnailPreview(reader.result as string);
    };
  };

  const onChangeTextHandler = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setFundingData({ ...fundingData, [name]: value });
  };

  const onChangeDateHandler = (value: Dayjs | null) => {
    console.log(value);
  };

  // useEffect(() => {
  //   console.log(fundingData);
  // }, [fundingData]);

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <div className={styles['funding-thumbnail-box']}>
          <p className={styles.title}>펀딩 썸네일 사진 등록</p>
          <img src={thunmbnailPreview || defaultThumbnail} alt="thumbnail" className={styles['thumbnail-image']} />

          <input type="file" accept="image/*" onChange={onFileHandler} />
        </div>
        <div className={styles['funding-title-box']}>
          <p className={styles.title}>펀딩 제목</p>
          <input type="text" name="title" onChange={onChangeTextHandler} />
        </div>

        <div className={styles['funding-description-box']}>
          <p className={styles.title}>펀딩 요약 </p>
          <input type="text" name="fundingDescription" onChange={onChangeTextHandler} />
        </div>

        <div className={styles['funding-contents-box']}>
          <p className={styles.title}>펀딩 내용</p>
          <ToastEditor
            ref={editorRef}
            initialValue="펀딩 내용을 입력해주세요."
            height="500px"
            useCommandShortcut
            initialEditType="wysiwyg"
            onChange={editorChangeHandler}
            hooks={{ addImageBlobHook: onUploadImage }}
            language="ko-KR"
            hideModeSwitch // 하단의 타입 선택 탭 숨기기
          />
        </div>

        <div className={styles['funding-date-box']}>
          <p className={styles.title}>펀딩 기간 설정</p>

          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DatePicker
              label="시작 날짜를 선택해주세요"
              value={startDate}
              onChange={(newValue) => {
                onChangeDateHandler(newValue);
              }}
              renderInput={(params) => <TextField {...params} sx={{ mr: 2 }} />}
            />
          </LocalizationProvider>

          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DatePicker
              label="끝나는 날짜를 선택해주세요"
              value={endDate}
              onChange={(newValue) => {
                onChangeDateHandler(newValue);
              }}
              renderInput={(params) => <TextField {...params} sx={{ mx: 2 }} />}
            />
          </LocalizationProvider>
        </div>

        <div className={styles['funding-amount-box']}>
          <p className={styles.title}>펀딩 단계별 활동 설정</p>

          <div className={styles['progress-box']}>
            <div className={styles['stage-text-box']}>
              <p>시작</p>
              <p>1단계</p>
              <p>2단계</p>
              <p>3단계</p>
            </div>
            <LinearProgress variant="determinate" value={progress} />

            <TabPanel value={0} index={tabIdx}>
              <div className={styles['stage-contents-box']}>
                <input type="text" placeholder="금액을 입력해주세요" name="amount1" className={styles['money-input']} onChange={onChangeTextHandler} />
                <textarea placeholder="내용을 입력해주세요" className={styles['contents-textarea']} name="description1" onChange={onChangeTextHandler} />
              </div>
            </TabPanel>

            <TabPanel value={1} index={tabIdx}>
              <div className={styles['stage-contents-box']}>
                <input type="text" placeholder="금액을 입력해주세요" className={styles['money-input']} name="amount2" onChange={onChangeTextHandler} />
                <textarea placeholder="내용을 입력해주세요" className={styles['contents-textarea']} name="description2" onChange={onChangeTextHandler} />
              </div>
            </TabPanel>

            <TabPanel value={2} index={tabIdx}>
              <div className={styles['stage-contents-box']}>
                <input type="text" placeholder="금액을 입력해주세요" className={styles['money-input']} name="amount3" onChange={onChangeTextHandler} />
                <textarea placeholder="내용을 입력해주세요" className={styles['contents-textarea']} name="description3" onChange={onChangeTextHandler} />
              </div>
            </TabPanel>
            <div className={styles['stage-button-box']}>
              <button type="button" onClick={prevStageHandler}>
                이전 단계
              </button>

              <button type="button" onClick={nextStageHandler}>
                다음 단계
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default CreateFundingContainer;