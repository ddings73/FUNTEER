import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import styles from './NoticeContainer.module.scss';
import { requestNoticeList } from '../../../api/admin';

export type NoticeContainerItemType = {
  id: number;
  title: string;
  localDate: string;
};

function NoticeContainer() {
  const navigate = useNavigate();
  const [noticeList, setNoticeList] = useState<NoticeContainerItemType[]>([]);

  useEffect(() => {
    requestNotice();
  }, []);

  const onClickNoticeHandler = (data: NoticeContainerItemType) => {
    navigate(`./${data.id}`, { state: { data } });
  };

  const requestNotice = async () => {
    try {
      const response = await requestNoticeList();
      console.log(response);
      setNoticeList(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className={styles.container}>
      <ul className={styles['title-line']}>
        <li className={styles['mobile-none']}>번호</li>
        <li>제목</li>
        <li>작성일</li>
      </ul>
      {noticeList.map((data) => (
        <button
          type="button"
          key={data.id}
          className={styles['list-line']}
          onClick={() => {
            onClickNoticeHandler(data);
          }}
        >
          <li className={styles['mobile-none']}>
            <p>{data.id}</p>
          </li>
          <li>
            <p>{data.title}</p>
          </li>
          <li>
            <p>{data.localDate}</p>
          </li>
        </button>
      ))}
    </div>
  );
}

export default NoticeContainer;
