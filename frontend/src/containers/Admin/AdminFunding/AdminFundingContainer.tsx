import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import TextField from '@mui/material/TextField';
import MenuItem from '@mui/material/MenuItem';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import { AiOutlineSearch, AiOutlineReload } from 'react-icons/ai';
import { Button } from '@mui/material';
import Pagination from '@mui/material/Pagination';
import styles from './AdminFundingContainer.module.scss';
import { requestAdminFundingList, requestFundingAccept } from '../../../api/admin';
import { requestAdminSearchedFundingList } from '../../../api/admin';
import { customAlert, s1000 } from '../../../utils/customAlert';

enum FundingState {
  All = '전체',
  FUNDING_WAIT = '승인 대기',
  FUNDING_ACCEPT = '승인',
  FUNDING_REJECT = '거절',
  FUNDING_INPROGRESS = '진행중',
  FUNDING_EXTEND = '연장',
  FUNDING_FAIL = '펀딩 실패',
  FUNDING_COMPLETE = '펀딩 완료',
  REPORT_WAIT = '보고서 대기',
  REPORT_ACCEPT = '보고서 승인',
  REPORT_REJECT = '보고서 거부',
}

type adminFundingListContainerItemType = {
  id: number;
  title: string;
  startDate: string;
  endDate: string;
  teamName: string;
  postType: FundingState;
};

const FundingStateMap = new Map<string, string>([
  ['All', '전체'],
  ['FUNDING_WAIT', '승인 대기'],
  ['FUNDING_ACCEPT', '승인'],
  ['FUNDING_REJECT', '거절'],
  ['FUNDING_INPROGRESS', '진행중'],
  ['FUNDING_FAIL', '펀딩 실패'],
  ['FUNDING_EXTEND', '연장'],
  ['FUNDING_COMPLETE', '펀딩 완료'],
  ['REPORT_WAIT', '보고서 대기'],
  ['REPORT_ACCEPT', '보고서 승인'],
  ['REPORT_REJECT', '보고서 거부'],
]);

