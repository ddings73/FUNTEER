import * as React from 'react';
import { useState, useEffect } from 'react';
// Material UI Imports
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import Badge from '@mui/material/Badge';
import { styled } from '@mui/material/styles';
import NotificationsNoneIcon from '@mui/icons-material/NotificationsNone';
/*eslint-disable*/
/*기타 Imports */
import { Link, Outlet, NavLink } from 'react-router-dom';
import styles from './Navbar.module.scss';
import NavDataSettings from './NavbarSettingsData';
/* 이미지 import */
import logoImg from '../assets/images/FunteerLogo.png';
/*로그인 Import */
import { useAppDispatch } from '../store/hooks';
import userSlice, { isLoginState, setUserLoginState } from '../store/slices/userSlice';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../store/store';
import { title } from 'process';
import { userInfo } from 'os';
import NavbarMenuData from './NavbarMenuData';

const pages = NavbarMenuData;
const settings = ['마이페이지', '나의 펀딩 내역', '도네이션 내역', '1:1 문의 내역', '로그아웃'];

function ResponsiveAppBar() {
  const [isLogin, setIsLogin] = useState(false);
  const insertedToken = localStorage.getItem('token');
  const dispatch = useAppDispatch();

  const [anchorElNav, setAnchorElNav] = React.useState<null | HTMLElement>(null);
  const [anchorElUser, setAnchorElUser] = React.useState<null | HTMLElement>(null);

  const handleOpenNavMenu = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorElNav(event.currentTarget);
  };
  const handleOpenUserMenu = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };
  const StyledBadge = styled(Badge)(({ theme }) => ({
    '& .MuiBadge-badge': {
      right: -3,
      top: 13,
      border: `2px solid ${theme.palette.background.paper}`,
      padding: '0 4px',
    },
  }));

  useEffect(() => {}, [isLoginState]);

  // console.log('로그인임?', isLogin);

  return (
    <AppBar className={styles.appBar} position="fixed">
      <Container className={styles.appContainer} maxWidth="xl">
        <Toolbar disableGutters>
          {/* Desktop 구조 */}
          <Link to="/">
            <img className={styles.logoImg} src={logoImg} alt="logoImg" />
          </Link>
          <Box sx={{ flexGrow: 1 }} className={styles.menuBox}>
            <IconButton
              className={styles.iconBtn}
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleOpenNavMenu}
              color="inherit"
            >
              <MenuIcon />
            </IconButton>

            <Menu
              className={styles.navItems}
              id="menu-appbar"
              anchorEl={anchorElNav}
              anchorOrigin={{
                vertical: 'bottom',
                horizontal: 'left',
              }}
              keepMounted
              transformOrigin={{
                vertical: 'top',
                horizontal: 'left',
              }}
              open={Boolean(anchorElNav)}
              onClose={handleCloseNavMenu}
              sx={{
                display: { md: 'none' },
              }}
            >
              {pages.map((page) => (
                <MenuItem key={page.title} onClick={handleCloseNavMenu} className={styles.menuItem}>
                  {page.title}
                </MenuItem>
              ))}
            </Menu>
          </Box>

          <Typography
            variant="h5"
            noWrap
            component="a"
            href=""
            sx={{
              flexGrow: 1,
              display: { md: 'none' },
            }}
            className={styles.mobBox}
          >
            <img className={styles.logoImgMobile} src={logoImg} alt="logoImgMobile" />
          </Typography>
          <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' }, justifyContent: 'flex-end', alignItems: 'center', margin: '0 2%' }} className={styles.pageBox}>
            {pages.map((page) => (
              <Button key={page.title} onClick={handleCloseNavMenu} sx={{ my: 2, color: 'white', display: 'block', ml: 2 }} className={styles.menuBtn}>
                {page.title}
              </Button>
            ))}
          </Box>

          <Box sx={{ flexGrow: 0 }}>
            <div style={{ display: insertedToken === null ? 'flex' : 'none' }}>
              <NavLink to="/Login">
                <button className={styles.accountBtn} type="button">
                  로그인
                </button>
              </NavLink>
              <NavLink to="/signup">
                <button className={styles.accountBtn} type="button">
                  회원가입
                </button>
              </NavLink>
            </div>
            <div style={{ display: insertedToken === null ? 'none' : 'flex' }}>
              <IconButton aria-label="notifi" className={styles.noti}>
                <StyledBadge badgeContent={4} color="secondary" anchorOrigin={{ horizontal: 'right', vertical: 'top' }} sx={{ mr: 2 }}>
                  <NotificationsNoneIcon fontSize="large" />
                </StyledBadge>
              </IconButton>
              <Tooltip title="Open settings">
                <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                  <Avatar alt="Remy Sharp" src="/static/images/avatar/2.jpg" />
                </IconButton>
              </Tooltip>
              <Menu
                sx={{ mt: '45px' }}
                id="menu-appbar"
                anchorEl={anchorElUser}
                anchorOrigin={{
                  vertical: 'top',
                  horizontal: 'right',
                }}
                keepMounted
                transformOrigin={{
                  vertical: 'top',
                  horizontal: 'right',
                }}
                open={Boolean(anchorElUser)}
                onClose={handleCloseUserMenu}
              >
                {NavDataSettings.map((data) => (
                  <NavLink to={data.path} className={styles.navlinks}>
                    <MenuItem onClick={handleCloseUserMenu}>
                      <Typography textAlign="center">{data.title}</Typography>
                    </MenuItem>
                  </NavLink>
                ))}
              </Menu>
            </div>
          </Box>
        </Toolbar>
      </Container>
    </AppBar>
  );
}
export default ResponsiveAppBar;
