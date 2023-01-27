import React from 'react';
import Drawer from '@mui/material/Drawer';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import AdminSideBarItem from './AdminSideBarItem';
import styles from './AdminSideBar.module.scss';
import logo from '../../assets/images/FunteerLogoInversion.png';

const drawerWidth = 240;

export default function PermanentDrawerLeft() {
  return (
    <Drawer
      sx={{
        width: drawerWidth,
        flexShrink: 0,
        '& .MuiDrawer-paper': {
          width: drawerWidth,
          boxSizing: 'border-box',
        },
      }}
      variant="permanent"
      anchor="left"
    >
      <div className={styles.sidebar}>
        <img src={logo} alt="FUNTEER" className={styles.logo} />
        <List>
          {AdminSideBarItem.map((data) => (
            <ListItem key={data.title}>
              <ListItemButton>
                <ListItemIcon>{data.icon}</ListItemIcon>
                <ListItemText primary={data.title} />
              </ListItemButton>
            </ListItem>
          ))}
        </List>
      </div>
    </Drawer>
  );
}
