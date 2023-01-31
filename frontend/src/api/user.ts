import { http } from './axios';
import { memberSignUpType, teamSignUpType, UserSignInType } from '../types/user';

export const requestSignIn = async (userInfo: UserSignInType) => {
  const data: UserSignInType = {
    email: userInfo.email,
    password: userInfo.password,
    type: userInfo.type,
  };
  const res = await http.post('/login', data);

  return res;
};

export const requestEmailDuplConfirm = async (email: string) => {
  const res = await http.get('/confirm/email', {
    params: {
      email,
    },
  });

  return res;
};

export const requestNameDuplConfirm = async (name: string) => {
  const res = await http.get('/confirm/name', {
    params: {
      name,
    },
  });

  return res;
};

export const requestNicknameDuplConfirm = async (nickname: string) => {
  const res = await http.get('/confirm/nickname', {
    params: {
      nickname,
    },
  });

  return res;
};

export const requestPhoneDuplConfirm = async (phone: string) => {
  const res = await http.get('/confirm/phone', {
    params: {
      phone,
    },
  });

  return res;
};

export const requestMemberSignUp = async (memberSignUpInfo: memberSignUpType) => {
  const data = {
    name: memberSignUpInfo.name,
    email: memberSignUpInfo.email,
    password: memberSignUpInfo.password,
    nickname: memberSignUpInfo.nickname,
    phone: memberSignUpInfo.phone,
  };

  const res = await http.post('/member', data);

  return res;
};

export const requestTeamSignUp = async (teamSignUpInfo: teamSignUpType) => {
  const formData = new FormData();
  const entries = Object.entries(teamSignUpInfo);

  entries.forEach((data) => {
    const key = data[0];
    if (key !== 'passwordCheck') {
      const value = data[1];

      formData.append(`${key}`, value);
    }
  });

  const res = await http.post('team', formData);

  return res;
};
