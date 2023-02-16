import React, { useState, useRef, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router';
import { AiOutlineClose } from 'react-icons/ai';
import { Editor as ToastEditor } from '@toast-ui/react-editor';
import Button from '@mui/material/Button';
import { NoticeEditInterface } from '../../../types/notice';
import styles from './NoticeEditContainer.module.scss';
import requiredIcon from '../../../assets/images/funding/required.svg';
import { requestEditNotice } from '../../../api/admin';
import { customAlert, customTextOnlyAlert, noTimeSuccess, s1000 } from '../../../utils/customAlert';

function NoticeEditContainer() {
  const navigate = useNavigate();
  const location = useLocation();
  const { id, title, content, files } = location.state.NoticeDetail;
  const [noticeEditInfo, setNoticeEditInfo] = useState<NoticeEditInterface>({
    id,
    title,
    content,
    files,
    newFiles: [],
  });
  const editorRef = useRef<ToastEditor>(null);

  useEffect(() => {
    console.log('기존 파일', noticeEditInfo.files);
    console.log('새 파일', noticeEditInfo.newFiles);
  }, [noticeEditInfo]);

  const onChangeTitle = (e: React.ChangeEvent<HTMLInputElement>) => {
    setNoticeEditInfo({ ...noticeEditInfo, title: e.target.value });
  };

  const onChangeEditor = (e: any) => {
    const text = editorRef.current?.getInstance().getHTML();
    setNoticeEditInfo({ ...noticeEditInfo, content: text });
  };

  const onChangeFiles = (e: React.ChangeEvent<HTMLInputElement>) => {
    let tmp: File[] = [];
    if (e.target.files) {
      const list = e.target.files;
      Object.entries(list).forEach((data) => {
        tmp = [...tmp, data[1]];
      });
      const prev = noticeEditInfo.newFiles;
      setNoticeEditInfo({ ...noticeEditInfo, newFiles: [...prev, ...tmp] });
    }
  };

  const onClickDeleteFile = (index: number) => {
    setNoticeEditInfo({ ...noticeEditInfo, files: noticeEditInfo.files.filter((_, i) => i !== index) });
  };

  const onClickDeleteNewFile = (index: number) => {
    setNoticeEditInfo({ ...noticeEditInfo, newFiles: noticeEditInfo.newFiles.filter((_, i) => i !== index) });
  };

  const onClickEditBtn = () => {
    requestEdit();
  };

  const requestEdit = async () => {
    try {
      const response = await requestEditNotice(
        parseInt(noticeEditInfo.id, 10),
        noticeEditInfo.title,
        noticeEditInfo.content as string,
        noticeEditInfo.files,
        noticeEditInfo.newFiles,
      );
      console.log('공지사항 수정 요청', response);
      customTextOnlyAlert(noTimeSuccess, '공지사항 수정 완료');
      navigate(-1);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>공지사항 수정</h1>
        <div className={styles['label-div']}>
          <p>제목</p> <img src={requiredIcon} alt="required icon" />
        </div>
        <input value={noticeEditInfo.title} onChange={onChangeTitle} name="title" type="text" className={styles['email-title']} placeholder="제목을 입력해주세요." />
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
            initialValue={noticeEditInfo.content}
            onChange={onChangeEditor}
            language="ko-KR"
            hideModeSwitch
          />
        </div>
        <div className={styles['label-div']} style={{ marginTop: '3rem' }}>
          <p>파일첨부</p> <img style={{ marginRight: '1rem' }} src={requiredIcon} alt="required icon" />
          <input type="file" multiple onChange={onChangeFiles} />
        </div>
        {files && (
          <div className={styles['file-list']}>
            {noticeEditInfo.files.map((file, index) => (
              <p>
                {Object.keys(file)} <AiOutlineClose className={styles['withdraw-btn']} onClick={() => onClickDeleteFile(index)} />
              </p>
            ))}
            {noticeEditInfo.newFiles.map((newFile, index) => (
              <p>
                {newFile.name} <AiOutlineClose className={styles['withdraw-btn']} onClick={() => onClickDeleteNewFile(index)} />
              </p>
            ))}
          </div>
        )}
        <div className={styles['btn-div']}>
          <Button
            color="warning"
            variant="outlined"
            className={styles.cancel}
            onClick={() => {
              navigate(-1);
            }}
          >
            취소
          </Button>
          <Button color="warning" variant="outlined" className={styles.submit} onClick={onClickEditBtn}>
            등록
          </Button>
        </div>
      </div>
    </div>
  );
}

export default NoticeEditContainer;
