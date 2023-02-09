import React, { useState, useEffect, useRef } from 'react';
import { Button, MenuItem, Select } from '@mui/material';
import { SelectChangeEvent } from '@mui/material/Select';
import { useAppDispatch, useAppSelector } from '../../store/hooks';
import styles from './ChargeContainer.module.scss';
import { openModal } from '../../store/slices/payModalSlice';
import { requestUserProfile } from '../../api/user';
import { requestVerifyPayment, requestPayment } from '../../api/payment';
import { CallBackParams, PayParams } from '../../types/payment';
import { customAlert, customTextAlert, s2000, w2000 } from '../../utils/customAlert';

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
        customAlert(s2000, '결제 성공');
      } catch (error) {
        /** 결제 모달 닫기 */
        console.log('결제 요청 실패', error);
        customTextAlert(w2000, '결제 실패', '알 수 없는 에러입니다. 고객센터에 문의해주세요.');
      }
      /** 결제 검증 실패 */
    } catch (error) {
      /** 결제 모달 닫기 */
      console.error('결제 검증 에러', error);
      customTextAlert(w2000, '결제 실패', '알 수 없는 에러입니다. 고객센터에 문의해주세요.');
    }
    /** 결제 실패 */
  } else {
    /** 결제 실패 알림 */
    customTextAlert(w2000, '결제 실패', `${response.error_msg}`);
  }
};

/** 결제 요청 함수 */
export const payment = (data: PayParams) => {
  const { IMP } = window;
  IMP?.init('imp45886434');

  IMP.request_pay(data, callback);
};

type NoticeType = {
  idx: number;
  text: string;
};

type ChargeHistoryType = {
  idx: number;
  text: string;
  date: string;
};

const chargeNotice: NoticeType[] = [
  {
    idx: 1,
    text: '결제는 KG 이니시스 서비스 내에서 진행됩니다.',
  },
  {
    idx: 2,
    text: '결제 수단은 카드(카드, 네이버 페이, 카카오 페이, PAYCO 등), 휴대폰 입니다.',
  },
  {
    idx: 3,
    text: '휴대폰 결제는 통신사 휴대폰 결제 동의 및 결제 비밀번호 설정 후 이용 가능합니다.',
  },
  {
    idx: 4,
    text: '충전된 마일리지는 펀딩, 라이브 방송 후원, 기부 참여에 사용할 수 있습니다.',
  },
  {
    idx: 5,
    text: '환불은 결제일 기준 7일 이내에 잔액이 결제 금액보다 많은 경우 가능합니다.',
  },
  {
    idx: 6,
    text: '펀딩, 라이브 방송 후원, 기부 참여에 사용된 마일리지는 다시 환불받을 수 없습니다.',
  },
];

const dummyChargeHistory: ChargeHistoryType[] = [
  {
    idx: 0,
    text: '123,000',
    date: '2023.01.03',
  },
  {
    idx: 1,
    text: '234,000',
    date: '2023.01.12',
  },
  {
    idx: 2,
    text: '23,000',
    date: '2023.01.23',
  },
];

function ChargeContainer() {
  const dispatch = useAppDispatch();
  /** 유저 ID */
  const userId = useAppSelector((state) => state.userSlice.userId);
  /** 잔액 */
  const [money, setMoney] = React.useState(0);
  /** 정렬 기준 */
  const [sort, setSort] = React.useState('date');
  /** 스크롤 */
  const [scrollY, setScrollY] = useState(0);
  /** 배너 ref */
  const bannerRef = useRef<HTMLDivElement>(null);
  /** 타이틀 ref */
  const titleRef = useRef<HTMLParagraphElement>(null);

  useEffect(() => {
    requestMoneyInfo();
  }, []);

  /** 잔액 조회 */
  const requestMoneyInfo = async () => {
    try {
      if (userId) {
        const response = await requestUserProfile(userId);
        console.log('유저 프로필 정보', response);
        setMoney(response.data.money);
      }
    } catch (error) {
      console.error(error);
    }
  };

  /** 충전 결제 모달 open */
  const onClickChargeHandler = () => {
    dispatch(openModal({ isOpen: true }));
  };

  /** 정렬 기준 선택 */
  const changeSortHandler = (event: SelectChangeEvent) => {
    setSort(event.target.value);
  };

  /** 스크롤 */
  const handleScroll = () => {
    setScrollY(window.scrollY);
  };

  /** 스크롤 useEffect */
  useEffect(() => {
    window.addEventListener('scroll', handleScroll);
    return () => {
      window.removeEventListener('scroll', handleScroll);
    };
  }, []);

  /** 배너 높이 조정 */
  useEffect(() => {
    if (bannerRef.current) {
      bannerRef.current.style.height = `calc(200px - ${scrollY}px * 3)`;
    }
    if (titleRef.current) {
      titleRef.current.style.opacity = `calc((200 - ${scrollY} * 3) / 200)`;
    }
  }, [scrollY]);

  return (
    <div className={styles.container}>
      <div ref={bannerRef} className={styles.banner}>
        <p ref={titleRef} className={styles.title}>
          FUNTEER 마일리지 충전
        </p>
      </div>
      <div className={styles.contents}>
        <div className={styles['charge-box']}>
          <p>잔액</p>
          <div>
            <span>
              <span style={{ color: 'rgba(236, 153, 75, 1)', fontSize: '1.75rem' }}>{money.toLocaleString('ko-KR')}</span> 원
            </span>
            <Button variant="contained" onClick={onClickChargeHandler}>
              충전
            </Button>
          </div>
        </div>
        <div className={styles['notice-box']}>
          <p>고지사항 및 안내문</p>
          <ul>
            {chargeNotice.map((notice) => (
              <li key={notice.idx}>
                {notice.idx}. {notice.text}
              </li>
            ))}
          </ul>
        </div>
        <div className={styles['charge-history-box']}>
          <div className={styles['charge-title-box']}>
            <p>충전 내역 확인</p>
            <Select value={sort} sx={{ height: '2.5rem' }} onChange={changeSortHandler} displayEmpty inputProps={{ 'aria-label': 'Without label' }}>
              <MenuItem value="date">날짜순</MenuItem>
              <MenuItem value="amount">금액순</MenuItem>
            </Select>
          </div>
          <table>
            <thead>
              <tr>
                <th>금액</th>
                <th>날짜</th>
              </tr>
            </thead>
            <tbody>
              {dummyChargeHistory.map((chargeHistory) => (
                <tr key={chargeHistory.idx}>
                  <td>{chargeHistory.text} 원</td>
                  <td>{chargeHistory.date}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default ChargeContainer;
