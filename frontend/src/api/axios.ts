import axios,{AxiosInstance} from "axios";

export const http: AxiosInstance = axios.create({
  baseURL: 'https://i8e204.p.ssafy.io/api/v1',
  withCredentials: true,
});
