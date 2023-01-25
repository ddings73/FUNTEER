import React from 'react';
import { Routes, Route } from 'react-router-dom';
import { Paths } from './paths';
import { MainPage, Login, ErrorPage, TeamPage, ServiceDetail } from './pages/index';
import Navbar from './components/Navbar';

// redux
// eslint-disable-next-line
import { increment, decrement } from './store/slices/counterSlice';
// eslint-disable-next-line
import { useAppDispatch, useAppSelector } from './store/hooks';

function App() {
  return (
    /**
     * path = 이동할 경로 , element = 렌더링할 페이지
     */ <>
      <Navbar />
      <Routes>
        <Route index element={<MainPage />} />
        <Route path={Paths.login} element={<Login />} />
        <Route path={Paths.team} element={<TeamPage />} />
        <Route path="/*" element={<ErrorPage />} />
        <Route path={Paths.service} element={<ServiceDetail />} />
      </Routes>
    </>
  );
}

export default App;
