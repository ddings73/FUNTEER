import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import AdminFaqCreateContainer from '../../containers/Admin/AdminFaq/AdminFaqCreateContainer';
import { useAppSelector } from '../../store/hooks';

function AdminFaqCreate() {
  const userType = useAppSelector((state) => state.userSlice.userType);
  console.log(userType);
  const navigate = useNavigate();
  useEffect(() => {
    if (!userType || !(userType === 'ADMIN')) {
      navigate('/login');
    }
  }, []);
  return <AdminFaqCreateContainer />;
}
export default AdminFaqCreate;
