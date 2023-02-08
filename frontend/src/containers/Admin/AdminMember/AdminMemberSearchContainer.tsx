import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import Pagination from '@mui/material/Pagination';
import { AiOutlineClose } from 'react-icons/ai';
import { requestMembers, requestWithdrawMember } from '../../../api/admin';
import { AdminMemberInterface } from '../../../types/user';
import styles from './AdminMemberSearchContainer.module.scss';

function AdminMemberSearchContainer() {
  const location = useLocation();
  /** 키워드 */
  const [keyword, setKeyword] = useState<string>('');
  /** 현재 페이지 */
  const [page, setPage] = useState<number>(1);
  /** 최대 페이지 */
  const [maxPage, setMaxPage] = useState<number>(0);
  /** 현재 페이지의 개인 회원들 */
  const [pageMembers, setPageMembers] = useState<AdminMemberInterface[]>([]);

  useEffect(() => {
    if (!keyword) {
      setKeyword(location.state.keyword);
    } else {
      requestPageMembers();
    }
  }, [keyword, page]);

  /** 페이지 교체 */
  const handleChangePage = (e: React.ChangeEvent<any>, selectedPage: number) => {
    setPage(selectedPage);
  };

  /** 회원 탈퇴 버튼 클릭 */
  const handleWithdrawBtn = (e: React.MouseEvent<SVGElement>, userId: number) => {
    withdrawMember(userId);
  };

  /** 회원 탈퇴 요청 */
  const withdrawMember = async (userId: number) => {
    try {
      const response = await requestWithdrawMember(userId);
      console.log(response);
    } catch (error) {
      console.error(error);
    }
  };

  /** 페이지 멤버 요청 */
  const requestPageMembers = async () => {
    setPageMembers([]);
    try {
      const response = await requestMembers(page - 1, 8, keyword);
      console.log(response);
      setMaxPage(response.data.totalPages);
      setPageMembers(response.data.userList);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>개인 회원관리</h1>
        <h3 className={styles.keyword}>
          검색어: <span>{keyword}</span>
        </h3>
        <ul className={styles['title-line']}>
          <li>번호</li>
          <li>이름</li>
          <li>닉네임</li>
          <li style={{ fontSize: '0.9rem' }}>프로필 사진</li>
          <li>전화번호</li>
          <li>이메일</li>
          <li>잔액</li>
          <li>탈퇴 처리</li>
        </ul>
        {pageMembers.map((data) => (
          <ul key={data.userId} className={styles['list-line']}>
            <li>{data.userId}</li>
            <li>
              <p>{data.name}</p>
            </li>
            <li>
              <p>{data.nickname}</p>
            </li>
            <li>
              <div className={styles['img-div']}>
                <img src={data.profileImgUrl} alt="profileImg" />
              </div>
            </li>
            <li>
              <p>{data.phone}</p>
            </li>
            <li>
              <p>{data.email}</p>
            </li>
            <li>
              <p>{data.money.toLocaleString('ko-KR')}</p>
            </li>
            <li>
              <AiOutlineClose className={styles['withdraw-btn']} onClick={(e) => handleWithdrawBtn(e, data.userId)} />
            </li>
          </ul>
        ))}
        <Pagination sx={{ marginTop: '2rem' }} count={maxPage} page={page} onChange={handleChangePage} />
      </div>
    </div>
  );
}

export default AdminMemberSearchContainer;
