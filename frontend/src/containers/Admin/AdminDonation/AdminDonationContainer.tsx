import React, { useState } from 'react';
import { useNavigate } from 'react-router';
import MenuItem from '@mui/material/MenuItem';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import styles from './AdminDonationListContainer.module.scss';
import AdminDonationContainerItem, { DonationState } from './AdminDonationContainerItem';

function AdminDonationContainer() {
  const navigate=useNavigate();

  const onStateChangeHandler = (state: string, e: SelectChangeEvent) => {
    if (state === DonationState.ACTIVE) {
      console.log('도네이션 종료');
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

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>도네이션 관리</h1>
        <button type="button" onClick={onClickDonationRegister} style={{margin:'0rem 0rem 1rem auto'}}>도네이션 작성</button>

        <ul className={styles['title-line']}>
          <li>번호</li>
          <li className={styles['title-col']}>제목</li>
          <li>목표금액</li>
          <li>현재금액</li>
          <li>시작일</li>
          <li>종료일</li>
          <li>상태</li>
        </ul>
        {AdminDonationContainerItem.map((data) => (
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
              <p>{data.targetAmount}</p>
            </li>
            <li>
              <p>{data.currentDonationAmount}</p>
            </li>
            <li>
              <p>{data.postDate}</p>
            </li>
            <li>
              <p>{data.endDate}</p>
            </li>
            <li>
              <p>{data.donationState}</p>
            </li>
            <li>
            <Select
                value={data.donationState}
                onChange={(e) => onStateChangeHandler(data.donationState, e)}
                className={data.donationState.includes('진행중') ? styles['show-approve'] : styles['hide-approve']}
              >
                <MenuItem value={data.donationState}>{data.donationState}</MenuItem>
                <MenuItem value="CLOSE">종료</MenuItem>
              </Select>
            </li>
          </div>
        ))}
      </div>
    </div>
  );
}

export default AdminDonationContainer;
