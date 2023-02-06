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


/**
 * @name 유저정보수정
 * @param userInfo 
 * @param userId 
 * @returns 
 */
export const requestModifyUserInfo = async (userInfo:changeUserInfoInterface,userId:string)=>{

  const data = {
    newPassword:userInfo.newPassword,
    password:userInfo.password,
    userId:Number(userId)
  }
  const response = await http.put('member/account',data)
  return response;
}


/**
 * @name 유저프로필공개설정
 * @param display 
 * @param userId 
 * @returns 
 */
export const requestModifyUserDisplay =async(display:boolean, userId:string)=>{
  console.log(display,userId)
  const data ={
    display,
    userId:Number(userId)
  }
  const response = await http.put('member/profile',data)
  return response;
}

/**
 * @name 유저프로필수정
 * @param profileImage 
 * @param userId 
 * @returns 
 */

export const requestModifyUserProfileImage = async(profileImage:Blob ,userId:string)=>{
  const formDate = new FormData();
  formDate.append('profileImg',profileImage)
  formDate.append('userId',userId)


  const response = await http.put('member/profile',formDate)
  return response
}