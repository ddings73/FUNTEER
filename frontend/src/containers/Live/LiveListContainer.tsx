import { InputAdornment, TextField } from '@mui/material'
import React, { useEffect, useState } from 'react'
import SearchIcon from '@mui/icons-material/Search';
import { requestLiveList } from '../../api/live'
import styles from './LiveListContainer.module.scss'
import { FundingElementType } from '../../types/funding';
import { requestFundingSearch } from '../../api/funding';



function LiveListContainer(){
    const [fundingList, setFundingList] = useState<FundingElementType[]>([]);
  const [successFundingCount, setSuccessFundingCount] = useState<number>(0);
  const [totalFundingAmount, setTotalFundingAmount] = useState<number>(0);
  const [totalFundingCount, setTotalFundingCount] = useState<number>(0);
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [nextLoading, setNextLoading] = useState<boolean>(false);
  const [currentPage, setCurrentPage] = useState<number>(-1);
  const [isLastPage, setIsLastPAge] = useState<boolean>(false);
//   const [ref, inView] = useInView();
  const [selectCategory, setSelectCategory] = useState<number>(-1);
  const [fundingStateFilter, setFundingStateFilter] = useState<string>('All');



    const initLiveList = async()=>{
        try{
            const response = await requestLiveList()
            console.log(response)
        }
        catch(error){
            console.error(error)
        }
    }

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

    useEffect(()=>{
        initLiveList()
    },[])
    return(
        <div className={styles.container}>
        <div className={styles.contents}>
          <div className={styles['title-box']}>
            <div className={styles['description-box']}>
              <p>현재 진행중인 봉사활동을 라이브로 시청해보세요.</p>
            </div>
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
                <span>0</span>건의 라이브 방송이 진행중에 있어요.
              </p>
  

            </div>
          </div>
          {/* {currentPage >= 0 ? <div ref={ref} /> : ''} */}
        </div>
      </div>
    )
}

export default LiveListContainer;