function AdminFundingContainer() {
  const navigate = useNavigate();
  const [page, setPage] = useState<number>(1);
  const [maxPage, setMaxPage] = useState<number>(0);
  const [pageFundings, setPageFundings] = useState<adminFundingListContainerItemType[]>([]);
  const [fundingSearch, setFundingSearch] = useState<string>('');
  const [fundingStateFilter, setFundingStateFilter] = useState<string>('전체');

  useEffect(() => {
    if (!fundingSearch) {
      requestPageFundings();
    } else {
      requestPageFundingsWithKeyword();
    }
  }, [page]);

  /** 페이지 교체 */
  const handleChangePage = (e: React.ChangeEvent<any>, selectedPage: number) => {
    setPage(selectedPage);
  };

  /** 검색창 입력 */
  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFundingSearch(e.target.value);
  };

  /** 검색 */
  const handleClickSearch = async (e: React.MouseEvent<SVGElement>) => {
    setPage(1);
    requestPageFundingsWithKeyword();
  };

  /** 엔터 검색 */
  const handleEnter = async (e: React.KeyboardEvent<HTMLDivElement>) => {
    if (e.key === 'Enter') {
      setPage(1);
      requestPageFundingsWithKeyword();
    }
  };

  /** 검색 초기화 및 새로고침 */
  const handleClickInit = async () => {
    setFundingSearch('');
    setPage(1);
    requestPageFundings();
  };

  const handleChangeFilter = (e: SelectChangeEvent) => {
    setFundingStateFilter(e.target.value);
  };

  /** 일반 페이지 펀딩 요청 */
  const requestPageFundings = async () => {
    setPageFundings([]);
    try {
      const { data } = await requestAdminFundingList(page - 1, 8);
      console.log(data);
      setMaxPage(data.totalPages);
      setPageFundings([...data.content]);
    } catch (error) {
      console.error(error);
    }
  };

  /** 검색 동반 페이지 펀딩 요청 */
  const requestPageFundingsWithKeyword = async () => {
    setPageFundings([]);
    try {
      const { data } = await requestAdminSearchedFundingList(page - 1, 8, fundingSearch);
      console.log('검색 펀딩', data);
      setMaxPage(data.totalPages);
      setPageFundings([...data.content]);
    } catch (error) {
      console.error(error);
    }
  };

  const filtedFundings = pageFundings.filter((funding) => {
    let filter;
    if (fundingStateFilter === '전체') {
      filter = true;
    } else {
      filter = FundingStateMap.get(funding.postType) === fundingStateFilter;
    }
    return filter;
  });

  const fundingStateSet = Object.values(FundingState);

  const onClickFundingItemHandler = (data: adminFundingListContainerItemType, e: React.MouseEvent<HTMLButtonElement>) => {
    navigate(`../../funding/detail/${data.id}`, { state: { data } });
    console.log('펀딩 관리 상세 페이지 이동');
  };

  const handleStateChange = (id: number, state: string, e: SelectChangeEvent) => {
    requestChangeState(id, e.target.value, state);
  };

  /** 상태 변경 요청 */
  const requestChangeState = async (id: number, method: string, fundingState: string) => {
    if (method === 'accept') {
      if (fundingState === 'FUNDING_WAIT') {
        try {
          const response = await requestFundingAccept(id, false);
          console.log(response);
          customAlert(s1000, '펀딩 상태 변경 완료');
          setPage(1);
          requestPageFundings();
        } catch (error) {
          console.error(error);
        }
      } else if (fundingState === 'REPORT_WAIT') {
        try {
          const response = await requestFundingAccept(id, true);
          console.log(response);
          customAlert(s1000, '펀딩 상태 변경 완료');
          setPage(1);
          requestPageFundings();
        } catch (error) {
          console.error(error);
        }
      }
      // 펀딩 또는 보고서 거부 시 안내 페이지로
    } else if (method === 'reject') {
      navigate(`./reject/${id}`, {
        state: {
          id,
          fundingState,
        },
      });
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>펀딩 관리</h1>
        <div className={styles['search-filter-div']}>
          <div className={styles['search-div']}>
            <TextField
              size="small"
              color="warning"
              value={fundingSearch}
              label="펀딩 검색"
              variant="outlined"
              style={{ fontFamily: 'NanumSquare' }}
              className={styles['search-input']}
              onChange={handleSearchChange}
              onKeyPress={handleEnter}
            />
            <AiOutlineSearch onClick={handleClickSearch} />
            <AiOutlineReload onClick={handleClickInit} />
          </div>
          <Select
            value={fundingStateFilter}
            style={{ fontFamily: 'NanumSquare' }}
            onChange={handleChangeFilter}
            sx={{ height: '30px', fontSize: '0.9rem', fontFamily: 'NanumSquare' }}
          >
            {fundingStateSet.map((state) => (
              <MenuItem key={state} value={state} sx={{ height: '30px', fontSize: '0.9rem', fontFamily: 'NanumSquare' }}>
                {state}
              </MenuItem>
            ))}
          </Select>
        </div>
        <ul className={styles['title-line']}>
          <li style={{ maxWidth: '120px' }}>번호</li>
          <li className={styles['title-col']}>제목</li>
          <li style={{ maxWidth: '120px' }}>단체명</li>
          <li style={{ maxWidth: '120px' }}>시작일</li>
          <li style={{ maxWidth: '120px' }}>종료일</li>
          <li style={{ maxWidth: '120px' }}>상태</li>
          <li style={{ maxWidth: '120px' }}>승인</li>
        </ul>
        {filtedFundings.map((data) => (
          <div key={data.id} className={styles['list-line']}>
            <li>
              <p>{data.id}</p>
            </li>
            <button
              type="button"
              className={styles['title-col-btn']}
              onClick={(e) => {
                onClickFundingItemHandler(data, e);
              }}
            >
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
                value={data.postType}
                sx={{ height: '30px', fontSize: '0.9rem', fontFamily: 'NanumSquare' }}
                onChange={(e) => handleStateChange(data.id, data.postType, e)}
                className={data.postType.includes('WAIT') ? styles['show-approve'] : styles['hide-approve']}
              >
                <MenuItem value={data.postType} sx={{ height: '30px', fontSize: '0.9rem', fontFamily: 'NanumSquare' }}>
                  {FundingStateMap.get(data.postType)}
                </MenuItem>
                <MenuItem value="accept" sx={{ height: '30px', fontSize: '0.9rem', fontFamily: 'NanumSquare' }}>
                  승인
                </MenuItem>
                <MenuItem value="reject" sx={{ height: '30px', fontSize: '0.9rem', fontFamily: 'NanumSquare' }}>
                  거부
                </MenuItem>
              </Select>
            </li>
          </div>
        ))}
        <Pagination sx={{ marginTop: '2rem' }} count={maxPage} page={page} onChange={handleChangePage} />
      </div>
    </div>
  );
}

export default AdminFundingContainer;
