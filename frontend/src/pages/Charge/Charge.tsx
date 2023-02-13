import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router';
import PayModal from '../../components/Modal/PayModal';
import ChargeContainer from '../../containers/Charge/ChargeContainer';
import { useAppSelector } from '../../store/hooks';

function Charge() {
  const payModalState = useAppSelector((state) => state.payModalSlice);
  const isLogin = useAppSelector((state) => state.userSlice.isLogin);
  const navigate = useNavigate();

  useEffect(() => {
    if (!isLogin) {
      navigate('/login');
    }
  }, []);

  return (
    <>
      <PayModal isOpen={payModalState.isOpen} />
      <ChargeContainer />
    </>
  );
}

export default Charge;
