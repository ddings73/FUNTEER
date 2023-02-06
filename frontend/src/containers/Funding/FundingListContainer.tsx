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
import {useInView} from 'react-intersection-observer'
import cn from 'classnames'
import styles from './FundingListContainer.module.scss';
import FundingListElement from '../../components/Funding/FundingListElement';
import { FundingElementType } from '../../types/funding';
import { requestFundingList, requestFundingSearch, requestNextFundingList } from '../../api/funding';
// icon
import disable from '../../assets/images/funding/categoryIcon/disable.png';
import child from '../../assets/images/funding/categoryIcon/child.png';
import animal from '../../assets/images/funding/categoryIcon/animal.png';
import oldman from '../../assets/images/funding/categoryIcon/oldman.png';
import planet from '../../assets/images/funding/categoryIcon/planet.png';


// 한번에 불러올 게시글 수
const size = 12
const categoryList = [{
  url : disable,
  caption:"장애인"
},{
  url:child,
  caption:"아동"
},
{url:animal,
caption:"동물"},
{
  url:oldman,
  caption:"노인"
},
{
  url:planet,
  caption:"환경"
}
]
function FundingListContainer() {

  const [fundingList, setFundingList] = useState<FundingElementType[]>([]);
  const [successFundingCount, setSuccessFundingCount] = useState<number>(0);
  const [totalFundingAmount, setTotalFundingAmount] = useState<number>(0);
  const [totalFundingCount, setTotalFundingCount] = useState<number>(0);
  const [isLoading,setIsLoading] =useState<boolean>(false)
  const [nextLoading,setNextLoading] =useState<boolean>(false);
  const [currentPage,setCurrentPage] = useState<number>(-1);
  const [isLastPage,setIsLastPAge] = useState<boolean>(false);
  const [ref,inView] =useInView();
  const [selectCategory, setSelectCategory]= useState<number>(-1)
  
  // 펀딩개수 계싼
  const fundingCount = useMemo(()=>{
    return totalFundingCount
  },[totalFundingCount])

  const search = async (text: string) => {
    try {
      setIsLoading(true)
      const response = await requestFundingSearch(text);
      setFundingList([...response.data.content])
      setTotalFundingCount(response.data.numberOfElements)
      setIsLoading(false)
    } catch (error) {
      console.log(error);
    }
  };

  const handleTextChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    search(value);
  };

  const initFundingList =async () => {
    try {
      setIsLoading(true);
      const { data } = await requestFundingList(size);
      setFundingList([...data.fundingListResponses.content]);
      setSuccessFundingCount(data.successFundingCount);
      setTotalFundingAmount(data.totalFundingAmount);
      setTotalFundingCount(data.totalFundingCount);
      setCurrentPage(data.fundingListResponses.number)
      setIsLastPAge(data.fundingListResponses.last)
      setIsLoading(false);
    } catch (error) {
      console.log(error);
    }
  };

  const nextFundingList = async()=>{    
    try{
      setNextLoading(true);
      const {data} = await requestNextFundingList(currentPage,size)
      setFundingList([...fundingList,...data.fundingListResponses.content])
      setCurrentPage(data.fundingListResponses.number)
      setIsLastPAge(data.fundingListResponses.last)
      setNextLoading(false);
    }
    catch(error){
      console.error(error);
    }

  }

  useEffect(() => {
    initFundingList();
  }, []);

  useEffect(()=>{
    
    if(inView && !isLastPage){
      nextFundingList()
    }
  },[inView])

  const onToggleCategory = (index:number)=>{
    console.log(index);
    setSelectCategory(index)
    
  }

  useEffect(()=>{
    console.log(selectCategory);
  },[selectCategory])

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
          {categoryList.map((item,index)=>(      
          // eslint-disable-next-line react/no-array-index-key
          <div aria-hidden="true"  onClick={()=>onToggleCategory(index)}  className={cn(styles.link, index===selectCategory ? "toggle" :"")}  key={index}  >
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

            {isLoading  ? ( 
          <div className={styles['spinner-box']}>   
            <CircularProgress color="warning" />
          </div>
              ) : (
            <div className={styles['funding-list']}>
              {fundingList?.map((funding) => (
                  <FundingListElement {...funding} key={funding.id}  />
              ))}

              {nextLoading ?   <div className={styles['nextLoading-box']}>   
                 <CircularProgress color="warning" />
          </div>:"" }
            </div>
              )}
          </div>
        </div>
       {currentPage >=0 ? (<div ref={ref}  />
) : ""}       
</div>
    </div>
  );
}

export default FundingListContainer;
