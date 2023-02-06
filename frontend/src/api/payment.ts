import { CallBackParams } from '../types/payment';
import { http } from './axios';

export const paymentSuccessRequest = async (paymentSuccessInfo: CallBackParams) => {
  if (paymentSuccessInfo.paid_amount) {
    const data: CallBackParams = { ...paymentSuccessInfo, amount: paymentSuccessInfo.paid_amount };
    console.log('결제 성공 요청 데이터', data);

    const response = await http.post('member/charge', data);
    return response;
  }
  return 'no amount';
};
