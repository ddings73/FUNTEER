import React, { useEffect, useState } from 'react';
import { useInView } from 'react-intersection-observer';
import { useNavigate } from 'react-router-dom';
import MenuItem from '@mui/material/MenuItem';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import AdminDonationContainerItem, { DonationState } from './AdminDonationContainerItem';
import { requestAdminDonationList, requestDonationStatus, requestNextAdminDonationList } from '../../../api/donation';
import { DonationListElementType, DonationStatusModi } from '../../../types/donation';
import styles from './AdminDonationListContainer.module.scss';

function AdminDonationContainer() {
  const size = 10;
  const [donationList, setDonationList] = useState<DonationListElementType[]>([]);
  const [ref, inView] = useInView();
  const [donationStatusModi, setDonationStatusModi] = useState<DonationStatusModi>(
    {
      postType:"",
      donationId:0
    }
  );
  const navigate=useNavigate();

  const onStateChangeHandler = async (id: number, state: string, e: SelectChangeEvent) => {
    if (state === 'DONATION_ACTIVE') {
      
      // // long id requesetBody =>json 
      // setDonationStatusModi({...donationStatusModi,postType:state})
      // setDonationStatusModi({...donationStatusModi,donationId:id})
      try{
        const response = await requestDonationStatus(id, state);
        console.log(response);
      }catch(error){
        console.log(error);
      }
    } 
    window.location.reload();
  };
  const onClickDonationItemHandler = () => {
    console.log('도네이션 관리 상세 페이지 이동');
  };

  const onClickDonationRegister=()=>{
    console.log("작성페이지로");
    // admin/donation/creat
    navigate('create');
  }

  const requestDonationList = async () => {
    try {
      const response = await requestAdminDonationList(size);
      console.log(response)
      setDonationList(response.data)
    } catch (error) {
      console.error(error)
    }
  }

  useEffect(()=>{
    requestDonationList();
  },[])

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>도네이션 관리</h1>
        <button type="button" onClick={onClickDonationRegister} style={{margin:'0rem 0rem 1rem auto'}}>도네이션 작성</button>

        <ul className={styles['title-line']}>
          <li>번호</li>
          <li className={styles['title-col']}>제목</li>
          <li>목표금액</li>
          <li>시작일</li>
          <li>종료일</li>
          <li>상태</li>
        </ul>
        
        {donationList.map((data) => (
          <div className={styles['list-line']}>
            <li>
              <p>{data.id}</p>
            </li>
            <button type="button" className={styles['title-col-btn']} onClick={onClickDonationItemHandler}>
              <li>
                <p>{data.title}</p>
              </li>
            </button>
            <li>
              <p>{data.targetMoney}</p>
            </li>
            <li>
              <p>{data.startDate}</p>
            </li>
            <li>
              <p>{data.endDate}</p>
            </li>
            <li>
            <Select
                value={data.postType}
                onChange={(e) => onStateChangeHandler(data.id, data.postType, e)}
                className={data.postType.includes('ACTIVE') ? styles['show-approve'] : styles['hide-approve']}
              >
                <MenuItem value='DONATION_ACTIVE'>진행중</MenuItem>
                <MenuItem value='DONATION_CLOSE'>종료</MenuItem>
              </Select>
            </li>
          </div>
        ))}
      </div>
    </div>
  );
}

export default AdminDonationContainer;
