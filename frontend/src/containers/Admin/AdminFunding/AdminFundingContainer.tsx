import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import TextField from '@mui/material/TextField';
import MenuItem from '@mui/material/MenuItem';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import { Button } from '@mui/material';
import styles from './AdminFundingContainer.module.scss';
import { requestFundingList } from '../../../api/funding';
import { FundingState, adminFundingListContainerItemType } from './AdminFundingContainerItem';


function AdminFundingContainer() {
  const navigate = useNavigate();
  const [fundingList, setFundingList] = useState<adminFundingListContainerItemType[]>([]);
  const [fundingSearch, setFundingSearch] = useState<string>('');
  const [fundingStateFilter, setFundingStateFilter] = useState<string>('전체');
  const [approveState, setApproveState] = useState<string>('처리');

  const FundingStateMap = new Map<string, string>([
    ['All', '전체'],
    ['FUNDING_WAIT', "승인 대기"],
    ['FUNDING_ACCEPT', "승인"],
    ['FUNDING_REJECT', '거절'],
    ['FUNDING_INPROGRESS', '진행중'],
    ['FUNDING_FAIL', '펀딩 실패'],
    ['FUNDING_EXTEND', '연장'],
    ['FUNDING_COMPLETE', '펀딩 완료'],
    ['REPORT_WAIT', '보고서 대기'],
    ['REPORT_ACCEPT', '보고서 승인'],
    ['REPORT_REJECT', '보고서 거부']
  ])

  const stateValue = () => {
    console.log(FundingState)
  }

  useEffect(() => {
    stateValue();
  }, [])



  const onFundingSearchInputChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    console.log('asdasdasdas')
    setFundingSearch(e.target.value);
  };

  const onFundingStateFilterChangeHandler = (e: SelectChangeEvent) => {
    setFundingStateFilter(e.target.value);
  };

  const requestFunding = async () => {
    try {
      const { data } = await requestFundingList(10);
      setFundingList([...data.fundingListResponses.content])
    } catch (error) {
      console.log(error)
    }
  }

  useEffect(() => {
    requestFunding();
  }, [])

  const filtedFundings = fundingList.filter((funding) => {
    let filter;
    if (fundingStateFilter === '전체') {
      // filter = funding.id.toString().includes(fundingSearch) || funding.title.includes(fundingSearch) || funding.teamName.includes(fundingSearch);
      filter = funding.id.toString().includes(fundingSearch) || funding.title.includes(fundingSearch);

    } else {
      filter =
        (funding.id.toString().includes(fundingSearch) || funding.title.includes(fundingSearch) || funding.teamName.includes(fundingSearch)) &&
        FundingStateMap.get(funding.postType) === fundingStateFilter;
    }
    return filter;
  });

  const fundingStateSet = Object.values(FundingState);

  const onClickFundingItemHandler = (data: adminFundingListContainerItemType, e: React.MouseEvent<HTMLButtonElement>) => {
    navigate(`../../funding/detail/${data.id}`, { state : { data}})
    console.log('펀딩 관리 상세 페이지 이동');
  };

  const onStateChangeHandler = (state: string, e: SelectChangeEvent) => {
    if (state === FundingState.FUNDING_WAIT) {
      console.log('펀딩 승인 상태 변경 요청');
    } else if (state === FundingState.FUNDING_COMPLETE) {
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
            <button type="button" className={styles['title-col-btn']} onClick={(e) => {onClickFundingItemHandler(data, e)}}>
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
              <p>{FundingStateMap.get(data.postType)}</p>
            </li>
            <li>
              <Select
                value={FundingStateMap.get(data.postType)}
                onChange={(e) => onStateChangeHandler(data.postType, e)}
                className={data.postType.includes('WAIT') ? styles['show-approve'] : styles['hide-approve']}
              >
                <MenuItem value={data.postType}>{FundingStateMap.get(data.postType)}</MenuItem>
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
