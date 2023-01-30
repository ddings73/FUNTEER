export type FundingElementType = {
  title: string;
};

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
