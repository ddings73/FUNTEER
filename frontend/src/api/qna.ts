import { QNACreateInterface } from '../types/qna';
import { http } from './axios';

/** 본인 QNA 리스트 */
export const requestQNAList = (page: number, size: number) => {
  const params = {
    page,
    size,
  };

  const response = http.get('qna', { params });

  return response;
};

/** QNA 생성 요청 */
export const requestCreateQNA = async (QNAInfo: QNACreateInterface) => {
  const formData = new FormData();

  formData.append('title', QNAInfo.title);
  formData.append('content', QNAInfo.content);
  if (QNAInfo.files) {
    QNAInfo.files.forEach((file) => {
      formData.append('files', file);
    });
  }

  const response = await http.post('qna', formData);

  return response;
};

/** QNA 상세 요청 */
export const requestQNADetail = async (postId: number) => {
  const response = await http.get(`qna/${postId}`);

  return response;
};

/** QNA 답변 요청 */
export const requestReply = async (qnaId: number) => {
  const response = await http.get(`qna/${qnaId}/reply`);

  return response;
};

/** QNA 답변 등록 */
export const requestCreateReply = async (qnaId: number, content: string) => {
  const data = {
    content,
  };

  const response = await http.post(`qna/${qnaId}/reply`, data);

  return response;
};
