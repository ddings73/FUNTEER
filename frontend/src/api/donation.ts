import { DonationElementType, DonationInterface, DonationStatusModi } from '../types/donation';
import { http } from './axios';

/**
 * 자체기부 생성 API
 * @method POST
 * @param {DonationInterface} donationData
 */

export const requestCreateDonation = async (donationData: DonationInterface) => {
  const formData = new FormData();
  const entry = Object.entries(donationData);

  entry.forEach((data) => {
    const key = data[0];
    const value = data[1];
    formData.append(`${key}`, value);
  });

  const res = await http.post('admin/donation', formData);
  return res;
};

/** 자체기부 수정 API */
export const requestModifyDonation = async (donationIdx: number, donationData: DonationInterface) => {
  const formData = new FormData();
  const entry = Object.entries(donationData);

  entry.forEach((data) => {
    const key = data[0];
    const value = data[1];
    formData.append(`${key}`, value);
  });

  const res = await http.put(`admin/donation/${donationIdx}`, formData);
  return res;
};

/**
 * 도네이션 최근
 * @method GET
 */
export const requestCurrentDonation = async () => {
  const res = await http.get('donation');
  console.log(res);

  return res;
};

/**
 * 도네이션 리스트 호출 API
 * @method GET
 */

export const requestDonationList = async (size: number) => {
  const res = await http.get(`donation/list?size=${size}`);
  console.log(res);

  return res;
};

export const requestAdminDonationList = async (size: number, page?: number) => {
  const params = {
    page,
    size,
  };

  const response = await http.get('admin/donation', { params });

  return response;
};

/**
 * @name 다음자체모금리스트호출
 * @returns
 */
export const requestNextAdminDonationList = async (currentPage: number, size: number) => {
  const response = await http.get(`donation/list?page=${currentPage + 1}&size=${size}`);

  return response;
};

/*
 * 도네이션 상세 호출
 * @method GET
 */

export const requestDonationDetail = async (donationIdx?: number) => {
  const res = await http.get(`donation/${donationIdx}`);
  console.log(res);
  return res;
};

/**
 * 상태 변경
 * @RequestBody Long postId, PostType postType
 * @method Put (admin/donation)
 */

export const requestDonationStatus = async (id: number, state: string) => {
  const data = {
    donationId: id,
    postType: state,
  };

  const res = await http.put(`admin/donation`, data);

  return res;
};
