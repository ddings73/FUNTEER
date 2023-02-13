import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { donateModalType } from '../../types/modal';

const initialState: donateModalType = {
  isOpen: false,
  postId: 0,
  userId: 0,
  mileage: 0,
};

export const donateModalSlice = createSlice({
  name: 'donateModalSlice',
  initialState,
  reducers: {
    openModal: (state, action: PayloadAction<donateModalType>) => {
      state.isOpen = action.payload.isOpen;
      state.postId = action.payload.postId;
      state.userId = action.payload.userId;
      state.mileage = action.payload.mileage;
    },
    closeModal: (state) => {
      state.isOpen = false;
    },
  },
});

export const { openModal, closeModal } = donateModalSlice.actions;

export default donateModalSlice.reducer;
