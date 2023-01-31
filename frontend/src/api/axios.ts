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

// http.interceptors.response.use(
//   function (response) {
//     return response;
//   },
//   async function (error) {
//     if (error.response && error.response === 401) {
//       try {
//         const originRequest = error.config;
//         const response = await requestUpdateToken();
//       } catch (error) {}
//     }
//   },
// );
