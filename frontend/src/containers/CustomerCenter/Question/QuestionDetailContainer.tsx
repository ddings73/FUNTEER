import React, { useEffect, useState } from 'react';
import { AiOutlineFileText } from 'react-icons/ai';
import { BsFillImageFill } from 'react-icons/bs';
import { useNavigate, useParams } from 'react-router';
import { requestCreateReply, requestQNADetail, requestReply } from '../../../api/qna';
import { useAppSelector } from '../../../store/hooks';
import { QNADetailInterface } from '../../../types/qna';
import { customAlert, s1000 } from '../../../utils/customAlert';
import styles from './QuestionDetailContainer.module.scss';
import requiredIcon from '../../../assets/images/funding/required.svg';

const imgExpansion = ['.jpg', '.jpeg', '.png', '.gif', '.bmp', '.webp', '.svg', '.jfif', '.tif', '.tiff'];

function QuestionDetailContainer() {
  const navigate = useNavigate();
  const userType = useAppSelector((state) => state.userSlice.userType);
  const { qnaId } = useParams();
  const [QNAInfo, setQNAInfo] = useState<QNADetailInterface>({
    id: 0,
    title: '',
    content: '',
    respond: false,
    files: [] as File[],
    regDate: '',
  });
  const [reply, setReply] = useState<string>('');
  const [onReplyForm, setOnReplyForm] = useState<boolean>(false);

  useEffect(() => {
    if (qnaId) {
      requestQNAInfo();
      requestReplyInfo();
    }
  }, [qnaId]);

  const isImage = (fileUrl: string) => {
    // eslint-disable-next-line
    for (let i = 0; i < imgExpansion.length; i++) {
      if (fileUrl.endsWith(imgExpansion[i])) {
        return true;
      }
    }
    return false;
  };

  const onClickReply = () => {
    setOnReplyForm(true);
  };

  const onClickCreateReply = () => {
    requestCreate();
  };

  const onChangeReply = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setReply(e.target.value);
  };

  const requestQNAInfo = async () => {
    try {
      const response = await requestQNADetail(parseInt(qnaId as string, 10));
      console.log('QNA 상세 정보 요청', response);
      setQNAInfo(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const requestReplyInfo = async () => {
    try {
      const response = await requestReply(parseInt(qnaId as string, 10));
      console.log('답변 요청', response);
      setReply(response.data.content);
    } catch (error) {
      console.error(error);
    }
  };

  const requestCreate = async () => {
    try {
      const response = await requestCreateReply(parseInt(qnaId as string, 10), reply);
      console.log('답변 작성 요청', response);
      customAlert(s1000, '문의 답변 작성 완료');
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.content}>
        <div className={styles['title-div']}>
          <h1 className={styles.title}>{QNAInfo.title}</h1> <p className={styles['is-responded']}>{QNAInfo.respond ? '답변 완료' : '대기중'}</p>
        </div>
        <hr />
        <p className={styles['faq-content']}>{QNAInfo.content}</p>
        {QNAInfo.files?.map((file) => (
          <a href={Object.values(file)[0]} className={styles.file}>
            {isImage(Object.values(file)[0]) && <BsFillImageFill style={{ marginRight: '0.5rem' }} />}
            {!isImage(Object.values(file)[0]) && <AiOutlineFileText style={{ marginRight: '0.4rem' }} />}
            {Object.keys(file)[0]}
          </a>
        ))}
        {!QNAInfo.respond && userType === 'ADMIN' && (
          <>
            <hr />
            {!onReplyForm && (
              <div className={styles['fullWidth-div']}>
                <button type="button" className={styles['reply-button']} onClick={onClickReply}>
                  답변하기
                </button>
              </div>
            )}
            {onReplyForm && (
              <div>
                <div className={styles['label-div']}>
                  <p>
                    답변 내용 작성 <img src={requiredIcon} alt="required icon" />
                  </p>
                </div>
                <textarea rows={10} value={reply} onChange={onChangeReply} />
                <div className={styles['fullWidth-div']}>
                  <button type="button" className={styles['reply-button']} onClick={onClickCreateReply}>
                    등록
                  </button>
                </div>
              </div>
            )}
          </>
        )}
        {QNAInfo.respond && (
          <>
            <hr />
            <div className={styles['label-div']}>
              <p style={{ color: 'rgba(236, 153, 75, 1)' }}>답변</p>
            </div>
            <p className={styles['reply-text']}>{reply}</p>
          </>
        )}
        <button type="button" className={styles.back} onClick={() => navigate(-1)}>
          {'< 목록으로'}
        </button>
      </div>
    </div>
  );
}

export default QuestionDetailContainer;
