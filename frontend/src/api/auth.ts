import { http } from './axios';

/**
 * 토큰 갱신 API
 * @method POST
 */
export const requestUpdateToken = async (accessToken: string, refreshToken: string) => {
  const data = {
    accessToken,
    refreshToken,
  };
  const response = await http.post('refresh', data);
  return response;
};
