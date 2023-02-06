import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import AdminDonationCreateContainer from '../../containers/Admin/AdminDonation/AdminDonationCreateContainer';
import { useAppSelector } from '../../store/hooks';

function AdminDonationCreate() {
  const userType = useAppSelector((state) => state.userSlice.userType);
  console.log(userType);
  const navigate = useNavigate();
  useEffect(() => {
    if (!userType || !(userType === 'ADMIN')) {
      navigate('/login');
    }
  }, []);
  return <AdminDonationCreateContainer />;
}
export default AdminDonationCreate;
