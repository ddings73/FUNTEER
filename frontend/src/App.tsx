import React from 'react';
import { Routes, Route } from 'react-router-dom';
import { Paths } from './paths';

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
      <Route path={Paths.charger} element={<div>충전페이지</div>} />
    </Routes>
  );
}

export default App;
