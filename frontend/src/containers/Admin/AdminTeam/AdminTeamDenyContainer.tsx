import React, { useState } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router';
import Button from '@mui/material/Button';
import styles from './AdminTeamDenyContainer.module.scss';
import { useAppDispatch } from '../../../store/hooks';
import { closeModal } from '../../../store/slices/fileModalSlice';
import requiredIcon from '../../../assets/images/funding/required.svg';
import { customAlert, w1500 } from '../../../utils/customAlert';

function AdminTeamDenyContainer() {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  const location = useLocation();
  const { dn } = useParams();

  /** 단체 거부 정보 */
  const [teamDenyInfo, setTeamDenyInfo] = useState({
    title: '',
    content: '',
  });

  /** 정보 입력 */
  const onChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    setTeamDenyInfo({ ...teamDenyInfo, [name]: value });
  };

  /** 취소 버튼 클릭 */
  const onClickBackHandler = () => {
    dispatch(closeModal());
    navigate(-1);
  };

  /** 전송 버튼 클릭 */
  const onClickSubmitHandler = () => {
    if (teamDenyInfo.title.length < 10 || teamDenyInfo.content.length < 20) {
      customAlert(w1500, '제목을 10자, 내용을 20자 이상 입력해주세요.');
    }

    console.log('단체 가입 거부 요청');
  };

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>단체 가입 거부 사유</h1>
        <div className={styles['label-div']}>
          <p>제목</p> <img src={requiredIcon} alt="required icon" />
        </div>

        <input name="title" type="text" className={styles['email-title']} placeholder="제목을 10자 이상 입력해주세요." />
        <div className={styles['label-div']}>
          <p>내용</p> <img src={requiredIcon} alt="required icon" />
        </div>
        <textarea name="content" className={styles['email-content']} placeholder="내용을 20자 이상 입력해주세요." />
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
