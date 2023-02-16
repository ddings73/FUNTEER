export type ConfirmModalType = {
  isOpen: boolean;
  title: string;
  content: string;
  handleModal: () => void;
};

export type FileModalType = {
  isOpen: boolean;
  userId: string;
  vmsFileUrl: string;
  performFileUrl: string;
  deniedNum: string;
  teamState: string;
};

export type payModalType = {
  isOpen: boolean;
};

export type donateModalType = {
  isOpen: boolean;
  postId: number;
  userId: number;
  mileage: number;
};

export type reportModalType = {
  isOpen: boolean;
  fundingId: string;
};
