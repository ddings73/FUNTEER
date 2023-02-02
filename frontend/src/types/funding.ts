export type FundingElementType = {
  id: number;
  thumbnail: string;
  title: string;
  fundingDescription: string;
  amount: number;
  currentFundingAmount: number;
  startDate: string;
  endDate: string;
  postType: string;
  postDate: string;
};

export interface FundingStatisticType {
  successFundingCount: number;
  totalFundingAmount: number;
  totalFundingCount: number;
}

export interface FundingInterface {
  thumbnail: Blob;
  title: string;
  fundingDescription: string;
  categoryId: number;
  content: string | undefined;
  startDate: string;
  hashtags: string;
  endDate: string;
  LEVEL_ONE: amountLevelType;
  LEVEL_TWO: amountLevelType;
  LEVEL_THREE: amountLevelType;
}

export type amountLevelType = {
  amount: string;
  descriptions: descriptionType[];
};

export type descriptionType = {
  description: string;
};

export enum LevelEnum {
  LEVEL_ONE,
  LEVEL_TWO,
  LEVEL_THREE,
}
