import { http } from './axios';
import { teamSignUpType } from '../types/user';
import { customAlert, w1500 } from '../utils/customAlert';

/** 단체 회원가입 */
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

/** 단체 개인정보 조회 */
export const requestTeamAccountInfo = async () => {
  const res = await http.get('/team/account');
  return res;
};

/** 단체 프로필 조회 */
export const requestTeamProfileInfo = async (teamId: string | undefined) => {
  const res = await http.get(`/team/${teamId}/profile`);
  return res;
};

/** 단체 프로필 이미지 수정 */
export const requestChangeProfileImg = async (teamId: string, profileImg: Blob) => {
  const formData = new FormData();

  formData.append('userId', teamId);
  formData.append('profileImg', profileImg);

  const res = await http.put('team/profile', formData);

  return res;
};

/** 단체 설명 수정 */
export const requestChangeDescription = async (teamId: string, description: string) => {
  const formData = new FormData();

  formData.append('userId', teamId);
  formData.append('description', description);

  const res = await http.put('team/profile', formData);

  return res;
};

/** 단체 비밀번호 수정 */
export const requestChangePassword = async (teamId: string, password: string, newPassword: string) => {
  const formData = new FormData();

  formData.append('userId', teamId);
  formData.append('password', password);
  formData.append('newPassword', newPassword);

  const res = await http.put('team/account', formData);

  return res;
};

/** 단체 실적 파일 수정 */
export const requestChangePerform = async (teamId: string, password: string, performFile: File | null) => {
  const formData = new FormData();

  formData.append('userId', teamId);
  formData.append('password', password);
  if (performFile) {
    formData.append('performFile', performFile);
  }

  const res = await http.put('team/account', formData);

  return res;
};
