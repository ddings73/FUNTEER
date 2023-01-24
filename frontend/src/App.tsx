import React from 'react';
import { Routes, Route } from 'react-router-dom';
import { Paths } from './paths';
import { MainPage, Login, ErrorPage } from './pages/index';

// redux
// eslint-disable-next-line
import { increment, decrement } from './store/slices/counterSlice';
// eslint-disable-next-line
import { useAppDispatch, useAppSelector } from './store/hooks';

function App() {
  return (
    /**
     * path = 이동할 경로 , element = 렌더링할 페이지
     */
    <Routes>
      <Route path={Paths.main} element={<MainPage />} />
      <Route path={Paths.login} element={<Login />} />
      <Route path="/*" element={<ErrorPage />} />
    </Routes>
  );
}

export default App;
