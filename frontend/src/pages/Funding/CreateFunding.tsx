import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import CreateFundingContainer from '../../containers/Funding/CreateFundingContainer';
import { useAppSelector } from '../../store/hooks';

function CreateFunding() {
  const userType = useAppSelector((state) => state.userSlice.userType);
  console.log(userType);
  const navigate = useNavigate();
  useEffect(() => {
    if (!userType || userType === 'NORMAL') {
      navigate('/login');
    }
  }, []);
  return <CreateFundingContainer />;
}

export default CreateFunding;
