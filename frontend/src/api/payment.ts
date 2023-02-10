import { http } from './axios';

/** 충전 검증 */
// eslint-disable-next-line
export const requestVerifyPayment = async (imp_uid: string) => {
  // eslint-disable-next-line
  const response = await http.post(`verifyIamport/${imp_uid}`);

  return response;
};

/** 백엔드에 KG 이니시스 정보 전달 = 충전 반영 */
// eslint-disable-next-line
export const requestPayment = async (amount: number | undefined, imp_uid: string) => {
  const data = {
    amount,
    // eslint-disable-next-line
    impUid: imp_uid,
  };

  const response = await http.post('member/charge', data);

  return response;
};

/** 충전 내역 */
export const requestChargeList = async (page: number, size: number, sort: string) => {
  const params = {
    sort,
    page,
    size,
  };

  const response = http.get('member/chargeList', { params });

  return response;
};
