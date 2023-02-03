import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import MenuItem from '@mui/material/MenuItem';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import { Button } from '@mui/material';
import styles from './AdminFundingContainer.module.scss';
import AdminFundingContainerItem, { FundingState } from './AdminFundingContainerItem';

function AdminFundingContainer() {
  const [fundingSearch, setFundingSearch] = useState<string>('');
  const [fundingStateFilter, setFundingStateFilter] = useState<string>(FundingState.All);
  const [approveState, setApproveState] = useState<string>('처리');

  const onFundingSearchInputChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFundingSearch(e.target.value);
  };

  const onFundingStateFilterChangeHandler = (e: SelectChangeEvent) => {
    setFundingStateFilter(e.target.value);
  };

  const filtedFundings = AdminFundingContainerItem.filter((funding) => {
    let filter;
    if (fundingStateFilter === '전체') {
      filter = funding.id.toString().includes(fundingSearch) || funding.title.includes(fundingSearch) || funding.teamName.includes(fundingSearch);
    } else {
      filter =
        (funding.id.toString().includes(fundingSearch) || funding.title.includes(fundingSearch) || funding.teamName.includes(fundingSearch)) &&
        funding.fundingState === fundingStateFilter;
    }
    return filter;
  });

  const fundingStateSet = Object.values(FundingState);

  const onClickFundingItemHandler = () => {
    console.log('펀딩 관리 상세 페이지 이동');
  };

  const onStateChangeHandler = (state: string, e: SelectChangeEvent) => {
    if (state === FundingState.NotFundApproved) {
      console.log('펀딩 승인 상태 변경 요청');
    } else if (state === FundingState.NotActApproved) {
      console.log('활동 종료 상태 변경 요청');
    }
    window.location.reload();
  };

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>펀딩 관리</h1>
        <div className={styles['search-div']}>
          <TextField label="펀딩 검색" variant="outlined" className={styles['search-input']} onChange={onFundingSearchInputChangeHandler} />
          <Select value={fundingStateFilter} onChange={onFundingStateFilterChangeHandler} sx={{ height: '40px' }}>
            {fundingStateSet.map((state) => (
              <MenuItem key={state} value={state}>
                {state}
              </MenuItem>
            ))}
          </Select>
        </div>

        <ul className={styles['title-line']}>
          <li>번호</li>
          <li className={styles['title-col']}>제목</li>
          <li>단체명</li>
          <li>시작일</li>
          <li>종료일</li>
          <li>상태</li>
          <li>승인</li>
        </ul>
        {filtedFundings.map((data) => (
          <div className={styles['list-line']}>
            <li>
              <p>{data.id}</p>
            </li>
            <button type="button" className={styles['title-col-btn']} onClick={onClickFundingItemHandler}>
              <li>
                <p>{data.title}</p>
              </li>
            </button>
            <li>
              <p>{data.teamName}</p>
            </li>
            <li>
              <p>{data.startDate}</p>
            </li>
            <li>
              <p>{data.endDate}</p>
            </li>
            <li>
              <p>{data.fundingState}</p>
            </li>
            <li>
              <Select
                value={data.fundingState}
                onChange={(e) => onStateChangeHandler(data.fundingState, e)}
                className={data.fundingState.includes('대기') ? styles['show-approve'] : styles['hide-approve']}
              >
                <MenuItem value={data.fundingState}>{data.fundingState}</MenuItem>
                <MenuItem value="approve">승인</MenuItem>
                <MenuItem value="deny">거부</MenuItem>
              </Select>
            </li>
          </div>
        ))}
      </div>
    </div>
  );
}

export default AdminFundingContainer;
