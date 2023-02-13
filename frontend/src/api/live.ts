import { async } from 'q';
import { http } from './axios';

export const requestCreateSession = async (sessionName: string, fundingId?: number) => {
  const data = {
    sessionName,
    fundingId,
  };
  const response = await http.post('openvidu/sessions', data);
  return response;
};


export const requestLiveList = async () => {
  const response = await http.get("openvidu/sessions")
  return response;
  
}

export const requestLiveDonation = async(amount:number,sessionName:string)=>{
  const data = {
    amount,
    sessionName
  }
  const response = await http.post("openvidu/sessions/gift",data)
  return response;
}