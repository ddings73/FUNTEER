import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import TextField from '@mui/material/TextField';
import MenuItem from '@mui/material/MenuItem';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import Pagination from '@mui/material/Pagination';
import { AiOutlineClose, AiOutlineSearch, AiOutlineReload } from 'react-icons/ai';
import { requestMembers, requestWithdrawMember } from '../../../api/admin';
import { AdminMemberInterface } from '../../../types/user';
import styles from './AdminMemberContainer.module.scss';

export enum MemberState {
  All = '전체',
  NORMAL = '정상',
  KAKAO = '정상(카카오)',
  NORMAL_RESIGN = '탈퇴',
}

export const memberStateMap = new Map<string, string>([
  ['NORMAL', '정상'],
  ['KAKAO', '정상(카카오)'],
  ['NORMAL_RESIGN', '탈퇴'],
]);

function AdminMemberContainer() {
  /** 현재 페이지 */
  const [page, setPage] = useState<number>(1);
  /** 최대 페이지 */
  const [maxPage, setMaxPage] = useState<number>(0);
  /** 현재 페이지의 개인 회원들 */
  const [pageMembers, setPageMembers] = useState<AdminMemberInterface[]>([]);
  /** 검색창 */
  const [memberSearch, setMemberSearch] = useState<string>('');
  /** 필터 */
  const [memberStateFilter, setMemberStateFilter] = useState<string>('전체');

  /** 페이지 변경 시 멤버 요청 */
  useEffect(() => {
    if (!memberSearch) {
      requestPageMembers();
    } else {
      requestPageMembersWithKeyword();
    }
  }, [page]);

  /** 페이지 교체 */
  const handleChangePage = (e: React.ChangeEvent<any>, selectedPage: number) => {
    setPage(selectedPage);
  };

  /** 검색창 입력 */
  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setMemberSearch(e.target.value);
  };

  /** 검색 */
  const handleClickSearch = async (e: React.MouseEvent<SVGElement>) => {
    setPage(1);
    requestPageMembersWithKeyword();
  };

  /** 엔터 검색 */
  const handleEnter = async (e: React.KeyboardEvent<HTMLDivElement>) => {
    if (e.key === 'Enter') {
      setPage(1);
      requestPageMembersWithKeyword();
    }
  };

  /** 검색 초기화 및 새로고침 */
  const handleClickInit = async () => {
    setMemberSearch('');
    setPage(1);
    requestPageMembers();
  };

  /** 필터 변경 */
  const handleChangeFilter = (e: SelectChangeEvent<string>) => {
    setMemberStateFilter(e.target.value);
  };

  /** 필터 적용된 페이지 멤버 리스트 */
  const filtedMembers = pageMembers.filter((member) => {
    let filter;
    if (memberStateFilter === '전체') {
      filter = true;
    } else {
      filter = memberStateMap.get(member.userType) === memberStateFilter;
    }
    return filter;
  });

  const memberStateSet = Object.values(MemberState);

  /** 회원 탈퇴 버튼 클릭 */
  const handleWithdrawBtn = (e: React.MouseEvent<SVGElement>, id: number) => {
    withdrawMember(id);
  };

  /** 회원 탈퇴 요청 */
  const withdrawMember = async (id: number) => {
    try {
      const response = await requestWithdrawMember(id);
      console.log(response);
    } catch (error) {
      console.error(error);
    }
  };

  /** 일반 페이지 멤버 요청 */
  const requestPageMembers = async () => {
    setPageMembers([]);
    try {
      const response = await requestMembers(page - 1, 8);
      console.log(response);
      setMaxPage(response.data.totalPages);
      setPageMembers(response.data.userList);
    } catch (error) {
      console.error(error);
    }
  };

  /** 검색어 있는 상태에서 페이지 멤버 요청 */
  const requestPageMembersWithKeyword = async () => {
    setPageMembers([]);
    try {
      const response = await requestMembers(page - 1, 8, memberSearch);
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
        <div className={styles['filter-div']}>
          <div className={styles['search-div']}>
            <TextField
              color="warning"
              label="회원 검색"
              variant="outlined"
              className={styles['search-input']}
              value={memberSearch}
              onChange={handleSearchChange}
              onKeyPress={handleEnter}
            />
            <AiOutlineSearch onClick={handleClickSearch} />
            <AiOutlineReload onClick={handleClickInit} />
          </div>
          <Select value={memberStateFilter} onChange={handleChangeFilter} sx={{ height: '30px', fontSize: '0.9rem', fontFamily: 'NanumSquare' }}>
            {memberStateSet.map((state) => (
              <MenuItem key={state} value={state} sx={{ height: '30px', fontSize: '0.9rem', fontFamily: 'NanumSquare' }}>
                {state}
              </MenuItem>
            ))}
          </Select>
        </div>

        <ul className={styles['title-line']}>
          <li>번호</li>
          <li>이름</li>
          <li>닉네임</li>
          <li>이미지</li>
          <li>전화번호</li>
          <li>이메일</li>
          <li>잔액</li>
          <li>상태</li>
          <li> </li>
        </ul>
        {filtedMembers.map((data) => (
          <ul key={data.id} className={styles['list-line']}>
            <li>{data.id}</li>
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
              <p>{memberStateMap.get(data.userType)}</p>
            </li>
            <li>
              <AiOutlineClose className={styles['withdraw-btn']} onClick={(e) => handleWithdrawBtn(e, data.id)} />
            </li>
          </ul>
        ))}
        <Pagination sx={{ marginTop: '2rem' }} count={maxPage} page={page} onChange={handleChangePage} />
      </div>
    </div>
  );
}

export default AdminMemberContainer;
