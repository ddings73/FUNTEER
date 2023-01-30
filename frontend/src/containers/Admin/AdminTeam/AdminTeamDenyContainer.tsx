import React from 'react';
import { useLocation, useNavigate, useParams } from 'react-router';
import Button from '@mui/material/Button';
import styles from './AdminTeamDenyContainer.module.scss';
import { useAppDispatch } from '../../../store/hooks';
import { closeModal } from '../../../store/slices/fileModalSlice';

function AdminTeamDenyContainer() {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  const location = useLocation();
  const { dn } = useParams();

  const onClickBackHandler = () => {
    dispatch(closeModal());
    navigate(-1);
  };

  const onClickSubmitHandler = () => {
    console.log('단체 가입 거부 요청');
  };

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>단체 가입 거부 사유</h1>
        <input type="text" className={styles['email-title']} placeholder="제목" />
        <textarea className={styles['email-content']} placeholder="내용" />
        <div className={styles['btn-div']}>
          <Button variant="contained" className={styles.submit} onClick={onClickBackHandler}>
            취소
          </Button>
          <Button variant="contained" className={styles.submit} onClick={onClickSubmitHandler}>
            전송
          </Button>
        </div>
      </div>
    </div>
  );
}

export default AdminTeamDenyContainer;
