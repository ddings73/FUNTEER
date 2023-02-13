import React, { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router';
import { useAppDispatch } from '../../store/hooks';
import { resetLoginState, setUserLoginState } from '../../store/slices/userSlice';
import styles from './LogOutContainer.module.scss';

export function LogOutContainer() {
  const navigate = useNavigate();
  const dispatch = useAppDispatch();
  const loginState = useSelector((state) => {
    return state;
  });
  useEffect(() => {
    localStorage.removeItem('token');
    dispatch(resetLoginState());
    alert('로그아웃 되었습니다.');
    navigate('/');
  }, [loginState]);
  return <div className={styles.bodyContainer}>로그아웃</div>;
}
export default LogOutContainer;
