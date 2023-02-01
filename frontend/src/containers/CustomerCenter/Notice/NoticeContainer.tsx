import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './NoticeContainer.module.scss';
import NoticeContainerItem, { noticeContainerItemType } from './NoticeContainerItem';

function NoticeContainer() {
  const navigate = useNavigate();

  const onClickNoticeHandler = (data: noticeContainerItemType, e: React.MouseEvent<HTMLButtonElement>) => {
    navigate(`./${data.id}`, { state: { data } });
  };

  return (
    <div className={styles.container}>
      <ul className={styles['title-line']}>
        <li className={styles['mobile-none']}>번호</li>
        <li>제목</li>
        <li>작성일</li>
      </ul>
      {NoticeContainerItem.map((data) => (
        <button
          type="button"
          key={data.id}
          className={styles['list-line']}
          onClick={(e) => {
            onClickNoticeHandler(data, e);
          }}
        >
          <li className={styles['mobile-none']}>
            <p>{data.id}</p>
          </li>
          <li>
            <p>{data.title}</p>
          </li>
          <li>
            <p>{data.postDate}</p>
          </li>
        </button>
      ))}
    </div>
  );
}

export default NoticeContainer;
