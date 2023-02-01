import React, { useState, useEffect, useCallback } from 'react';
import CountUp from 'react-countup';

import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import { Box } from '@mui/system';
import TextField from '@mui/material/TextField';
import InputAdornment from '@mui/material/InputAdornment';
import SearchIcon from '@mui/icons-material/Search';
import { Link } from 'react-router-dom';
import styles from './FundingListContainer.module.scss';
import FundingListElement from '../../components/Funding/FundingListElement';
import { FundingElementType } from '../../types/funding';
import { requestFundingList, requestFundingSearch } from '../../api/funding';
import { useAppSelector } from '../../store/hooks';

// icon
import disable from '../../assets/images/funding/categoryIcon/disable.png';
import child from '../../assets/images/funding/categoryIcon/child.png';
import animal from '../../assets/images/funding/categoryIcon/animal.png';
import oldman from '../../assets/images/funding/categoryIcon/oldman.png';
import planet from '../../assets/images/funding/categoryIcon/planet.png';

function FundingListContainer() {
  const [fundingList, setFundingList] = useState<FundingElementType[]>([]);
  const [successFundingCount, setSuccessFundingCount] = useState<number>(0);
  const [totalFundingAmount, setTotalFundingAmount] = useState<number>(0);
  const [totalFundingCount, setTotalFundingCount] = useState<number>(0);
  const [searchText, setSearchText] = useState<string>('');

  const search = async (text: string) => {
    try {
      const response = await requestFundingSearch(text);
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };

  const handleTextChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    search(value);
  };

  const initFundingList = useCallback(async () => {
    try {
      const { data } = await requestFundingList();
      console.log(data);
      setFundingList([...data.fundingListResponses]);
      setSuccessFundingCount(data.successFundingCount);
      setTotalFundingAmount(data.totalFundingAmount);
      setTotalFundingCount(data.totalFundingCount);
    } catch (error) {
      console.log(error);
    }
  }, []);

  useEffect(() => {
    initFundingList();
  }, []);

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
          <Link to="/asd" className={styles.link}>
            <img src={animal} className={styles.icon} alt="동물" />
            <span>동물 </span>
          </Link>
          <Link to="/asd" className={styles.link}>
            <img src={planet} className={styles.icon} alt="환경" />
            <span>환경</span>
          </Link>
          <Link to="/asd" className={styles.link}>
            <img src={disable} className={styles.icon} alt="동물" />
            <span>장애인</span>
          </Link>
          <Link to="/asd" className={styles.link}>
            <img src={oldman} className={styles.icon} alt="노인" />
            <span>노인</span>
          </Link>
          <Link to="/asd" className={styles.link}>
            <img src={child} className={styles.icon} alt="아동" />
            <span>아동</span>
          </Link>
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
              총 <span>{totalFundingCount}</span>건의 프로젝트가 진행중에 있어요.
            </p>

            <div className={styles['funding-list']}>
              {fundingList?.map((funding) => (
                <FundingListElement {...funding} key={funding.id} />
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default FundingListContainer;
