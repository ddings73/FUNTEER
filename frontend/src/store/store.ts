import { configureStore, combineReducers, getDefaultMiddleware } from '@reduxjs/toolkit';
// import { persistStore, persistReducer, FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER } from 'redux-persist';
import { persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';
import userSlice from './slices/userSlice';
import modalSlice from './slices/modalSlice';
import fileModalSlice from './slices/fileModalSlice';
import payModalSlice from './slices/payModalSlice';
import donateModalSlice from './slices/donateModalSlice';
import reportModalSlice from './slices/reportModalSlice';

const persistConfig = {
  key: 'root',
  storage,
  whiteList: ['userSlice'],
};

const rootReducer = combineReducers({
  userSlice,
  modalSlice,
  fileModalSlice,
  payModalSlice,
  donateModalSlice,
  reportModalSlice,
});

const persistedReducer = persistReducer(persistConfig, rootReducer);

const store = configureStore({
  reducer: persistedReducer,
  middleware: getDefaultMiddleware({
    serializableCheck: false,
    // {ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER]},
  }),
});

export type RootState = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;

export default store;
