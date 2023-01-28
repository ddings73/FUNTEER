import React from 'react';
import profile1 from '../../assets/images/teamProfile/김보경.jpg';
import profile2 from '../../assets/images/teamProfile/김송빈.jpg';
import profile3 from '../../assets/images/teamProfile/김승섭.jpg';
import profile4 from '../../assets/images/teamProfile/김진호.jpg';
import profile5 from '../../assets/images/teamProfile/백준봉.jpg';
import profile6 from '../../assets/images/teamProfile/안명수.jpg';

type adminMemberContainerItemType = {
  name: string;
  nickname: string;
  profileImageUrl: string;
  email: string;
  mileage: string;
};

const tmp = 500000;

export const AdminMemberContainerItem: adminMemberContainerItemType[] = [
  {
    name: '김보경',
    nickname: '김보경',
    profileImageUrl: profile1,
    email: 'bbookng@gmail.com',
    mileage: tmp.toLocaleString('en-Us'),
  },
  {
    name: '김송빈',
    nickname: '김송빈',
    profileImageUrl: profile2,
    email: 'rlthdqls@gmail.com',
    mileage: tmp.toLocaleString('en-Us'),
  },
  {
    name: '김승섭',
    nickname: '김승섭',
    profileImageUrl: profile3,
    email: 'sub9707@naver.com',
    mileage: tmp.toLocaleString('en-Us'),
  },
  {
    name: '김진호',
    nickname: '김진호',
    profileImageUrl: profile4,
    email: 'jinhodevelop@gmail.com',
    mileage: tmp.toLocaleString('en-Us'),
  },
  {
    name: '백준봉',
    nickname: '백준봉',
    profileImageUrl: profile5,
    email: 'becoding96@gmail.com',
    mileage: tmp.toLocaleString('en-Us'),
  },
  {
    name: '안명수',
    nickname: '안명수',
    profileImageUrl: profile6,
    email: 'ddings7303@gmail.com',
    mileage: tmp.toLocaleString('en-Us'),
  },
];

export default AdminMemberContainerItem;
