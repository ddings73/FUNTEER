import { configureStore, combineReducers, getDefaultMiddleware } from '@reduxjs/toolkit';
// import { persistStore, persistReducer, FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER } from 'redux-persist';
// import storage from 'redux-persist/lib/storage';
import userSlice from './slices/userSlice';
import modalSlice from './slices/modalSlice';
import fileModalSlice from './slices/fileModalSlice';
import payModalSlice from './slices/payModalSlice';

// const persistConfig = {
//   key: 'root',
//   version: 1,
//   storage,
// };

const rootReducer = combineReducers({
  userSlice,
  modalSlice,
  fileModalSlice,
  payModalSlice,
});

// const persistedReducer = persistReducer(persistConfig, rootReducer);

const store = configureStore({
  reducer: rootReducer,
  middleware: getDefaultMiddleware({
    serializableCheck: false,
    // {ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER]},
  }),
});

export type RootState = ReturnType<typeof store.getState>;

// Inferred type: {posts: PostsState, comments: CommentsState, users: UsersState}
export type AppDispatch = typeof store.dispatch;

// export const persistor = persistStore(store);

export default store;
