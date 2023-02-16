import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';
import { requestTeamAccountInfo, requestTeamDonationList } from '../../../api/team';
import TeamSideBarList from '../../../components/TeamPageSideBar/TeamSideBarList';
import styles from './TeamDonationContainer.module.scss';

export type teamDonationType = {
  giftId: number;
  fundTitle: string;
  username: string;
  amount: number;
  giftDate: string;
  fundingId: number;
};

function TeamDonationContainer() {
  const navigate = useNavigate();
  const { teamId } = useParams();
  const [name, setName] = useState<string>('');
  const size = 8;
  const [page, setPage] = useState<number>(1);
  const [maxPage, setMaxPage] = useState<number>(1);
  const [donationList, setDonationList] = useState<teamDonationType[]>([]);

  useEffect(() => {
    requestTeamName();
  }, []);

  useEffect(() => {
    requestPageDonationList();
  }, [page]);

  /** 페이지 교체 */
  const handleChangePage = (e: React.ChangeEvent<any>, selectedPage: number) => {
    setPage(selectedPage);
  };

  /** 펀딩 상세 페이지로 */
  const onClickGoFunding = (fundingId: number) => {
    navigate(`/funding/detail/${fundingId}`);
  };

  /** 팀 이름 요청 */
  const requestTeamName = async () => {
    try {
      const response = await requestTeamAccountInfo();
      setName(response.data.name);
    } catch (error) {
      console.error(error);
    }
  };

  /** 도네이션 리스트 요청 */
  const requestPageDonationList = async () => {
    try {
      const response = await requestTeamDonationList(page - 1, size, 'giftDate,DESC');
      console.log('단체 도네이션 내역 요청', response);
      setMaxPage(response.data.totalPages);
      setDonationList(response.data.giftList);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div className={styles.container}>
      <TeamSideBarList teamId={teamId} />
      <div className={styles.content}>
        <div className={styles['content-inner']}>
          <h1 className={styles.title}>
            <span>{name}</span> 단체가 받은 기프트 내역
          </h1>
          <p className={styles.comment}>라이브 방송 중 후원받은 금액입니다.</p>
          <p className={styles.comment}>단체의 마일리지에 합산됩니다.</p>
          <ul className={styles['title-line']}>
            {/* <li className={styles['text-center']}>번호</li> */}
            <li className={styles['mobile-none']}>펀딩 제목</li>
            <li className={styles['mobile-none']}>후원자</li>
            <li>금액</li>
            <li>날짜</li>
          </ul>
          {donationList.map((data) => (
            <button
              type="button"
              key={data.giftId}
              className={styles['content-line']}
              onClick={() => {
                onClickGoFunding(data.fundingId);
              }}
            >
              {/* <li className={styles['text-center']}>{data.giftId}</li> */}
              <li className={styles['mobile-none']}>{data.fundTitle}</li>
              <li className={styles['mobile-none']}>{data.username}</li>
              <li>{data.amount.toLocaleString('ko-KR')} 원</li>
              <li>{data.giftDate}</li>
            </button>
          ))}
          <div className={styles['page-bar']}>
            <Stack spacing={2}>
              <Pagination showFirstButton showLastButton count={maxPage} variant="outlined" page={page} onChange={handleChangePage} />
            </Stack>
          </div>
        </div>
      </div>
    </div>
  );
}

export default TeamDonationContainer;
