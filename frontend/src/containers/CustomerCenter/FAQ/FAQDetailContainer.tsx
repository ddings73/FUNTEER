import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { Key, South } from '@mui/icons-material';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Viewer } from '@toast-ui/react-editor';
import { height } from '@mui/system';
import styles from '../Notice/NoticeDetailContainer.module.scss';
import { requestNoticeDetail } from '../../../api/admin';
import { requestFaqDetail } from '../../../api/faq';
import { FaqDetailElementType, FaqElementType } from '../../../types/faq';


function FAQDetailContainer() {
  const navigate = useNavigate();
  const location = useLocation();
  const { faqIdx } = location.state.id;

  console.log(faqIdx)

  const [FAQDetail, setFAQDetail] = useState<FaqDetailElementType>({
      id: 0,
      title: '',
      content: '',
    });

  useEffect(() => {
    if (!FAQDetail.id) {
      setFAQDetail({...FAQDetail, id: faqIdx})
    } else {
      fetchData();
    }
  }, []);

  const fetchData = async () => {
    console.log(FAQDetail)
    if (FAQDetail.id) {
      try {
        const response = await requestFaqDetail(faqIdx);
        console.log('res: ', response);
        console.log('data res: ', response.data);
        // setFAQDetail({...response.data});
      } catch (error) {
        console.log(error);
      }
    }
  }

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>{FAQDetail.title}</h1>
        <hr />
        <div className={styles.content}>
          {FAQDetail.content && (
            <Viewer initialValue={ FAQDetail.content || ''}
            />
          )}
        </div>
        <hr />
        <button type="button" className={styles.back} onClick={() => navigate(-1)}>
          {'< 목록으로'}
        </button>
      </div>
    </div>
  );
}

export default FAQDetailContainer;
