import React, { useState, useRef, useEffect, useMemo } from 'react';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor as ToastEditor } from '@toast-ui/react-editor';
import dayjs, { Dayjs } from 'dayjs';
import LinearProgress from '@mui/material/LinearProgress';
import { useNavigate, useParams } from 'react-router-dom';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';

import { LocalizationProvider } from '@mui/x-date-pickers';
import { Accordion, AccordionDetails, AccordionSummary, Button, FormControl, Icon, InputLabel, MenuItem, Select, TextField, Typography } from '@mui/material';
import { SelectChangeEvent } from '@mui/material/Select';
import ArrowBackOutlinedIcon from '@mui/icons-material/ArrowBackOutlined';
import ArrowForwardOutlinedIcon from '@mui/icons-material/ArrowForwardOutlined';
import RoomIcon from '@mui/icons-material/Room';
import Tooltip, { TooltipProps, tooltipClasses } from '@mui/material/Tooltip';
import { styled } from '@mui/material/styles';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import styles from './ModifyFundingContainer.module.scss';
import { requestModifyFunding, requestUploadImage,requestFundingDetail, requestRegisterThumbnail } from '../../api/funding';
import { FundingInterface } from '../../types/funding';
import { useAppDispatch } from '../../store/hooks';
import {  openModal } from '../../store/slices/modalSlice';
import requiredIcon from '../../assets/images/funding/required.svg';
import uploadIcon from '../../assets/images/funding/upload.svg';
import { diffDayStartToEnd } from '../../utils/day';
import { stringToSeparator } from '../../utils/convert';
import TabPanel from '../../components/Funding/TabPanel';
import TabContent from '../../components/Funding/TabContent';

const HtmlTooltip = styled(({ className, ...props }: TooltipProps) => <Tooltip {...props} classes={{ popper: className }} />)(({ theme }) => ({
  [`& .${tooltipClasses.tooltip}`]: {
    backgroundColor: '#f5f5f9',
    color: 'rgba(0, 0, 0, 0.87)',
    maxWidth: 220,
    fontSize: theme.typography.pxToRem(12),
    border: '1px solid #dadde9',
  },
  [`& .title`]: {
    fontSize: '15px',
    fontWeight: 'bold',
  },
}));

