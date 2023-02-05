import React from 'react';

export enum FundingState {
  All = '전체',
  NotFundApproved = '승인 대기',
  FundApproved = '승인',
  Funding = '펀딩중',
  Funded = '펀딩 완료',
  Acted = '활동 종료',
  NotActApproved = '보고서 대기',
  ActApproved = '종료',
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

export const AdminTeamContainerItem: adminTeamContainerItemType[] = [
  {
    id: 1,
    title: '기계식 키보드를 사주세요!!! 기계식 키보드를 사주세요!!! 기계식 키보드를 사주세요!!!',
    teamName: '잠시만두',
    amount1: '50,000',
    amount2: '100,000',
    amount3: '200,000',
    currentFundingAmount: '500',
    postDate: '2023-01-11',
    startDate: '2023-01-13',
    endDate: '2023-01-27',
    planFile: 'planfile.pdf',
    reportFile: 'reportfile.pdf',
    fundingState: FundingState.NotFundApproved,
  },
  {
    id: 2,
    title: '기계식 키보드를 사주지 마세요.',
    teamName: '잠시김치만두',
    amount1: '50,000',
    amount2: '100,000',
    amount3: '200,000',
    currentFundingAmount: '500',
    postDate: '2023-01-11',
    startDate: '2023-01-13',
    endDate: '2023-01-27',
    planFile: 'planfile.pdf',
    reportFile: 'reportfile.pdf',
    fundingState: FundingState.FundApproved,
  },
  {
    id: 3,
    title: '기계식 키보드를 사주세요.',
    teamName: '잠시고기만두',
    amount1: '50,000',
    amount2: '100,000',
    amount3: '200,000',
    currentFundingAmount: '500',
    postDate: '2023-01-11',
    startDate: '2023-01-13',
    endDate: '2023-01-27',
    planFile: 'planfile.pdf',
    reportFile: 'reportfile.pdf',
    fundingState: FundingState.Funding,
  },
  {
    id: 4,
    title: '기계식 키보드를 사주지 마세요.',
    teamName: '잠시만두',
    amount1: '50,000',
    amount2: '100,000',
    amount3: '200,000',
    currentFundingAmount: '500',
    postDate: '2023-01-11',
    startDate: '2023-01-13',
    endDate: '2023-01-27',
    planFile: 'planfile.pdf',
    reportFile: 'reportfile.pdf',
    fundingState: FundingState.Funded,
  },
  {
    id: 5,
    title: '기계식 키보드를 사주세요.',
    teamName: '잠시고기만두',
    amount1: '50,000',
    amount2: '100,000',
    amount3: '200,000',
    currentFundingAmount: '500',
    postDate: '2023-01-11',
    startDate: '2023-01-13',
    endDate: '2023-01-27',
    planFile: 'planfile.pdf',
    reportFile: 'reportfile.pdf',
    fundingState: FundingState.NotActApproved,
  },
  {
    id: 6,
    title: '기계식 키보드를 사주지 마세요.',
    teamName: '잠시만두',
    amount1: '50,000',
    amount2: '100,000',
    amount3: '200,000',
    currentFundingAmount: '500',
    postDate: '2023-01-11',
    startDate: '2023-01-13',
    endDate: '2023-01-27',
    planFile: 'planfile.pdf',
    reportFile: 'reportfile.pdf',
    fundingState: FundingState.ActApproved,
  },
  {
    id: 7,
    title: '밥 사주세요.',
    teamName: '잠시만두',
    amount1: '50,000',
    amount2: '100,000',
    amount3: '200,000',
    currentFundingAmount: '500',
    postDate: '2023-01-11',
    startDate: '2023-01-13',
    endDate: '2023-01-27',
    planFile: 'planfile.pdf',
    reportFile: 'reportfile.pdf',
    fundingState: FundingState.NotFundApproved,
  },
];

export default AdminTeamContainerItem;
