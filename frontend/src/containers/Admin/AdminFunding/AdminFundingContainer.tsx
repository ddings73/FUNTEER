import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import MenuItem from '@mui/material/MenuItem';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import styles from './AdminFundingContainer.module.scss';
import AdminFundingContainerItem, { FundingState } from './AdminFundingContainerItem';

function AdminFundingContainer() {
  const [fundingSearch, setFundingSearch] = useState<string>('');
  const [fundingStateFilter, setFundingStateFilter] = useState<string>(FundingState.NotFundApproved);

  const onFundingSearchInputChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFundingSearch(e.target.value);
  };

  const onFundingStateFilterChangeHandler = (e: SelectChangeEvent) => {
    setFundingStateFilter(e.target.value);
  };

  const filtedFundings = AdminFundingContainerItem.filter((funding) => {
    const filter =
      (funding.id.toString().includes(fundingSearch) || funding.title.includes(fundingSearch) || funding.teamName.includes(fundingSearch)) &&
      funding.fundingState === fundingStateFilter;
    return filter;
  });

  const fundingStateSet = Object.values(FundingState);

  const onClickFundingItemHandler = () => {
    console.log('펀딩 관리 상세 페이지 이동');
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
          <li>제목</li>
          <li>단체명</li>
          <li>시작일</li>
          <li>종료일</li>
          <li>상태</li>
        </ul>
        {filtedFundings.map((data) => (
          <button type="button" key={data.id} className={styles['list-line']} onClick={onClickFundingItemHandler}>
            <li>
              <p>{data.id}</p>
            </li>
            <li>
              <p>{data.title}</p>
            </li>
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
          </button>
        ))}
      </div>
    </div>
  );
}

export default AdminFundingContainer;
