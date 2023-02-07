import React from 'react';

export enum DonationState {
  All = '전체',
  CLOSE='종료',
  ACTIVE='진행중',
}

type adminTeamContainerItemType = {
  id: number;
  title: string;
  currentDonationAmount: string;
  targetAmount:string;
  postDate: string;
  endDate: string;
  donationState: DonationState;
};

export const AdminTeamContainerItem: adminTeamContainerItemType[] = [
  {
    id: 1,
    title: '기계식 키보드를 사주세요!!! 기계식 키보드를 사주세요!!! 기계식 키보드를 사주세요!!!',
    currentDonationAmount: '500',
    postDate: '2023-01-11',
    endDate: '2023-01-27',
    donationState: DonationState.CLOSE,
    targetAmount:'1000000',
  },
  {
    id: 2,
    title: '기계식 키보드를 사주세요!!! 기계식 키보드를 사주세요!!! 기계식 키보드를 사주세요!!!',
    currentDonationAmount: '500',
    postDate: '2023-01-11',
    endDate: '2023-01-27',
    donationState: DonationState.CLOSE,
    targetAmount:'1000000',
  },
  {
    id: 3,
    title: '기계식 키보드를 사주세요!!! 기계식 키보드를 사주세요!!! 기계식 키보드를 사주세요!!!',
    currentDonationAmount: '500',
    postDate: '2023-01-11',
    endDate: '2023-01-27',
    donationState: DonationState.CLOSE,
    targetAmount:'1000000',
  },
  {
    id: 4,
    title: '기계식 키보드를 사주세요!!! 기계식 키보드를 사주세요!!! 기계식 키보드를 사주세요!!!',
    currentDonationAmount: '500',
    postDate: '2023-01-11',
    endDate: '2023-01-27',
    donationState: DonationState.CLOSE,
    targetAmount:'1000000',
  },
  {
    id: 5,
    title: '기계식 키보드를 사주세요!!! 기계식 키보드를 사주세요!!! 기계식 키보드를 사주세요!!!',
    currentDonationAmount: '500',
    postDate: '2023-01-11',
    endDate: '2023-01-27',
    donationState: DonationState.ACTIVE,
    targetAmount:'1000000',
  },
];

export default AdminTeamContainerItem;
