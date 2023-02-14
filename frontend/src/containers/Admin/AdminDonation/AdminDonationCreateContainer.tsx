import React, { useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import '@toast-ui/editor/dist/toastui-editor.css';
import { AiOutlineClose } from 'react-icons/ai';
import { Editor as ToastEditor } from '@toast-ui/react-editor';
import { Button } from '@mui/material';
import { requestCreateDonation } from '../../../api/donation';
import { DonationInterface } from '../../../types/donation';
import styles from './AdminDonationCreateContainer.module.scss';
import requiredIcon from '../../../assets/images/funding/required.svg';

function AdminDonationCreateContainer() {
  const navigate = useNavigate();
  const editorRef = useRef<ToastEditor>(null);
  const [donationData, setDonationData] = useState<DonationInterface>({
    file: null,
    title: '',
    content: '',
    amount: '',
  });

  const editorChangeHandler = (e: any) => {
    const text = editorRef.current?.getInstance().getHTML();
    if (text) setDonationData({ ...donationData, content: text });
  };

  const onFileHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (!e.target.files) {
      return;
    }
    console.log(e.target.files[0]);
    const fileUpload = e.target.files[0];
    setDonationData({ ...donationData, file: fileUpload });
  };

  /** onChangeTextHandler */
  const onChangeTextHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    // 금액 입력중 숫자외의 문자가 들어오면 제거
    const regex = /[^0-9]/g;
    const separatorValue = value.replaceAll(regex, '');

    switch (name) {
      case 'title':
        if (value.length <= 30) {
          setDonationData({ ...donationData, title: value });
        }

        break;

      case 'amount':
        setDonationData({ ...donationData, amount: separatorValue });
        break;

      default:
        break;
    }
  };

  /** 취소 버튼 */
  const onClickCancel = async () => {
    navigate(-1);
  };

  /** 등록 버튼 */
  const onCreateDonation = async () => {
    try {
      const response = await requestCreateDonation(donationData);
      console.log(response);
      navigate(-1);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>자체 모금 등록</h1>
        <div className={styles['label-div']}>
          <p>제목</p> <img src={requiredIcon} alt="required icon" />
        </div>
        <input name="title" type="text" className={styles['email-title']} placeholder="제목을 입력해주세요." onChange={onChangeTextHandler} />
        <div className={styles['label-div']}>
          <p>내용</p> <img src={requiredIcon} alt="required icon" />
        </div>
        <div>
          <ToastEditor
            ref={editorRef}
            placeholder="기부 행사에 대한 설명을 기입해주세요."
            height="500px"
            useCommandShortcut
            initialEditType="wysiwyg"
            onChange={editorChangeHandler}
            language="ko-KR"
            hideModeSwitch // 하단의 타입 선택 탭 숨기기
          />
        </div>
        <div className={styles['label-div']} style={{ marginTop: '3rem' }}>
          <p>목표 금액</p> <img src={requiredIcon} alt="required icon" />
        </div>
        <input type="text" name="amount" className={styles['email-title']} placeholder="목표금액을 입력해주세요." onChange={onChangeTextHandler} />
        <div className={styles['label-div']}>
          <p>첨부파일</p>
          <img src={requiredIcon} alt="required icon" />
        </div>
        <input name="file" type="file" style={{ width: '815px' }} accept="image/*" onChange={onFileHandler} required />

        <div className={styles['btn-div']}>
          <Button variant="contained" className={styles.submit} onClick={onClickCancel}>
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

export default AdminDonationCreateContainer;
