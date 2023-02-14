import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { reportModalType } from '../../types/modal';

const initialState: reportModalType = {
  isOpen: false,
  content: '',
};

export const reportModalSlice = createSlice({
  name: 'reportModalSlice',
  initialState,
  reducers: {
    openModal: (state) => {
      state.isOpen = true;
    },
    closeModalWith: (state, action: PayloadAction<reportModalType>) => {
      state.isOpen = action.payload.isOpen;
      state.content = action.payload.content;
    },
    closeModalPlain: (state) => {
      state.isOpen = false;
    },
  },
});

export const { openModal, closeModalWith, closeModalPlain } = reportModalSlice.actions;

export default reportModalSlice.reducer;
