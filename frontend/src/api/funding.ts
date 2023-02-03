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
  const entry = Object.entries(fundingData);

  entry.forEach((data) => {
    const key = data[0];
    const value = data[1];
    formData.append(`${key}`, `${value}`);
  });
  // formData.append('thumbnail', fundingData.thumbnail);
  // formData.append('title', fundingData.title);
  // formData.append('fundingDescription', fundingData.fundingDescription);
  // formData.append('categoryId', JSON.stringify(fundingData.categoryId));
  // formData.append('content', fundingData.content as string);
  // formData.append('startDate', fundingData.startDate);
  // formData.append('endDate', fundingData.endDate);
  // formData.append('hashTags', fundingData.hashtags);

  // formData.append('targetMoneyLevelOne', JSON.stringify(fundingData.targetMoneyLevelOne));
  // formData.append('targetMoneyLevelTwo', JSON.stringify(fundingData.targetMoneyLevelTwo));
  // formData.append('targetMoneyLevelThree', JSON.stringify(fundingData.targetMoneyLevelThree));
  const res = await http.post('funding', formData);
  return res;
};

/**
 * 펀딩 리스트 호출 API
 * @method GET
 */

export const requestFundingList = async () => {
  const res = await http.get('funding/');
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
