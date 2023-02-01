import { http } from './axios';

/**
 * @method: Get
 *
 */
export const requestUnderDonating = async () => {
  const res = await http.get('donation/');
  console.log(res);

  return res;
};
