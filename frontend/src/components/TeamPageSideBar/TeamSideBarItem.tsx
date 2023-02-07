import React from 'react';
import * as AiIcons from 'react-icons/ai';
import * as BsIcons from 'react-icons/bs';
import * as BiIcons from 'react-icons/bi';

type SideBarDataType = {
  title: string;
  path: string;
  icon: React.ReactNode;
};

export const SideBarData: SideBarDataType[] = [
  {
    title: '프로필 정보',
    path: '/team',
    icon: <AiIcons.AiFillHome />,
  },
  {
    title: '도네이션 내역',
    path: '/teamdonation',
    icon: <BiIcons.BiDonateHeart />,
  },
];

export default SideBarData;
