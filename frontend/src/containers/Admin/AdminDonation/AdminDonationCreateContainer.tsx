import React, { ChangeEvent, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Dayjs } from 'dayjs';
import { Button } from '@mui/material';
import { blob } from 'stream/consumers';
import requiredIcon from '../../../assets/images/funding/required.svg';
import { useAppDispatch } from '../../../store/hooks';
import { requestCreateDonation } from '../../../api/donation';
import { closeModal, openModal } from '../../../store/slices/modalSlice';
import styles from './AdminDonationContainer.module.scss'; // <- css 코드 여기서 작성
import { stringToSeparator } from '../../../types/convert';


function AdminDonationCreateContainer() {
  /** 여기서 함수, 변수 선언하거나 axios 요청 */
  const navigate=useNavigate();
  const dispatch=useAppDispatch();
  const [donationData,setDonationData]=useState({
    file:new Blob(),
    title:'',
    content:'',
    amount:'',
  })
  const [postType,setPostType]=useState('DONATION_ACTIVE');
  const [endDate,setEndDate]=useState<Dayjs|null>(null);
  const [startDate,setStartDate]=useState<Dayjs|null>(null);


  const handleModal = () => {
    navigate(-1);
  };

  /** 취소 */
  const onClickBackHandler = () => {
    dispatch(closeModal());
    navigate(-1);
  };

  /** onChangeTextHandler */
  const onChangeTextHandler=(e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    // 금액 입력중 숫자외의 문자가 들어오면 제거
    const regex = /[^0-9]/g;
    const separatorValue = stringToSeparator(value.replaceAll(regex, ''));
    
    switch (name) {
      case 'title':
        if (value.length <= 30) {
          setDonationData({ ...donationData, title: value });
        }

        break;
      case 'content':
        if (value.length < 40) {
          setDonationData({ ...donationData, content: value });
        }
        break;
      case 'amount':
        setDonationData({...donationData,amount:separatorValue});
        break;

      default:
        break;
    }
  }

  
  /** 등록 버튼 */
  const onCreateDonation=async()=>{
    try{
      const response=await requestCreateDonation(donationData);
      if(response.status===200){
        dispatch(openModal({isOpen:true,title:'도네이션 생성 성공',content : '도네이션 생성에 성공했습니다.',handleModal}));
      }
      console.log(response);
    }catch(error){
      console.log(error);
    }
  }


  /** 아래는 TSX 문법, HTML 코드 작성 */
  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>자체 모금 등록</h1>
        <div className={styles['label-div']}>
          <p>제목</p> <img src={requiredIcon} alt="required icon" />
        </div>
        <input name="title" type="text" className={styles['email-title']} placeholder="제목을 입력해주세요." onChange={onChangeTextHandler} />
        <div className={styles['label-div']}>
          <p>내용</p> <img src={requiredIcon} alt="required icon" />
        </div>
        <textarea name="content" className={styles['email-content']}  placeholder="내용을 입력해주세요."  onChange={(e)=>onChangeTextHandler}/>
        <div className={styles['label-div']}>
          <p>목표 금액</p> <img src={requiredIcon} alt="required icon" />
        </div>
        <input type="text" name="amount" className={styles['email-title']} placeholder="목표금액을 입력해주세요." onChange={onChangeTextHandler}/>
        <div className={styles['label-div']}>
          <p>첨부파일</p><img src={requiredIcon} alt="required icon" />
        </div>
        <input type="file" style={{margin:'0.5rem 0 1rem 0', width: '710px'}}/>
        <div className={styles['btn-div']}>
          <Button variant="contained" className={styles.submit} onClick={onClickBackHandler}>
            취소
          </Button>
          <Button variant="contained" className={styles.submit} onClick={onCreateDonation}>
            등록
          </Button>
        </div>
      </div>
    </div>
  );
}

export default AdminDonationCreateContainer;
