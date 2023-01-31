import axios, { AxiosInstance } from 'axios';
import { requestUpdateToken } from './auth';

export const http: AxiosInstance = axios.create({
  baseURL: 'https://i8e204.p.ssafy.io/api/v1',
  withCredentials: true,
});
http.interceptors.request.use(
  function (config) {
    const token = localStorage.getItem('token');

    if (!token) {
      config.headers.accessToken = null;
      config.headers.refreshToken = null;
      return config;
    }
    const { accessToken, refreshToken } = JSON.parse(token);
    config.headers.accessToken = accessToken;
    config.headers.refreshToken = refreshToken;
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
      try {
        const originalRequest = error.config;
        const response = await requestUpdateToken();
        if (response) {
          localStorage.removeItem('token');
          localStorage.setItem('token', JSON.stringify('token', response.data));
          originalRequest.headers.accessToken = response.data.accessToken;
          originalRequest.headers.refreshToken = response.data.refreshToken;
          return await http.request(originalRequest);
        }
      } catch (error) {
        localStorage.removeItem('token');
        console.log(error);
      }
      return Promise.reject(error);
    }
    return Promise.reject(error);
  },
);
