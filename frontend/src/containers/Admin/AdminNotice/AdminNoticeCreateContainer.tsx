import React, { useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Editor as ToastEditor } from '@toast-ui/react-editor';
import { AiOutlineClose } from 'react-icons/ai';
import Button from '@mui/material/Button';
import { requestCreateNotice } from '../../../api/admin';
import { NoticeInterface } from '../../../types/notice';
import styles from './AdminNoticeCreateContainer.module.scss';
import requiredIcon from '../../../assets/images/funding/required.svg';

function AdminNoticeCreateContainer() {
  const navigate = useNavigate();
  const [noticeData, setNoticeData] = useState<NoticeInterface>({
    files: [],
    title: '',
    content: '',
  });
  const editorRef = useRef<ToastEditor>(null);

  useEffect(() => {
    console.log(noticeData);
  }, [noticeData]);

  const onChangeTextHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setNoticeData({ ...noticeData, title: e.target.value });
  };

  const editorChangeHandler = (e: any) => {
    const text = editorRef.current?.getInstance().getHTML();
    setNoticeData({ ...noticeData, content: text });
  };

  const onChangeFiles = (e: React.ChangeEvent<HTMLInputElement>) => {
    let temp: File[] = [];
    if (e.target.files) {
      const list = e.target.files;
      Object.entries(list).forEach((el) => {
        temp = [...temp, el[1]];
      });
      const prev = noticeData.files;
      setNoticeData({ ...noticeData, files: [...prev, ...temp] });
      console.log('temp', temp);
    }
  };

  const onClickDeleteFile = (index: number) => {
    setNoticeData({ ...noticeData, files: noticeData.files.filter((_, i) => i !== index) });
  };

  const createNotice = async () => {
    try {
      const response = await requestCreateNotice(noticeData);
      navigate(-1);
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>공지사항 작성</h1>
        <div className={styles['label-div']}>
          <p>제목</p> <img src={requiredIcon} alt="required icon" />
        </div>
        <input value={noticeData.title} onChange={onChangeTextHandler} name="title" type="text" className={styles['email-title']} placeholder="제목을 입력해주세요." />
        <div className={styles['label-div']}>
          <p>내용</p> <img src={requiredIcon} alt="required icon" />
        </div>
        <div>
          <ToastEditor
            ref={editorRef}
            placeholder="공지사항 내용을 작성해주세요."
            height="500px"
            useCommandShortcut
            initialEditType="wysiwyg"
            onChange={editorChangeHandler}
            language="ko-KR"
            hideModeSwitch
          />
        </div>
        <div className={styles['label-div']} style={{ marginTop: '3rem' }}>
          <p>파일첨부</p> <img style={{ marginRight: '1rem' }} src={requiredIcon} alt="required icon" />
          <input type="file" multiple onChange={onChangeFiles} />
        </div>
        <div className={styles['file-list']}>
          {noticeData.files.map((file, index) => (
            <p>
              {file.name} <AiOutlineClose className={styles['withdraw-btn']} onClick={() => onClickDeleteFile(index)} />
            </p>
          ))}
        </div>
        <div className={styles['btn-div']}>
          <Button variant="contained" className={styles.submit} onClick={createNotice}>
            등록
          </Button>
        </div>
      </div>
    </div>
  );
}

export default AdminNoticeCreateContainer;
