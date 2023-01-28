import React from 'react';
import { Outlet } from 'react-router-dom';
import AdminSideBar from '../components/AdminSideBar/AdminSideBar';
import styles from './adminRoot.module.scss';

function AdminRoot() {
  return (
    <div className={styles['admin-page']}>
      <AdminSideBar />
      <Outlet />
    </div>
  );
}

export default AdminRoot;
