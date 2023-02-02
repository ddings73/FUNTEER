declare global {
  interface Window {
    IMP: any;
  }
}

export interface PayParams {
  pg?: string; // PG사
  pay_method: string; // 결제수단
  merchant_uid: string; // 가맹점에서 생성/관리하는 고유 주문번호
  amount: number; // 결제금액
  buyer_name?: string; // 구매자 이름
  buyer_tel: string; // 구매자 전화번호
  buyer_email?: string; // 구매자 이메일
}

export interface CallBackParams extends PayParams {
  success: boolean; // 성공 여부
  errorMsg: string; // 에러 메세지
  impUid: string; // 아임포트 고유 결제 번호
  paidAmount?: number; // 결제승인된 또는 가상계좌 입금예정 금액
}
