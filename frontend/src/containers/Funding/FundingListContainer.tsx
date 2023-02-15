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
  { id: 4, url: disable, caption: '장애인' },
  { id: 1, url: child, caption: '아동' },
  { id: 3, url: animal, caption: '동물' },
  { id: 2, url: oldman, caption: '노인' },
  { id: 5, url: planet, caption: '환경' },
];
function FundingListContainer() {
  const [fundingList, setFundingList] = useState<FundingElementType[]>([]);
  const [successFundingCount, setSuccessFundingCount] = useState<number>(0);
  const [totalFundingAmount, setTotalFundingAmount] = useState<number>(0);
  const [totalFundingCount, setTotalFundingCount] = useState<number>(0);
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [nextLoading, setNextLoading] = useState<boolean>(false);
  const [currentPage, setCurrentPage] = useState<number>(-1);
  const [isLastPage, setIsLastPAge] = useState<boolean>(false);
  const [ref, inView] = useInView();
  const [selectCategory, setSelectCategory] = useState<number>(-1);
  const [fundingStateFilter, setFundingStateFilter] = useState<string>('All');
  const [selectIdx, setSelectIdx] = useState<number>(1);

  // 펀딩개수 계싼
  const fundingCount = useMemo(() => {
    return totalFundingCount;
  }, [totalFundingCount]);

  const search = async (text: string) => {
    try {
      setIsLoading(true);
      const response = await requestFundingSearch(text);
      setFundingList([...response.data.content]);
      setTotalFundingCount(response.data.numberOfElements);
      setIsLoading(false);
    } catch (error) {
      console.log(error);
    }
  };

  const handleTextChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    search(value);
  };

  const hanlderFilter = (id: string, idx: number) => {
    setSelectIdx(idx);
    getPostTypeList(id);
  };

  const getPostTypeList = async (filterdType: string) => {
    console.log(filterdType);
    try {
      setIsLoading(true);
      const { data } = await requestFundingList(size);
      const temp = data.fundingListResponses.content;
      let next;
      if (filterdType === 'FUNDING_IN_PROGRESS') {
        next = temp.filter((el: { postType: string }) => el.postType === 'FUNDING_IN_PROGRESS' || el.postType === 'FUNDING_EXTEND');
        setFundingList([...next]);
      } else if (filterdType === 'FUNDING_ACCEPT') {
        next = temp.filter((el: { postType: string }) => el.postType === 'FUNDING_ACCEPT');
        setFundingList([...next]);
      } else if (filterdType === 'FUNDING_COMPLETE') {
        next = temp.filter(
          (el: { postType: string }) =>
            el.postType === 'FUNDING_COMPLETE' || el.postType === 'REPORT_WAIT' || el.postType === 'REPORT_REJECT' || el.postType === 'REPORT_ACCEPT',
        );
        setFundingList([...next]);
      } else {
        setFundingList([...temp]);
      }
      setIsLoading(false);
    } catch (error) {
      console.log(error);
    }
  };

  const initFundingList = async () => {
    try {
      setIsLoading(true);
      const { data } = await requestFundingList(size);

      // 처음 렌더링 될 때 진행중인 펀딩리스트만 필터링해서 보여줌
      const progressList: FundingElementType[] = data.fundingListResponses.content.filter((list: FundingElementType) => list.postType === 'FUNDING_IN_PROGRESS');

      setFundingList([...progressList]);
      setSuccessFundingCount(data.successFundingCount);
      setTotalFundingAmount(data.totalFundingAmount);
      setTotalFundingCount(data.totalFundingCount);
      setCurrentPage(data.fundingListResponses.number);
      setIsLastPAge(data.fundingListResponses.last);
      setIsLoading(false);
    } catch (error) {
      console.log(error);
    }
  };

  const nextFundingList = async () => {
    try {
      setNextLoading(true);
      const { data } = await requestNextFundingList(currentPage, size);
      setFundingList([...fundingList, ...data.fundingListResponses.content]);
      setCurrentPage(data.fundingListResponses.number);
      setIsLastPAge(data.fundingListResponses.last);
      setNextLoading(false);
    } catch (error) {
      console.error(error);
    }
  };
  const onToggleCategory = async (categoryId: number) => {
    try {
      if (categoryId === selectCategory) {
        setSelectCategory(-1);
        setIsLoading(true);
        const { data } = await requestFundingList(size);
        setFundingList([...data.fundingListResponses.content]);
        setSuccessFundingCount(data.successFundingCount);
        setTotalFundingAmount(data.totalFundingAmount);
        setTotalFundingCount(data.totalFundingCount);
        setCurrentPage(data.fundingListResponses.number);
        setIsLastPAge(data.fundingListResponses.last);
        setIsLoading(false);
      } else {
        setSelectCategory(categoryId);
        console.log(categoryId);
        setIsLoading(true);
        const response = await requestCategoryFundingList(categoryId);
        setFundingList([...response.data.content]);
        setTotalFundingCount(response.data.numberOfElements);
        setIsLoading(false);
        console.log(response);
      }
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    initFundingList();
  }, []);

  useEffect(() => {
    if (inView && !isLastPage) {
      nextFundingList();
    }
  }, [inView]);

  useEffect(() => {
    console.log(selectCategory);
  }, [selectCategory]);

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <div className={styles['title-box']}>
          <div className={styles['description-box']}>
            <p>당신의 착한 마음을 Funteer가 응원합니다.</p>
          </div>
          <div className={styles['statistic-box']}>
            <div>
              <p>
                <CountUp start={0} end={successFundingCount} separator="," duration={4} />건 <br /> 봉사 펀딩에 성공했어요.
              </p>
            </div>
            <div>
              <p>
                <CountUp start={0} end={totalFundingAmount} separator="," duration={4} />원 <br /> 기부에 성공했어요.
              </p>
            </div>
          </div>
        </div>
        <div className={styles['category-box']}>
          {categoryList.map((item) => (
            <div aria-hidden="true" onClick={() => onToggleCategory(item.id)} className={cn(styles.link, item.id === selectCategory ? styles.toggle : '')} key={item.id}>
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
                onClick={() => hanlderFilter('All', 0)}
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
        {currentPage >= 0 ? <div ref={ref} /> : ''}
      </div>
    </div>
  );
}

export default FundingListContainer;
