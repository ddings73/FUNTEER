import React from 'react'
import styled from 'styled-components'
import styles from './FundingListElement.module.scss'
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

function FundingListElement (){
    const percent:number[] = [
        70,80,90
    ]
    return (
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

    )
}

export default FundingListElement;