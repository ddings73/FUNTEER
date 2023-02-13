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
};

export type payModalType = {
  isOpen: boolean;
};
