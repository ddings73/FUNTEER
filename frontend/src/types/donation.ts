import { string } from 'yargs';

export type DonationElementType = {
  id: number;
  title: string;
  amount: number;
  currentDonationAmount: number;
  startDate: string;
  endDate: string;
  postType: string;
  postDate: string;
  file:string;
};

export interface DonationInterface {
  title: string;
  content: string | undefined;
  amount:string;
  file:Blob;
}

