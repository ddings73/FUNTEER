import React from 'react';
import { Outlet } from 'react-router-dom';
import AdminSideBar from '../components/AdminSideBar/AdminSideBar';
import styles from './adminRoot.module.scss';
import FileModal from '../components/Modal/FileModal';
import { useAppSelector } from '../store/hooks';

function AdminRoot() {
  const fileModalState = useAppSelector((state) => state.fileModalSlice);

  return (
    <div className={styles['admin-page']}>
      <FileModal
        isOpen={fileModalState.isOpen}
        vmsNum={fileModalState.vmsNum}
        vmsFile={fileModalState.vmsFile}
        performFile={fileModalState.performFile}
        deniedNum={fileModalState.deniedNum}
      />
      <AdminSideBar />
      <Outlet />
    </div>
  );
}

export default AdminRoot;
