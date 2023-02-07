import React, { useEffect, useState } from 'react';
import { useInView } from 'react-intersection-observer';
import { useNavigate } from 'react-router-dom';
import { requestAdminFaqList, requestNextAdminFaqList } from '../../../api/faq';
import { DonationElementType } from '../../../types/donation';
import { FaqElementType } from '../../../types/faq';
import styles from './AdminFaqContainer.module.scss'; // <- css 코드 여기서 작성
import AdminFaqContainerItem from './AdminFaqContainerItem';

function AdminFaqContainer() {
  const size = 10;
  const [faqList, setFaqList] = useState<FaqElementType[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [nextLoading, setNextLoading] = useState<boolean>(false);
  const [currentPage, setCurrentPage] = useState<number>(-1);
  const [isLastPage, setIsLastPAge] = useState<boolean>(false);
  const [ref, inView] = useInView();
  const navigate=useNavigate();

  const onClickFaqItemHandler = () => {
    console.log('Faq 관리 상세 페이지 이동');
  };

  const onClickFaqRegister=()=>{
    console.log("작성페이지로");
    navigate('create');
  }

  const initFaqList = async () => {
    try {
      setIsLoading(true);
      const { data } = await requestAdminFaqList(10);
      setFaqList([...data.faqListResponse.content]);
      setCurrentPage(data.faqListResponse.number);
      setIsLastPAge(data.faqListResponse.last);
      setIsLoading(false);
    } catch (error) {
      console.log(error);
    }
  };

  const nextFaqList = async () => {
    try {
      setNextLoading(true);
      const { data } = await requestNextAdminFaqList(currentPage, size);
      setFaqList([...faqList, ...data.faqListResponses.content]);
      setCurrentPage(data.faqListResponses.number);
      setIsLastPAge(data.faqListResponses.last);
      setNextLoading(false);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    initFaqList();
  }, []);

  useEffect(() => {
    if (inView && !isLastPage) {
      nextFaqList();
    }
  }, [inView]);

  return (
    <div className={styles.container}>
    <div className={styles.contents}>
      <h1 className={styles.title}>faq 관리</h1>
      <button type="button" onClick={onClickFaqRegister} style={{margin:'0rem 0rem 1rem auto'}}>faq 작성</button>

      <ul className={styles['title-line']}>
        <li>번호</li>
        <li className={styles['title-col']}>제목</li>
        <li>등록일</li>
       
      </ul>
      
      {faqList.map((data) => (
        <div className={styles['list-line']}>
          <li>
            <p>{data.id}</p>
          </li>
          <button type="button" className={styles['title-col-btn']} onClick={onClickFaqItemHandler}>
            <li>
              <p>{data.title}</p>
            </li>
          </button>
          <li>
            <p>{data.localDate}</p>
          </li>
        </div>
      ))}
    </div>
  </div>
  );
}

export default AdminFaqContainer;
