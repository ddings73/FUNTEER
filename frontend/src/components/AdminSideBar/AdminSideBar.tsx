import React from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import Drawer from '@mui/material/Drawer';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import AdminSideBarItem from './AdminSideBarItem';
import styles from './AdminSideBar.module.scss';
import logo from '../../assets/images/FunteerLogoInversion.png';

const drawerWidth = 280;

export default function PermanentDrawerLeft() {
  const navigate = useNavigate();

  const onClickAdminLogo = () => {
    navigate('/admin');
  };

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
      className={styles.drawer}
    >
      <div className={styles.sidebar}>
        <button type="button" className={styles['logo-btn']} onClick={onClickAdminLogo}>
          <img src={logo} alt="FUNTEER" className={styles.logo} />
        </button>
        <List>
          {AdminSideBarItem.map((data) => (
            <NavLink key={data.title} to={data.path} id="nav-link" className={({ isActive }) => (isActive ? styles['menu-active'] : styles['menu-inactive'])}>
              <ListItem className={styles['list-item']}>
                <ListItemButton>
                  <ListItemIcon>{data.icon}</ListItemIcon>
                  <ListItemText primary={data.title} />
                </ListItemButton>
              </ListItem>
            </NavLink>
          ))}
        </List>
      </div>
    </Drawer>
  );
}
