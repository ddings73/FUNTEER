import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import TextField from '@mui/material/TextField';
import Swal from 'sweetalert2';
import MenuItem from '@mui/material/MenuItem';
import { AiOutlineSearch, AiOutlineClose, AiOutlineReload } from 'react-icons/ai';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import Pagination from '@mui/material/Pagination';
import { openModal } from '../../../store/slices/fileModalSlice';
import { useAppDispatch, useAppSelector } from '../../../store/hooks';
import { RootState } from '../../../store/store';
import { requestTeams, requestWithdrawTeam } from '../../../api/admin';
import { AdminTeamInterface } from '../../../types/user';
import styles from './AdminTeamContainer.module.scss';
import { customAlert, s1000 } from '../../../utils/customAlert';

export enum TeamState {
  All = '전체',
  TEAM = '정상',
  TEAM_RESIGN = '탈퇴',
  TEAM_WAIT = '가입 대기',
  TEAM_EXPIRED = '갱신 필요',
}

export const teamStateMap = new Map<string, string>([
  ['All', '전체'],
  ['TEAM', '정상'],
  ['TEAM_RESIGN', '탈퇴'],
  ['TEAM_WAIT', '가입 대기'],
  ['TEAM_EXPIRED', '갱신 필요'],
  ['전체', 'All'],
  ['정상', 'TEAM'],
  ['탈퇴', 'TEAM_RESIGN'],
  ['가입 대기', 'TEAM_WAIT'],
  ['갱신 필요', 'TEAM_EXPIRED'],
]);

function AdminTeamContainer() {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  /** dn: 가입 승인 거부된 팀의 유저 번호 */
  const dn = useAppSelector((state: RootState) => state.fileModalSlice.deniedNum);
  const size = 8;
  const [page, setPage] = useState<number>(1);
  const [maxPage, setMaxPage] = useState<number>(0);
  const [pageTeams, setPageTeams] = useState<AdminTeamInterface[]>([]);
  const [teamSearch, setTeamSearch] = useState<string>('');
  const [teamStateFilter, setTeamStateFilter] = useState<string>('All');

  useEffect(() => {
    requestPageTeams();
  }, [page, teamStateFilter]);

  /** dn이 변하면 해당 팀에게 사유를 안내하기 위한 페이지로 이동 */
  useEffect(() => {
    if (dn) {
      goDenyPage();
    }
  }, [dn]);

  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setTeamSearch(e.target.value);
  };

  const handleClickSearch = async () => {
    if (page === 1) {
      requestPageTeams();
    } else {
      setPage(1);
    }
  };

  const handleEnter = async (e: React.KeyboardEvent<HTMLDivElement>) => {
    if (e.key === 'Enter') {
      if (page === 1) {
        requestPageTeams();
      } else {
        setPage(1);
      }
    }
  };

  const handleClickInit = async () => {
    window.location.reload();
  };

  const handleChangeFilter = (e: SelectChangeEvent<string>) => {
    setTeamStateFilter(e.target.value);
  };

  /** 필수 파일 확인 버튼 */
  const handleFileBtn = (teamId: number, vf: string, pf: string) => {
    dispatch(openModal({ isOpen: true, userId: teamId.toString(), vmsFileUrl: vf, performFileUrl: pf, deniedNum: '' }));
  };

  /** 거부 페이지 이동 함수 */
  const goDenyPage = () => {
    navigate(`deny/${dn}`, {
      state: {
        dn,
      },
    });
  };

  const teamStateSet = Object.values(TeamState);

  const handleWithdrawBtn = (e: React.MouseEvent<SVGElement>, id: number) => {
    Swal.fire({
      text: '해당 단체를 탈퇴 처리 하시겠습니까?',
      showConfirmButton: false,
      showDenyButton: true,
      showCancelButton: true,
      denyButtonText: `확인`,
      denyButtonColor: 'rgba(211, 79, 4, 1)',
      cancelButtonText: '취소',
    }).then((result) => {
      if (result.isDenied) {
        withdrawTeam(id);
      }
    });
  };

  const handleChangePage = (e: React.ChangeEvent<any>, selectedPage: number) => {
    setPage(selectedPage);
  };

  const requestPageTeams = async () => {
    setPageTeams([]);
    try {
      let response;
      if (teamStateFilter === 'All') {
        response = await requestTeams(page - 1, size, teamSearch);
      } else {
        response = await requestTeams(page - 1, size, teamSearch, teamStateFilter);
      }
      console.log('관리자 팀 리스트 요청', response);
      setMaxPage(response.data.totalPages);
      setPageTeams(response.data.userList);
    } catch (error) {
      console.error('팀 요청 에러', error);
    }
  };

  const withdrawTeam = async (id: number) => {
    try {
      const response = await requestWithdrawTeam(id);
      console.log(response);
      customAlert(s1000, '단체 탈퇴 처리가 완료되었습니다.');
      requestPageTeams();
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>단체 회원관리</h1>
        <div className={styles['filter-div']}>
          <div className={styles['search-div']}>
            <TextField
              size="small"
              color="warning"
              label="단체 검색"
              variant="outlined"
              className={styles['search-input']}
              value={teamSearch}
              onChange={handleSearchChange}
              onKeyPress={handleEnter}
            />
            <AiOutlineSearch onClick={handleClickSearch} />
            <AiOutlineReload onClick={handleClickInit} />
          </div>
          <Select value={teamStateFilter} onChange={handleChangeFilter} sx={{ height: '30px', fontSize: '0.9rem', fontFamily: 'NanumSquare' }}>
            {teamStateSet.map((state) => (
              <MenuItem key={state} value={teamStateMap.get(state)} sx={{ height: '30px', fontSize: '0.9rem', fontFamily: 'NanumSquare' }}>
                {state}
              </MenuItem>
            ))}
          </Select>
        </div>
        <ul className={styles['title-line']}>
          <li>번호</li>
          <li>단체명</li>
          <li>이메일</li>
          <li>대표자 번호</li>
          <li>인증 파일</li>
          <li>최근 실적일</li>
          <li>상태</li>
          <li> </li>
        </ul>
        {pageTeams.map((data) => (
          <ul key={data.id} className={styles['list-line']}>
            <li>{data.id}</li>
            <li>
              <p>{data.name}</p>
            </li>
            <li>
              <p>{data.email}</p>
            </li>
            <li>
              <p>{data.phone}</p>
            </li>
            <li>
              {data.userType === 'TEAM_WAIT' && (
                <button
                  type="button"
                  className={styles['file-btn']}
                  onClick={(e) => {
                    handleFileBtn(data.id, data.vmsFileUrl, data.performFileUrl);
                  }}
                >
                  확인
                </button>
              )}
            </li>
            <li>
              <p>{data.lastActivity}</p>
            </li>
            <li>
              <p>{teamStateMap.get(data.userType)}</p>
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

export default AdminTeamContainer;
