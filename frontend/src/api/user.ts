import { http } from './axios';
import { changeUserInfoInterface, memberSignUpType, teamSignUpType, UserSignInType } from '../types/user';

/**
 * 이메일 로그인 요청
 * @method POST
 * @param useInfo - userInfo : UserSignInType
 */
export const requestSignIn = async (userInfo: UserSignInType) => {
  const data: UserSignInType = {
    email: userInfo.email,
    password: userInfo.password,
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

/**
 * 유저 정보 조회 API
 * @method GET
 */
export const requestUserInfo = async () => {
  const response = await http.get(`member/account`);
  return response;
};

export const requestFollow = async (teamId: string | undefined) => {
  const res = await http.put(`member/follow/${teamId}`);
  return res;
};

/**
 * @name 로그아웃
 * @method DELETE
 */
export const requestLogout = async () => {
  const response = await http.delete('out');
  return response;
};

/**
 * @name 유저프로필조회
 * @method GET
 */
export const requestUserProfile = async (userId: string) => {
  const response = await http.get(`member/${userId}/profile`);
  return response;
};

export const requestTeamProfile = async (userId: string) => {
  const response = await http.get(`team/${userId}/profile`);
  return response;
};

/**
 * @name 유저정보수정
 * @param userInfo
 * @param userId
 * @returns
 */
export const requestModifyUserInfo = async (userInfo: changeUserInfoInterface, userId: string) => {
  const data = {
    newPassword: userInfo.newPassword,
    password: userInfo.password,
    userId: Number(userId),
  };
  const response = await http.put('member/account', data);
  return response;
};

/**
 * @name 유저프로필공개설정
 * @param display
 * @param userId
 * @returns
 */
export const requestModifyUserDisplay = async (display: boolean, userId: string) => {
  const formData = new FormData();
  formData.append('display', String(display));
  formData.append('userId', userId);
  const response = await http.put('member/profile', formData);
  return response;
};

/**
 * @name 유저프로필수정
 * @param profileImage
 * @param userId
 * @returns
 */

export const requestModifyUserProfileImage = async (profileImage: Blob, userId: string) => {
  const formDate = new FormData();
  formDate.append('profileImg', profileImage);
  formDate.append('userId', userId);

  const response = await http.put('member/profile', formDate);
  return response;
};

/**
 * @name 이메일찾기
 * @param name
 * @param phone
 * @returns
 */
export const requestFindEmail = async (name: string, phone: string) => {
  const data = {
    name,
    phone,
  };
  const response = http.put('forget/email', data);
  return response;
};

/**
 * @name 이메일인증코드보내기
 * @param email
 * @returns
 */
export const requestSendEmailAuthCode = async (email: string) => {
  const response = http.get(`mail/send/?email=${email}`);
  return response;
};

/**
 * @name 이메일인증코드확인
 * @param code
 * @param email
 * @returns
 */
export const requestCheckEmailAuthCode = async (code: string, email: string) => {
  const response = await http.get(`mail/confirm/?code=${code}&email=${email}`);
  return response;
};

/**
 * @name 비밀번호재설정
 * @param email
 * @param name
 * @param password
 * @returns
 */
export const requestResetPassword = async (email: string, name: string, password: string) => {
  const data = {
    email,
    name,
    password,
  };

  const response = http.put('forget/pw', data);
  return response;
};

/**
 * @name 기프트내역
 * @method GET
 */
export const requestGiftList = async () => {
  const response = await http.get(`member/gift?page=0&size=10&sort=DESC`);
  return response;
};
