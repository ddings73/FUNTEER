import { http } from './axios';

export const requestUploadImage = async (imageBase64: Blob) => {
  const formData = new FormData();
  formData.append('file', imageBase64);
  const res = await http.post('funding/upload', formData);
  return res.data;
};
