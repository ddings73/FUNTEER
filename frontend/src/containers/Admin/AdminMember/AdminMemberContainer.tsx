import React, { useState, useEffect } from 'react';
import TextField from '@mui/material/TextField';
import MenuItem from '@mui/material/MenuItem';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import styles from './AdminMemberContainer.module.scss';
import AdminMemberContainerItem, { MemberState } from './AdminMemberContainerItem';

function AdminMemberContainer() {
  const [memberSearch, setMemberSearch] = useState<string>('');
  const [memberStateFilter, setMemberStateFilter] = useState<string>(MemberState.All);

  const onMemberSearchInputChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setMemberSearch(e.target.value);
  };

  const onMemberStateFilterChangeHandler = (e: SelectChangeEvent) => {
    setMemberStateFilter(e.target.value);
  };

  const filtedMembers = AdminMemberContainerItem.filter((member) => {
    let filter;
    if (memberStateFilter === '전체') {
      filter = member.name.includes(memberSearch) || member.nickname.includes(memberSearch) || member.phone.includes(memberSearch) || member.email.includes(memberSearch);
    } else {
      filter =
        (member.name.includes(memberSearch) || member.nickname.includes(memberSearch) || member.phone.includes(memberSearch) || member.email.includes(memberSearch)) &&
        member.memberState === memberStateFilter;
    }
    return filter;
  });

  // const onMemberStateChangeHandler = (e: SelectChangeEvent) => {
  //   console.log('개인 회원 상태 변경 요청');
  //   window.location.reload();
  // };

  const onClickWithdrawBtnHandler = (e: React.MouseEvent<HTMLButtonElement>) => {
    console.log('회원 탈퇴 처리');
  };

  const memberStateSet = Object.values(MemberState);

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>개인 회원관리</h1>
        <div className={styles['search-div']}>
          <TextField label="회원 검색" variant="outlined" className={styles['search-input']} onChange={onMemberSearchInputChangeHandler} />
          <Select value={memberStateFilter} onChange={onMemberStateFilterChangeHandler} sx={{ height: '40px' }}>
            {memberStateSet.map((state) => (
              <MenuItem key={state} value={state}>
                {state}
              </MenuItem>
            ))}
          </Select>
        </div>
        <ul className={styles['title-line']}>
          <li>이름</li>
          <li className={styles.wide}>닉네임</li>
          <li>프로필 사진</li>
          <li className={styles.wide}>전화번호</li>
          <li className={styles.wide}>이메일</li>
          <li>잔액</li>
          <li>상태</li>
          <li>탈퇴 처리</li>
        </ul>
        {filtedMembers.map((data) => (
          <ul key={data.nickname} className={styles['list-line']}>
            <li>
              <p>{data.name}</p>
            </li>
            <li className={styles.wide}>
              <p>{data.nickname}</p>
            </li>
            <li>
              <div className={styles['img-div']}>
                <img src={data.profileImageUrl} alt="profileImg" />
              </div>
            </li>
            <li className={styles.wide}>
              <p>{data.phone}</p>
            </li>
            <li className={styles.wide}>
              <p>{data.email}</p>
            </li>
            <li>
              <p>{data.money}</p>
            </li>
            <li>
              <p>{data.memberState}</p>
              {/* <Select value={data.memberState} onChange={onMemberStateChangeHandler} sx={{ height: '30px' }}>
                <MenuItem value={MemberState.Normal}>{MemberState.Normal}</MenuItem>
                <MenuItem value={MemberState.Dormant}>{MemberState.Dormant}</MenuItem>
                <MenuItem value={MemberState.Withdrawn}>{MemberState.Withdrawn}</MenuItem>
              </Select> */}
            </li>
            <li>
              <button type="button" className={styles['withdraw-btn']} onClick={onClickWithdrawBtnHandler}>
                X
              </button>
            </li>
          </ul>
        ))}
      </div>
    </div>
  );
}

export default AdminMemberContainer;
