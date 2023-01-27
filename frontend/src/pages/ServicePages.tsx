import React from 'react';
import { Outlet } from 'react-router-dom';
import Navbar from '../components/Navbar';

function ServicePages() {
  return (
    <div>
      <Navbar />
      <Outlet />
    </div>
  );
}

export default ServicePages;
