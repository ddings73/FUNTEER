import React, { useEffect, useRef, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { BsCameraFill } from 'react-icons/bs';
import { FaMoneyBillWaveAlt } from 'react-icons/fa';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import EmailIcon from '@mui/icons-material/Email';
import { requestTeamProfileInfo } from '../../../api/team';
import TeamSideBarList from '../../../components/TeamPageSideBar/TeamSideBarList';
import { teamProfileType } from '../../../types/user';
import { requestFollow } from '../../../api/user';
import styles from './TeamEditContainer.module.scss';
import defaultImage from '../../../assets/images/default-profile-img.svg';

function TeamEditContainer() {
  const location = useLocation();

  /** 프로필 정보 */
  const [teamEditInfo, setTeamEditInfo] = useState({ ...location.state });
  /** 프로필 이미지 정보 */
  const [files, setFiles] = useState<FileList | null>(null);

  /** 단체 설명 */
  const onChangeDescription = (e: React.ChangeEvent<HTMLInputElement>) => {
    setTeamEditInfo({ ...teamEditInfo, description: e.target.value });
  };

  /** 이미지 미리보기 */
  const onChangeImage = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setFiles(e.target.files);

      const newImgUrl = URL.createObjectURL(e.target.files[0]);
      setTeamEditInfo({ ...teamEditInfo, profileImgUrl: newImgUrl });
    }
  };

  return (
    <div className={styles.container}>
      <TeamSideBarList teamId={teamEditInfo.teamId} />
      <div className={styles.content}>
        <div className={styles['content-inner']}>
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
            <p className={styles.name}>{teamEditInfo.name}</p>
            <p className={styles.label}>단체 설명</p>
            <TextField
              name="description"
              id="standard-multiline-flexible"
              multiline
              minRows={7}
              maxRows={7}
              variant="outlined"
              value={teamEditInfo.description}
              sx={{ fontSize: '1.125rem' }}
              onChange={onChangeDescription}
            />
          </div>
        </div>
      </div>
    </div>
  );
}

export default TeamEditContainer;
