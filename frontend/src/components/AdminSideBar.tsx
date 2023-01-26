import React from 'react';
import { Outlet } from 'react-router-dom';

function AdminSideBar() {
  return (
    <div>
      AdminSideBar
      <Outlet />
    </div>
  );
}

export default AdminSideBar;
