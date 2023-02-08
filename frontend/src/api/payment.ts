import { http } from './axios';

// eslint-disable-next-line
export const requestVerifyPayment = async (imp_uid: string) => {
  // eslint-disable-next-line
  const response = await http.post(`verifyIamport/${imp_uid}`);

  return response;
};

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
