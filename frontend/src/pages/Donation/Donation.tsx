import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

import DonationContainer from '../../containers/Donation/DonationContainer';
import { useAppSelector } from '../../store/hooks';

function Donation() {
  const navigate = useNavigate();
  const isLogin = useAppSelector((state) => state.userSlice.isLogin);

  useEffect(() => {
    if (!isLogin) navigate('/login');
  }, []);
  return <DonationContainer />;
}

export default Donation;
