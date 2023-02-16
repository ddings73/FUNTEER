import { async } from 'q';
// import { commentType } from '../components/Cards/CommentCardSubmit';
import { FundingInterface, FundingReportInterface } from '../types/funding';
import { http } from './axios';

/**
 * Toast Editor 내의 이미지 등록 함수
 * @method POST
 * @param  {Blob} imageBase64 file객체
 */
export const requestUploadImage = async (imageBase64: Blob) => {
  const formData = new FormData();
  formData.append('file', imageBase64);
  const res = await http.post('funding/upload', formData);
  return res.data;
};

/**
 * 펀딩 생성 API
 * @method POST
 * @param {FundingInterface} fundingData
 */

export const requestCreateFunding = async (fundingData: FundingInterface) => {
  const formData = new FormData();

  formData.append('thumbnail', fundingData.thumbnail);
  formData.append('data', new Blob([JSON.stringify(fundingData)], { type: 'application/json' }));
  const res = await http.post('funding', formData);
  return res;
};

/**
 * 펀딩 수정 API
 * @method PUT
 * @param {FundingInterface} fundingData
 */

export const requestModifyFunding = async (fundIdx: string, fundingData: FundingInterface) => {
  const formData = new FormData();

  formData.append('thumbnail', fundingData.thumbnail);
  formData.append('data', new Blob([JSON.stringify(fundingData)], { type: 'application/json' }));
  const res = await http.put(`funding/${fundIdx}`, formData);
  return res;
};

/**
 * 펀딩 리스트 호출 API
 * @method GET
 */

export const requestFundingList = async (categoryId?: string, keyword?: string, postType?: string, currentPage?: number, size?: number) => {
  const res = await http.get(`funding/?categoryId=${categoryId}&keyword=${keyword}&postType=${postType}&page=${currentPage}&size=${size}`);
  return res;
};

/**
 * 펀딩 리스트 검색 API
 * @method GET
 */

export const requestFundingSearch = async (text: string) => {
  const response = await http.get(`funding/search/?keyword=${text}`);
  return response;
};

/*
 * 펀딩 상세 호출
 * @method GET
 */

export const requestFundingDetail = async (fundIdx?: string) => {
  const res = await http.get(`funding/${fundIdx}`);
  console.log(res);
  return res;
};

/**
 * @name 다음펀딩리스트호출
 * @returns
 */
export const requestNextFundingList = async (currentPage: number, size: number) => {
  console.log(currentPage, size);
  const response = await http.get(`funding/?page=${currentPage + 1}&size=${size}`);
  return response;
};

/**
 * @name 카테고리별펀딩리스트
 * @method GET
 */

export const requestCategoryFundingList = async (categoryId: number) => {
  const response = await http.get(`funding/category/${categoryId}`);
  return response;
};

/**
 * @name 펀딩좋아요
 * @method PUT
 */
export const requestWish = async (fundingId?: string) => {
  const res = await http.put(`member/like/${fundingId}`);
  console.log(res);
  return res;
};

/**
 * 펀딩 썸테일 등록
 */
export const requestRegisterThumbnail = async (file: Blob) => {
  const formData = new FormData();
  formData.append('file', file);
  const response = await http.post('funding/upload/thumbnail', formData);
  return response;
};
/**
 * @name 펀딩응원댓글
 * @method POST
 * @param {commentType} commentData
 */
export const postFundingComment = async (commentData: string, fundingId?: string) => {
  const res = await http.post(`funding/${fundingId}/comment`, { content: commentData });
  return res;
};

/**
 * @name 펀딩댓글리스트호출API
 * @method GET
 */
export const requestCommentList = async (fundingId?: string, sort?: string) => {
  const res = await http.get(`funding/${fundingId}/?page=0&sort=${sort}`);
  console.log('댓글호출', res);

  return res;
};

/**
 * @name 다음댓글리스트호출
 * @method GET
 */
export const requestNextCommentList = async (currentPage: number, fundingId?: string, sort?: string) => {
  const response = await http.get(`funding/${fundingId}/?page=${currentPage + 1}&sort=${sort}`);
  return response;
};

/**
 * @name 댓글삭제호출
 * @method DELETE
 */
export const requestDeleteComment = async (commentId?: number) => {
  const res = await http.delete(`funding/comment/${commentId}`);
  console.log('삭제 댓글 res: ', res);
};

/**
 * @name 펀딩참여
 * @method POST
 * @param amount:string, fundingId: number
 */
export const fundingJoin = async (amount?: string, fundingId?: string) => {
  const res = await http.post(`funding/${fundingId}/pay`, { amount, fundingId });
  return res;
};

/**
 * @name 펀딩참여조회
 * @method GET
 */

export const requestFundingJoin = async (page: number, size: number, sort?: string) => {
  const res = await http.get(`member/mileage?page=${page}&postGroup=FUNDING&size=${size}&sort=${sort}`);
  return res;
};

/**
 * @name 펀딩보고서조회
 * @method GET
 */

export const requestFundingReport = async (fundingId?: string) => {
  const res = await http.get(`funding/${fundingId}/report`);
  console.log('report res', res);
  return res;
};

/**
 * @name 펀딩보고서 작성
 * @param fundingId
 */
export const fundingReportPost = async (fundingId: string, reportData: FundingReportInterface) => {
  console.log('데읻타타타타타타타타타타탙', reportData);
  const formData = new FormData();

  // formData.append('data', new Blob([JSON.stringify(reportData)], { type: 'application/json' }));
  // formData.append('data', new Blob([JSON.stringify(reportData)], { type: 'application/json' }));

  formData.append('content', reportData.content as string);

  // reportData.fundingDetailRequests.forEach((element) => {
  //   console.log(element);
  formData.append('fundingDetailRequests', JSON.stringify(reportData.fundingDetailRequests));
  // });
  formData.append('receiptFile', new Blob());

  const config = {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  };

  const res = await http.post(`funding/${fundingId}/report`, formData, config);

  return res;
};
