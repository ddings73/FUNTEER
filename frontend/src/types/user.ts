import { string } from 'yargs';

export type UserSignInType = {
  email: string;
  password: string;
};

export type memberSignUpType = {
  email: string;
  password: string;
  passwordCheck: string;
  name: string;
  nickname: string;
  phone: string;
};

export type teamSignUpType = {
  name: string;
  email: string;
  password: string;
  passwordCheck: string;
  phone: string;
  vmsFile: Blob;
  performFile: Blob;
};

export type teamProfileType = {
  profileImgUrl: string;
  name: string;
  email: string;
  phone: string;
  money: number;
  description: string;
  fundingList: object;
  totalFundingAmount: number;
};

export interface userProfileInterface {
  nickname: string;
  profileUrl: string;
  money: number;
  wishCnt: number;
  followingCnt: number;
}

export interface userInfoInterface {
  email: string;
  name: string;
  phone: string;
}

export interface changeUserInfoInterface {
  password:string
  newPassword:string
}