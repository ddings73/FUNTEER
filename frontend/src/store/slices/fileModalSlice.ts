import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { FileModalType } from '../../types/modal';

const initialState: FileModalType = {
  isOpen: false,
  userId: '',
  vmsFileUrl: '',
  performFileUrl: '',
  deniedNum: '',
  teamState: '',
};

export const fileModalSlice = createSlice({
  name: 'fileModalSlice',
  initialState,
  reducers: {
    openModal: (state, action: PayloadAction<FileModalType>) => {
      state.isOpen = action.payload.isOpen;
      state.userId = action.payload.userId;
      state.vmsFileUrl = action.payload.vmsFileUrl;
      state.performFileUrl = action.payload.performFileUrl;
      state.deniedNum = action.payload.deniedNum;
      state.teamState = action.payload.teamState;
    },
    closeModal: (state) => {
      state.isOpen = false;
      state.userId = '';
      state.vmsFileUrl = '';
      state.performFileUrl = '';
      state.deniedNum = '';
      state.teamState = '';
    },
    denyTeam: (state, action: PayloadAction<FileModalType>) => {
      state.isOpen = action.payload.isOpen;
      state.userId = action.payload.userId;
      state.vmsFileUrl = action.payload.vmsFileUrl;
      state.performFileUrl = action.payload.performFileUrl;
      state.deniedNum = action.payload.deniedNum;
      state.teamState = action.payload.teamState;
    },
  },
});

export const { openModal, closeModal, denyTeam } = fileModalSlice.actions;
export default fileModalSlice.reducer;
