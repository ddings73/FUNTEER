import { NoticeInterface } from '../types/notice';
import { http } from './axios';

/** 개인 회원 목록 조회 */
export const requestMembers = async (page: number, size: number, keyword?: string, userType?: string) => {
  const params = {
    page,
    size,
    keyword,
    userType,
    sort: 'id,DESC',
  };

  const response = await http.get('admin/members', { params });

  return response;
};

/** 개인 회원 탈퇴 처리 */
export const requestWithdrawMember = async (userId: number) => {
  const response = await http.delete(`admin/member/${userId}`);

  return response;
};

/** 단체 회원 목록 조회 */
export const requestTeams = async (page: number, size: number, keyword?: string, userType?: string) => {
  const params = {
    page,
    size,
    keyword,
    userType,
    sort: 'id,DESC',
  };

  const response = await http.get('admin/team', { params });

  return response;
};

/** 단체 회원 탈퇴 처리 */
export const requestWithdrawTeam = async (userId: number) => {
  const response = await http.delete(`admin/team/${userId}`);

  return response;
};

/** 단체 회원 가입 거부 */
export const requestDenyTeam = async (userId: number, rejectComment: string) => {
  const data = {
    rejectComment,
  };

  const response = await http.put(`admin/team/${userId}/reject`, data);

  return response;
};

/** 단체 회원 가입 승인 */
export const requestAcceptTeam = async (userId: number) => {
  const response = await http.post(`admin/team/${userId}/accept`);

  return response;
};

/** 펀딩 리스트 */
export const requestAdminFundingList = async (page: number, size: number, keyword?: string, postType?: string) => {
  const params = {
    page,
    size,
    keyword,
    postType,
    sort: 'id,DESC',
  };

  const response = await http.get('admin/funding', { params });

  return response;
};

/** 펀딩 승인 */
export const requestFundingAccept = async (id: number, isReport: boolean) => {
  let response;

  if (!isReport) {
    response = await http.put(`admin/funding/${id}/accept`);
  } else {
    response = await http.put(`admin/funding/${id}/report/accept`);
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
    response = await http.put(`admin/funding/${id}/reject`, data);
  } else {
    response = await http.put(`admin/funding/${id}/report/reject`, data);
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

/** 공지사항 수정 */
export const requestEditNotice = async (postId: number, title: string, content: string, files?: File[], newFiles?: File[]) => {
  const formData = new FormData();

  formData.append('title', title);
  formData.append('content', content);
  if (files) {
    files.forEach((file) => {
      const prevFile = new File([JSON.stringify({ [Object.keys(file)[0]]: Object.values(file)[0] })], Object.keys(file)[0], { type: 'application/json' });
      formData.append('files', prevFile);
    });
  }
  if (newFiles) {
    newFiles.forEach((file) => {
      formData.append('files', file);
    });
  }

  const response = await http.put(`notice/${postId}`, formData);

  return response;
};
