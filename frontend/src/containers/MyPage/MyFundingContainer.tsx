import React, { useEffect, useState } from 'react';
import { ToggleButton, ToggleButtonGroup } from '@mui/material';
import { requestFundingJoin } from '../../api/funding';
import LongCard from '../../components/Cards/LongCard';
import SideBarList from '../../components/MyPageSideBar/SideBarList';
import styles from './MyFundingContainer.module.scss';

export interface fundListType {
  amount: number;
  payDate: string;
  postId: number;
  postName: string;
}

export function MyFundingContainer() {
  // 페이지네이션
  const [page, setPage] = useState(0);
  const [fundList, setFundList] = useState<fundListType[]>([
    {
      amount: 0,
      payDate: '',
      postId: 0,
      postName: '',
    },
  ]);
  // 정렬 설정
  const [alignment, setAlignment] = useState('DESC');

  const handleChange = (event: React.MouseEvent<HTMLElement>, newAlignment: string) => {
    setAlignment(newAlignment);
  };

  const getFundJoinList = async () => {
    try {
      const res = await requestFundingJoin(page, 10, alignment);
      console.log(alignment);
      setFundList(res.data.list);
      console.log('펀딩참여 결과', res.data.list);
    } catch (e) {
      console.log(e);
    }
  };

  useEffect(() => {
    getFundJoinList();
  }, [alignment]);

  return (
    <div className={styles.bodyContainer}>
      <SideBarList />
      <div className={styles.contentContainer}>
        <div className={styles.backBox}>
          <h1 className={styles.title}>나의 펀딩 내역</h1>
          <div className={styles.toggleBtn}>
            <ToggleButtonGroup color="warning" value={alignment} exclusive onChange={handleChange} aria-label="Platform" className={styles.btnGrp}>
              <ToggleButton value="DESC">최신순</ToggleButton>
              <ToggleButton value="ASC">오래된순</ToggleButton>
            </ToggleButtonGroup>
          </div>
          <div className={styles.contentBox}>
            {fundList.map((data) => (
              <LongCard {...data} key={data.postId} />
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}

export default MyFundingContainer;
