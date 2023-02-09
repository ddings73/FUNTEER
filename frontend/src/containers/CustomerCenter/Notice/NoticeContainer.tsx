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

  const onClickNoticeHandler = (data: NoticeContainerItemType, e: React.MouseEvent<HTMLButtonElement>) => {
    navigate(`./${data.id}`, { state: { data } });
  };

  const [noticeList, setNoticeList] = useState<NoticeContainerItemType[]>([]);

  const requestNotice = async () => {
    try {
      const response = await requestNoticeList();
      console.log(response)
      setNoticeList(response.data)
    } catch (error) {
      console.error(error)
    }
  }

  useEffect(() => {
    requestNotice();
  }, [])

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
            <p>{data.localDate}</p>
          </li>
        </button>
      ))}
    </div>
  );
}

export default NoticeContainer;
