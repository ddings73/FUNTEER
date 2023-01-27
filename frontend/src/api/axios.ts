import axios, { AxiosInstance } from 'axios';

export const http: AxiosInstance = axios.create({
  baseURL: 'http://localhost:8080/api/v1',
  withCredentials: true,
});
