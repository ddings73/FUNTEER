export type ConfirmModalType = {
  isOpen: boolean;
  title: string;
  content: string;
  handleModal: () => void;
};

export type FileModalType = {
  isOpen: boolean;
  vmsNum: string;
  vmsFile: string;
  performFile: string;
  deniedNum: string;
};

export type payModalType = {
  isOpen: boolean;
};
