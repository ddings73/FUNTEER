import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';
import MenuItem from '@mui/material/MenuItem';
import Pagination from '@mui/material/Pagination';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import { requestAdminDonationList, requestDonationStatus } from '../../../api/donation';
import { DonationListElementType } from '../../../types/donation';
import styles from './AdminDonationContainer.module.scss';

enum DonationState {
  All = '전체',
  DONATION_ACTIVE = '진행중',
  DONATION_CLOSE = '종료',
}

const donationStateMap = new Map<string, string>([
  ['DONATION_ACTIVE', '진행중'],
  ['DONATION_CLOSE', '종료'],
]);

function AdminDonationContainer() {
  const navigate = useNavigate();
  const [page, setPage] = useState<number>(1);
  const [maxPage, setMaxPage] = useState<number>(0);
  const [donationList, setDonationList] = useState<DonationListElementType[]>([]);

  /** 최대 페이지를 못구했으면 먼저 구하고 도네 리스트 요청 */
  useEffect(() => {
    requestPageDonations();
  }, [page]);

  /** 도네이션 종료 */
  const onStateChangeHandler = async (id: number, state: string) => {
    Swal.fire({
      text: '자체 기부를 종료 하시겠습니까?',
      showConfirmButton: false,
      showDenyButton: true,
      showCancelButton: true,
      denyButtonText: `확인`,
      denyButtonColor: 'rgba(211, 79, 4, 1)',
      cancelButtonText: '취소',
    }).then((result) => {
      if (result.isDenied) {
        requestDonationQuit(id, state);
      }
    });
  };

  /** 도네이션 종료 요청 */
  const requestDonationQuit = async (id: number, state: string) => {
    if (state === 'DONATION_ACTIVE') {
      try {
        const response = await requestDonationStatus(id, state);
        console.log(response);
      } catch (error) {
        console.log(error);
      }
    }
  };

  /** 도네이션 상세 페이지로 */
  const onClickDonationItemHandler = (id: number) => {
    navigate(`${id}`);
  };

  /** 도네이션 작성 페이지로 */
  const onClickDonationRegister = () => {
    navigate('create');
  };

  /** 페이지 교체 */
  const handleChangePage = (e: React.ChangeEvent<any>, selectedPage: number) => {
    setPage(selectedPage);
  };

  /** 페이지 요청 */
  const requestPageDonations = async () => {
    setDonationList([]);
    try {
      const response = await requestAdminDonationList(8, page - 1);
      console.log(response);
      setMaxPage(response.data.totalPages);
      setDonationList(response.data.content);
    } catch (error) {
      console.error(error);
    }
  };

  /** 상태를 기준으로 정렬 */
  donationList.sort((a, b) => {
    if (a < b) {
      return -1;
    }

    if (a > b) {
      return 1;
    }

    return 0;
  });

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>기부 관리</h1>
        <button type="button" onClick={onClickDonationRegister} className={styles.create}>
          기부 작성
        </button>

        <ul className={styles['title-line']}>
          <li>번호</li>
          <li className={styles['title-col']}>제목</li>
          <li>목표금액</li>
          <li>시작일</li>
          <li>종료일</li>
          <li>상태</li>
        </ul>

        {donationList.map((data) => (
          <div key={data.donationId} className={styles['list-line']}>
            <li>
              <p>{data.donationId}</p>
            </li>
            <button type="button" className={styles['title-col-btn']} onClick={() => onClickDonationItemHandler(data.donationId)}>
              <li>
                <p>{data.title}</p>
              </li>
            </button>
            <li>
              <p>{parseInt(data.amount, 10).toLocaleString()}</p>
            </li>
            <li>
              <p>{data.startDate}</p>
            </li>
            <li>
              <p>{data.endDate}</p>
            </li>
            <li>
              <Select
                color="warning"
                value={data.postType}
                onChange={() => onStateChangeHandler(data.donationId, data.postType)}
                className={data.postType.includes('ACTIVE') ? styles['show-approve'] : styles['hide-approve']}
              >
                <MenuItem value="DONATION_ACTIVE" sx={{ fontSize: '0.9rem' }}>
                  진행중
                </MenuItem>
                <MenuItem value="DONATION_CLOSE" sx={{ fontSize: '0.9rem' }}>
                  종료
                </MenuItem>
              </Select>
              <p className={data.postType.includes('ACTIVE') ? styles['hide-approve'] : styles.quit}>종료</p>
            </li>
          </div>
        ))}

        <Pagination sx={{ marginTop: '2rem' }} count={maxPage} page={page} onChange={handleChangePage} />
      </div>
    </div>
  );
}

export default AdminDonationContainer;
