import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { Key, South } from '@mui/icons-material';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Viewer } from '@toast-ui/react-editor';
import { height } from '@mui/system';
import styles from './NoticeDetailContainer.module.scss';
import { requestNoticeDetail } from '../../../api/admin';

export type NoticeDetailType = {
  id: string;
  title: string;
  content: string;
  localDate: string;
  files: Map<string, string>[];
};

function NoticleDetailContainer() {
  const navigate = useNavigate();
  const location = useLocation();

  const data = location.state.data as NoticeDetailType;

  const [NoticeDetail, setNoticeDetail] = useState<NoticeDetailType>({
      id: '',
    title: '',
    content: '',
    localDate: '',
    files: [],
    });

  const requestNotice = async () => {
    try {
      const response = await requestNoticeDetail(data.id);
      setNoticeDetail(response.data)
      const ffff = response.data.files[0]
      // console.log(ffff)
      // console.log(ffff['pngwing.com (3).png'])      
      // console.log(Object.keys(ffff))
    } catch (error) {
      console.error(error)
    }
  }

  useEffect(() => {
    requestNotice();
  }, [])

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>{NoticeDetail.title}</h1>
        <p className={styles['post-date']}>{NoticeDetail.localDate}</p>
        <hr />
        <div className={styles.content}>
          {NoticeDetail.content && (
            <Viewer initialValue={ NoticeDetail.content || ''}
            />
          )}
        </div>
        <hr />
        <p className={styles['post-date']}>첨부파일</p>
        { NoticeDetail.files.map((file) => (
  
        <a href={Object.values(file)[0]}>{Object.keys(file)[0]}</a>
        
        ))}
        <button type="button" className={styles.back} onClick={() => navigate(-1)}>
          {'< 목록으로'}
        </button>
      </div>
    </div>
  );
}

export default NoticleDetailContainer;
