import React from 'react';
import { Outlet } from 'react-router-dom';
import Footer from '../components/Footer/Footer';
import ConfirmModal from '../components/Modal/ConfirmModal';
import DonateModal from '../components/Modal/DonateModal';
import NavBar from '../components/Navbar';
import { useAppSelector } from '../store/hooks';
import ScrollToTop from '../utils/ScrollToTop';

function UserFooterRoot() {
  const confirmModalState = useAppSelector((state) => state.modalSlice);
  const donateModalState = useAppSelector((state) => state.donateModalSlice);

  return (
    <>
      <ScrollToTop />
      <ConfirmModal isOpen={confirmModalState.isOpen} title={confirmModalState.title} content={confirmModalState.content} handleModal={confirmModalState.handleModal} />
      <DonateModal isOpen={donateModalState.isOpen} postId={donateModalState.postId} userId={donateModalState.userId} mileage={donateModalState.mileage} />
      <NavBar />
      <Outlet />
      <Footer />
    </>
  );
}
export default UserFooterRoot;
