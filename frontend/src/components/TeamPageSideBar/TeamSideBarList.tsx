import React from 'react';
import { NavLink } from 'react-router-dom';
import Paper from '@mui/material/Paper';
import ListItemIcon from '@mui/material/ListItemIcon';
import Typography from '@mui/material/Typography';
import * as AiIcons from 'react-icons/ai';
import styles from './TeamSideBarList.module.scss';
import SideBarData from './TeamSideBarItem';

type teamSideBarProps = {
  teamId: string | undefined;
};

export function TeamSideBarList({ teamId }: teamSideBarProps) {
  return (
    <Paper className={styles.paperContainer}>
      <div className={styles.menuList}>
        {SideBarData.map((data) => (
          <NavLink to={data.path.concat(`/${teamId}`)} className={({ isActive }) => (isActive ? styles.NavActive : styles.NavInActive)}>
            <div className={styles.menuItem}>
              <ListItemIcon className={styles.menuIcon}>{data.icon}</ListItemIcon>
              <Typography variant="inherit">{data.title}</Typography>
            </div>
          </NavLink>
        ))}
      </div>
    </Paper>
  );
}

export default TeamSideBarList;
