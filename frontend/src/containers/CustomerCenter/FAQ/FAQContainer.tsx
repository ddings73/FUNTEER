import React from 'react';
import styles from './FAQContainer.module.scss';
import FAQContainerMemberItem from './FAQContainerMemberItem';
import FAQContainerTeamItem from './FAQContainerTeamItem';

export default function FAQContainer() {
  return (
    <div className={styles.container}>
      <h1>후원자 분들이 많이 문의했어요.</h1>
      <h1>봉사 단체에서 많이 문의했어요.</h1>
    </div>
  );
}
