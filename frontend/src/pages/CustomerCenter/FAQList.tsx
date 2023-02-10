import React from 'react'
import { AppBar, Box, Tab, Tabs, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import styles from './CustomerCenter.module.scss';
import FAQContainer from '../../containers/CustomerCenter/FAQ/FAQContainer';

interface TabPanelProps {
    children: React.ReactNode;
    dir: string;
    index: number;
    value: number;
  }
  
  function TabPanel(props: TabPanelProps) {
    const { children, value, index, ...other } = props;
  
    return (
      <div role="tabpanel" hidden={value !== index} id={`full-width-tabpanel-${index}`} aria-labelledby={`full-width-tab-${index}`} {...other}>
        {value === index && (
          <Box sx={{ p: 3 }}>
            <Typography>{children}</Typography>
          </Box>
        )}
      </div>
    );
  }
  
  function a11yProps(index: number) {
    return {
      id: `full-width-tab-${index}`,
      'aria-controls': `full-width-tabpanel-${index}`,
    };
  }

function FAQList() {
    const navigate = useNavigate();
    
    const goOnNotice = () => {
        navigate('../notice')
    }

    const goOnQna = () => {
        navigate('../qna')
    }
  return (
    <div className={styles.page}>
      <div className={styles['tab-contents']}>
      <Box sx={{ width: '100%' }}>
            <AppBar position="static" sx={{ backgroundColor: 'white', boxShadow: 'none' }}>
                <Tabs
                sx={{ color: 'black' }}
                TabIndicatorProps={{
                    sx: { backgroundColor: '#E6750A' },
                }}
                textColor="inherit"
                variant="fullWidth"
                aria-label="full width tabs example"
                >
                <Tab label="공지사항" onClick = {goOnNotice} sx={{ fontWeight: 'bold' }} />
                <Tab label="FAQ" sx={{ fontWeight: 'bold' }} />
                <Tab label="1:1 문의" onClick = {goOnQna} sx={{ fontWeight: 'bold' }} />
                </Tabs>
            </AppBar>
        <FAQContainer/>
        </Box>
    </div>
    </div>
  )
}

export default FAQList;