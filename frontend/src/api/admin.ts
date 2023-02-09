import { NoticeInterface } from '../types/notice';
import { http } from './axios';

/** 개인 회원 목록 조회 */
export const requestMembers = async (page: number, size: number, keyword?: string) => {
  const params = {
    page,
    size,
    keyword,
    sort: 'ASC',
  };

  const response = http.get('admin/members', { params });

  return response;
};

/** 개인 회원 탈퇴 처리 */
export const requestWithdrawMember = async (userId: number) => {
  const response = http.delete(`admin/member/${userId}`);

  return response;
};

/** 단체 회원 목록 조회 */
export const requestTeams = async (page: number, size: number, keyword?: string) => {
  const params = {
    page,
    size,
    keyword,
    sort: 'ASC',
  };

  const response = http.get('admin/team', { params });

  return response;
};

/** 단체 회원 탈퇴 처리 */
export const requestWithdrawTeam = async (userId: number) => {
  const response = http.delete(`admin/team/${userId}`);

  return response;
};

/** 단체 회원 가입 거부 */
export const requestDenyTeam = async (userId: number, rejectComment: string) => {
  const data = {
    rejectComment,
  };

  const response = http.put(`admin/team/${userId}/reject`, data);

  return response;
};

/** 공지사항 작성 */
export const requestCreateNotice = async (noticeData: NoticeInterface) => {
  const formData = new FormData();

  formData.append('title', noticeData.title);
  formData.append('content', noticeData.content as string);
  noticeData.files.forEach((file) => {
    formData.append('files', file);
  });

  const response = await http.post('/notice', formData);
  return response;
};

/** 공지사항 삭제 */
export const requestDeleteNotice = async (noticeId: string) => {
  const response = await http.delete(`/notice/${noticeId}`);
  return response;
};

/** 공지사항 상세 */
export const requestNoticeDetail = async (noticeId: string | undefined) => {
  const response = await http.get(`/notice/${noticeId}`);
  return response;
};

/**
 * 공지사항 리스트 호출 API
 * @method GET
 */

export const requestNoticeList = async () => {
  const res = await http.get(`notice`);
  console.log(res);

  return res;
};
