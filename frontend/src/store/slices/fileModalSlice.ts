import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { FileModalType } from '../../types/modal';

const initialState: FileModalType = {
  isOpen: false,
  vmsNum: '',
  vmsFile: '',
  performFile: '',
  deniedNum: '',
};

export const fileModalSlice = createSlice({
  name: 'modalSlice',
  initialState,
  reducers: {
    openModal: (state, action: PayloadAction<FileModalType>) => {
      state.isOpen = action.payload.isOpen;
      state.vmsNum = action.payload.vmsNum;
      state.vmsFile = action.payload.vmsFile;
      state.performFile = action.payload.performFile;
      state.deniedNum = action.payload.deniedNum;
    },
    closeModal: (state) => {
      state.isOpen = false;
      state.vmsNum = '';
      state.vmsFile = '';
      state.performFile = '';
      state.deniedNum = '';
    },
    denyTeam: (state, action: PayloadAction<FileModalType>) => {
      state.isOpen = action.payload.isOpen;
      state.vmsNum = action.payload.vmsNum;
      state.vmsFile = action.payload.vmsFile;
      state.performFile = action.payload.performFile;
      state.deniedNum = action.payload.deniedNum;
    },
  },
});

export const { openModal, closeModal, denyTeam } = fileModalSlice.actions;
export default fileModalSlice.reducer;
