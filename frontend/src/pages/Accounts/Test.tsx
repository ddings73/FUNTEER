import axios from 'axios';
import React, { useEffect } from 'react';
import { BsChevronDoubleLeft } from 'react-icons/bs';
import { useSearchParams } from "react-router-dom";


export function Test() {
    const [searchParams, setSeratchParams] = useSearchParams();
    console.dir(searchParams.get("email"));
    const email = searchParams.get("email");


    useEffect(()=>{
        const data ={
            email
        };
        const response = axios.post(`http://localhost:8080/api/v1/login/kakao`,data);
        console.log("response=>")
        console.dir(response);
    })
    
    return <div>123</div>;
}

export default Test;
