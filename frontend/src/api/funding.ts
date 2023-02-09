import { async } from 'q';
// import { commentType } from '../components/Cards/CommentCardSubmit';
import { FundingInterface } from '../types/funding';
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
}


/**
 * 펀딩 리스트 호출 API
 * @method GET
 */

export const requestFundingList = async (size: number) => {
  const res = await http.get(`funding/?size=${size}`);
  console.log(res);

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
  const res = await http.get(`funding/${fundingId}/?sort=${sort}`);
  console.log('댓글호출', res);

  return res;
};

/**
 * @name 다음댓글리스트호출
 * @returns
 */
export const requestNextCommentList = async (currentPage: number, fundingId?: string, sort?: string) => {
  const response = await http.get(`funding/${fundingId}/?page=${currentPage + 1}&?sort=${sort}`);
  return response;
};
