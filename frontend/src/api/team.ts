import { http } from './axios';

export const testRequestTeamAccountInfo = async () => {
  const res = await http.get('/team/account');
  return res;
};

export const testRequestTeamProfileInfo = async () => {
  const res = await http.get('/team/78/profile');
  return res;
};
