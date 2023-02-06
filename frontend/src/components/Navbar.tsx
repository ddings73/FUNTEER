import * as React from 'react';
import { useState, useEffect, useRef } from 'react';
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
import { Link, Outlet, NavLink, useNavigate, Navigate } from 'react-router-dom';
import styles from './Navbar.module.scss';
import NavDataSettings from './NavbarSettingsData';
/* 이미지 import */
import logoImg from '../assets/images/FunteerLogo.png';
/*로그인 Import */
import { useAppDispatch, useAppSelector } from '../store/hooks';
import userSlice, { isLoginState, setUserLoginState } from '../store/slices/userSlice';
import { useDispatch, useSelector } from 'react-redux';
import NavbarMenuData from './NavbarMenuData';

const pages = NavbarMenuData;
const settings = ['마이페이지', '나의 펀딩 내역', '도네이션 내역', '1:1 문의 내역', '로그아웃'];

function ResponsiveAppBar() {
  const navigateTo = useNavigate();
  function clickNavigate(address: string) {
    navigateTo(address);
  }
  const [scrollPosition, setScrollPosition] = useState(0);
  const [anchorElUser, setAnchorElUser] = useState<null | HTMLElement>(null);
  const [ishovered, setIsHovered] = useState(false);

  const updateScroll = () => {
    setScrollPosition(window.scrollY || document.documentElement.scrollTop);
  };
  const handleOpenUserMenu = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorElUser(event.currentTarget);
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

  const isLogin = useAppSelector((state) => state.userSlice.isLogin);
  let menuDataLength: number = NavbarMenuData.length;
  // console.log('로그인임?', isLogin);

  useEffect(() => {
    window.addEventListener('scroll', updateScroll);
  });

  return (
    <div>
      <AppBar className={styles.appBar} position="fixed" sx={{ backgroundColor: scrollPosition < 10000 ? 'transparent' : 'rgb(255,255,255)' }}>
        <Container className={styles.appContainer} maxWidth="xl">
          <Toolbar disableGutters>
            {/* Desktop 구조 */}
            <Link to="/">
              <img className={styles.logoImg} src={logoImg} alt="logoImg" />
            </Link>

            <Box
              sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}
              className={styles.pageBox}
              onMouseOut={() => {
                setIsHovered(false);
              }}
              onMouseOver={() => {
                setIsHovered(true);
              }}
            >
              {pages.map((page) => (
                <div className={styles.menuWrapper} key={page.title}>
                  <Button key={page.title} sx={{ my: 2, color: 'white', display: 'block', ml: 2 }} className={styles.menuBtn}>
                    {page.title}
                  </Button>
                  <ul
                    className={ishovered ? styles.menuSubMenuHovered : styles.menuSubMenu}
                    onMouseOut={() => {
                      setIsHovered(false);
                    }}
                    onMouseOver={() => {
                      setIsHovered(true);
                    }}
                  >
                    {page.subRoutes.map((Menu, i) => (
                      <li className={styles.menuSubMenuEl} key={i} onClick={() => clickNavigate(page.subPath[i])}>
                        {Menu}
                      </li>
                    ))}
                  </ul>
                </div>
              ))}
            </Box>

            <Box sx={{ flexGrow: 0 }}>
              <div style={{ display: isLogin ? 'none' : 'flex' }}>
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
              <div style={{ display: isLogin ? 'flex' : 'none' }}>
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
                  {NavDataSettings.map((data, i) => (
                    <NavLink to={data.path} className={styles.navlinks} key={i}>
                      <MenuItem onClick={handleCloseUserMenu}>
                        <Typography textAlign="center" sx={{ color: i == menuDataLength - 1 ? 'red' : 'black' }}>
                          {data.title}
                        </Typography>
                      </MenuItem>
                    </NavLink>
                  ))}
                </Menu>
              </div>
            </Box>
          </Toolbar>
        </Container>
      </AppBar>
      <div
        onMouseOut={() => {
          setIsHovered(false);
        }}
        onMouseOver={() => {
          setIsHovered(true);
        }}
        className={ishovered ? styles.megaContainer : styles.megaContainerHidden}
      />
    </div>
  );
}
export default ResponsiveAppBar;
