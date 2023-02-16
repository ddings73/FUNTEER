import React, { useState } from 'react';
import { AxiosError } from 'axios';
import { useNavigate, useParams } from 'react-router';
import { AiOutlineLoading3Quarters } from 'react-icons/ai';
import Button from '@mui/material/Button';
import styles from './AdminTeamDenyContainer.module.scss';
import { useAppDispatch } from '../../../store/hooks';
import { closeModal } from '../../../store/slices/fileModalSlice';
import requiredIcon from '../../../assets/images/funding/required.svg';
import { customAlert, customTextOnlyAlert, noTimeWarn, s1000, w1500 } from '../../../utils/customAlert';
import { requestDenyTeam } from '../../../api/admin';

function AdminTeamDenyContainer() {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  /** 거부 당한 팀 번호 (유저 번호) */
  const { dn } = useParams();
  /** 단체 거부 정보 */
  const [content, setContent] = useState<string>('');
  const [isLoading, setIsLoading] = useState(false);

  /** 정보 입력 */
  const handleChangeContent = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setContent(e.target.value);
  };

  /** 취소 버튼 클릭 */
  const onClickBackHandler = () => {
    dispatch(closeModal());
    navigate(-1);
  };

  /** 전송 버튼 클릭 */
  const onClickSubmitHandler = () => {
    if (content.length < 5) {
      customTextOnlyAlert(noTimeWarn, '내용을 5자 이상 입력해주세요.');
      return;
    }

    setIsLoading(true);

    denyTeam();
  };

  /** 팀 거부 요청 */
  const denyTeam = async () => {
    if (dn) {
      try {
        const response = await requestDenyTeam(parseInt(dn, 10), content);
        console.log(response);
        setIsLoading(false);
        customAlert(s1000, '메일 전송 완료');
        setTimeout(() => {
          dispatch(closeModal());
          navigate(-1);
        }, 1000);
      } catch (error: unknown) {
        if (error instanceof AxiosError) {
          alert(error.response?.data.message);
          console.error(error);
        }
      }
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>단체 가입 거부</h1>
        <div className={styles['label-div']}>
          <p>사유 작성</p> <img src={requiredIcon} alt="required icon" />
        </div>
        <textarea className={styles['email-content']} placeholder="내용을 5자 이상 입력해주세요." onChange={handleChangeContent} />
        <div className={styles['btn-div']}>
          <Button variant="contained" className={styles.submit} onClick={onClickBackHandler}>
            취소
          </Button>
          <Button variant="contained" className={styles.submit} onClick={onClickSubmitHandler}>
            {!isLoading && '전송'}
            {isLoading && <AiOutlineLoading3Quarters className={styles.loading} />}
          </Button>
        </div>
      </div>
    </div>
  );
}

export default AdminTeamDenyContainer;
