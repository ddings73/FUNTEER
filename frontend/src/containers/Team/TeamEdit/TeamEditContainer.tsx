import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { BsCameraFill } from 'react-icons/bs';
import { Visibility, VisibilityOff } from '@mui/icons-material';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import TeamSideBarList from '../../../components/TeamPageSideBar/TeamSideBarList';
import styles from './TeamEditContainer.module.scss';
import defaultImage from '../../../assets/images/default-profile-img.svg';
import { requestChangeDescription, requestChangePassword, requestChangePerform, requestChangeProfileImg, requestChangeVms } from '../../../api/team';
import { customTextOnlyAlert, noTimeSuccess, noTimeWarn } from '../../../utils/customAlert';

function TeamEditContainer() {
  const navigate = useNavigate();
  const location = useLocation();

  /** 프로필 정보 */
  const [teamEditInfo, setTeamEditInfo] = useState({ ...location.state });
  /** 설명 변경 여부 */
  const [desChanged, setDesChanged] = useState<boolean>(false);
  /** 비밀번호 변경 펼치기 */
  const [changePw, setChangePw] = useState<boolean>(false);
  /** 비밀번호 가시 버튼 */
  const [passwordVisibility, setPasswordVisibility] = useState<boolean>(false);
  /** 비밀번호 초기 값 */
  const initialPasswordInfo = {
    password: '',
    newPassword: '',
    newPasswordCheck: '',
  };
  /** 비밀번호 정보 */
  const [passwordInfo, setPasswordInfo] = useState(initialPasswordInfo);
  /** vms 파일 변경 펼치기 */
  const [changeVms, setChangeVms] = useState<boolean>(false);
  /** vms 파일 비밀번호 */
  const [vmsPassword, setVmsPassword] = useState('');
  /** vms 파일 */
  const [vmsFile, setVmsFile] = useState<File | null>(null);
  /** 실적 파일 변경 펼치기 */
  const [changePerform, setChangePerform] = useState<boolean>(false);
  /** 실적 파일 비밀번호 */
  const [performPassword, setPerformPassword] = useState('');
  /** 실적 파일 */
  const [performFile, setPerformFile] = useState<File | null>(null);

  /** 이미지 수정 */
  const onChangeImage = async (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      /** 화면 반영 */
      const newImgUrl = URL.createObjectURL(e.target.files[0]);
      setTeamEditInfo({ ...teamEditInfo, profileImgUrl: newImgUrl });

      /** 이미지 수정 요청 */
      try {
        const response = await requestChangeProfileImg(teamEditInfo.teamId, e.target.files[0]);
        customTextOnlyAlert(noTimeSuccess, '프로필 이미지가 변경되었습니다.');
        console.log(response);
      } catch (error) {
        customTextOnlyAlert(noTimeWarn, '잘못된 요청입니다.');
        console.error(error);
      }
    }
  };

  /** 단체 설명 입력 */
  const onChangeDescription = (e: React.ChangeEvent<HTMLInputElement>) => {
    setDesChanged(true);
    setTeamEditInfo({ ...teamEditInfo, description: e.target.value });
  };

  /** 단체 설명 변경 요청 */
  const onClickDescriptionChange = async () => {
    try {
      const response = await requestChangeDescription(teamEditInfo.teamId, teamEditInfo.description);
      customTextOnlyAlert(noTimeSuccess, '단체 설명이 변경되었습니다.');
      console.log(response);
    } catch (error) {
      customTextOnlyAlert(noTimeWarn, '잘못된 요청입니다.');
      console.error(error);
    }
  };

  /** 비밀번호 입력 */
  const onChangePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setPasswordInfo({ ...passwordInfo, [name]: value });
  };

  const onCancelChangePassword = () => {
    setChangePw(false);
    setPasswordInfo(initialPasswordInfo);
  };

  /** 비밀번호 변경 요청 */
  const onClickPasswordChange = async () => {
    if (passwordInfo.newPassword !== passwordInfo.newPasswordCheck) {
      customTextOnlyAlert(noTimeWarn, '비밀번호와 비밀번호 확인 값이 다릅니다.');
      return;
    }

    /** 비밀번호 정규식: 8 ~ 15자, 하나 이상의 문자와 숫자 */
    const validPW = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z]).*$/;
    if (!validPW.test(passwordInfo.newPassword)) {
      customTextOnlyAlert(noTimeWarn, '적합하지 않은 비밀번호입니다.');
      // eslint-disable-next-line
      return;
    }

    try {
      const response = await requestChangePassword(teamEditInfo.teamId, passwordInfo.password, passwordInfo.newPassword);
      customTextOnlyAlert(noTimeSuccess, '비밀번호가 변경되었습니다.');
      console.log(response);
    } catch (error) {
      customTextOnlyAlert(noTimeWarn, '잘못된 요청입니다.');
      console.error(error);
    }
  };

  /** VMS 파일용 비밀번호 변경 */
  const onChangeVmsPassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    setVmsPassword(e.target.value);
  };

  /** VMS 파일 변경 */
  const onChangeVms = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setVmsFile(e.target.files[0]);
    }
  };

  /** VMS 파일 변경 취소 */
  const onCancelChangeVms = () => {
    setChangeVms(false);
    setVmsFile(null);
  };

  /** VMS 파일 변경 요청 */
  const onClickVmsChange = async () => {
    try {
      const response = await requestChangeVms(teamEditInfo.teamId, vmsPassword, vmsFile);
      customTextOnlyAlert(noTimeSuccess, 'VMS 파일이 변경되었습니다.');
      console.log(response);
    } catch (error) {
      customTextOnlyAlert(noTimeWarn, '이미 인증된 단체입니다.');
      console.error(error);
    }
  };

  /** 실적 파일용 비밀번호 변경 */
  const onChangePerformPassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPerformPassword(e.target.value);
  };

  /** 실적 파일 변경 */
  const onChangePerform = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setPerformFile(e.target.files[0]);
    }
  };

  /** 실적 파일 변경 취소 */
  const onCancelChangePerform = () => {
    setChangePerform(false);
    setPerformFile(null);
  };

  /** 실적 파일 변경 요청 */
  const onClickPerformChange = async () => {
    try {
      const response = await requestChangePerform(teamEditInfo.teamId, performPassword, performFile);
      customTextOnlyAlert(noTimeSuccess, '실적 파일이 변경되었습니다.');
      console.log(response);
    } catch (error) {
      customTextOnlyAlert(noTimeWarn, '1년 이내의 실적이 이미 존재합니다.');
      console.error(error);
    }
  };

  return (
    <div className={styles.container}>
      <TeamSideBarList teamId={teamEditInfo.teamId} />
      <div className={styles.content}>
        <div className={styles['content-inner']}>
          <div className={styles['info-div']}>
            <div className={styles.left}>
              <div className={styles['profile-card-img-div']}>
                <div className={styles['profile-card-img-inner']}>
                  <img src={teamEditInfo.profileImgUrl ? teamEditInfo.profileImgUrl : defaultImage} alt="프로필 이미지" className={styles['profile-img']} />
                </div>
                <label htmlFor="profile-img-update" className={styles['camera-div']}>
                  <BsCameraFill className={styles.camera} />
                  <input type="file" id="profile-img-update" className={styles['img-input']} onChange={onChangeImage} />
                </label>
              </div>
            </div>
            <div className={styles.right}>
              <p className={styles.name}>프로필 수정</p>
              <p className={styles.label}>단체 설명</p>
              <TextField
                color="warning"
                name="description"
                id="standard-multiline-flexible"
                multiline
                minRows={7}
                maxRows={7}
                variant="outlined"
                value={teamEditInfo.description || ''}
                sx={{ fontSize: '1.125rem', marginLeft: '0.2rem', backgroundColor: 'white' }}
                onChange={onChangeDescription}
              />
              {desChanged && (
                <button type="button" style={{ color: 'rgb(236, 153, 75)' }} className={styles['des-btn']} onClick={onClickDescriptionChange}>
                  변경
                </button>
              )}
            </div>
          </div>
          <div className={styles.hr1}> </div>
          <div className={styles['pr-div']}>
            <div className={styles.item}>
              <div className={styles['pw-label']}>
                <p>비밀번호 변경</p>
                {passwordVisibility && <Visibility className={styles.eye} onClick={() => setPasswordVisibility(false)} />}
                {!passwordVisibility && <VisibilityOff className={styles.eye} onClick={() => setPasswordVisibility(true)} />}
              </div>
              {!changePw && (
                <Button color="warning" variant="outlined" className={styles['change-btn']} onClick={() => setChangePw(true)}>
                  변경
                </Button>
              )}
              {changePw && (
                <>
                  <input
                    name="password"
                    type={!passwordVisibility ? 'password' : ''}
                    placeholder="기존 비밀번호"
                    className={styles['pw-input']}
                    onChange={onChangePassword}
                  />
                  <input
                    name="newPassword"
                    type={!passwordVisibility ? 'password' : ''}
                    placeholder="새 비밀번호"
                    className={styles['pw-input']}
                    onChange={onChangePassword}
                  />
                  <input
                    name="newPasswordCheck"
                    type={!passwordVisibility ? 'password' : ''}
                    placeholder="새 비밀번호 확인"
                    className={styles['pw-input']}
                    onChange={onChangePassword}
                  />
                  <div className={styles['pw-btn-div']}>
                    <button type="button" style={{ color: 'rgba(0, 0, 0, 0.8)' }} className={styles['pw-btn']} onClick={onCancelChangePassword}>
                      취소
                    </button>
                    <button type="button" style={{ color: 'rgb(236, 153, 75)' }} className={styles['pw-btn']} onClick={onClickPasswordChange}>
                      변경
                    </button>
                  </div>
                </>
              )}
            </div>
            <div className={styles.hr2}> </div>
            <div className={styles.item}>
              <div className={styles['perform-label']}>
                <p>VMS 파일 변경</p>
              </div>
              {!changeVms && (
                <Button color="warning" variant="outlined" className={styles['change-btn']} onClick={() => setChangeVms(true)}>
                  변경
                </Button>
              )}
              {changeVms && (
                <div>
                  <input
                    name="password"
                    type="password"
                    placeholder="비밀번호 입력"
                    style={{ marginTop: '0.5rem' }}
                    className={styles['pw-input']}
                    onChange={onChangeVmsPassword}
                  />
                  <label htmlFor="file">
                    <input type="file" onChange={onChangeVms} />
                  </label>
                  <br />
                  <button type="button" style={{ color: 'rgba(0, 0, 0, 0.8)' }} className={styles['perform-btn']} onClick={onCancelChangeVms}>
                    취소
                  </button>
                  <button type="button" style={{ color: 'rgb(236, 153, 75)' }} className={styles['perform-btn']} onClick={onClickVmsChange}>
                    변경
                  </button>
                </div>
              )}
            </div>
            <div className={styles.hr2}> </div>
            <div className={styles.item}>
              <div className={styles['perform-label']}>
                <p>봉사 실적 파일 변경</p>
              </div>
              {!changePerform && (
                <Button color="warning" variant="outlined" className={styles['change-btn']} onClick={() => setChangePerform(true)}>
                  변경
                </Button>
              )}
              {changePerform && (
                <div>
                  <input
                    name="password"
                    type="password"
                    placeholder="비밀번호 입력"
                    style={{ marginTop: '0.5rem' }}
                    className={styles['pw-input']}
                    onChange={onChangePerformPassword}
                  />
                  <label htmlFor="file">
                    <input type="file" onChange={onChangePerform} />
                  </label>
                  <br />
                  <button type="button" style={{ color: 'rgba(0, 0, 0, 0.8)' }} className={styles['perform-btn']} onClick={onCancelChangePerform}>
                    취소
                  </button>
                  <button type="button" style={{ color: 'rgb(236, 153, 75)' }} className={styles['perform-btn']} onClick={onClickPerformChange}>
                    변경
                  </button>
                </div>
              )}
            </div>
            <button type="button" className={styles.back} onClick={() => navigate(-1)}>
              {'< 프로필 페이지'}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default TeamEditContainer;
