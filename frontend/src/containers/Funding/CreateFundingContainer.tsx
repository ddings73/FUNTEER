import React, { useState, useRef, useEffect, useMemo } from 'react';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor as ToastEditor } from '@toast-ui/react-editor';
import dayjs, { Dayjs } from 'dayjs';
import LinearProgress from '@mui/material/LinearProgress';
import { useNavigate } from 'react-router-dom';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';

import { LocalizationProvider } from '@mui/x-date-pickers';
import { Button, Icon, IconButton, TextField } from '@mui/material';
import AddCircleOutlineOutlinedIcon from '@mui/icons-material/AddCircleOutlineOutlined';
import ArrowBackOutlinedIcon from '@mui/icons-material/ArrowBackOutlined';
import ArrowForwardOutlinedIcon from '@mui/icons-material/ArrowForwardOutlined';
import styles from './CreateFundingContainer.module.scss';
import { requestCreateFunding, requestUploadImage } from '../../api/funding';
import { FundingInterface, amountLevelType } from '../../types/funding';
import defaultThumbnail from '../../assets/images/default-profile-img.svg';
import { useAppDispatch, useAppSelector } from '../../store/hooks';
import { openModal } from '../../store/slices/modalSlice';
import requiredIcon from '../../assets/images/funding/required.svg';
import uploadIcon from '../../assets/images/funding/upload.svg';
import { diffDayStartToEnd } from '../../utils/day';
import { stringToSeparator } from '../../types/convert';

interface TabPanelProps {
  // eslint-disable-next-line react/require-default-props
  children?: React.ReactNode;
  index: number;
  value: number;
}

type todoType = {
  description: string;
};

function TabPanel(props: TabPanelProps) {
  const { children, value, index, ...other } = props;

  return (
    <div role="tabpanel" hidden={value !== index} id={`simple-tabpanel-${index}`} aria-labelledby={`simple-tab-${index}`} {...other}>
      {value === index && <div>{children}</div>}
    </div>
  );
}

