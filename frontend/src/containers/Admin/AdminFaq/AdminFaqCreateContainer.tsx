import React, { ChangeEvent, useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor as ToastEditor } from '@toast-ui/react-editor';
import { Button } from '@mui/material';
import requiredIcon from '../../../assets/images/funding/required.svg';
import { useAppDispatch } from '../../../store/hooks';
import styles from './AdminCreateContainer.module.scss'; // <- css 코드 여기서 작성
import { http } from '../../../api/axios';
import { FaqInterface } from '../../../types/faq';
import { requestCreateFaq } from '../../../api/faq';


function AdminFaqCreateContainer() {
  /** 여기서 함수, 변수 선언하거나 axios 요청 */
  const navigate=useNavigate();
  const dispatch=useAppDispatch();
  const editorRef = useRef<ToastEditor>(null);
  const [faqData,setFaqData]=useState<FaqInterface>({
    title:'',
    content:'',
  })


  const editorChangeHandler=(e:any)=>{
    const text=editorRef.current?.getInstance().getHTML();
    if(text)
    setFaqData({...faqData, content:text});
  }

  const handleModal = () => {
    navigate(-1);
  };

  /** 취소 */
  const onClickBackHandler = () => {
    navigate(-1);
  };

  /** onChangeTextHandler */
  const onChangeTextHandler=(e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    if(name==='title')
    setFaqData({ ...faqData, title: value });
      
  }


  /** 등록 버튼 */
  const onCreateDonation=async()=>{
    try{
      const response=await requestCreateFaq(faqData);
      navigate(-1);
      console.log(response);
    }catch(error){
      console.log(error);
    }
  }

  /** 아래는 TSX 문법, HTML 코드 작성 */
  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>자체 모금 등록</h1>
        <div className={styles['label-div']}>
          <p>제목</p> <img src={requiredIcon} alt="required icon" />
        </div>
        <input name="title" type="text" className={styles['email-title']} placeholder="제목을 입력해주세요." onChange={onChangeTextHandler} />
        <div className={styles['label-div']}>
        <ToastEditor
            ref={editorRef}
            placeholder="자주 묻는 질문에 대해 자세히 설명해주세요."
            height="500px"
            useCommandShortcut
            initialEditType="wysiwyg"
            onChange={editorChangeHandler}
            language="ko-KR"
            hideModeSwitch // 하단의 타입 선택 탭 숨기기
          />
        </div>
        <div className={styles['btn-div']}>
          <Button variant="contained" className={styles.submit} onClick={onClickBackHandler}>
            취소
          </Button>
          <Button variant="contained" className={styles.submit} onClick={onCreateDonation}>
            등록
          </Button>
        </div>
      </div>
    </div>
  );
}

export default AdminFaqCreateContainer;
