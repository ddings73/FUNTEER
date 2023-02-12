import React from 'react';
import { useNavigate } from 'react-router-dom';
import { AppBar, Box, Tab, Tabs, Typography } from '@mui/material';
import FAQContainer from '../../../containers/CustomerCenter/FAQ/FAQContainer';
import styles from '../CustomerCenter.module.scss';

function FAQList() {
  const navigate = useNavigate();

  const goOnNotice = () => {
    navigate('../notice');
  };

  const goOnQna = () => {
    navigate('../qna');
  };

  return (
    <div className={styles.page}>
      <div className={styles['tab-contents']}>
        <Box sx={{ width: '100%' }}>
          <AppBar position="static" sx={{ backgroundColor: 'white', boxShadow: 'none' }}>
            <Tabs variant="fullWidth" sx={{ color: 'black' }}>
              <Tab label="공지사항" onClick={goOnNotice} sx={{ fontWeight: 'bold' }} />
              <Tab label="FAQ" sx={{ fontWeight: 'bold' }} className={styles.indicator} />
              <Tab label="1:1 문의" onClick={goOnQna} sx={{ fontWeight: 'bold' }} />
            </Tabs>
          </AppBar>
          <FAQContainer />
        </Box>
      </div>
    </div>
  );
}

export default FAQList;
