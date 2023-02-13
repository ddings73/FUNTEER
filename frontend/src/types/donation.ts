import { string } from 'yargs';

export type DonationElementType = {
  id: number;
  title: string;
  content: string;
  targetAmount: string;
  currentAmount: string;
  startDate: string;
  endDate: string;
  postType: string;
  file: string;
};

export type DonationListElementType = {
  id: number;
  title: string;
  targetAmount: string;
  startDate: string;
  endDate: string;
  postType: string;
  file: string;
};

export interface DonationStatusModi {
  donationId: number | 0;
  postType: string;
}

export interface DonationInterface {
  title: string;
  content: string | undefined;
  amount: string;
  file: Blob | null;
}
