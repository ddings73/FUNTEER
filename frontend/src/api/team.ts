import { http } from './axios';

export const requestTeamAccountInfo = async () => {
  const res = await http.get('/team/account');
  return res;
};

export const requestTeamProfileInfo = async (teamId: string | undefined) => {
  const res = await http.get(`/team/${teamId}/profile`);
  return res;
};
