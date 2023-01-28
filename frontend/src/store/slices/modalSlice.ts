import {createSlice, PayloadAction} from '@reduxjs/toolkit'
import { ConfirmModalType } from '../../types/modal'



const initialState : ConfirmModalType = {
    isOpen:false,
    title:"",
    content:"",
}

export const modalSlice = createSlice({
    name:"modalSlice",
    initialState,
    reducers:{
        openModal : (state,action:PayloadAction<ConfirmModalType>)=>{
            state.isOpen = action.payload.isOpen
            state.title = action.payload.title
            state.content = action.payload.content
        },
        closeModal : (state)=>{
            state.isOpen=false
            state.title=""
            state.content=""
        }
    }
})

export const {openModal,closeModal} = modalSlice.actions
export default modalSlice.reducer