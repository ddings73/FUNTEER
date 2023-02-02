import React from 'react';

declare global {
  interface Window {
    IMP: any;
  }
}

function PaymentContainer() {
  const onClickPayment = () => {
    const { IMP } = window;
    IMP?.init('imp45886434');

    const data = {
      pg: 'html5_inicis', // PG사
      pay_method: 'card', // 결제수단
      merchant_uid: `mid_${new Date().getTime()}`, // 주문번호
      amount: 100, // 결제금액
      name: '아임포트 결제 데이터 분석', // 주문명
      buyer_name: '홍길동', // 구매자 이름
      buyer_tel: '01012341234', // 구매자 전화번호
      buyer_email: 'example@example', // 구매자 이메일
    };

    IMP.request_pay(data, callback);
  };

  const onClickVbankPayment = () => {
    const { IMP } = window;
    IMP?.init('imp45886434');

    const data = {
      pg: 'html5_inicis', // PG사
      pay_method: 'vbank', // 결제수단
      merchant_uid: `mid_${new Date().getTime()}`, // 주문번호
      amount: 100, // 결제금액
      name: '아임포트 결제 데이터 분석', // 주문명
      buyer_name: '홍길동', // 구매자 이름
      buyer_tel: '01012341234', // 구매자 전화번호
      buyer_email: 'example@example', // 구매자 이메일
    };

    IMP.request_pay(data, callback);
  };

  type typeOfCallback = {
    success: boolean;
    errorMsg: string;
    impUid: string;
    merchantUid: string;
  };

  const callback: (response: typeOfCallback) => void = (response) => {
    const { success, errorMsg, impUid } = response;
    console.log(response);

    if (success) {
      alert('결제 성공');
    } else {
      alert(`결제 실패: ${errorMsg}`);
    }
  };

  return (
    <div style={{ marginTop: '100px' }}>
      <button type="button" onClick={onClickPayment}>
        카드 결제 + 카페랑 네페 다 있음
      </button>
      <button type="button" onClick={onClickVbankPayment}>
        가상계좌 결제
      </button>
    </div>
  );
}

export default PaymentContainer;
