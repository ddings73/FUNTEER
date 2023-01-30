import React from 'react';
import profile1 from '../../../assets/images/teamProfile/김보경.jpg';
import profile2 from '../../../assets/images/teamProfile/김송빈.jpg';
import profile3 from '../../../assets/images/teamProfile/김승섭.jpg';
import profile4 from '../../../assets/images/teamProfile/김진호.jpg';
import profile5 from '../../../assets/images/teamProfile/백준봉.jpg';
import profile6 from '../../../assets/images/teamProfile/안명수.jpg';

export enum MemberState {
  Normal = '정상',
  Dormant = '휴면',
  Withdrawn = '탈퇴',
}

type adminMemberContainerItemType = {
  name: string;
  nickname: string;
  profileImageUrl: string;
  phone: string;
  email: string;
  money: string;
  memberState: MemberState;
};

const tmp = 500000;

export const AdminMemberContainerItem: adminMemberContainerItemType[] = [
  {
    name: '김보경',
    nickname: '김보경',
    profileImageUrl: profile1,
    phone: '010-1234-1234',
    email: 'bbookng@gmail.com',
    money: tmp.toLocaleString('en-Us'),
    memberState: MemberState.Normal,
  },
  {
    name: '김송빈',
    nickname: '김송빈',
    profileImageUrl: profile2,
    phone: '010-1234-1234',
    email: 'rlthdqls@gmail.com',
    money: tmp.toLocaleString('en-Us'),
    memberState: MemberState.Dormant,
  },
  {
    name: '김승섭',
    nickname: '김승섭',
    profileImageUrl: profile3,
    phone: '010-1234-1234',
    email: 'sub9707@naver.com',
    money: tmp.toLocaleString('en-Us'),
    memberState: MemberState.Normal,
  },
  {
    name: '김진호',
    nickname: '김진호',
    profileImageUrl: profile4,
    phone: '010-1234-1234',
    email: 'jinhodevelop@gmail.com',
    money: tmp.toLocaleString('en-Us'),
    memberState: MemberState.Dormant,
  },
  {
    name: '백준봉',
    nickname: '백준봉',
    profileImageUrl: profile5,
    phone: '010-1234-1234',
    email: 'becoding96@gmail.com',
    money: tmp.toLocaleString('en-Us'),
    memberState: MemberState.Normal,
  },
  {
    name: '안명수',
    nickname: '안명수',
    profileImageUrl: profile6,
    phone: '010-1234-1234',
    email: 'ddings7303@gmail.com',
    money: tmp.toLocaleString('en-Us'),
    memberState: MemberState.Dormant,
  },
];

export default AdminMemberContainerItem;
