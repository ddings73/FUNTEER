import React from 'react'
import { Outlet } from 'react-router-dom'
import NavBar from '../components/Navbar'

function UserRoot(){
    return (
        <>
        <NavBar/>
        <Outlet/>
        </>
    )
}
export default UserRoot