import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { FileModalType } from '../../types/modal';

const initialState: FileModalType = {
  isOpen: false,
  userId: '',
  vmsFileUrl: '',
  performFileUrl: '',
  deniedNum: '',
};

export const fileModalSlice = createSlice({
  name: 'modalSlice',
  initialState,
  reducers: {
    openModal: (state, action: PayloadAction<FileModalType>) => {
      state.isOpen = action.payload.isOpen;
      state.userId = action.payload.userId;
      state.vmsFileUrl = action.payload.vmsFileUrl;
      state.performFileUrl = action.payload.performFileUrl;
      state.deniedNum = action.payload.deniedNum;
    },
    closeModal: (state) => {
      state.isOpen = false;
      state.userId = '';
      state.vmsFileUrl = '';
      state.performFileUrl = '';
      state.deniedNum = '';
    },
    denyTeam: (state, action: PayloadAction<FileModalType>) => {
      state.isOpen = action.payload.isOpen;
      state.userId = action.payload.userId;
      state.vmsFileUrl = action.payload.vmsFileUrl;
      state.performFileUrl = action.payload.performFileUrl;
      state.deniedNum = action.payload.deniedNum;
    },
  },
});

export const { openModal, closeModal, denyTeam } = fileModalSlice.actions;
export default fileModalSlice.reducer;
