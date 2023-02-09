import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { Key, South } from '@mui/icons-material';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Viewer } from '@toast-ui/react-editor';
import { height } from '@mui/system';
import styles from '../../CustomerCenter/Notice/NoticeDetailContainer.module.scss';
import { DonationElementType } from '../../../types/donation';
import { requestDonationDetail } from '../../../api/donation';

function AdminDonationDetailContainer() {
  const navigate = useNavigate();
  const location = useLocation();
  const { dn } = useParams();
  console.log(dn)

  const [donationDetail, setDonationDetail] = useState<DonationElementType>({
    id: 0,
    title: '',
    content:'',
    targetAmount: '',
    currentAmount: '',
    startDate: '',
    endDate: '',
    postType: '',
    file:'',

  });

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await requestDonationDetail(Number(dn));
        console.log('res: ', response);
        console.log('data res: ', response.data);
        setDonationDetail(response.data);
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, []);


  return (
    <div className={styles.container}>
    <div className={styles.contents}>
      <h1 className={styles.title}>{donationDetail.title}</h1>
      <p className={styles.fundPeriod}>{donationDetail.startDate} ~ {donationDetail.endDate}</p>
      <div className={styles.object}>현재금액 : {donationDetail.currentAmount}원 / 목표금액: {donationDetail.targetAmount}원  </div>
      <hr />
      <div className={styles.content}>
        {donationDetail.content && (
          <Viewer initialValue={ donationDetail.content || ''}
          />
        )}
        <img src={donationDetail.file} alt="donationImage" />
      </div>
      
      <button type="button" className={styles.back} onClick={() => navigate(-1)}>
        {'< 목록으로'}
      </button>
    </div>
  </div>
  );
}

export default AdminDonationDetailContainer;
