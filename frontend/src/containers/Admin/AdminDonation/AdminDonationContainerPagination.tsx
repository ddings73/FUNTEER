import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
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
  const [donationFilter, setDonationFilter] = useState<string>(DonationState.All);

  useEffect(() => {
    if (!maxPage) {
      getMaxPage();
    } else {
      requestPageDonations();
    }
  }, [page]);

  const onStateChangeHandler = async (id: number, state: string) => {
    if (state === 'DONATION_ACTIVE') {
      try {
        const response = await requestDonationStatus(id, state);
        console.log(response);
      } catch (error) {
        console.log(error);
      }
    }
    window.location.reload();
  };

  const onClickDonationItemHandler = (id: number) => {
    navigate(`${id}`);
  };

  const onClickDonationRegister = () => {
    navigate('create');
  };

  const handleChangeFilter = (e: SelectChangeEvent<string>) => {
    setDonationFilter(e.target.value);
  };

  const filtedDonations = donationList.filter((donation) => {
    let filter;

    if (donationFilter === '전체') {
      filter = true;
    } else {
      filter = donationStateMap.get(donation.postType) === donationFilter;
    }

    return filter;
  });

  /** 페이지 교체 */
  const handleChangePage = (e: React.ChangeEvent<any>, selectedPage: number) => {
    setPage(selectedPage);
  };

  /** 최대 페이지 구하기 */
  const getMaxPage = async () => {
    try {
      const response = await requestAdminDonationList(10000);
      const pageCalc = response.data.length % 8 ? response.data.length / 8 + 1 : response.data.length / 8;
      setMaxPage(pageCalc);
    } catch (error) {
      console.error(error);
    }
  };

  /** 페이지 요청 */
  const requestPageDonations = async () => {
    setDonationList([]);
    try {
      const response = await requestAdminDonationList(8, page - 1);
      console.log(response);
      setDonationList(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const donationStateSet = Object.values(DonationState);

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>도네이션 관리</h1>

        <div className={styles.filter}>
          <button type="button" onClick={onClickDonationRegister} className={styles.create}>
            도네이션 작성
          </button>
          <Select value={donationFilter} onChange={handleChangeFilter} sx={{ height: '30px', margin: '0 0 0 0.5rem', fontSize: '0.9rem', fontFamily: 'NanumSquare' }}>
            {donationStateSet.map((state) => (
              <MenuItem key={state} value={state} sx={{ height: '30px', fontSize: '0.9rem', fontFamily: 'NanumSquare' }}>
                {state}
              </MenuItem>
            ))}
          </Select>
        </div>

        <ul className={styles['title-line']}>
          <li>번호</li>
          <li className={styles['title-col']}>제목</li>
          <li>목표금액</li>
          <li>시작일</li>
          <li>종료일</li>
          <li>상태</li>
        </ul>

        {filtedDonations.map((data) => (
          <div className={styles['list-line']}>
            <li>
              <p>{data.id}</p>
            </li>
            <button type="button" className={styles['title-col-btn']} onClick={() => onClickDonationItemHandler(data.id)}>
              <li>
                <p>{data.title}</p>
              </li>
            </button>
            <li>
              <p>{data.targetAmount}</p>
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
                onChange={() => onStateChangeHandler(data.id, data.postType)}
                className={data.postType.includes('ACTIVE') ? styles['show-approve'] : styles['hide-approve']}
              >
                <MenuItem value="DONATION_ACTIVE" sx={{ fontSize: '0.9rem', fontFamily: 'NanumSquare' }}>
                  진행중
                </MenuItem>
                <MenuItem value="DONATION_CLOSE" sx={{ fontSize: '0.9rem', fontFamily: 'NanumSquare' }}>
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
