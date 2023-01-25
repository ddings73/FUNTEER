import React from 'react';
import { Routes, Route } from 'react-router-dom';
import { Paths } from './paths';
import { Charge, FindEmail, Login, ResetPassword } from './pages/index';
import ErrorPage from './pages/ErrorPage';

// redux
import { increment, decrement } from './store/slices/counterSlice';
import { useAppDispatch, useAppSelector } from './store/hooks';
import FindPassword from './pages/FindPassword';
import MemberSignUp from './pages/MemberSignUp';

function App() {
  return (
    /**
     * path = 이동할 경로 , element = 렌더링할 페이지
     */
    <Routes>
      <Route path={Paths.index} element={<div>메인페이지 입니다.</div>} />
      <Route path={Paths.memberSignUp} element={<MemberSignUp />} />
      <Route path={Paths.login} element={<Login />} />
      <Route path={Paths.findEmail.index} element={<FindEmail />} />
      <Route path={Paths.findPassword.index} element={<FindPassword />} />
      <Route path={Paths.findPassword.reset} element={<ResetPassword />} />
      <Route path={Paths.charge} element={<Charge />} />
      <Route path="/*" element={<ErrorPage />} />
    </Routes>
  );
}

export default App;
