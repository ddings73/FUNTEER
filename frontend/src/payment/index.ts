import { CallBackParams, PayParams } from '../types/payment';
import { customAlert, customTextAlert, s1000, w1500 } from '../utils/customAlert';

const callback: (response: CallBackParams) => void = (response) => {
  const { success, errorMsg } = response;
  console.log(response);

  if (success) {
    customAlert(s1000, '결제 성공');
    console.log('결제 정보 전달 요청');
  } else {
    customTextAlert(w1500, '결제 실패', `${errorMsg}`);
  }
};

export const payment = (data: PayParams) => {
  const { IMP } = window;
  IMP?.init('imp45886434');

  IMP.request_pay(data, callback);
};
