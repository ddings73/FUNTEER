import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import TextField from '@mui/material/TextField';
import { AxiosError } from 'axios';
import MenuItem from '@mui/material/MenuItem';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import { AiOutlineSearch, AiOutlineReload } from 'react-icons/ai';
import { Button } from '@mui/material';
import Pagination from '@mui/material/Pagination';
import styles from './AdminFundingContainer.module.scss';
import { requestAdminFundingList, requestFundingAccept } from '../../../api/admin';
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

const fundingStateMap = new Map<string, string>([
  ['전체', 'All'],
  ['승인 대기', 'FUNDING_WAIT'],
  ['승인', 'FUNDING_ACCEPT'],
  ['거절', 'FUNDING_REJECT'],
  ['진행중', 'FUNDING_IN_PROGRESS'],
  ['펀딩 실패', 'FUNDING_FAIL'],
  ['연장', 'FUNDING_EXTEND'],
  ['펀딩 완료', 'FUNDING_COMPLETE'],
  ['보고서 대기', 'REPORT_WAIT'],
  ['보고서 승인', 'REPORT_ACCEPT'],
  ['보고서 거부', 'REPORT_REJECT'],
  ['All', '전체'],
  ['FUNDING_WAIT', '승인 대기'],
  ['FUNDING_ACCEPT', '승인'],
  ['FUNDING_REJECT', '거절'],
  ['FUNDING_IN_PROGRESS', '진행중'],
  ['FUNDING_FAIL', '펀딩 실패'],
  ['FUNDING_EXTEND', '연장'],
  ['FUNDING_COMPLETE', '펀딩 완료'],
  ['REPORT_WAIT', '보고서 대기'],
  ['REPORT_ACCEPT', '보고서 승인'],
  ['REPORT_REJECT', '보고서 거부'],
]);

function AdminFundingContainer() {
  const navigate = useNavigate();
  const size = 8;
  const [page, setPage] = useState<number>(1);
  const [maxPage, setMaxPage] = useState<number>(0);
  const [pageFundings, setPageFundings] = useState<adminFundingListContainerItemType[]>([]);
  const [fundingSearch, setFundingSearch] = useState<string>('');
  const [fundingStateFilter, setFundingStateFilter] = useState<string>('All');
  const fundingStateSet = Object.values(FundingState);

  useEffect(() => {
    requestPageFundings();
  }, [page, fundingStateFilter]);

  /** 페이지 교체 */
  const handleChangePage = (e: React.ChangeEvent<any>, selectedPage: number) => {
    setPage(selectedPage);
  };

  /** 검색창 입력 */
  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFundingSearch(e.target.value);
  };

  /** 검색 */
  const handleClickSearch = async () => {
    if (page === 1) {
      requestPageFundings();
    } else {
      setPage(1);
    }
  };

  /** 엔터 검색 */
  const handleEnter = async (e: React.KeyboardEvent<HTMLDivElement>) => {
    if (e.key === 'Enter') {
      if (page === 1) {
        requestPageFundings();
      } else {
        setPage(1);
      }
    }
  };

  /** 검색 초기화 및 새로고침 */
  const handleClickInit = async () => {
    window.location.reload();
  };

  /** 필터 교체 */
  const handleChangeFilter = (e: SelectChangeEvent) => {
    setFundingStateFilter(e.target.value);
  };

  /** 펀딩 리스트 요청 */
  const requestPageFundings = async () => {
    setPageFundings([]);
    try {
      let response;
      if (fundingStateFilter === 'All') {
        response = await requestAdminFundingList(page - 1, size, fundingSearch);
      } else {
        response = await requestAdminFundingList(page - 1, size, fundingSearch, fundingStateFilter);
      }
      console.log('관리자 펀딩 리스트 요청', response);
      setMaxPage(response.data.totalPages);
      setPageFundings(response.data.content);
    } catch (error) {
      console.error(error);
    }
  };

  const onClickFundingItemHandler = (data: adminFundingListContainerItemType) => {
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
        } catch (error: unknown) {
          if (error instanceof AxiosError) {
            alert(error.response?.data.message);
            console.error(error);
          }
        }
      } else if (fundingState === 'REPORT_WAIT') {
        try {
          const response = await requestFundingAccept(id, true);
          console.log(response);
          customAlert(s1000, '펀딩 상태 변경 완료');
          setPage(1);
          requestPageFundings();
        } catch (error: unknown) {
          if (error instanceof AxiosError) {
            alert(error.response?.data.message);
            console.error(error);
          }
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
              <MenuItem key={state} value={fundingStateMap.get(state)} sx={{ height: '30px', fontSize: '0.9rem', fontFamily: 'NanumSquare' }}>
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
        {pageFundings.map((data) => (
          <div key={data.id} className={styles['list-line']}>
            <li>
              <p>{data.id}</p>
            </li>
            <button
              type="button"
              className={styles['title-col-btn']}
              onClick={() => {
                onClickFundingItemHandler(data);
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
              <p>{fundingStateMap.get(data.postType)}</p>
            </li>
            <li>
              <Select
                value={data.postType}
                sx={{ height: '30px', fontSize: '0.9rem', fontFamily: 'NanumSquare' }}
                onChange={(e) => handleStateChange(data.id, data.postType, e)}
                className={data.postType.includes('WAIT') ? styles['show-approve'] : styles['hide-approve']}
              >
                <MenuItem value={data.postType} sx={{ height: '30px', fontSize: '0.9rem', fontFamily: 'NanumSquare' }}>
                  {fundingStateMap.get(data.postType)}
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
