import React, { useState, useEffect } from 'react';
import axios from 'axios';
import FavoriteIcon from '@mui/icons-material/Favorite';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { useInView } from 'react-intersection-observer';
import { Box, CircularProgress, Fab } from '@mui/material';
import TextField from '@mui/material/TextField';
import LocalAtmIcon from '@mui/icons-material/LocalAtm';
import FundSummary from '../../components/Cards/FundSummary';
import styles from './FundingDetailContainer.module.scss';
import { requestCommentList, requestFundingDetail, requestNextCommentList } from '../../api/funding';
import TeamInfo from '../../components/Cards/TeamInfoCard';
import DetailArcodian from '../../components/Cards/DetailArcodian';
import CommentCardSubmit from '../../components/Cards/CommentCardSubmit';
import CommentCard from '../../components/Cards/CommentCard';
import CommentSkeleton from '../../components/Skeleton/CommentSkeleton';
import { teamStateMap } from '../Admin/AdminTeam/AdminTeamContainer';
import { useAppSelector } from '../../store/hooks';
import { requestUserProfile } from '../../api/user';

export interface ResponseInterface {
  id: number;
  title: string;
  startDate: string;
  endDate: string;
  postDate: string;
  thumbnail: string;
  category: string;
  content: string;
  targetMoneyListLevelThree: targetType;
  currentFundingAmount: string;
  wishCount: number;
  fundingDescription: string;
  comments: commentType[];
  team: teamType;
}
export type commentType = {
  memberNickName: string;
  content: string;
  memberProfileImg: string;
  regDate: string;
};
export type teamType = {
  id: number;
  email: string;
  name: string;
  phone: string;
  profileImgUrl: string;
  // performFileUrl: string;
  // vmsFileUrl: string;
};
type targetType = {
  amount?: string;
  targetMoneyType?: string;
  description?: string;
};

