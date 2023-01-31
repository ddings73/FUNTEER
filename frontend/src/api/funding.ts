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
  const entries = Object.entries(fundingData);

  entries.forEach((data) => {
    const key = data[0];
    const value = data[1];
    console.log(typeof value);

    formData.append(`${key}`, value);
  });

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
 * 펀딩 상세 호출
 * @method GET
 */
export const requestFundingDetail = async (id: number) => {
  const res = await http.get(`funding/${id}`);
  console.log(res);

  return res;
};
