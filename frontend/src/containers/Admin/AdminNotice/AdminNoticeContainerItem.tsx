import React from "react";

  
export type AdminNoticeContainerItemType = {
  id: number;
  title: string;
  content: string;
  regDate: string;
  files: string;
};

const tmp = 500000;

export const AdminNoticeContainerItem: AdminNoticeContainerItemType[] = [
  {
      id: 1,
    title: '김보경',
    content: '닉네임이어어엄청길어요ㅋㅋㅋㅋ',
    regDate: '2023-02-06',
    files: 'test'
  },

];

export default AdminNoticeContainerItem;
