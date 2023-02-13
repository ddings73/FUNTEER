import React, { useEffect } from 'react';
import { Outlet, useLocation } from 'react-router-dom';
import ConfirmModal from '../components/Modal/ConfirmModal';
import NavBar from '../components/Navbar';
import { useAppSelector } from '../store/hooks';
import ScrollToTop from '../utils/ScrollToTop';

function UserRoot() {
  const confirmModalState = useAppSelector((state) => state.modalSlice);
  
  return (
    <>
      <ScrollToTop />
      <ConfirmModal isOpen={confirmModalState.isOpen} title={confirmModalState.title} content={confirmModalState.content} handleModal={confirmModalState.handleModal} />
      <NavBar />
      <Outlet />
    </>
  );
}
export default UserRoot;
