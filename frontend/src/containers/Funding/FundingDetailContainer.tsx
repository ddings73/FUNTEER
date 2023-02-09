import React, { useState, useEffect, useCallback, useMemo, useRef } from 'react';
import axios from 'axios';
import FavoriteIcon from '@mui/icons-material/Favorite';
import { useParams } from 'react-router-dom';
import { useInView } from 'react-intersection-observer';
import { CircularProgress } from '@mui/material';
import FundSummary from '../../components/Cards/FundSummary';
import styles from './FundingDetailContainer.module.scss';
import { requestCommentList, requestFundingDetail, requestNextCommentList } from '../../api/funding';
import TeamInfo from '../../components/Cards/TeamInfoCard';
import DetailArcodian from '../../components/Cards/DetailArcodian';
import CommentCardSubmit from '../../components/Cards/CommentCardSubmit';
import CommentCard from '../../components/Cards/CommentCard';
import CommentSkeleton from '../../components/Skeleton/CommentSkeleton';
import './FundingDetailContainer.scss';

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
    console.log('눌림');
    console.log(board.comments);
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
  const size = 5;
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

  useEffect(() => {
    initCommentList();
  }, []);

  useEffect(() => {
    if (inView && !isLastPage) {
      nextCommentList();
    }
  }, [inView]);

  console.log('commentList', commentList);
  console.log(isLastPage);
  return (
    <div className={styles.bodyContainer}>
      <div className={styles.banner}>
        <div className={styles.bannerContent}>
          <h1 className={styles.bannerTitle}>{board.title}</h1>
          <div className={styles.bannerButtonGroup}>
            <button className={styles.bannerGrpBtn} type="button">
              보고서 제출
            </button>
            <button className={styles.bannerGrpBtn} type="button">
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
        <div className={styles.toggles}>
          <p>정렬 기준</p>
          <div className="toggle-button-cover">
            <div className="button-cover">
              <div className="button r" id="button-3">
                <input type="checkbox" className="checkbox" checked={isChecked} onChange={(e) => checkHandler(e)} />
                <div className="knobs" />
                <div className="layer" />
              </div>
            </div>
          </div>
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
    </div>
  );
}
export default FundingDetailContainer;
