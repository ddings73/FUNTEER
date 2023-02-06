import React from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { noticeContainerItemType } from './NoticeContainerItem';
import styles from './NoticleDetailContainer.module.scss';

function NoticleDetailContainer() {
  const navigate = useNavigate();
  const location = useLocation();
  const data = location.state.data as noticeContainerItemType;

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>{data.title}</h1>
        <p className={styles['post-date']}>{data.postDate}</p>
        <hr />
        <p className={styles.content}>{data.content}</p>
        <button type="button" className={styles.back} onClick={() => navigate(-1)}>
          {'< 목록으로'}
        </button>
      </div>
    </div>
  );
}

export default NoticleDetailContainer;
