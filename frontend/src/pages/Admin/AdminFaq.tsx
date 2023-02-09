import React, { useEffect } from 'react';
import { useNavigate } from 'react-router';
import AdminFaqContainer from '../../containers/Admin/AdminFaq/AdminFaqContainer';
import { useAppSelector } from '../../store/hooks';

function AdminFaq() {
  const userType = useAppSelector((state) => state.userSlice.userType);
  console.log(userType);
  const navigate = useNavigate();
  useEffect(() => {
    if (!userType || !(userType === 'ADMIN')) {
      navigate('/login');
    }
  }, []);
  return <AdminFaqContainer />;
}

export default AdminFaq;
