import React from 'react';
import styles from './ErrorPageContainer.module.scss';
import fzf from '../assets/images/404temp.png';

function ErrorPageContainer() {
  return (
    <div className={styles.bodyContainer}>
      <div className={styles.itemWrapper}>
        <img alt="errorimg" src={fzf} />
        <button className={styles.homeBtn} type="button">
          꿀호떡
        </button>
      </div>
    </div>
  );
}

export default ErrorPageContainer;
