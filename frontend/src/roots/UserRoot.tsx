import React from 'react'
import { Outlet } from 'react-router-dom'
import ConfirmModal from '../components/Modal/ConfirmModal'
import NavBar from '../components/Navbar'
import { useAppSelector } from '../store/hooks'



function UserRoot(){
    const confirmModalState = useAppSelector((state)=>state.modalSlice)
    
    return (
        <>
        <ConfirmModal isOpen={confirmModalState.isOpen} title={confirmModalState.title} content={confirmModalState.content} />
        <NavBar/>
        <Outlet/>
        </>
    )
}
export default UserRoot