import { http } from './axios';

/**
 * 토큰 갱신 API
 * @method POST
 */
export const requestUpdateToken = async () => {
  const response = await http.get('refresh');
  console.log("updateToken", response);
  
  return response;
};
