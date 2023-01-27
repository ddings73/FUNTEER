import React from 'react';
import { Outlet } from 'react-router-dom';
import AdminSideBar from '../components/AdminSideBar/AdminSideBar';
import styles from './AdminPage.module.scss';

function ServicePage() {
  return (
    <div className={styles['service-page']}>
      <AdminSideBar />
      <Outlet />
    </div>
  );
}

export default ServicePage;
