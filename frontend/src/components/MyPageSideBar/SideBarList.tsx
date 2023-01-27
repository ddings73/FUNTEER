import React from 'react';
import MenuList from '@mui/material/MenuList';
import MenuItem from '@mui/material/MenuItem';
import Paper from '@mui/material/Paper';
import ListItemIcon from '@mui/material/ListItemIcon';
import Typography from '@mui/material/Typography';
import SendIcon from '@mui/icons-material/Send';
import styles from './SideBarList.module.scss';
import SideBarData from './SideBarData';
// const barMenuItems

export function SideBarList() {
  return (
    <Paper className={styles.paperContainer}>
      <MenuList>
        {SideBarData.map((data) => (
          <MenuItem>
            <ListItemIcon>{data.icon}</ListItemIcon>
            <Typography variant="inherit">{data.title}</Typography>
          </MenuItem>
        ))}
      </MenuList>
    </Paper>
  );
}

export default SideBarList;
