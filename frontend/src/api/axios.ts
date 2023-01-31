import axios, { AxiosInstance } from 'axios';
import { requestUpdateToken } from './auth';

export const http: AxiosInstance = axios.create({
  baseURL: 'https://i8e204.p.ssafy.io/api/v1',
  withCredentials: true,
});
http.interceptors.request.use(
  function (config) {
    const accessToken = localStorage.getItem('accessToken')
    const refreshToken = localStorage.getItem('refreshToken');

    if (!accessToken ||!refreshToken) {
      config.headers.accessToken = null;
      config.headers.refreshToken = null;
      return config;
    }
    config.headers.Authorization =`Bearer ${JSON.parse(accessToken)}`;
    config.headers.refreshToken = JSON.parse(refreshToken);
    console.log('requestHEADER' ,config);
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
        console.log("original",originalRequest);
        
        const response = await requestUpdateToken();
        console.log("response",response);
        
        if (response) {
          localStorage.removeItem('accessToken');
          localStorage.removeItem('refreshToken');

          localStorage.setItem('accessToken', JSON.stringify( response.data.accessToken));
          localStorage.setItem('refreshToken', JSON.stringify(response.data.refreshToken));

          originalRequest.headers.Authorization =  `Bearer ${JSON.stringify(response.data.accessToken)}`;
          originalRequest.headers.refreshToken = response.data.refreshToken;
          return await http.request(originalRequest);
        }
      } catch (error) {

        // localStorage.removeItem("accessToken")
        // localStorage.removeItem("refreshToken")
        console.log(error);
      }
      return Promise.reject(error);
    }
    return Promise.reject(error);
  },
);
