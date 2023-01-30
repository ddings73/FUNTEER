export type FundingElementType = {
  thumbnail:string
  title:string
  fundingDescription:string
  amount:number
  currentFundingAmount:number
  startDate:string
  endDate:string
  postType:string
  postDate:string
};

export interface FundingStatisticType  {
  successFundingCount:number
  totalFundingAmount:number
  totalFundingCount:number
}

export interface FundingInterface {
  thumbnail: Blob;
  title: string;
  fundingDescription: string;
  categoryId: number;
  content: string | undefined;
  startDate: string;
  hashtags:string
  endDate: string;
  amount1: number;
  description1: string;
  amount2: number;
  description2: string;
  amount3: number;
  description3: string;
}



