import { http } from './axios';

/**
 * 토큰 갱신 API
 * @method POST
 */
export const requestUpdateToken = async () => {
  const refreshToken = localStorage.getItem('refreshToken');

  const response = await http.post('refresh', {
    refreshToken: `${refreshToken}`,
  });
  // eslint-disable-next-line consistent-return
  return response;
};
