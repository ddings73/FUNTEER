import React, { useEffect } from 'react';
import { Outlet, useNavigate } from 'react-router-dom';
import AdminSideBar from '../components/AdminSideBar/AdminSideBar';
import styles from './adminRoot.module.scss';
import FileModal from '../components/Modal/FileModal';
import { useAppSelector } from '../store/hooks';

function AdminRoot() {
  const navigate = useNavigate();
  const fileModalState = useAppSelector((state) => state.fileModalSlice);
  const userType = useAppSelector((state) => state.userSlice.userType);

  useEffect(() => {
    if (userType !== 'ADMIN') {
      navigate('/');
    }
  }, []);

  return (
    <div className={styles['admin-page']}>
      <FileModal
        isOpen={fileModalState.isOpen}
        userId={fileModalState.userId}
        vmsFileUrl={fileModalState.vmsFileUrl}
        performFileUrl={fileModalState.performFileUrl}
        deniedNum={fileModalState.deniedNum}
      />
      <AdminSideBar />
      <Outlet />
    </div>
  );
}

export default AdminRoot;
