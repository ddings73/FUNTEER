import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import { useParams } from 'react-router-dom';
import SendIcon from '@mui/icons-material/Send';
import styles from './CommentCardSubmit.module.scss';
import profileTemp from '../../assets/images/default-profile-img.svg';
import { postFundingComment, requestCommentList } from '../../api/funding';
import { commentType } from '../../containers/Funding/FundingDetailContainer';
import { useAppSelector } from '../../store/hooks';
import { customTextOnlyAlert, noTimeSuccess } from '../../utils/customAlert';

export function CommentCardSubmit(props: any, commentCount: number) {
  const { fundIdx } = useParams();
  const isLogin = useAppSelector((state) => state.userSlice.isLogin);
  const [comment, setComment] = useState('');
  const commentOnChange = (e: any) => {
    setComment(e.target.value);
  };
  const fetchData = async () => {
    try {
      const response = await postFundingComment(comment, fundIdx);
      console.log('댓글res', response);
    } catch (error) {
      console.log(error);
    }
  };
  const onSubmit = async (e: any) => {
    e.preventDefault();
    await fetchData();
    customTextOnlyAlert(noTimeSuccess, '댓글 등록 완료!');
    setComment('');
    /* eslint-disable */
    props.initCommentList();
  };
  const handleKeyPress = (e: any) => {
    if (e.key === 'Enter') {
      if (!comment) {
        e.preventDefault();
      } else {
        e.preventDefault();
        fetchData();
        customTextOnlyAlert(noTimeSuccess, '댓글 등록 완료!');
        setComment('');
      }
    }
  };

  return (
    <div className={styles.cardContainer}>
      <form onKeyUp={handleKeyPress} onSubmit={onSubmit} style={{ width: '80%', marginTop: '20px', display: 'flex' }}>
        <div className={styles.commentMid}>
          <img className={styles.commentImg} alt="프로필" src={profileTemp} />
          <TextField
            className={styles.tesxtField}
            placeholder={isLogin ? '댓글을 입력해주세요' : '로그인 후 이용해주세요'}
            variant="filled"
            sx={{
              width: '80%',
              '& .MuiInputBase-input': {
                paddingTop: '2%',
              },
            }}
            value={comment}
            onChange={commentOnChange}
            size="small"
            color="warning"
          />
        </div>
        {isLogin ? (
          <div className={styles.BtnGrop}>
            <button className={styles.commentBtn} type="submit">
              <SendIcon />
              <p>댓글 등록</p>
            </button>
          </div>
        ) : (
          ''
        )}
      </form>
    </div>
  );
}

export default CommentCardSubmit;
