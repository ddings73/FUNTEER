export type myGiftType = {
  amount: number;
  fundTitle: string;
  giftDate: string;
  giftId: number;
  username: string;
};

export type myDonateType = {
  list: myDonateListType[];
  totalElements: number;
  totalPages: number;
};
export type myDonateListType = {
  amount: number;
  payDate: string;
  postId: number;
  postName: string;
};
