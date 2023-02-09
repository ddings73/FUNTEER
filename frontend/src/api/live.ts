import { http } from './axios';

export const requestCreateSession = async (sessionName: string, fundingId?: number) => {
  const data = {
    sessionName,
    fundingId,
  };
  const response = await http.post('openvidu/sessions', data);
  return response;
};
