import React from 'react';
import { useTheme } from '@mui/material/styles';
import AppBar from '@mui/material/AppBar';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import styles from './CustomerCenter.module.scss';
import FAQContainer from '../../containers/CustomerCenter/FAQ/FAQContainer';
import NoticeContainer from '../../containers/CustomerCenter/Notice/NoticeContainer';
import QuestionContainer from '../../containers/CustomerCenter/Question/QuestionContainer';

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

export default function CustomerCenter() {
  const theme = useTheme();
  const [value, setValue] = React.useState(0);

  const handleChange = (event: React.SyntheticEvent, newValue: number) => {
    setValue(newValue);
  };

  const handleChangeIndex = (index: number) => {
    setValue(index);
  };

  return (
    <div className={styles.container}>
      <div className={styles.contents}>
        <Box sx={{ width: '100%' }}>
          <AppBar position="static">
            <Tabs
              value={value}
              onChange={handleChange}
              sx={{ backgroundColor: '#EC994B' }}
              TabIndicatorProps={{
                sx: { backgroundColor: '#E6750A' },
              }}
              textColor="inherit"
              variant="fullWidth"
              aria-label="full width tabs example"
            >
              <Tab label="FAQ" {...a11yProps(0)} />
              <Tab label="공지사항" {...a11yProps(1)} />
              <Tab label="1:1 문의" {...a11yProps(2)} />
            </Tabs>
          </AppBar>

          <TabPanel value={value} index={0} dir={theme.direction}>
            <FAQContainer />
          </TabPanel>
          <TabPanel value={value} index={1} dir={theme.direction}>
            <NoticeContainer />
          </TabPanel>
          <TabPanel value={value} index={2} dir={theme.direction}>
            <QuestionContainer />
          </TabPanel>
        </Box>
      </div>
    </div>
  );
}
