declare global {
  interface Window {
    IMP: any;
  }
}

export interface PayParams {
  pg?: string; // PG사
  pay_method: string; // 결제 수단
  merchant_uid: string; // 가맹점에서 생성/관리하는 고유 주문번호
  amount: number; // 결제 금액
  name?: string; // 결제 제목
  buyer_name?: string; // 구매자 이름
  buyer_tel: string; // 구매자 전화번호
  buyer_email?: string; // 구매자 이메일
}

export interface CallBackParams extends PayParams {
  success: boolean; // 성공 여부
  error_msg: string; // 에러 메세지
  imp_uid: string; // 아임포트 고유 결제 번호
  paid_amount?: number; // 결제 승인된 또는 가상 계좌 입금 예정 금액
}
