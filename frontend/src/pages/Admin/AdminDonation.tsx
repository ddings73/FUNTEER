import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import AdminDonationContainer from '../../containers/Admin/AdminDonation/AdminDonationContainer';
import { useAppSelector } from '../../store/hooks';

function AdminDonation() {
  const userType = useAppSelector((state) => state.userSlice.userType);
  console.log(userType);
  const navigate = useNavigate();
  useEffect(() => {
    if (!userType || !(userType === 'ADMIN')) {
      navigate('/login');
    }
  }, []);
  return <AdminDonationContainer />;
}
export default AdminDonation;
