import React from 'react';

type NavbarMenuDataType = {
  title: string;
  subRoutes: string[];
  subPath: string[];
};

export const NavbarMenuData: NavbarMenuDataType[] = [
  {
    title: '서비스소개',
    subRoutes: ['서비스 소개', '운영팀 소개'],
    subPath: ['service', 'team'],
  },
  {
    title: '펀딩서비스',
    subRoutes: ['전체 펀딩 목록', '펀딩 등록(단체)', '펀딩 관리(단체)'],
    subPath: ['funding', 'funding/create', 'funding/detail/:fundIdx'],
  },
  {
    title: '기부서비스',
    subRoutes: ['진행중인 기부', '전체 기부 목록'],
    subPath: ['donation', 'funding/detail/:fundIdx'],
  },
  {
    title: '라이브방송',
    subRoutes: ['전체 라이브 목록', '도네이션'],
    subPath: ['service', 'team'],
  },
  {
    title: '고객센터',
    subRoutes: ['공지사항', 'FAQ', '1:1 문의내역'],
    subPath: ['cc', 'cc', 'cc'],
  },
];

export default NavbarMenuData;
