import React from 'react';
import PayModal from '../../components/Modal/PayModal';
import ChargeContainer from '../../containers/AddOns/ChargeContainer';
import { useAppSelector } from '../../store/hooks';

function Charge() {
  const payModalState = useAppSelector((state) => state.payModalSlice);

  return (
    <>
      <PayModal isOpen={payModalState.isOpen} />
      <ChargeContainer />
    </>
  );
}

export default Charge;
