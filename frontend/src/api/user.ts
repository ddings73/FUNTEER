import { http } from './axios';
import { memberSignUpType, UserSignInType } from '../types/user';

export const requestSignIn = async (userInfo: UserSignInType) => {
  const data: UserSignInType = {
    email: userInfo.email,
    password: userInfo.password,
    type: userInfo.type,
  };
  const res = await http.post('/login', data);

  return res;
};

export const requestMemberSignUp = async (memberSignUpInfo: memberSignUpType) => {
  const data: memberSignUpType = {
    name: memberSignUpInfo.name,
    email: memberSignUpInfo.email,
    password: memberSignUpInfo.password,
    nickname: memberSignUpInfo.nickname,
    phone: memberSignUpInfo.phone,
  };

  const res = await http.post('/member', data);

  return res;
};
