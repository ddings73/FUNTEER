export type UserSignInType = {
  email: string;
  password: string;
  type: string;
};

export type memberSignUpType = {
  email: string;
  password: string;
  passwordCheck: string;
  name: string;
  nickname: string;
  phone: string;
};

export type teamSignUpType = {
  name: string;
  email: string;
  password: string;
  phone: string;
  vmsFile: Blob;
  performFile: Blob;
};
