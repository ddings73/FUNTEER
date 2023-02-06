import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { ConfirmModalType } from '../../types/modal';

const initialState: ConfirmModalType = {
  isOpen: false,
  title: '',
  content: '',
  handleModal: Function,
};

export const modalSlice = createSlice({
  name: 'modalSlice',
  initialState,
  reducers: {
    openModal: (state, action: PayloadAction<ConfirmModalType>) => {
      state.isOpen = action.payload.isOpen;
      state.title = action.payload.title;
      state.content = action.payload.content;
      state.handleModal = action.payload.handleModal;
    },
    closeModal: (state) => {
      state.isOpen = false;
      state.handleModal = Function;
    },
  },
});

export const { openModal, closeModal } = modalSlice.actions;
export default modalSlice.reducer;
