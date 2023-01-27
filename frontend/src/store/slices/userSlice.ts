import { createSlice } from '@reduxjs/toolkit';
import type { PayloadAction } from '@reduxjs/toolkit';
import { RootState } from '../store';

export interface UserStateInterface {
  isLogin: boolean;
}

const initialState: UserStateInterface = {
  isLogin: false,
};

export const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    setUserLoginState: (state, action: PayloadAction<boolean>) => {
      state.isLogin = action.payload;
    },
  },
});

export const { setUserLoginState } = userSlice.actions;

export const isLoginState = (state: RootState) => state.userSlice.isLogin;

export default userSlice.reducer;
