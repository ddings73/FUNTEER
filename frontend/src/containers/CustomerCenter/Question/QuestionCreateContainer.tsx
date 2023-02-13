import React, { useState } from 'react';
import { useNavigate } from 'react-router';
import { AiOutlineClose } from 'react-icons/ai';
import { requestCreateFaq } from '../../../api/faq';
import { FaqInterface } from '../../../types/faq';
import styles from './QuestionCreateContainer.module.scss';
import requiredIcon from '../../../assets/images/funding/required.svg';
import { customAlert, s1000, w1500 } from '../../../utils/customAlert';
import { QNACreateInterface } from '../../../types/qna';
import { requestCreateQNA } from '../../../api/qna';

function QuestionCreateContainer() {
  const navigate = useNavigate();
  const [QNAInfo, setQNAInfo] = useState<QNACreateInterface>({
    title: '',
    content: '',
    files: [],
  });

  const onChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    if (QNAInfo.title.length > 40) {
      customAlert(w1500, '질문은 40자 이내로 적어주세요.');
      setQNAInfo({ ...QNAInfo, title: e.target.value.slice(0, 40) });
      return;
    }
    setQNAInfo({ ...QNAInfo, [e.target.name]: e.target.value });
  };

  const onChangeFiles = (e: React.ChangeEvent<HTMLInputElement>) => {
    let tmp: File[] = [];
    if (e.target.files) {
      const list = e.target.files;
      Object.entries(list).forEach((data) => {
        tmp = [...tmp, data[1]];
      });
      const prev = QNAInfo.files as File[];
      setQNAInfo({ ...QNAInfo, files: [...prev, ...tmp] });
    }
  };

  const onClickDeleteFile = (index: number) => {
    if (QNAInfo.files) {
      setQNAInfo({ ...QNAInfo, files: QNAInfo.files.filter((_, i) => i !== index) });
    }
  };

  const onClickCreate = () => {
    requestCreate();
  };

  const requestCreate = async () => {
    try {
      const response = await requestCreateQNA(QNAInfo);
      console.log('FAQ 생성 요청', response);
      customAlert(s1000, '1:1 문의가 등록되었습니다.');
      navigate(-1);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.content}>
        <h1 className={styles.title}>1:1 문의 작성</h1>
        <div className={styles['label-div']}>
          <p>
            질문 <img src={requiredIcon} alt="required icon" />
          </p>
        </div>
        <input type="text" name="title" value={QNAInfo.title} className={styles['content-title']} onChange={onChange} />
        <div className={styles['label-div']}>
          <p>
            답변 <img src={requiredIcon} alt="required icon" />
          </p>
        </div>
        <textarea rows={10} name="content" value={QNAInfo.content} onChange={onChange} />
        <div className={styles['label-div']}>
          <p>첨부 파일</p>
        </div>
        <input type="file" multiple onChange={onChangeFiles} />
        <div className={styles['file-list']}>
          {QNAInfo.files &&
            QNAInfo.files.map((file, index) => (
              <p key={file.name}>
                {file.name} <AiOutlineClose className={styles['withdraw-btn']} onClick={() => onClickDeleteFile(index)} />
              </p>
            ))}
        </div>
        <div className={styles['btn-div']}>
          <button
            type="button"
            onClick={() => {
              navigate(-1);
            }}
          >
            취소
          </button>
          <button type="button" onClick={onClickCreate}>
            등록
          </button>
        </div>
      </div>
    </div>
  );
}

export default QuestionCreateContainer;
