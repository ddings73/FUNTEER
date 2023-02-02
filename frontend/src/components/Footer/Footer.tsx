import React from 'react';
import styles from './Footer.module.scss';

function Footer() {
  return (
    <footer className={styles.footer}>
      <div className={styles.contents}>
        <div className={styles.left}>
          <h1>FUNTEER</h1>
          <nav>
            <a href="https://helpcenter.wadiz.kr/hc/ko?_ga=2.182115239.1053366649.1675304785-1518736862.1675212581">도움말</a>
            <a href="https://www.wadiz.kr/web/wterms/signup">이용약관</a>
            <a href="https://www.wadiz.kr/web/wterms/privacy">개인정보처리방침</a>
          </nav>
          <p>Copyrightⓒ2023 funteer All rights reserved.</p>
        </div>

        <div className={styles.right}>
          <p>당신의 봉사 활동을 응원합니다.</p>
          <p>함께 더 나은 세상을 만들어가요.</p>
        </div>
      </div>
    </footer>
  );
}

export default Footer;
