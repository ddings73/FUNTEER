import React from 'react';
import { Button } from '@mui/material';
import PersonIcon from '@mui/icons-material/Person';
import Diversity3Icon from '@mui/icons-material/Diversity3';
import { useLocation, useNavigate } from 'react-router-dom';
import styles from './SignUpContainer.module.scss';

function SignUpContainer() {
  const navigate = useNavigate();
  const { pathname } = useLocation();

  const goMemberSignUp = () => {
    navigate(`${pathname}/member`);
  };

  const goTeamSignUp = () => {
    navigate(`${pathname}/team`);
  };

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>회원가입 유형 선택</h1>
        <h3 className={styles.comment}>개인 회원과 단체 회원 중 가입을 원하는 유형을 선택해주세요.</h3>
        <h3 className={styles.comment}>펀딩 생성은 단체 회원만 가능합니다.</h3>
        <div className={styles.select}>
          <Button className={styles['select-button']} variant="contained" onClick={goMemberSignUp}>
            <h2 className={styles['button-title']}>개인 회원</h2>
            <h3 className={styles.comment}>펀딩과 기부에 참여</h3>
            <PersonIcon>member</PersonIcon>
          </Button>
          <Button className={styles['select-button']} variant="contained" onClick={goTeamSignUp}>
            <h2 className={styles['button-title']}>단체 회원</h2>
            <h3 className={styles.comment}>펀딩 생성</h3>
            <Diversity3Icon>team</Diversity3Icon>
          </Button>
        </div>
      </div>
    </div>
  );
}

export default SignUpContainer;
