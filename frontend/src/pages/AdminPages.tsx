import React from 'react';
import { Outlet } from 'react-router-dom';
import AdminSideBar from '../components/AdminSideBar';

function ServicePages() {
  return (
    <div>
      <AdminSideBar />
      <Outlet />
    </div>
  );
}

export default ServicePages;
