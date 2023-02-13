import { NoticeInterface } from '../types/notice';
import { http } from './axios';

/** 개인 회원 목록 조회 */
export const requestMembers = async (page: number, size: number, keyword?: string) => {
  const params = {
    page,
    size,
    keyword,
    sort: 'id,ASC',
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
    sort: 'id,ASC',
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

/** 단체 회원 가입 승인 */
export const requestAcceptTeam = async (userId: number) => {
  const response = http.post(`admin/team/${userId}/accept`);

  return response;
};

/** 펀딩 리스트 */
export const requestAdminFundingList = async (page: number, size: number) => {
  const params = {
    page,
    size,
  };

  const response = http.get('funding', { params });

  return response;
};

/** 펀딩 검색 리스트 */
export const requestAdminSearchedFundingList = async (page: number, size: number, keyword: string) => {
  const params = {
    keyword,
    page,
    size,
  };

  const response = http.get('funding/search', { params });

  return response;
};

/** 펀딩 승인 */
export const requestFundingAccept = async (id: number, isReport: boolean) => {
  let response;

  if (!isReport) {
    response = http.put(`admin/funding/${id}/accept`);
  } else {
    response = http.put(`admin/funding/${id}/report/accept`);
  }

  return response;
};

/** 펀딩 승인 거부 */
export const requestRejectFunding = async (id: number, isReport: boolean, rejectReason: string) => {
  const data = {
    rejectReason,
  };

  let response;

  if (!isReport) {
    response = http.put(`admin/funding/${id}/reject`, data);
  } else {
    response = http.put(`admin/funding/${id}/report/reject`, data);
  }

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

export const requestNoticeList = async (page?: number, size?: number) => {
  const params = {
    page,
    size,
  };

  const res = await http.get(`notice`, { params });

  return res;
};
