import React, { useState, useEffect, useCallback, useMemo } from 'react';
import CountUp from 'react-countup';

import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import { Box } from '@mui/system';
import TextField from '@mui/material/TextField';
import InputAdornment from '@mui/material/InputAdornment';
import SearchIcon from '@mui/icons-material/Search';
import { Link, NavLink } from 'react-router-dom';
import CircularProgress from '@mui/material/CircularProgress';
import { useInView } from 'react-intersection-observer';
import cn from 'classnames';
import Skeleton from '@mui/material/Skeleton';
import Typography, { TypographyProps } from '@mui/material/Typography';
import { async } from 'q';
import { Button } from '@mui/material';
import styles from './FundingListContainer.module.scss';
import FundingListElement from '../../components/Funding/FundingListElement';
import { FundingElementType } from '../../types/funding';
import { requestCategoryFundingList, requestFundingList, requestFundingSearch, requestNextFundingList } from '../../api/funding';
// icon
import disable from '../../assets/images/funding/categoryIcon/disable.png';
import child from '../../assets/images/funding/categoryIcon/child.png';
import animal from '../../assets/images/funding/categoryIcon/animal.png';
import oldman from '../../assets/images/funding/categoryIcon/oldman.png';
import planet from '../../assets/images/funding/categoryIcon/planet.png';
import FundingElementSkeleton from '../../components/Skeleton/FundingElementSkeleton';

// 한번에 불러올 게시글 수
const size = 12;
const categoryList = [
  { id: '4', url: disable, caption: '장애인' },
  { id: '1', url: child, caption: '아동' },
  { id: '3', url: animal, caption: '동물' },
  { id: '2', url: oldman, caption: '노인' },
  { id: '5', url: planet, caption: '환경' },
];
function FundingListContainer() {
  const [fundingList, setFundingList] = useState<FundingElementType[]>([]);
  const [successFundingCount, setSuccessFundingCount] = useState<number>(0);
  const [totalFundingAmount, setTotalFundingAmount] = useState<number>(0);
  const [totalFundingCount, setTotalFundingCount] = useState<number>(0);
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [nextLoading, setNextLoading] = useState<boolean>(false);
  const [currentPage, setCurrentPage] = useState<number>(0);
  const [totalPage, setTotalPage] = useState<number>(0);
  const [ref, inView] = useInView();
  const [selectIdx, setSelectIdx] = useState<number>(1);
  const [fundingStateFilter, setFundingStateFilter] = useState<string>('FUNDING_IN_PROGRESS');
  const [categoryId, setCategoryId] = useState<string>('');
  const [searchText, setSearchText] = useState<string>('');

  // 펀딩개수 계싼
  const fundingCount = useMemo(() => {
    return totalFundingCount;
  }, [totalFundingCount]);

  const handleTextChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setSearchText(value);
  };

  const hanlderFilter = (id: string, idx: number) => {
    // 버튼 색 변경
    setSelectIdx(idx);

    // 필터 타입 변경
    setFundingStateFilter(id);
  };

  const getFundingList = async () => {
    try {
      setIsLoading(true);
      const { data } = await requestFundingList(categoryId, searchText, fundingStateFilter, 0, size);
      console.log(data);
      setFundingList([...data.fundingListResponses]);
      setSuccessFundingCount(data.successFundingCount);
      setTotalFundingAmount(data.inProgressFundingAmount);
      setTotalFundingCount(data.totalElements );
      setCurrentPage(data.number);
      setTotalPage(data.totalPages);
      console.log(data);
      setIsLoading(false);
    } catch (error) {
      console.log(error);
    }
  };

  const nextFundingList = async () => {
    try {
      setNextLoading(true);
      const { data } = await requestFundingList(categoryId, searchText, fundingStateFilter, currentPage + 1, size);
      console.log(data);
      setFundingList([...fundingList, ...data.fundingListResponses]);
      setCurrentPage(data.number);
      setTotalPage(data.totalPages);
      setNextLoading(false);
    } catch (error) {
      console.error(error);
    }
  };
  const onToggleCategory = async (id: string) => {
    if (categoryId === id) {
      setCategoryId('');
    } else {
      setCategoryId(id);
    }
  };

  useEffect(() => {
    getFundingList();
  }, [searchText, fundingStateFilter, categoryId]);

  useEffect(() => {
    if (inView) {
      nextFundingList();
    }
  }, [inView]);

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <div className={styles['title-box']}>
          <div className={styles['description-box']}>
            <p>
              당신의 착한 마음을 <br /> <span style={{ color: 'rgba(236, 153, 75, 1)' }}>FUNTEER</span>가 응원합니다.
            </p>
          </div>
          <div className={styles['statistic-box']}>
            <div>
              <p>
                <CountUp start={0} end={successFundingCount} separator="," duration={2} />건 <br /> 봉사 펀딩에 성공했어요.
              </p>
            </div>
            <div>
              <p>
                <CountUp start={0} end={totalFundingAmount} separator="," duration={2} />원 <br /> 기부에 성공했어요.
              </p>
            </div>
          </div>
        </div>
        <div className={styles['category-box']}>
          {categoryList.map((item) => (
            <div aria-hidden="true" onClick={() => onToggleCategory(item.id)} className={cn(styles.link, item.id === categoryId ? styles.toggle : '')} key={item.id}>
              <img src={item.url} className={styles.icon} alt={item.caption} />
              <span>{item.caption} </span>
            </div>
          ))}
        </div>
        <div className={styles['search-box']}>
          <TextField
            className={styles.search}
            variant="outlined"
            onChange={handleTextChange}
            placeholder="검색어를 입력해주세요."
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <SearchIcon />
                </InputAdornment>
              ),
            }}
          />
        </div>

        <div className={styles['funding-list-box']}>
          <div className={styles['funding-filter-box']}>
            <p>
              <span>{fundingCount}</span>건의 프로젝트가 진행중에 있어요.
            </p>
            <div className={styles['funding-button-box']}>
              <Button
                sx={{ color: 'black', fontWeight: 'bold' }}
                className={cn(selectIdx === 0 ? styles.selected : '')}
                type="button"
                onClick={() => hanlderFilter('', 0)}
              >
                전체
              </Button>
              <Button
                sx={{ color: 'black', fontWeight: 'bold' }}
                className={cn(selectIdx === 1 ? styles.selected : '')}
                type="button"
                onClick={() => hanlderFilter('FUNDING_IN_PROGRESS', 1)}
              >
                진행중
              </Button>
              <Button
                sx={{ color: 'black', fontWeight: 'bold' }}
                className={cn(selectIdx === 2 ? styles.selected : '')}
                type="button"
                onClick={() => hanlderFilter('FUNDING_ACCEPT', 2)}
              >
                오픈 예정
              </Button>
              <Button
                sx={{ color: 'black', fontWeight: 'bold' }}
                className={cn(selectIdx === 3 ? styles.selected : '')}
                type="button"
                onClick={() => hanlderFilter('FUNDING_COMPLETE', 3)}
              >
                종료된 펀딩
              </Button>
            </div>
          </div>
          {isLoading ? (
            <FundingElementSkeleton />
          ) : (
            <div className={styles['funding-list']}>
              {fundingList?.map((funding) => (
                <FundingListElement {...funding} key={funding.id} />
              ))}

              {nextLoading ? (
                <div className={styles['nextLoading-box']}>
                  <CircularProgress color="warning" />
                </div>
              ) : (
                ''
              )}
            </div>
          )}
        </div>
      </div>
      {currentPage < totalPage ? <div ref={ref} /> : ''}
    </div>
  );
}

export default FundingListContainer;