type TabContentPropsType = {
  data: amountLevelType;
  onChangeTextHandler: (e: React.ChangeEvent<HTMLInputElement>) => void;
  onChangeTodoHandler: (e: React.ChangeEvent<HTMLInputElement>) => void;
  onKeyDownHandler: (e: React.KeyboardEvent<HTMLInputElement>) => void;
  addTodos: () => void;
  level: string;
};
function TabContent(props: TabContentPropsType) {
  const { data, onChangeTextHandler, onChangeTodoHandler, onKeyDownHandler, addTodos, level } = props;
  return (
    <div className={styles['stage-contents-box']}>
      <div className={styles.stage}>
        <p className={styles['stage-title']}>목표 금액</p>
        <input
          type="text"
          value={data.amount}
          placeholder="펀딩 최소달성 금액을 입력해주세요"
          name={level}
          className={styles['input-text']}
          onChange={onChangeTextHandler}
        />

        <div className={styles['todo-list-box']}>
          <p className={styles['todo-list-title']}>기준 충족시 진행할 봉사의 내용을 입력해주세요.</p>
          {/* {todos.map((todo, index) => (
            <p className={styles['todo-contents']}>
              {index + 1}. {todo.description}
            </p>
          ))} */}

          <div className={styles['todo-input-box']}>
            <input
              type="text"
              className={styles['input-text']}
              // value={todoText}
              placeholder="내용을 입력해주세요."
              onChange={onChangeTodoHandler}
              onKeyDown={onKeyDownHandler}
            />
            <IconButton size="large" aria-label="delete" className={styles['create-button']} color="warning" onClick={addTodos}>
              <AddCircleOutlineOutlinedIcon fontSize="inherit" />
            </IconButton>
          </div>
        </div>
      </div>
    </div>
  );
}
function CreateFundingContainer() {
  const thumbnailRef = useRef<HTMLInputElement>(null);
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  const editorRef = useRef<ToastEditor>(null);
  const [fundingData, setFundingData] = useState<FundingInterface>({
    thumbnail: new Blob(),
    title: '',
    fundingDescription: '',
    categoryId: 1,
    content: '',
    startDate: '',
    endDate: '',
    hashtags: '#tags',
    LEVEL_ONE: {
      amount: '',
      descriptions: [],
    },
    LEVEL_TWO: {
      amount: '',
      descriptions: [],
    },
    LEVEL_THREE: {
      amount: '',
      descriptions: [],
    },
  });
  const [thunmbnailPreview, setThumbnailPreview] = useState<string>();
  const [progress, setProgress] = useState<number>(100 / 3);
  const [tabIdx, setTabIdx] = useState<number>(0);
  const [startDate, setStartDate] = useState<Dayjs | null>(null);
  const [endDate, setEndDate] = useState<Dayjs | null>(null);
  const [todoText, setTodoText] = useState<string>('');
  const [todos, setTodos] = useState<todoType[]>([]);

  const allFundingDays = useMemo(() => diffDayStartToEnd(fundingData.startDate, fundingData.endDate), [fundingData.startDate, fundingData.endDate]);

  const editorChangeHandler = (e: any) => {
    const text = editorRef.current?.getInstance().getHTML();

    setFundingData({ ...fundingData, content: text });
  };

  const onUploadImage = async (blob: Blob, callback: any) => {
    console.log(blob);

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
    setFundingData({ ...fundingData, thumbnail: file });
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = () => {
      setThumbnailPreview(reader.result as string);
    };
  };

  // 목표 금액 Handler
  const onChangeTextHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    const separatorValue = stringToSeparator(value);

    switch (name) {
      case 'LEVEL_ONE':
        setFundingData({ ...fundingData, LEVEL_ONE: { ...fundingData.LEVEL_ONE, amount: separatorValue } });
        break;
      case 'LEVEL_TWO':
        setFundingData({ ...fundingData, LEVEL_TWO: { ...fundingData.LEVEL_TWO, amount: separatorValue } });
        break;
      case 'LEVEL_THREE':
        setFundingData({ ...fundingData, LEVEL_TWO: { ...fundingData.LEVEL_THREE, amount: separatorValue } });
        break;
      default:
        break;
    }
  };

  const onChangeDateHandler = (value: Dayjs | null, type: string) => {
    const date = value?.format('YYYY-MM-DD');
    setFundingData({ ...fundingData, [type]: date });
  };

  const handleModal = () => {
    navigate(-1);
  };

  const onCreateFunding = async () => {
    try {
      const response = await requestCreateFunding(fundingData);
      if (response.status === 200) {
        dispatch(openModal({ isOpen: true, title: '펀딩 생성 성공', content: '펀딩 생성에 성공했습니다.', handleModal }));
      }
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };

  const onClickUpload = () => {
    if (thumbnailRef.current) thumbnailRef.current.click();
  };

  const onKeyDownHandler = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') addTodos();
  };
  const addTodos = () => {
    if (todoText) {
      setTodos([...todos, { description: todoText }]);
      setTodoText('');
    }
  };

  const onChangeTodoHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setTodoText(e.target.value);
  };

  useEffect(() => {
    console.log(fundingData);
  }, [fundingData]);

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <div className={styles['funding-thumbnail-box']}>
          <p className={styles.title}>
            프로젝트 대표 이미지 <img className={styles.required} src={requiredIcon} alt="" />
          </p>
          <p className={styles.subTitle}>후원자들이 프로젝트의 내용을 쉽게 파악할 수 있는 이미지를 올려주세요.</p>
          <div className={styles['thumbnail-upload-box']}>
            <img src={thunmbnailPreview || defaultThumbnail} alt="thumbnail" className={styles['thumbnail-image']} />

            <div className={styles['upload-button-box']} onClick={onClickUpload} aria-hidden="true">
              <p className={styles['upload-icon-box']}>
                <img className={styles['upload-icon']} src={uploadIcon} alt="업로드 아이콘" /> 이미지 업로드{' '}
              </p>
              <p className={styles.subDescription}>파일 형식은 jpg 또는 png로 업로드 해주세요. </p>
              <p className={styles.subDescription}>
                <span>
                  {' '}
                  이미지를 등록하면 즉시 반영됩니다. <br />
                </span>
              </p>
              <input ref={thumbnailRef} type="file" accept="image/*" onChange={onFileHandler} className={styles['thumbnail-upload-input']} />
            </div>
          </div>
        </div>
        <div className={styles['funding-title-box']}>
          <p className={styles.title}>
            펀딩 제목 <img className={styles.required} src={requiredIcon} alt="" />
          </p>
          <input type="text" name="title" className={styles['input-text']} onChange={onChangeTextHandler} placeholder="제목을 입력해주세요" />
        </div>

        <div className={styles['funding-description-box']}>
          <p className={styles.title}>
            펀딩 요약 설명 <img className={styles.required} src={requiredIcon} alt="" />
          </p>
          <input
            type="text"
            name="fundingDescription"
            onChange={onChangeTextHandler}
            className={styles['input-text']}
            placeholder="진행하는 펀딩에 대해 간단히 설명해 주세요."
          />
        </div>

        <div className={styles['funding-contents-box']}>
          <p className={styles.title}>
            펀딩 내용 <img className={styles.required} src={requiredIcon} alt="" />
          </p>
          <ToastEditor
            ref={editorRef}
            placeholder="진행하시는 펀딩에 대해 자세히 설명해주세요."
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
          <p className={styles.title}>
            펀딩 기간 설정 <img className={styles.required} src={requiredIcon} alt="" />
          </p>

          <div className={styles['date-picker-box']}>
            <LocalizationProvider dateAdapter={AdapterDayjs}>
              <DatePicker
                disablePast
                label="펀딩 시작 일자를 선택해주세요"
                inputFormat="YYYY-MM-DD"
                value={startDate}
                onChange={(newValue) => {
                  setStartDate(newValue);
                  onChangeDateHandler(newValue, 'startDate');
                }}
                renderInput={(params) => <TextField {...params} sx={{ mr: 2, mb: 5, minWidth: 300 }} />}
              />
            </LocalizationProvider>

            <LocalizationProvider dateAdapter={AdapterDayjs}>
              <DatePicker
                disablePast
                label="펀딩 종료 일자를 선택해주세요"
                minDate={startDate}
                inputFormat="YYYY-MM-DD"
                value={endDate}
                onChange={(newValue) => {
                  setEndDate(newValue);
                  onChangeDateHandler(newValue, 'endDate');
                }}
                renderInput={(params) => <TextField {...params} sx={{ minWidth: 300 }} />}
              />
            </LocalizationProvider>
          </div>

          {allFundingDays > 0 ? (
            <p className={styles['funding-days-text']}>총 {allFundingDays}일간 펀딩을 진행합니다.</p>
          ) : (
            <p className={styles['funding-days-text']}>시작 일자와 종료 일자를 선택해주세요.</p>
          )}
        </div>

        <div className={styles['funding-amount-box']}>
          <p className={styles.title}>
            펀딩 단계별 활동 설정 <img className={styles.required} src={requiredIcon} alt="" />
          </p>

          <div className={styles['progress-box']}>
            <div className={styles['stage-text-box']}>
              <p className={styles.subTitle}>시작</p>
              <p className={styles.subTitle}>최소 기준</p>
              <p className={styles.subTitle}>추가 모금 1단계 </p>
              <p className={styles.subTitle}>추가 모금 2단계</p>
            </div>
            <LinearProgress sx={{ height: 15, borderRadius: 2 }} variant="determinate" value={progress} color="warning" />

            <TabPanel value={0} index={tabIdx}>
              <TabContent
                data={fundingData.LEVEL_ONE}
                onChangeTextHandler={onChangeTextHandler}
                onChangeTodoHandler={onChangeTodoHandler}
                onKeyDownHandler={onKeyDownHandler}
                addTodos={addTodos}
                level="LEVEL_ONE"
              />
            </TabPanel>

            <TabPanel value={1} index={tabIdx}>
              <TabContent
                data={fundingData.LEVEL_TWO}
                onChangeTextHandler={onChangeTextHandler}
                onChangeTodoHandler={onChangeTodoHandler}
                onKeyDownHandler={onKeyDownHandler}
                addTodos={addTodos}
                level="LEVEL_TWO"
              />
            </TabPanel>

            <TabPanel value={2} index={tabIdx}>
              <TabContent
                data={fundingData.LEVEL_THREE}
                onChangeTextHandler={onChangeTextHandler}
                onChangeTodoHandler={onChangeTodoHandler}
                onKeyDownHandler={onKeyDownHandler}
                addTodos={addTodos}
                level="LEVEL_THREE"
              />
            </TabPanel>
            <div className={styles['stage-button-box']}>
              <Button className={styles['stage-button']} type="button" onClick={prevStageHandler} variant="outlined" startIcon={<ArrowBackOutlinedIcon />}>
                이전 단계
              </Button>

              <Button className={styles['stage-button']} type="button" onClick={nextStageHandler} endIcon={<ArrowForwardOutlinedIcon />}>
                다음 단계
              </Button>
            </div>
          </div>
          <Button variant="contained" type="button" className={styles['submit-button']} onClick={onCreateFunding}>
            펀딩 생성하기
          </Button>
        </div>
      </div>
    </div>
  );
}

export default CreateFundingContainer;
