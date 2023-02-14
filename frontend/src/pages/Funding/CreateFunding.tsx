import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import CreateFundingContainer from '../../containers/Funding/CreateFundingContainer';
import { useAppDispatch, useAppSelector } from '../../store/hooks';
import { closeModal, openModal } from '../../store/slices/modalSlice';

function CreateFunding() {
  const dispatch = useAppDispatch();
  const userType = useAppSelector((state) => state.userSlice.userType);
  const isLogin = useAppSelector((state) => state.userSlice.isLogin);
  const navigate = useNavigate();

  const handleModal = ()=>{
    dispatch(closeModal())

  }
  useEffect(() => {
    if(!isLogin)
    {
      navigate("/login",{replace:true})
    }
    else if(userType !== "TEAM"){
      navigate("/",{replace:true})
        dispatch(openModal({isOpen:true,title:"권한 없음", content:"펀딩생성은 단체 회원만 가능합니다.",handleModal}))
      }
  }, []);
  return <CreateFundingContainer />;
}

export default CreateFunding;
