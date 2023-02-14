import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { reportModalType } from '../../types/modal';

const initialState: reportModalType = {
  isOpen: false,
  fundingId: '',
};

export const reportModalSlice = createSlice({
  name: 'reportModalSlice',
  initialState,
  reducers: {
    openModal: (state, action: PayloadAction<reportModalType>) => {
      state.isOpen = true;
      state.fundingId = action.payload.fundingId;
    },
    closeModal: (state) => {
      state.isOpen = false;
    },
  },
});

export const { openModal, closeModal } = reportModalSlice.actions;

export default reportModalSlice.reducer;
