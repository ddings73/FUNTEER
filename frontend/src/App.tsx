import React from 'react';
import { Routes, Route } from 'react-router-dom';
import { Paths } from './paths';
import { Login } from './pages/index';
import ErrorPage from './pages/ErrorPage';

// redux
import { increment, decrement } from './store/slices/counterSlice';
import { useAppDispatch, useAppSelector } from './store/hooks';

function App() {
  return (
    /**
     * path = 이동할 경로 , element = 렌더링할 페이지
     */
    <Routes>
      <Route path={Paths.index} element={<div>메인페이지 입니다.</div>} />
      <Route path={Paths.login} element={<Login />} />
      <Route path="/*" element={<ErrorPage />} />
    </Routes>
  );
}

export default App;
