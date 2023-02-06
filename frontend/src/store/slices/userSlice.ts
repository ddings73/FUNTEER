import { createSlice } from '@reduxjs/toolkit';
import type { PayloadAction } from '@reduxjs/toolkit';
import { RootState } from '../store';

export interface UserStateInterface {
  isLogin: boolean;
  userType: string;
}

const initialState: UserStateInterface = {
  isLogin: false,
  userType: '',
};

export const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    setUserLoginState: (state, action: PayloadAction<boolean>) => {
      state.isLogin = action.payload;
    },
    setUserType: (state, action: PayloadAction<string>) => {
      state.userType = action.payload;
    },
    resetLoginState: (state) => {
      state.isLogin = false;
      state.userType = '';
    },
  },
});

export const { setUserLoginState, setUserType, resetLoginState } = userSlice.actions;

export const isLoginState = (state: RootState) => state.userSlice.isLogin;

export default userSlice.reducer;
