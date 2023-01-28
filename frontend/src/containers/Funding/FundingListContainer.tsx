import React,{useState,useEffect} from 'react'
import CountUp from 'react-countup';

import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import { Box } from '@mui/system';
import TextField from '@mui/material/TextField';
import styled from 'styled-components'
import styles from './FundingListContainer.module.scss'

import thumnail from '../../assets/images/funding/funding_thumbnail.png'


const ProgressBar = styled.div<{gage:number}>`
        width: 100%;
        height: 5px;
        background-color: rgba(217, 217, 217, 1);

        &::after{
            content: "";
            display: block;
             width: ${props=>props.gage}%;
             height: 5px;
            background-color: red;
        }
`


type AmountType = {
    funding:number
    money:number
}

function FundingListContainer (){
    const percent:number[] = [
        70,80,90
    ]
    const [count,setCount] = useState<AmountType>({
        funding:1231,
        money:12312323
    })
    
    const [searchText,setSearchText] = useState<string>("")

    const [age, setAge] = React.useState('');

    const handleChange = (event: SelectChangeEvent) => {
     console.log(event.target.value);
     
    };

 const handleTextChange = (e: React.ChangeEvent<HTMLInputElement>)=>{
   const {value} =e.target
   setSearchText(value)
    
 } 

    
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
                            <CountUp start={0} end={count.funding}   separator="," duration={4}/>건 <br/> 봉사 펀딩에 성공했어요.
                            </p>

                        </div>
                        <div>
                            <p>
                            <CountUp start={0} end={count.money} separator="," duration={4}/>원 <br/> 기부에 성공했어요.
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
                        <p>총 <span>250</span>건의 프로젝트가 진행중에 있어요.</p>

                        <div className={styles['funding-list']}>
                             <div className={styles['funding-box']}>
                                <img src={thumnail} alt=""/>
                                <p className={styles.title}>가난한 저에게 마우스를 사요주세.</p>
                                <p className={styles.content}>
                                    지친 사람들의 쉴 곳, 동식물들의 생명이 피어나는 곳 등 다양한 
                                     모습으로 우리 곁에 있으며 사계절의 변화를 보여주는 곳. 
                                    바로 지역 하천 입니다.</p>

                                    <div className={styles['progress-info-box']}>
                                        <p className={styles.money}>
                                            <span>90%</span>&nbsp;
                                            <span>1,232,402원</span>
                                        </p>
                                        <p className={styles.date}>
                                            12일남음
                                        </p>
                                    </div>
                               <ProgressBar gage={percent[0]}/>
                             </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default FundingListContainer