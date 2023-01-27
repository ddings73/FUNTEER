import { customAxios } from "./axios";
import { UserSignInType } from "../types/user";

export const requestSignIn = async(userInfo:UserSignInType)=>{
    const res = await customAxios.get('/login')

    return res;
}