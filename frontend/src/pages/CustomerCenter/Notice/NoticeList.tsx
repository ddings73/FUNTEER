import React from 'react';
import { useNavigate } from 'react-router-dom';
import { AppBar, Box, Tab, Tabs } from '@mui/material';
import NoticeContainer from '../../../containers/CustomerCenter/Notice/NoticeContainer';
import styles from '../CustomerCenter.module.scss';

function NoticeList() {
  const navigate = useNavigate();

  const goOnFAQ = () => {
    navigate('../faq');
  };

  const goOnQna = () => {
    navigate('../qna');
  };

  return (
    <div className={styles.page}>
      <div className={styles['tab-contents']}>
        <Box sx={{ width: '100%' }}>
          <AppBar position="static" sx={{ backgroundColor: 'white', boxShadow: 'none' }}>
            <Tabs
              value={0}
              variant="fullWidth"
              textColor="inherit"
              TabIndicatorProps={{
                style: {
                  color: 'rgba(236, 153, 75, 1)',
                  backgroundColor: 'rgba(236, 153, 75, 1)',
                },
              }}
              sx={{ color: 'black' }}
            >
              <Tab label="공지사항" sx={{ fontWeight: 'bold' }} />
              <Tab label="FAQ" onClick={goOnFAQ} sx={{ fontWeight: 'bold' }} />
              <Tab label="1:1 문의" onClick={goOnQna} sx={{ fontWeight: 'bold' }} />
            </Tabs>
          </AppBar>
          <NoticeContainer />
        </Box>
      </div>
    </div>
  );
}

export default NoticeList;
