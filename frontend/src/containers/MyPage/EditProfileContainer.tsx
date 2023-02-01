import React from 'react';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import styles from './EditProfileContainer.module.scss';
import SideBarList from '../../components/MyPageSideBar/SideBarList';
import ProfileSvg from '../../assets/images/default-profile-img.svg';

export function EditProfileContainer() {
  const [name, setName] = React.useState('김승섭');
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setName(event.target.value);
  };
  return (
    <div className={styles.bodyContainer}>
      <SideBarList />
      <div className={styles.contentContainer}>
        <div className={styles.backBox}>
          <div className={styles.contentBox}>
            <section className={styles.main}>
              <div className={styles.profileCard}>
                <div className={styles.image}>
                  <img className={styles.profilePic} src={ProfileSvg} alt="" />
                </div>
                <div className={styles.formData}>
                  <div className={styles.header}>
                    <h3>이름</h3>
                  </div>
                  <TextField sx={{ width: '100%' }} value={name} onChange={handleChange} />
                </div>
                <div className={styles.formData}>
                  <div className={styles.header}>
                    <h3>닉네임</h3>
                  </div>
                  <TextField sx={{ width: '100%' }} value={name} onChange={handleChange} />
                </div>
                <div className={styles.formData}>
                  <div className={styles.header}>
                    <h3>연락처</h3>
                  </div>
                  <TextField sx={{ width: '100%' }} value={name} onChange={handleChange} />
                </div>
                <div className={styles.formData}>
                  <div className={styles.header}>
                    <h3>현재 비밀번호</h3>
                  </div>
                  <TextField sx={{ width: '100%' }} value={name} onChange={handleChange} />
                </div>
                <div className={styles.formData}>
                  <div className={styles.header}>
                    <h3>변경할 비밀번호</h3>
                  </div>
                  <TextField sx={{ width: '100%' }} value={name} onChange={handleChange} />
                </div>
                <div className={styles.formData}>
                  <div className={styles.header}>
                    <h3>변경할 비밀번호 확인</h3>
                  </div>
                  <TextField sx={{ width: '100%' }} value={name} onChange={handleChange} />
                </div>
                <div className={styles.buttons}>
                  <Button variant="contained" sx={{ mr: 2 }} className={styles.btn}>
                    수정하기
                  </Button>
                  <Button variant="outlined" sx={{ ml: 2, width: '90px' }} className={styles.btn}>
                    취 소
                  </Button>
                </div>
              </div>
            </section>
          </div>
        </div>
      </div>
    </div>
  );
}

export default EditProfileContainer;
