import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { requestTeamAccountInfo } from '../../../api/team';
import TeamSideBarList from '../../../components/TeamPageSideBar/TeamSideBarList';
import styles from './TeamDonationContainer.module.scss';

export type teamDonationType = {
  donateId: number;
  fundingTitle: string;
  username: string;
  amount: number;
  date: string;
};

function TeamDonationContainer() {
  const { teamId } = useParams();
  const [name, setName] = useState<string>('');
  const [donationList, setDonationList] = useState<teamDonationType[]>([]);

  const requestTeamName = async () => {
    try {
      const response = await requestTeamAccountInfo();
      setName(response.data.name);
    } catch (error) {
      console.error(error);
    }
  };

  const requestDonationList = () => {
    setDonationList([
      {
        donateId: 1,
        fundingTitle: '잠시만두에게 키보드를 사주세요.',
        username: '방봉빔',
        amount: 100000,
        date: '2023-02-03',
      },
      {
        donateId: 2,
        fundingTitle: '잠시만두에게 키보드를 사주세요.',
        username: '방봉빔',
        amount: 100000,
        date: '2023-02-03',
      },
      {
        donateId: 3,
        fundingTitle: '잠시만두에게 키보드를 사주세요.',
        username: '방봉빔',
        amount: 100000,
        date: '2023-02-03',
      },
      {
        donateId: 4,
        fundingTitle: '잠시만두에게 키보드를 사주세요.',
        username: '방봉빔',
        amount: 100000,
        date: '2023-02-03',
      },
      {
        donateId: 5,
        fundingTitle: '잠시만두에게 키보드를 사주세요.',
        username: '방봉빔',
        amount: 100000,
        date: '2023-02-03',
      },
    ]);
  };

  useEffect(() => {
    requestTeamName();
    requestDonationList();
  }, []);

  return (
    <div className={styles.container}>
      <TeamSideBarList teamId={teamId} />
      <div className={styles.content}>
        <div className={styles['content-inner']}>
          <h1 className={styles.title}>
            <span>{name}</span> 단체가 받은 도네이션 내역
          </h1>
          <ul className={styles['title-line']}>
            <li className={styles['text-center']}>번호</li>
            <li className={styles['mobile-none']}>펀딩 제목</li>
            <li className={styles['mobile-none']}>후원자</li>
            <li>금액</li>
            <li>날짜</li>
          </ul>
          {donationList.map((data) => (
            <ul key={data.donateId} className={styles['content-line']}>
              <li className={styles['text-center']}>{data.donateId}</li>
              <li className={styles['mobile-none']}>{data.fundingTitle}</li>
              <li className={styles['mobile-none']}>{data.username}</li>
              <li>{data.amount.toLocaleString('ko-KR')}</li>
              <li>{data.date}</li>
            </ul>
          ))}
        </div>
      </div>
    </div>
  );
}

export default TeamDonationContainer;
