import React from 'react';
import * as AiIcons from 'react-icons/ai';
import * as BsIcons from 'react-icons/bs';
import * as BiIcons from 'react-icons/bi';
import * as FaIcons from 'react-icons/fa';
import * as SlIcons from 'react-icons/sl';

type SideBarDataType = {
  title: string;
  path: string;
  icon: React.ReactNode;
};

export const SideBarData: SideBarDataType[] = [
  {
    title: '프로필 정보',
    path: '/overview',
    icon: <AiIcons.AiFillHome />,
  },
  {
    title: '개인정보 수정',
    path: '/edit',
    icon: <BsIcons.BsFillPersonFill />,
  },
  {
    title: '펀딩 내역',
    path: '/myfunds',
    icon: <BiIcons.BiReceipt />,
  },
  {
    title: '자체 기부 내역',
    path: '/selfdonates',
    icon: <FaIcons.FaMoneyBillWave />,
  },
  {
    title: '도네이션 내역',
    path: '/mydonates',
    icon: <BiIcons.BiDonateHeart />,
  },
  {
    title: '획득한 뱃지',
    path: '/mybadges',
    icon: <SlIcons.SlBadge />,
  },
  {
    title: '찜 목록',
    path: '/myfavors',
    icon: <AiIcons.AiOutlineStar />,
  },
  {
    title: '팔로우 목록',
    path: '/follows',
    icon: <AiIcons.AiOutlineHeart />,
  },
];

export default SideBarData;