export function FundingDetailContainer() {
  const navigate = useNavigate();
  const [commentList, setCommentList] = useState([
    {
      memberNickName: '',
      content: '',
      memberProfileImg: '',
      regDate: '',
    },
  ]);
  const [users, setUsers] = useState(null);
  const { fundIdx } = useParams();
  const [board, setBoard] = useState<ResponseInterface>({
    id: 0,
    title: '',
    startDate: '',
    endDate: '',
    postDate: '',
    thumbnail: '',
    category: '',
    content: '',
    fundingDescription: '',
    targetMoneyListLevelThree: {},
    currentFundingAmount: '',
    wishCount: 0,
    comments: [],
    team: {
      id: 0,
      email: '',
      name: '',
      phone: '',
      profileImgUrl: '',
    },
  });
  // 게시물 좋아요
  const [isLiked, setIsLiked] = useState<boolean>(false);

  const handleLikeClick = () => {
    setIsLiked(!isLiked);
  };

  // 펀딩 상세 게시물 로드
  const fetchData = async () => {
    try {
      const response = await requestFundingDetail(fundIdx);
      console.log('res: ', response);
      console.log('data res: ', response.data);
      setBoard(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  // 게시물 댓글 로드
  useEffect(() => {
    fetchData();
  }, []);

  // 정렬 체크박스
  const [isChecked, setIsChecked] = useState<boolean>(false);
  function checkHandler(e: React.ChangeEvent<HTMLInputElement> | undefined) {
    setIsChecked(!isChecked);
  }

  // 무한 스크롤
  // 항목 리스트 초기화
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [currentPage, setCurrentPage] = useState<number>(0);
  const [isLastPage, setIsLastPage] = useState<boolean>(false);

  const initCommentList = async () => {
    try {
      setIsLoading(true);
      const { data } = await requestCommentList(fundIdx, 'regDate,DESC');
      setCommentList([...data.comments.content]);
      setCurrentPage(data.comments.number);
      setIsLastPage(data.comments.last);
      setIsLoading(false);
    } catch (error) {
      console.log(error);
    }
  };
  const [nextLoading, setNextLoading] = useState<boolean>(false);
  // 한번에 불러올 게시글 수
  const nextCommentList = async () => {
    try {
      console.log('here comes');
      setNextLoading(true);
      const { data } = await requestNextCommentList(currentPage, fundIdx, 'regDate,DESC');
      setCommentList([...commentList, ...data.comments.content]);
      setCurrentPage(data.comments.number);
      setIsLastPage(data.comments.last);
      setNextLoading(false);
    } catch (error) {
      console.error(error);
    }
  };

  const [ref, inView] = useInView();

  // Drawer 핸들러

  const [toggled, setToggled] = useState<boolean>(false);
  function handleDrawer() {
    setToggled(!toggled);
  }

  // 엔터키 input 완성

  function handleKeyUp(event: React.KeyboardEvent<HTMLImageElement>) {
    console.log('누름');
    if (event.key === 'Enter') {
      (document.activeElement as HTMLElement).blur();
    }
  }

  useEffect(() => {
    initCommentList();
  }, []);

  useEffect(() => {
    if (inView && !isLastPage) {
      nextCommentList();
    }
  }, [inView]);

  // 로그인 정보
  const userId = useAppSelector((state) => state.userSlice.userId);
  // 잔액
  const [money, setMoney] = React.useState(0);
  useEffect(() => {
    requestMoneyInfo();
  }, []);

  /** 잔액 조회 */
  const requestMoneyInfo = async () => {
    try {
      if (userId) {
        const response = await requestUserProfile(userId);
        console.log('유저 프로필 정보', response);
        setMoney(response.data.money);
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className={styles.bodyContainer}>
      <div className={styles.banner}>
        <div className={styles.bannerContent}>
          <h1 className={styles.bannerTitle}>{board.title}</h1>
          <div className={styles.bannerButtonGroup}>
            <button className={styles.bannerGrpBtn} type="button">
              보고서 제출
            </button>
            <button
              className={styles.bannerGrpBtn}
              type="button"
              onClick={() => {
                navigate('../../createLive', { replace: true });
              }}
            >
              라이브 시작
            </button>
            <button className={styles.bannerBtn} type="button">
              펀딩 수정하기
            </button>
          </div>
        </div>
      </div>
      <div className={styles.mainContainer}>
        <div className={styles.mainSum}>
          {' '}
          <FundSummary {...board} />
        </div>
        <div className={styles.mainContent}>
          <div dangerouslySetInnerHTML={{ __html: board.content }} className={styles.mainContentInner} />
        </div>
        <hr style={{ borderTop: '3px solid #bbb', borderRadius: '3px', opacity: '0.5' }} />
        <div className={styles.teamInfoCard} style={{ width: '90%', marginLeft: '6%' }}>
          <TeamInfo {...board.team} />
        </div>
        <DetailArcodian />

        <div className={styles.mainFooterAttatch}>
          <p className={styles.attachTitle}>첨부파일</p>
          <p className={styles.attachItem}>인증서.hwp</p>
          <p className={styles.attachItem}>증명서.pdf</p>
        </div>
        <div className={styles.mainFooterLikeWrapper}>
          <button className={isLiked ? styles.mainFooterLikeButton : styles.mainFooterLikeButtonClicked} onClick={handleLikeClick} type="button">
            <FavoriteIcon className={styles.mainFooterLike} />
          </button>
          <div className={styles.Likebox}>
            <div className={styles.mainFooterLikeTest}> 펀딩 찜</div>
            <div className={styles.mainFooterLikeTestSub}> 찜 수 {board.wishCount}</div>
          </div>
        </div>
        <hr style={{ borderTop: '3px solid #bbb', borderRadius: '3px', opacity: '0.5' }} />
        <div className={styles.mainCommentSubmit}>
          <CommentCardSubmit />
        </div>
        <div className={styles.mainComments}>
          {isLoading ? (
            <CommentSkeleton />
          ) : (
            commentList.map((comment) => {
              return (
                <CommentCard
                  memberNickName={comment.memberNickName}
                  content={comment.content}
                  memberProfileImg={comment.memberProfileImg}
                  regDate={comment.regDate}
                  key={comment.regDate}
                />
              );
            })
          )}
          {nextLoading ? <CircularProgress color="warning" /> : ''}
        </div>
        {currentPage >= 0 ? <div ref={ref} /> : ''}
      </div>
      <Fab
        aria-label="add"
        sx={{ color: 'white', backgroundColor: 'orange !important', position: 'fixed', bottom: '3%', right: '3%', width: '60px', height: '60px' }}
        onClick={() => handleDrawer()}
        className={styles.fabToggle}
      >
        <LocalAtmIcon />
      </Fab>
      <Box
        sx={{
          position: 'fixed',
          bottom: '10px',
          left: toggled ? '0px' : '-100%',
          width: '90%',
          height: '13%',
          transition: '1s ease-in-out',
          margin: '0 20px',
        }}
        className={styles.payBox}
      >
        <div className={styles.payBar}>
          <p>
            <span>{board.team.name}</span>님의 펀딩에 총 <span>123,456</span>명이 참여했어요
          </p>
          <div>
            <TextField
              label="금액 입력"
              id="custom-css-outlined-input"
              type="number"
              size="small"
              sx={{ margin: '0 20px', backgroundColor: 'white' }}
              // eslint-disable-next-line
              onKeyUp={handleKeyUp}
              color="warning"
            />
          </div>

          <p>마일리지로</p>
          <button type="button" className={styles.payBtn}>
            펀딩 참여하기
          </button>
          <div className={styles.mileText}>
            <p>현재 잔액: {money}원</p>
            <Link to="/charge" className={styles.milLink}>
              <p className={styles.milea}>마일리지 충전</p>
            </Link>
          </div>
        </div>
      </Box>
    </div>
  );
}
export default FundingDetailContainer;
