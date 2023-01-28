import axios,{AxiosInstance} from "axios";

export const http: AxiosInstance = axios.create({
  baseURL: 'https://localhost/api/v1',
  withCredentials: true,
});
