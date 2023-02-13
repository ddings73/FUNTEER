import { FaqInterface } from '../types/faq';
import { http } from './axios';

/**
 * faq 생성 API
 * @method POST
 * @param {FaqInterface} faqData
 */
export const requestCreateFaq = async (faqData: FaqInterface) => {
  const data = {
    groupOrPerson: faqData.groupOrPerson,
    title: faqData.title,
    content: faqData.content,
  };

  const res = await http.post('faq', data);

  return res;
};

/** faq 수정 API */
export const requestModifyFaq = async (FAQInfo: FaqInterface) => {
  const data = {
    groupOrPerson: FAQInfo.groupOrPerson,
    title: FAQInfo.title,
    content: FAQInfo.content,
  };

  const res = await http.put(`faq/${FAQInfo.id}`, data);

  return res;
};

/**
 * faq 리스트 호출 API
 * @method GET
 */
export const requestFaqList = async (size?: number) => {
  const params = {
    size,
  };

  const res = await http.get('faq', { params });

  return res;
};

/**
 * @name 다음 faq리스트호출
 * @returns
 */
export const requestNextAdminFaqList = async (currentPage: number, size: number) => {
  console.log(currentPage, size);

  const response = await http.get(`admin/faq/?page=${currentPage + 1}&size=${size}`);
  return response;
};

/*
 * faq 상세 호출
 * @method GET
 */
export const requestFaqDetail = async (postId?: number) => {
  const res = await http.get(`faq/${postId}`);
  console.log(res);
  return res;
};

/** faq 삭제 */
export const requestFaqDelete = async (postId: number) => {
  const res = await http.delete(`faq/${postId}`);

  return res;
};
