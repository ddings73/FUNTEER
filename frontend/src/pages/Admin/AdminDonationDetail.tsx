import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import AdminDonationDetailContainer from '../../containers/Admin/AdminDonation/AdminDonationDetailContainer';
import { useAppSelector } from '../../store/hooks';

function AdminDonationDetail() {
  const userType = useAppSelector((state) => state.userSlice.userType);
  console.log(userType);
  const navigate = useNavigate();
  useEffect(() => {
    if (!userType || !(userType === 'ADMIN')) {
      navigate('/login');
    }
  }, []);
  return <AdminDonationDetailContainer />;
}
export default AdminDonationDetail;
