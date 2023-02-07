import swal from 'sweetalert2';
import { requestVerifyPayment, requestPayment } from '../api/payment';
import { CallBackParams, PayParams } from '../types/payment';

/** 결제 콜백 함수 */
const callback: (response: CallBackParams) => void = async (response) => {
  console.log('결제 정보', response);

  if (response.success) {
    /** 결제 검증 요청 */
    try {
      const verifyResponse = await requestVerifyPayment(response.imp_uid);
      console.log('결제 검증 정보', verifyResponse);
      /** 검증 성공시 결제 request */
      try {
        const paymentResponse = await requestPayment(response.paid_amount, response.imp_uid);
        console.log('결제 요청 정보', paymentResponse);
        /** 결제 request 성공시 알림 */
        swal.fire({
          position: 'bottom-end',
          title: '결제 성공',
          icon: 'success',
          iconColor: 'rgba(236, 153, 75, 1)',
          showConfirmButton: false,
          timer: 2000,
          timerProgressBar: true,
        });
        /** 결제 request 실패 */
      } catch (error) {
        console.log('결제 요청 실패', error);
      }
      /** 결제 검증 실패 */
    } catch (error) {
      console.error('결제 검증 에러', error);
    }
    /** 결제 실패 */
  } else {
    /** 결제 실패 알림 */
    swal.fire({
      position: 'bottom-end',
      title: '결제 실패',
      // eslint-disable-next-line
      text: `${response.error_msg}`,
      icon: 'warning',
      iconColor: 'rgba(211, 79, 4, 1)',
      showConfirmButton: false,
    });
  }
};

/** 결제 요청 */
export const payment = (data: PayParams) => {
  const { IMP } = window;
  IMP?.init('imp45886434');

  IMP.request_pay(data, callback);
};
