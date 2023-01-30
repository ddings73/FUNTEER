export type FundingElementType = {
  title: string;
};

export interface FundingInterface {
  thumbnail: string;
  title: string;
  fundingDescription: string;
  category?: string;
  content: string;
  startDate: string;
  endDate: string;
  amount1: string;
  description1: string;
  amount2: string;
  description2: string;
  amount3: string;
  description3: string;
}
