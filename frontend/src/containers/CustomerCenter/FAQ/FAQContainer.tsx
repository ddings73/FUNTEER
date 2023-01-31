import React from 'react';
import styles from './FAQContainer.module.scss';
import FAQContainerMemberItem from './FAQContainerMemberItem';
import FAQContainerTeamItem from './FAQContainerTeamItem';

export default function FAQContainer() {
  return (
    <div className={styles.container}>
      <div className={styles['user-type-div']}>
        <h1 className={styles.title}>후원자 분들이 많이 문의했어요.</h1>
        {FAQContainerMemberItem.map((data) => (
          <details key={data.ques} className={styles.item}>
            <summary>{data.ques}</summary>
            <p>{data.ans}</p>
          </details>
        ))}
      </div>
      <div className={styles['user-type-div']}>
        <h1 className={styles.title}>봉사 단체에서 많이 문의했어요.</h1>
        {FAQContainerTeamItem.map((data) => (
          <details key={data.ques} className={styles.item}>
            <summary>{data.ques}</summary>
            <p>{data.ans}</p>
          </details>
        ))}
      </div>
    </div>
  );
}
