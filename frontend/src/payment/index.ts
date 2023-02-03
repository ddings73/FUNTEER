import swal from 'sweetalert2';
import { paymentSuccessRequest } from '../api/charge';
import { CallBackParams, PayParams } from '../types/payment';

const callback: (response: CallBackParams) => void = async (response) => {
  console.log('결제 정보', response);

  if (response.success) {
    /** 결제 성공 알림 */
    swal.fire({
      position: 'bottom-end',
      title: '결제 성공',
      icon: 'success',
      iconColor: 'rgba(236, 153, 75, 1)',
      showConfirmButton: false,
      timer: 2000,
      timerProgressBar: true,
    });
    /** 결제 성공 요청 */
    try {
      const axiosResponse = await paymentSuccessRequest(response);
      console.log('결제 성공 요청 정보', axiosResponse);
    } catch (error) {
      console.log('결제 성공 요청 에러', error);
    }
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

export const payment = (data: PayParams) => {
  const { IMP } = window;
  IMP?.init('imp45886434');

  IMP.request_pay(data, callback);
};
