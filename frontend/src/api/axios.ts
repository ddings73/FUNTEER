import axios, { AxiosInstance } from 'axios';
import { requestUpdateToken } from './auth';

export const http: AxiosInstance = axios.create({
  baseURL: 'https://i8e204.p.ssafy.io/api/v1',
  withCredentials: true,
});
http.interceptors.request.use(
  function (config) {
    config.headers.Authorization = `Bearer ${localStorage.getItem('accessToken')}`;
    return config;
  },
  function (error) {
    // 요청 오류가 있는 작업 수행
    return Promise.reject(error);
  },
);

http.interceptors.response.use(
  function (response) {
    return response;
  },
  async function (error) {
    if (error.response && error.response.status === 401) {
      // const refreshToken = JSON.parse(localStorage.getItem('refreshToken'));
      const refreshToken = localStorage.getItem('refreshToken');
      if (!refreshToken) {
        return error;
      }
      try {
        const response = await requestUpdateToken();
        localStorage.setItem('accessToken', response?.data.accessToken);
        localStorage.setItem('refreshToken', response?.data.refreshToken);
        return await http.request(error.config);
      } catch (e) {
        localStorage.removeItem('accessToken');
        localStorage.removeItem('refreshToken');
      }
      return Promise.reject(error);
    }
    return Promise.reject(error);
  },
);
