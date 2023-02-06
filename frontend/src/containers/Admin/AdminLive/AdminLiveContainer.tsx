import React from 'react';
import styles from './AdminLiveContainer.module.scss'; // <- css 코드 여기서 작성

function AdminLiveContainer() {
  /** 여기서 함수, 변수 선언하거나 axios 요청 */
  const tmp = 1;
  /** 아래는 TSX 문법, HTML 코드 작성 */
  return (
    <div>
      라이브 관리 컨테이너
      <p>{tmp}</p>
    </div>
  );
}

export default AdminLiveContainer;
