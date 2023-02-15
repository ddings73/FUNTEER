import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { payModalType } from '../../types/modal';

const initialState: payModalType = {
  isOpen: false,
};

export const payModalSlice = createSlice({
  name: 'payModalSlice',
  initialState,
  reducers: {
    openModal: (state, action: PayloadAction<payModalType>) => {
      state.isOpen = action.payload.isOpen;
    },
    closeModal: (state) => {
      state.isOpen = false;
    },
  },
});

export const { openModal, closeModal } = payModalSlice.actions;

export default payModalSlice.reducer;
