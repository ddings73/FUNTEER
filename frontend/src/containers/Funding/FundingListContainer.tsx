import React,{useState,useEffect, useCallback} from 'react'
import CountUp from 'react-countup';

import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import { Box } from '@mui/system';
import TextField from '@mui/material/TextField';
import styles from './FundingListContainer.module.scss'

import FundingListElement from '../../components/Funding/FundingListElement';
import { FundingElementType } from '../../types/funding';
import { requestFundingList } from '../../api/funding';


function FundingListContainer (){
    const [fundingList,setFundingList] = useState<FundingElementType[] | undefined>([])
    const [successFundingCount,setSuccessFundingCount] = useState<number>(0);
    const [totalFundingAmount,setTotalFundingAmount] =useState<number>(0);
    const [totalFundingCount,setTotalFundingCount] =useState<number>(0);

    const [searchText,setSearchText] = useState<string>("")

    const [age, setAge] = React.useState('');

    const handleChange = (event: SelectChangeEvent) => {
     console.log(event.target.value);
    };

 const handleTextChange = (e: React.ChangeEvent<HTMLInputElement>)=>{
   const {value} =e.target
   setSearchText(value)
    
 } 


 const initFundingList =useCallback(async()=>{
    try{
        const {data} =await requestFundingList()
        console.log(data);
        
        setFundingList([...data.fundingListResponses])
        setSuccessFundingCount(data.successFundingCount)
        setTotalFundingAmount(data.totalFundingAmount)
        setTotalFundingCount(data.totalFundingCount)
    }
    catch(error){
        console.log(error);
    }
 },[]) 

 useEffect(()=>{
    initFundingList()
 },[])
    
    return (
        <div className={styles.container}>
            <div className={styles.contents} >
                <div className={styles['title-box']}>
                    <div className={styles['description-box']}>
                        <p>당신의 착한 마음을 Funteer가 응원합니다.</p>
                    </div>
                    <div className={styles['statistic-box']}>
                        <div>
                            <p>
                            <CountUp start={0} end={successFundingCount}   separator="," duration={4}/>건 <br/> 봉사 펀딩에 성공했어요.
                            </p>

                        </div>
                        <div>
                            <p>
                            <CountUp start={0} end={totalFundingAmount} separator="," duration={4}/>원 <br/> 기부에 성공했어요.
                            </p>
                        </div>
                    </div>
                    
                </div>
                <div className={styles['search-box']} >
                {/* <Box sx={{ minWidth: 150 }}>
                <FormControl fullWidth>
                    <InputLabel id="demo-simple-select-label">카테고리</InputLabel>
                <Select
                    labelId="demo-simple-select-label"
                     id="demo-simple-select"
                value={age}
          label="카테고리"
          onChange={handleChange}
        >
          <MenuItem value={10}>Ten</MenuItem>
          <MenuItem value={20}>Twenty</MenuItem>
          <MenuItem value={30}>Thirty</MenuItem>
        </Select>
      </FormControl>
    </Box>
    <TextField sx={{minWidth:550, mr:2}}  variant="outlined" onChange={handleTextChange} /> */}
                </div>
                <div className={styles['funding-list-box']}>
                    <div className={styles['funding-filter-box']}>
                        <p>총 <span>{totalFundingCount}</span>건의 프로젝트가 진행중에 있어요.</p>

                        <div className={styles['funding-list']}>

                            {fundingList?.map((funding)=>(
                            <FundingListElement {...funding}/>
                            ))}

                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default FundingListContainer