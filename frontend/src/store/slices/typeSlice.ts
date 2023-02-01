import { createSlice } from '@reduxjs/toolkit';
import type { PayloadAction } from '@reduxjs/toolkit';

type codeType = {
  [key: string]: string;
};
export interface TypeState {
  user: codeType;
  post: codeType;
  targetMoney: codeType;
}

const initialState: TypeState = {
  user: {
    NORMAL: 'NORMAL',
  },
  post: {
    DONATION_ACTIVE: 'DONATION_ACTIVE',
  },
  targetMoney: {
    LEVEL_ONE: '1단계',
  },
};
