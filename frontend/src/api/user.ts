import { http } from './axios';
import { UserSignInType } from '../types/user';

export const requestSignIn = async (userInfo: UserSignInType) => {
  const data: UserSignInType = {
    email: userInfo.email,
    password: userInfo.password,
    type: userInfo.type,
  };
  const res = await http.post('/login', data);

  return res;
};
