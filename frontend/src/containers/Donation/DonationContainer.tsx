import React, { useEffect, useState } from 'react';
import { Button } from '@mui/material';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { Viewer } from '@toast-ui/react-editor';
import donationThumbnail from '../../assets/images/donation/donationImg.png';
import styles from './DonationContainer.module.scss';
import { useAppDispatch, useAppSelector } from '../../store/hooks';
import ListTable from '../../components/Table/ListTable';
import { requestCurrentDonation } from '../../api/donation';
import { requestUserProfile } from '../../api/user';
import { openModal } from '../../store/slices/donateModalSlice';
import { requestTeamProfileInfo } from '../../api/team';

/**
 * * 유저가 현재 가진돈은 계산해놨음
 * * 기부하기 클릭 시 모달같은거 띄워서 얼마 기부할 지 체크
 *  * 돈이 부족할때 처리
 *
 */

type ResponseInterface = {
  id: number;
  title: string;
  content: string;
  file: string;
  targetAmount: string;
  currentAmount: string;
  startDate: string;
};

function DonationContainer() {
  const dispatch = useAppDispatch();
  const userId = useAppSelector((state) => state.userSlice.userId);
  const userType = useAppSelector((state) => state.userSlice.userType);
  const [userMoney, setUserMoney] = useState<number>(0);
  const [donBoard, setDonBoard] = useState<ResponseInterface>({
    id: 0,
    title: '',
    content: '',
    file: '',
    targetAmount: '',
    currentAmount: '',
    startDate: '',
  });

  useEffect(() => {
    fetchData();
    setInitUserMoney();
  }, []);

  const onClickDonation = async () => {
    dispatch(openModal({ isOpen: true, postId: donBoard.id, userId: parseInt(userId, 10), mileage: userMoney }));
  };

  const fetchData = async () => {
    try {
      const response = await requestCurrentDonation();
      console.log('res: ', response);
      console.log('data res: ', response.data);
      setDonBoard(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  // 회원 마일리지 초기 설정
  const setInitUserMoney = async () => {
    try {
      let response;
      if (userType === 'NORMAL' || userType === 'KAKAO') {
        response = await requestUserProfile(userId);
        console.log(response.data.money);
        setUserMoney(response.data.money);
      } else if (userType === 'TEAM') {
        response = await requestTeamProfileInfo(userId);
        console.log(response.data.money);
        setUserMoney(response.data.money);
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <div className={styles.contentsWrapper}>
          <p className={styles.contentTitle}>진행중인 기부</p>
          <div className={styles['donation-box']}>
            <div className={styles.left}>
              <figure>
                <img src={donBoard.file} alt="donationImage" />
              </figure>
            </div>
            <div className={styles.right}>
              <p className={styles.title}>{donBoard.title}</p>
              {/* <div className={styles.text} dangerouslySetInnerHTML={{ __html: donBoard.content }} /> */}
              <p className={styles.text2}>{donBoard.content && <Viewer initialValue={donBoard.content || ''} />}</p>
              <Button className={styles.donButton} onClick={onClickDonation} type="button">
                기부 참여
              </Button>
            </div>
          </div>
          <div className={styles['amount-box']}>
            <p>총 적립금 {parseInt(donBoard.currentAmount, 10).toLocaleString('ko-KR')}원</p>
          </div>
          <div className={styles.finishedTable}>
            <Accordion sx={{ border: '2px solid rgb(175, 175, 175, 0.5)', marginBottom: '5rem' }}>
              <AccordionSummary expandIcon={<ExpandMoreIcon />} aria-controls="panel1a-content">
                <Typography ml={65} sx={{ fontWeight: '600', fontSize: '20px', opacity: 0.7 }}>
                  이미 종료된 기부 이벤트
                </Typography>
              </AccordionSummary>
              <AccordionDetails>
                <ListTable />
              </AccordionDetails>
            </Accordion>
          </div>
        </div>
      </div>
    </div>
  );
}
export default DonationContainer;
