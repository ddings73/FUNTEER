import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import CreateFundingContainer from '../../containers/Funding/CreateFundingContainer';
import { useAppSelector } from '../../store/hooks';

function CreateFunding() {
  const userType = useAppSelector((state) => state.userSlice.userType);
  const isLogin = useAppSelector((state) => state.userSlice.isLogin);
  const navigate = useNavigate();
  useEffect(() => {
    if (isLogin) {
      if (userType !== 'TEAM') navigate('/login');
    } else {
      navigate('/login');
    }
  }, []);
  return <CreateFundingContainer />;
}

export default CreateFunding;