function ModifyFundingContainer() {
  const {fundIdx}  = useParams()
  console.log(fundIdx)
  const thumbnailRef = useRef<HTMLInputElement>(null);
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  const editorRef = useRef<ToastEditor>(null);
  const [fundingData, setFundingData] = useState<FundingInterface>({
    thumbnail: "",
    title: '',
    fundingDescription: '',
    categoryId: 0,
    content: '',
    startDate: '',
    endDate: '',
    hashtags: '#tags',
    targetMoneyLevelOne: {
      amount: '',
      targetMoneyType: 'LEVEL_ONE',
      descriptions: [],
    },
    targetMoneyLevelTwo: {
      amount: '',
      targetMoneyType: 'LEVEL_TWO',
      descriptions: [],
    },
    targetMoneyLevelThree: {
      amount: '',
      targetMoneyType: 'LEVEL_THREE',
      descriptions: [],
    },
  });
  const [thunmbnailPreview, setThumbnailPreview] = useState<string>();
  const [progress, setProgress] = useState<number>(100 / 3);
  const [tabIdx, setTabIdx] = useState<number>(0);
  const [startDate, setStartDate] = useState<Dayjs | null>(null);
  const [endDate, setEndDate] = useState<Dayjs | null>(null);
  const [todoText, setTodoText] = useState<string>('');

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

    // 썸네일 S3등록
    const uploadS3Thumbnail = async(file:Blob)=>{
      try{
        const {data} = await requestRegisterThumbnail(file)
        setFundingData({...fundingData,thumbnail:data})
      }
      catch(error){
        console.log(error)
      }
  
    }

  const onFileHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (!e.target.files) {
      return;
    }
    const file = e.target.files[0];
    uploadS3Thumbnail(file)
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = () => {
      setThumbnailPreview(reader.result as string);
    };
  };

  // 목표 금액 Handler
  const onChangeTextHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    // 금액 입력중 숫자외의 문자가 들어오면 제거
    const regex = /[^0-9]/g;
    const separatorValue = stringToSeparator(value.replaceAll(regex, ''));
    switch (name) {
      case 'title':
        if (value.length <= 30) {
          setFundingData({ ...fundingData, title: value });
        }

        break;
      case 'fundingDescription':
        if (value.length < 40) {
          setFundingData({ ...fundingData, fundingDescription: value });
        }
        break;
      case 'LEVEL_ONE':
        setFundingData({ ...fundingData, targetMoneyLevelOne: { ...fundingData.targetMoneyLevelOne, amount: separatorValue } });
        break;
      case 'LEVEL_TWO':
        setFundingData({ ...fundingData, targetMoneyLevelTwo: { ...fundingData.targetMoneyLevelTwo, amount: separatorValue } });
        break;
      case 'LEVEL_THREE':
        setFundingData({ ...fundingData, targetMoneyLevelThree: { ...fundingData.targetMoneyLevelThree, amount: separatorValue } });
        break;
      default:
        break;
    }
  };

  // 해야할일 input 관리
  const onChangeTodoHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setTodoText(e.target.value);
  };

  const onChangeDateHandler = (value: Dayjs | null, type: string) => {
    const date = value?.format('YYYY-MM-DD');
    setFundingData({ ...fundingData, [type]: date });
  };

  const handleModal = () => {
    navigate("/");
  };

  const onModifyFunding = async () => {
    console.log(fundingData);
    try {
      const response = await requestModifyFunding(fundIdx as string,fundingData);
      if (response.status === 200) {
        dispatch(openModal({ isOpen: true, title: '펀딩 수정 성공', content: '펀딩 수정에 성공했습니다.', handleModal }));
      }
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };

  const onClickUpload = () => {
    if (thumbnailRef.current) thumbnailRef.current.click();
  };

  const onKeyDownHandler = (e: React.KeyboardEvent<HTMLInputElement>, level: string) => {
    if (e.key === 'Enter') addTodos(level);
  };
  const addTodos = (level: string) => {
    let prev;
    const todo = { description: todoText };
    // eslint-disable-next-line default-case
    switch (level) {
      case 'LEVEL_ONE':
        prev = fundingData.targetMoneyLevelOne.descriptions;
        setFundingData({ ...fundingData, targetMoneyLevelOne: { ...fundingData.targetMoneyLevelOne, descriptions: [...prev, todo] } });
        break;
      case 'LEVEL_TWO':
        prev = fundingData.targetMoneyLevelTwo.descriptions;
        setFundingData({ ...fundingData, targetMoneyLevelTwo: { ...fundingData.targetMoneyLevelTwo, descriptions: [...prev, todo] } });
        break;
      case 'LEVEL_THREE':
        prev = fundingData.targetMoneyLevelThree.descriptions;
        setFundingData({ ...fundingData, targetMoneyLevelThree: { ...fundingData.targetMoneyLevelThree, descriptions: [...prev, todo] } });
        break;
      default:
        break;
    }

    setTodoText('');
  };

  const removeTodo = (remove: number, level: string) => {
    let prev;
    let next;
    switch (level) {
      case 'LEVEL_ONE':
        prev = fundingData.targetMoneyLevelOne.descriptions;
        // eslint-disable-next-line no-case-declarations
        next = prev.filter((data, index) => index !== remove);
        setFundingData({ ...fundingData, targetMoneyLevelOne: { ...fundingData.targetMoneyLevelOne, descriptions: [...next] } });
        break;
      case 'LEVEL_TWO':
        prev = fundingData.targetMoneyLevelTwo.descriptions;
        // eslint-disable-next-line no-case-declarations
        next = prev.filter((data, index) => index !== remove);
        setFundingData({ ...fundingData, targetMoneyLevelTwo: { ...fundingData.targetMoneyLevelTwo, descriptions: [...next] } });
        break;
      case 'LEVEL_THREE':
        prev = fundingData.targetMoneyLevelThree.descriptions;
        // eslint-disable-next-line no-case-declarations
        next = prev.filter((data, index) => index !== remove);
        setFundingData({ ...fundingData, targetMoneyLevelThree: { ...fundingData.targetMoneyLevelThree, descriptions: [...next] } });
        break;

      default:
        break;
    }
  };

  const onChangeCategory = (event: SelectChangeEvent) => {
    setFundingData({ ...fundingData, categoryId: Number(event.target.value) });
  };

  const getFundingDetail = async()=>{
    try{
        const { data } = await requestFundingDetail(fundIdx)
        // setFundingData(response.data)
        setFundingData({...fundingData, 
          thumbnail:data.thumbnail,
        title: data.title,
        fundingDescription: data.fundingDescription,
        content: data.content,
        startDate: data.startDate,
        endDate: data.endDate,
        categoryId: data.categoryId,
        targetMoneyLevelOne: data.targetMoneyListLevelOne,
        targetMoneyLevelTwo: data.targetMoneyListLevelTwo,
        targetMoneyLevelThree: data.targetMoneyListLevelThree,
      })
      setStartDate(data.startDate)
      setEndDate(data.endDate)
      setThumbnailPreview(data.thumbnail)
    }
    catch(error){
        console.log(error)
    }
  }

  useEffect(() => {
    getFundingDetail()
  }, []);

  useEffect(()=>{
    editorRef.current?.getInstance().setHTML(fundingData.content as string);

  },[fundingData.content])

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <div className={styles['funding-thumbnail-box']}>
          <p className={styles.title}>
            프로젝트 대표 이미지 <img className={styles.required} src={requiredIcon} alt="" />
          </p>
          <p className={styles.subTitle}>후원자들이 프로젝트의 내용을 쉽게 파악할 수 있는 이미지를 올려주세요.</p>
          <div className={styles['thumbnail-upload-box']}>
            <img src={ thunmbnailPreview } alt="thumbnail" className={styles['thumbnail-image']} />

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
              <input ref={thumbnailRef} type="file" accept="image/*" onChange={onFileHandler} className={styles['thumbnail-upload-input']} required />
            </div>
          </div>
        </div>

        <div className={styles['funding-category-box']}>
          <p className={styles.title}>
            펀딩 카테고리 설정 <img className={styles.required} src={requiredIcon} alt="" />
          </p>

          <div className={styles['category-box']}>
            <FormControl fullWidth>
              <InputLabel id="demo-simple-select-label">카테고리 설정</InputLabel>
              <Select value={fundingData.categoryId as unknown as string} labelId="demo-simple-select-label" id="demo-simple-select" label="카테고리 설정" onChange={onChangeCategory}>
                <MenuItem >카테고리를 선택해주세요.</MenuItem>
                <MenuItem value={1}>아동</MenuItem>
                <MenuItem value={2}>노인</MenuItem>
                <MenuItem value={3}>동물</MenuItem>
                <MenuItem value={4}>장애인</MenuItem>
                <MenuItem value={5}>환경</MenuItem>
              </Select>
            </FormControl>
          </div>
        </div>
        <div className={styles['funding-title-box']}>
          <p className={styles.title}>
            펀딩 제목 <img className={styles.required} src={requiredIcon} alt="" />
          </p>
          <input
            type="text"
            value={fundingData.title}
            name="title"
            className={styles['input-text']}
            onChange={onChangeTextHandler}
            placeholder="제목을 입력해주세요"
            required
          />
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
            value={fundingData.fundingDescription}
            required
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
                label="펀딩 시작 일자를 선택해주세요."
                inputFormat="YYYY-MM-DD"

                value={startDate}
                onChange={(newValue) => {
                  setStartDate(newValue);
                  onChangeDateHandler(newValue, 'startDate');
                }}
                renderInput={(params) => <TextField {...params} sx={{ mr: 2, mb: 4, minWidth: 100, width: '49%' }} />}
              />
            </LocalizationProvider>

            <LocalizationProvider dateAdapter={AdapterDayjs} required>
              <DatePicker
                disablePast
                label="펀딩 종료 일자를 선택해주세요."
                minDate={startDate}
                inputFormat="YYYY-MM-DD"
                value={endDate}
                onChange={(newValue) => {
                  setEndDate(newValue);
                  onChangeDateHandler(newValue, 'endDate');
                }}
                renderInput={(params) => <TextField {...params} sx={{ minWidth: 100, mb: 3, width: '49%' }} />}
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
              <Tooltip title="asdas" placement="top">
                <Icon fontSize="large">
                  {' '}
                  <RoomIcon fontSize="large" sx={{ color: 'rgba(236, 153, 75, 1)' }} />
                </Icon>
              </Tooltip>

              <HtmlTooltip placement="top" title={<p className="title">최소 달성조건</p>}>
                <Icon fontSize="large">
                  {' '}
                  <RoomIcon fontSize="large" sx={tabIdx >= 0 ? { color: 'rgba(236, 153, 75, 1)' } : { color: 'rgb(109, 109, 109);' }} />
                </Icon>
              </HtmlTooltip>

              <HtmlTooltip placement="top" title={<p className="title">1단계 초과달성 </p>}>
                <Icon fontSize="large">
                  {' '}
                  <RoomIcon fontSize="large" sx={tabIdx >= 1 ? { color: 'rgba(236, 153, 75, 1)' } : { color: 'rgb(109, 109, 109);' }} />
                </Icon>
              </HtmlTooltip>

              <HtmlTooltip placement="top" title={<p className="title">2단계 초과달성 </p>}>
                <Icon fontSize="large">
                  {' '}
                  <RoomIcon fontSize="large" sx={tabIdx >= 2 ? { color: 'rgba(236, 153, 75, 1)' } : { color: 'rgb(109, 109, 109);' }} />
                </Icon>
              </HtmlTooltip>
            </div>
            <LinearProgress sx={{ height: 15, borderRadius: 2 }} variant="determinate" value={progress} color="warning" />

            <TabPanel value={0} index={tabIdx}>
              <TabContent
                  minAmount="0"
                data={fundingData.targetMoneyLevelOne}
                onChangeTextHandler={onChangeTextHandler}
                onChangeTodoHandler={onChangeTodoHandler}
                onKeyDownHandler={onKeyDownHandler}
                addTodos={addTodos}
                level="LEVEL_ONE"
                todoText={todoText}
                removeTodo={removeTodo}
              />
            </TabPanel>

            <TabPanel value={1} index={tabIdx}>
              <TabContent
                   minAmount={(fundingData.targetMoneyLevelOne.amount)}
                data={fundingData.targetMoneyLevelTwo}
                onChangeTextHandler={onChangeTextHandler}
                onChangeTodoHandler={onChangeTodoHandler}
                onKeyDownHandler={onKeyDownHandler}
                addTodos={addTodos}
                level="LEVEL_TWO"
                todoText={todoText}
                removeTodo={removeTodo}
              />
            </TabPanel>

            <TabPanel value={2} index={tabIdx}>
              <TabContent
                      minAmount = {(fundingData.targetMoneyLevelTwo.amount)}
                data={fundingData.targetMoneyLevelThree}
                onChangeTextHandler={onChangeTextHandler}
                onChangeTodoHandler={onChangeTodoHandler}
                onKeyDownHandler={onKeyDownHandler}
                addTodos={addTodos}
                level="LEVEL_THREE"
                todoText={todoText}
                removeTodo={removeTodo}
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
            <div className={styles['funding-preview']}>
            <Accordion className={styles.accordion}>
             <AccordionSummary  
              expandIcon={<ExpandMoreIcon />}
              aria-controls="panel1a-content"
               id="panel1a-header"
                >
        <Typography className={styles['accordion-title']}>최소 달성조건</Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Typography className={styles['funding-target-amount']}>목표 금액 :{fundingData.targetMoneyLevelOne.amount}</Typography>
          <ul>
           {fundingData.targetMoneyLevelOne.descriptions.map((list,index)=>(
            <li className={styles['target-todo']}>{index+1}. {list.description}</li>
           ))}
          </ul>
        </AccordionDetails>
      </Accordion>
      

      <Accordion className={styles.accordion}>
             <AccordionSummary
              expandIcon={<ExpandMoreIcon />}
              aria-controls="panel1a-content"
               id="panel1a-header"
                >
        <Typography className={styles['accordion-title']}>1단계 초과달성</Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Typography className={styles['funding-target-amount']}>목표 금액 :{fundingData.targetMoneyLevelTwo.amount}</Typography>
          <ul>
           {fundingData.targetMoneyLevelTwo.descriptions.map((list,index)=>(
            <li className={styles['target-todo']}>{index+1}. {list.description}</li>
           ))}
          </ul>
        </AccordionDetails>
      </Accordion>
      

      <Accordion className={styles.accordion}>
             <AccordionSummary
              expandIcon={<ExpandMoreIcon />}
              aria-controls="panel1a-content"
               id="panel1a-header"
                >
        <Typography className={styles['accordion-title']}>2단계 초과달성</Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Typography className={styles['funding-target-amount']}>목표 금액 :{fundingData.targetMoneyLevelThree.amount}</Typography>
          <ul>
           {fundingData.targetMoneyLevelThree.descriptions.map((list,index)=>(
            <li className={styles['target-todo']}>{index+1}. {list.description}</li>
           ))}
          </ul>
        </AccordionDetails>
      </Accordion>
      

      
            </div>
          </div>
          <Button variant="contained" type="button" className={styles['submit-button']} color="warning" onClick={onModifyFunding}>
            펀딩 수정하기
          </Button>
        </div>
      </div>
    </div>
  );
}

export default ModifyFundingContainer;
