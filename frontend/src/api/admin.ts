import { NoticeInterface } from '../types/notice';
import { http } from './axios';

export const requestCreateNotice = async(noticeData:NoticeInterface)=>{
    const formData = new FormData();
    formData.append('title', noticeData.title)
    formData.append('content', noticeData.content as string)
    formData.append('files',new Blob([JSON.stringify(noticeData.files)], { type: 'application/json '}))

    const response = await http.post('/notice', formData)
    return response;
}

export const requestDeleteNotice = async(noticeId:string)=> {
    const response = await http.delete(`/notice/${noticeId}`)
    return response;
}

export const requestNoticeDetail = async(noticeId:string | undefined) => {
    const response = await http.get(`/notice/${noticeId}`)
    return response;
}

/**
 * 공지사항 리스트 호출 API
 * @method GET
 */

export const requestNoticeList = async () => {
    const res = await http.get(`notice`);
    console.log(res);
  
    return res;
  };


  

