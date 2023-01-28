import React from 'react';
import styles from './AdminMemberContainer.module.scss';
import AdminMemberContainerItem from './AdminMemberContainerItem';

function AdminMemberContainer() {
  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <h1 className={styles.title}>개인 회원관리</h1>
        <ul className={styles['title-line']}>
          <li>이름</li>
          <li>닉네임</li>
          <li>프로필 사진</li>
          <li>이메일</li>
          <li>잔액</li>
        </ul>
        {AdminMemberContainerItem.map((data) => (
          <ul key={data.nickname} className={styles['list-line']}>
            <li>
              <p>{data.name}</p>
            </li>
            <li>
              <p>{data.nickname}</p>
            </li>
            <li>
              <div className={styles['img-div']}>
                <img src={data.profileImageUrl} alt="profileImg" />
              </div>
            </li>
            <li>
              <p>{data.email}</p>
            </li>
            <li>
              <p>{data.mileage}</p>
            </li>
          </ul>
        ))}
      </div>
    </div>
  );
}

export default AdminMemberContainer;
