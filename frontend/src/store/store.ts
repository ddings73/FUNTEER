import { configureStore } from '@reduxjs/toolkit';
import userSlice from './slices/userSlice';
import modalSlice from './slices/modalSlice';

const store = configureStore({
  reducer: {
    userSlice,
    modalSlice
  },
});

export type RootState = ReturnType<typeof store.getState>;

// Inferred type: {posts: PostsState, comments: CommentsState, users: UsersState}
export type AppDispatch = typeof store.dispatch;

export default store;
