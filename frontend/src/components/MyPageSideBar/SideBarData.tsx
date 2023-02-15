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
    path: '/myPage',
    icon: <AiIcons.AiFillHome />,
  },
  {
    title: '개인정보 수정',
    path: '/editProfile',
    icon: <BsIcons.BsFillPersonFill />,
  },
  {
    title: '펀딩 내역',
    path: '/myFunding',
    icon: <BiIcons.BiReceipt />,
  },
  {
    title: '자체 기부 내역',
    path: '/myFunteerDonate',
    icon: <FaIcons.FaMoneyBillWave />,
  },
  {
    title: '도네이션 내역',
    path: '/myDonates',
    icon: <BiIcons.BiDonateHeart />,
  },
  {
    title: '획득한 뱃지',
    path: '/myBadges',
    icon: <SlIcons.SlBadge />,
  },
];

export default SideBarData;
