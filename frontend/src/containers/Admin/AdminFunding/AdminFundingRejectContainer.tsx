import React, { useState } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router';
import { AxiosError } from 'axios';
import { AiOutlineLoading3Quarters } from 'react-icons/ai';
import Button from '@mui/material/Button';
import styles from './AdminFundingRejectContainer.module.scss';
import { useAppDispatch } from '../../../store/hooks';
import { closeModal } from '../../../store/slices/fileModalSlice';
import requiredIcon from '../../../assets/images/funding/required.svg';
import { customAlert, s1000, s1500, w1500 } from '../../../utils/customAlert';
import { requestDenyTeam, requestRejectFunding } from '../../../api/admin';

function AdminFundingRejectContainer() {
  const navigate = useNavigate();
  const location = useLocation();
  const { id, fundingState } = location.state;
  const [content, setContent] = useState<string>('');
  const [isLoading, setIsLoading] = useState(false);

  /** 정보 입력 */
  const handleChangeContent = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setContent(e.target.value);
  };

  /** 취소 버튼 클릭 */
  const handleClickBack = () => {
    navigate(-1);
  };

  /** 전송 버튼 클릭 */
  const handleClickSubmit = () => {
    if (content.length < 5) {
      customAlert(w1500, '내용을 5자 이상 입력해주세요.');
      return;
    }

    setIsLoading(true);

    rejectFunding();
  };

  /** 팀 거부 요청 */
  const rejectFunding = async () => {
    try {
      const response = await requestRejectFunding(id, fundingState === 'REPORT_WAIT', content);
      console.log(response);
      setIsLoading(false);
      customAlert(s1000, '메일 전송 완료');
      setTimeout(() => {
        navigate(-1);
      }, 1000);
    } catch (error: unknown) {
      if (error instanceof AxiosError) {
        alert(error.response?.data.message);
        console.error(error);
      }
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        {fundingState === 'FUNDING_WAIT' && <h1 className={styles.title}>펀딩 승인 거부</h1>}
        {fundingState === 'REPORT_WAIT' && <h1 className={styles.title}>보고서 승인 거부</h1>}
        <div className={styles['label-div']}>
          <p>사유 작성</p> <img src={requiredIcon} alt="required icon" />
        </div>
        <textarea className={styles['email-content']} placeholder="내용을 5자 이상 입력해주세요." onChange={handleChangeContent} />
        <div className={styles['btn-div']}>
          <Button variant="contained" className={styles.submit} onClick={handleClickBack}>
            취소
          </Button>
          <Button variant="contained" className={styles.submit} onClick={handleClickSubmit}>
            {!isLoading && '전송'}
            {isLoading && <AiOutlineLoading3Quarters className={styles.loading} />}
          </Button>
        </div>
      </div>
    </div>
  );
}

export default AdminFundingRejectContainer;
