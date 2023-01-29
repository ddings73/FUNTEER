import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import TextField from '@mui/material/TextField';
import MenuItem from '@mui/material/MenuItem';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import AdminTeamContainerItem, { TeamState } from './AdminTeamContainerItem';
import { openModal } from '../../../store/slices/fileModalSlice';
import { useAppDispatch } from '../../../store/hooks';
import styles from './AdminTeamContainer.module.scss';
import { RootState } from '../../../store/store';

function AdminTeamContainer() {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  const [TeamSearch, setTeamSearch] = useState<string>('');
  const [TeamStateFilter, setTeamStateFilter] = useState<string>(TeamState.NotCertified);

  /** 검색 */
  const onTeamSearchInputChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setTeamSearch(e.target.value);
  };

  /** 필터 */
  const onTeamStateFilterChangeHandler = (e: SelectChangeEvent) => {
    setTeamStateFilter(e.target.value);
  };

  /** 검색 + 필터 고려해서 필터링 */
  const filtedTeams = AdminTeamContainerItem.filter((Team) => {
    const filter =
      (Team.name.includes(TeamSearch) || Team.email.includes(TeamSearch) || Team.phone.includes(TeamSearch) || Team.vmsNum.includes(TeamSearch)) &&
      Team.teamState === TeamStateFilter;
    return filter;
  });

  /** 필수 파일 확인 버튼 */
  const onFileBtnClickHandler = (a: string, b: string, c: string, e: React.MouseEvent<HTMLButtonElement>) => {
    dispatch(openModal({ isOpen: true, vmsNum: a, vmsFile: b, performFile: c, deniedNum: '' }));
  };

  /** dn: 가입 승인 거부된 팀의 위촉번호 */
  const dn = useSelector((state: RootState) => state.fileModalSlice.deniedNum);

  /** dn이 변하면 해당 팀에게 사유를 안내하기 위한 페이지로 이동 */
  useEffect(() => {
    if (dn) {
      const deniedTeamUrl = 'deny/'.concat(dn);
      navigate(deniedTeamUrl);
    }
  }, [dn]);

  // const onTeamStateChangeHandler = (e: SelectChangeEvent) => {
  //   console.log('단체 회원 상태 변경 요청');
  //   window.location.reload();
  // };

  const teamStateSet = Object.values(TeamState);

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>단체 회원관리</h1>
        <div className={styles['search-div']}>
          <TextField label="단체 검색" variant="outlined" className={styles['search-input']} onChange={onTeamSearchInputChangeHandler} />
          <Select value={TeamStateFilter} onChange={onTeamStateFilterChangeHandler} sx={{ height: '40px' }}>
            {teamStateSet.map((state) => (
              <MenuItem key={state} value={state}>
                {state}
              </MenuItem>
            ))}
          </Select>
        </div>
        <ul className={styles['title-line']}>
          <li>이름</li>
          <li>이메일</li>
          <li>대표자 번호</li>
          <li>위촉번호</li>
          <li>최근 실적일</li>
          <li>인증 파일</li>
          <li>상태</li>
        </ul>
        {filtedTeams.map((data) => (
          <ul key={data.name} className={styles['list-line']}>
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
              <p>{data.vmsNum}</p>
            </li>
            <li>
              <p>{data.lastPerformDate}</p>
            </li>
            <li>
              <button
                type="button"
                className={styles['file-btn']}
                onClick={(e) => {
                  onFileBtnClickHandler(data.vmsNum, data.files[0], data.files[1], e);
                }}
              >
                확인
              </button>
            </li>
            <li>
              {/* <Select value={data.teamState} onChange={onTeamStateChangeHandler} sx={{ height: '30px' }}>
                <MenuItem value={TeamState.NotCertified}>{TeamState.NotCertified}</MenuItem>
                <MenuItem value={TeamState.Certified}>{TeamState.Certified}</MenuItem>
                <MenuItem value={TeamState.Expired}>{TeamState.Expired}</MenuItem>
                <MenuItem value={TeamState.Withdrawn}>{TeamState.Withdrawn}</MenuItem>
              </Select> */}
              <p>{data.teamState}</p>
            </li>
          </ul>
        ))}
      </div>
    </div>
  );
}

export default AdminTeamContainer;
