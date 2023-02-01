import React from 'react';

type NavSettingDataType = {
  title: string;
  path: string;
};

export const NavDataSettings: NavSettingDataType[] = [
  {
    title: '마이페이지',
    path: '/myPage',
  },
  {
    title: '나의 펀딩 내역',
    path: '/myFunding',
  },
  {
    title: '도네이션 내역',
    path: '/myDonates',
  },
  {
    title: '1:1 문의 내역',
    path: '/myBadges',
  },
  {
    title: '로그아웃',
    path: '/logout',
  },
];

export default NavDataSettings;
