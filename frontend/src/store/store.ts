import { configureStore, getDefaultMiddleware } from '@reduxjs/toolkit';
import userSlice from './slices/userSlice';
import modalSlice from './slices/modalSlice';
import fileModalSlice from './slices/fileModalSlice';

const store = configureStore({
  reducer: {
    userSlice,
    modalSlice,
    fileModalSlice,
  },
  middleware: getDefaultMiddleware({
    serializableCheck: false,
  }),
});

export type RootState = ReturnType<typeof store.getState>;

// Inferred type: {posts: PostsState, comments: CommentsState, users: UsersState}
export type AppDispatch = typeof store.dispatch;

export default store;
