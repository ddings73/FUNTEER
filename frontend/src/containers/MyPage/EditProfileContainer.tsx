import React, { useEffect, useState } from 'react';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { Password } from '@mui/icons-material';
import { async } from 'q';
import { log } from 'console';
import styles from './EditProfileContainer.module.scss';
import SideBarList from '../../components/MyPageSideBar/SideBarList';
import ProfileSvg from '../../assets/images/default-profile-img.svg';
import { changeUserInfoInterface, userInfoInterface } from '../../types/user';
import { requestModifyUserInfo, requestUserInfo } from '../../api/user';
import { useAppDispatch, useAppSelector } from '../../store/hooks';
import { closeModal, openModal } from '../../store/slices/modalSlice';



export function EditProfileContainer() {
  const dispatch = useAppDispatch();
  const userId = useAppSelector(state=>state.userSlice.userId)
  const [userInfo, setUserInfo] = useState<userInfoInterface>({
    email: '',
    name: '',
    phone: '',
  });
  

  const [changeUserInfo,setChangeUserInfo] = useState<changeUserInfoInterface>({
    password:"",
    newPassword:''
  })

  const [checkNewPassword,setCheckNewPassword]=useState<boolean>(false)
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const {name,value} = event.target
    
    setChangeUserInfo({...changeUserInfo,[name]:value})
  };

  const initUserInfo = async () => {
    try {
      const response = await requestUserInfo();
      console.log(response.data);
      
      setUserInfo({ ...response.data });
    } catch (error) {
      console.log(error);
    }
  };

  const onChangeCheckNewPassword = (event: React.ChangeEvent<HTMLInputElement>)=>{
    const {value} =event.target

    if(changeUserInfo.newPassword === value){
      setCheckNewPassword(true)
    }
    else{
      setCheckNewPassword(false)
    }
  }

  const handleModal = ()=>{
    dispatch(closeModal());
  }
  const modifyUserInfo = async()=>{
    if(!checkNewPassword){
      dispatch(openModal({isOpen:true,title:"회원정보 수정실패",content:"비밀번호가 일치하지 않습니다",handleModal}))
      return;
    }
    try{
      const response = await requestModifyUserInfo(changeUserInfo,userId)
      dispatch(openModal({isOpen:true,title:"회원정보 수정성공",content:"회원정보 수정에 성공했습니다",handleModal}))
    }
    catch(error){
      console.log(error)
    }
  }

  useEffect(() => {
    initUserInfo();
  }, []);

  return (
    <div className={styles.bodyContainer}>
      <SideBarList />
      <div className={styles.contentContainer}>
        <div className={styles.backBox}>
          <div className={styles.contentBox}>
            <section className={styles.main}>
              <div className={styles.profileCard}>
                <div className={styles.formData}>
                  <div className={styles.header}>
                    <h3>이름</h3>
                  </div>
                  <TextField sx={{ width: '100%' }} value={userInfo.name} 	inputProps={{ readOnly: true,}}  />
                </div>
                <div className={styles.formData}>
                  <div className={styles.header}>
                    <h3>이메일</h3>
                  </div>
                  <TextField sx={{ width: '100%' }} value={userInfo.email}  inputProps={{ readOnly: true,}} />
                </div>

                <div className={styles.formData}>
                  <div className={styles.header}>
                    <h3>연락처</h3>
                  </div>
                  <TextField sx={{ width: '100%' }} value={userInfo.phone}  inputProps={{ readOnly: true,}} />
                </div>
                <div className={styles.formData}>
                  <div className={styles.header}>
                    <h3>현재 비밀번호</h3>
                  </div>
                  <TextField sx={{ width: '100%' }} name="password" type="password" onChange={handleChange} />
                </div>
                <div className={styles.formData}>
                  <div className={styles.header}>
                    <h3>변경할 비밀번호 {checkNewPassword? "" :(<span className={styles['error-text']}>비밀번호가 일치하지 않습니다.</span> )} </h3>
                  </div>
                  <TextField sx={{ width: '100%' }} name="newPassword" type="password" onChange={handleChange} />
                </div>
                <div className={styles.formData}>
                  <div className={styles.header}>
                    <h3>변경할 비밀번호 확인 {checkNewPassword? "" :(<span className={styles['error-text']}>비밀번호가 일치하지 않습니다.</span> )} </h3>
                  </div>
                  <TextField sx={{ width: '100%' }} name="checkNewPassword" type="password" onChange={onChangeCheckNewPassword} />
                </div>
                <div className={styles.buttons}>
                  <Button variant="contained" onClick={modifyUserInfo}  className={styles.btn}>
                    수정하기
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
