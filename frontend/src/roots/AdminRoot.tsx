import React from 'react';
import { Outlet } from 'react-router-dom';
import AdminSideBar from '../components/AdminSideBar/AdminSideBar';
import styles from './AdminRoot.module.scss';

function AdminRoot() {
  return (
    <div className={styles['admin-root']}>
      <AdminSideBar />
      <Outlet />
    </div>
  );
}

export default AdminRoot;
