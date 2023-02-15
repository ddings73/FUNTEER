import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './Footer.module.scss';

function Footer() {
  const navigate = useNavigate();

  const goService = (e: React.MouseEvent<HTMLAnchorElement>) => {
    e.preventDefault();

    navigate('service');
  };

  return (
    <div style={{ display: 'block', width: '100%' }}>
      <footer className={styles.footer}>
        <div className={styles.contents}>
          <div className={styles.left}>
            <h1>FUNTEER</h1>
            <nav>
              <a href="." onClick={goService}>
                도움말
              </a>
              <a href="." onClick={goService}>
                이용약관
              </a>
              <a href="." onClick={goService}>
                개인정보처리방침
              </a>
            </nav>
            <p>Copyrightⓒ2023 funteer All rights reserved.</p>
          </div>

          <div className={styles.right}>
            <p>당신의 봉사 활동을 응원합니다.</p>
            <p>함께 더 나은 세상을 만들어가요.</p>
          </div>
        </div>
      </footer>
    </div>
  );
}

export default Footer;
