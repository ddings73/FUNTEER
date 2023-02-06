import { async } from 'q';
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
