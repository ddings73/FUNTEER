import { objectTraps } from "immer/dist/internal";


export enum FundingState {
  All = '전체',
  FUNDING_WAIT = '승인 대기',
  FUNDING_ACCEPT = '승인',
  FUNDING_REJECT = '거부',
  FUNDING_INPROGRESS = '진행중',
  FUNDING_EXTEND = '연장',
  FUNDING_FAIL = '펀딩 실패',
  FUNDING_COMPLETE = '펀딩 완료',
  REPORT_WAIT = '보고서 대기',
  REPORT_ACCEPT = '보고서 승인',
  REPORT_REJECT = '보고서 거부'
}

export enum FundingStateList {
  ALL = 'ALL',
  FUNDING_WAIT = 'FUNDING_WAIT',
  FUNDING_ACCEPT = 'FUNDING_ACCEPT',
  FUNDING_REJECT = 'FUNDING_REJECT',
  FUNDING_INPROGRESS = 'FUNDING_INPROGRESS',
  FUNDING_EXTEND = 'FUNDING_EXTEND',
  FUNDING_FAIL = 'FUNDING_FAIL',
  FUNDING_COMPLETE = 'FUNDING_COMPLETE',
  REPORT_WAIT = 'REPORT_WAIT',
  REPORT_ACCEPT = 'REPORT_ACCEPT',
  REPORT_REJECT = 'REPORT_REJECT'
}


export type adminFundingListContainerItemType = {
  id: number;
  title: string;
  startDate: string;
  endDate: string;
  teamName: string;
  postType: FundingState;
}

type adminTeamContainerItemType = {
  id: number;
  title: string;
  teamName: string;
  amount1: string;
  amount2: string;
  amount3: string;
  currentFundingAmount: string;
  postDate: string;
  startDate: string;
  endDate: string;
  planFile: string;
  reportFile: string;
  fundingState: FundingState;
};

