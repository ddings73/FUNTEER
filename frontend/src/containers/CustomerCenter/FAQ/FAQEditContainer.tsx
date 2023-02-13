import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router';
import { requestCreateFaq, requestModifyFaq } from '../../../api/faq';
import { FaqInterface } from '../../../types/faq';
import styles from './FAQCreateContainer.module.scss';
import requiredIcon from '../../../assets/images/funding/required.svg';
import { customAlert, s1000, w1500 } from '../../../utils/customAlert';

function FAQEditContainer() {
  const navigate = useNavigate();
  const location = useLocation();
  const { postId, groupOrPerson, title, content } = location.state;
  const [FAQEditInfo, setFAQEditInfo] = useState<FaqInterface>({
    id: postId,
    groupOrPerson,
    title,
    content,
  });

  const onChange = (e: React.ChangeEvent<HTMLSelectElement | HTMLInputElement | HTMLTextAreaElement>) => {
    if (FAQEditInfo.title.length > 40) {
      customAlert(w1500, '질문은 40자 이내로 적어주세요.');
      setFAQEditInfo({ ...FAQEditInfo, title: e.target.value.slice(0, 40) });
      return;
    }
    setFAQEditInfo({ ...FAQEditInfo, [e.target.name]: e.target.value });
  };

  const onClickCreate = () => {
    requestEdit();
  };

  const requestEdit = async () => {
    try {
      const response = await requestModifyFaq(FAQEditInfo);
      console.log('FAQ 수정 요청', response);
      customAlert(s1000, 'FAQ 수정이 완료되었습니다.');
      navigate(-1);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.content}>
        <h1 className={styles.title}>FAQ 작성</h1>
        <div className={styles['label-div']}>
          <p>
            유저 타입 <img src={requiredIcon} alt="required icon" />
          </p>
        </div>
        <select name="groupOrPerson" value={groupOrPerson} disabled>
          <option value={0}>개인</option>
          <option value={1}>단체</option>
        </select>
        <div className={styles['label-div']}>
          <p>
            질문 <img src={requiredIcon} alt="required icon" />
          </p>
        </div>
        <input type="text" name="title" value={FAQEditInfo.title} onChange={onChange} />
        <div className={styles['label-div']}>
          <p>
            답변 <img src={requiredIcon} alt="required icon" />
          </p>
        </div>
        <textarea rows={10} name="content" value={FAQEditInfo.content} onChange={onChange} />
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

export default FAQEditContainer;
