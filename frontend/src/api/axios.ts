import axios,{AxiosInstance} from "axios";

export const customAxios:AxiosInstance = axios.create({
    baseURL:"http://i8e204.p.ssafy.io:8080/api/v1"
})