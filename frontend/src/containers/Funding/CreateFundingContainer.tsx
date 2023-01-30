import React,{useState,useRef,useEffect} from 'react'
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor as ToastEditor } from '@toast-ui/react-editor';
import { Dayjs } from 'dayjs';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import TextField from '@mui/material/TextField';
import styles from './CreateFundingContainer.module.scss'

function CreateFundingContainer(){
    const editorRef = useRef<ToastEditor>(null)
    const [startDate, setStartDate] = useState<Dayjs | null>(null);
    const [endDate, setEndDate] = useState<Dayjs | null>(null);
    

    const editorChangeHandler = (e:any)=>{
        console.log(editorRef.current?.getInstance().getHTML());
    }

    const onUploadImage = async(blob:Blob,callback:any)=>{

        // const url = await 이미지 업로드 함수
        console.log(blob);
        
        
    }

    useEffect(() => {
        console.log(startDate, endDate);
                
    
    }, [startDate,endDate])
    
    return (
        <div className={styles.container}>
            <div className={styles.contents}>
                <div className={styles['funding-title-box']}>
                    <p>펀딩 제목</p>
                    <input type="text" />
                </div>

                <div className={styles['funding-contents-box']}>
                    <p>펀딩 내용</p>
                    <ToastEditor
                    ref={editorRef}
                        initialValue="펀딩 내용을 입력해주세요."
                        height='500px'
                        useCommandShortcut
                        initialEditType="wysiwyg"
                        onChange={editorChangeHandler}
                        hooks={{ addImageBlobHook: onUploadImage }}
                        language="ko-KR"
                        hideModeSwitch // 하단의 타입 선택 탭 숨기기
                    />
                </div>

                <div className={styles['funding-date-box']}>
                    <p>펀딩 기간 설정</p>
                    <LocalizationProvider dateAdapter={AdapterDayjs}>
                     <DatePicker
                     label="시작 날짜를 선택해주세요"
                    value={startDate}
                    onChange={(newValue) => {
                        setStartDate(newValue);
                     }}
                        renderInput={(params) => <TextField {...params} />}
                    />
                    </LocalizationProvider>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <LocalizationProvider dateAdapter={AdapterDayjs}>
                     <DatePicker
                     label="끝나는 날짜를 선택해주세요"
                    value={endDate}
                    onChange={(newValue) => {
                        setEndDate(newValue);
                     }}
                        renderInput={(params) => <TextField {...params} />}
                    />
                    </LocalizationProvider>
                </div>

                <div className={styles['funding-amount-box']} />

                
            </div>
        </div>
    )
}

export default CreateFundingContainer;