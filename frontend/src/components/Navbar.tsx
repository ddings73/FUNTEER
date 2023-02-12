import * as React from 'react';
import { useState, useEffect } from 'react';
// Material UI Imports
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
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
/* 이미지 import */
import logoImg from '../assets/images/FunteerLogo.png';
/*로그인 Import */
import { useAppDispatch, useAppSelector } from '../store/hooks';
import userSlice, { isLoginState, resetLoginState, setUserLoginState } from '../store/slices/userSlice';
import { useDispatch, useSelector } from 'react-redux';
import NavbarMenuData from './NavbarMenuData';
import { requestLogout } from '../api/user';
import { openModal } from '../store/slices/modalSlice';
import { Chip } from '@mui/material';
import { requestTeamAccountInfo } from '../api/team';

const pages = NavbarMenuData;
const settings = ['마이페이지', '나의 펀딩 내역', '도네이션 내역', '1:1 문의 내역', '로그아웃'];

function ResponsiveAppBar() {
  const navigateTo = useNavigate();
  const dispatch = useDispatch();
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

  // 로그아웃
  const logout = async () => {
    try {
      const response = await requestLogout();
      console.log(response);
      dispatch(resetLoginState());
      localStorage.removeItem('accessToken');
      localStorage.removeItem('refreshToken');
      alert('로그아웃 되었습니다.');
      navigateTo('/');
    } catch (error) {
      console.log(error);
    }
  };

  function logoHandler() {
    navigateTo('/');
    window.scrollTo(0, 0);
  }

  const isLogin = useAppSelector((state) => state.userSlice.isLogin);
  let menuDataLength: number = NavbarMenuData.length;
  // console.log('로그인임?', isLogin);

  // 회원 정보 GET
  const profileImgUrl = useAppSelector((state) => state.userSlice.profileImgUrl);
  const userName = useAppSelector((state) => state.userSlice.username);
  const userType = useAppSelector((state) => state.userSlice.userType);

  // 단체 정보 GET
  const [teamInfo, setTeamInfo] = useState<TeamInfoType>({
    email: '',
    id: 0,
    name: '',
  });

  async function getTeamInfo() {
    const res = await requestTeamAccountInfo();
    setTeamInfo(res.data);
    console.log('팀정보', res);
  }

  type TeamInfoType = {
    email: string;
    id: number;
    name: string;
  };

  useEffect(() => {
    if (isLogin && userType === 'TEAM') {
      getTeamInfo();
    }
  }, [userType]);

  return (
    <div>
      <AppBar className={styles.appBar} position="fixed" sx={{ backgroundColor: scrollPosition < 10000 ? 'transparent' : 'rgb(255,255,255)' }}>
        <Container className={styles.appContainer} maxWidth="xl">
          <Toolbar disableGutters>
            {/* Desktop 구조 */}
            <img className={styles.logoImg} src={logoImg} alt="logoImg" onClick={() => logoHandler()} style={{ cursor: 'pointer' }} />
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
              <div style={{ display: isLogin ? 'flex' : 'none', alignItems: 'center' }}>
                {userType === 'ADMIN' && (
                  <Chip label="관리자" color="error" component={NavLink} sx={{ marginRight: '10px', display: 'flex' }} size="small" to="/admin" clickable />
                )}
                {userType === 'TEAM' && <Chip label="단체회원" color="secondary" sx={{ marginRight: '10px', display: 'flex' }} size="small" />}
                {userType === 'NORMAL' && <Chip label="일반회원" color="warning" sx={{ marginRight: '10px', display: 'flex' }} size="small" />}

                <p style={{ color: 'black' }}>
                  <span style={{ fontWeight: '800' }}>{userName}</span>님 환영합니다
                </p>
                <IconButton aria-label="notifi" className={styles.noti}>
                  <StyledBadge badgeContent={4} color="secondary" anchorOrigin={{ horizontal: 'right', vertical: 'top' }} sx={{ mr: 2 }}>
                    <NotificationsNoneIcon fontSize="large" />
                  </StyledBadge>
                </IconButton>
                <Tooltip title="Open settings">
                  <IconButton onClick={handleOpenUserMenu} sx={{ p: 0, border: '3px solid orange' }}>
                    <Avatar alt="profileImg" src={profileImgUrl} />
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
                  <MenuItem onClick={handleCloseUserMenu}>
                    <Typography
                      textAlign="center"
                      onClick={() => {
                        navigateTo(userType === 'NORMAL' ? '/myPage' : userType === 'TEAM' ? `/team/${teamInfo.id}` : '/admin');
                      }}
                      sx={{ width: '100%' }}
                    >
                      마이페이지
                    </Typography>
                  </MenuItem>
                  <MenuItem onClick={handleCloseUserMenu}>
                    <Typography
                      textAlign="center"
                      onClick={() => {
                        navigateTo('/myFunding');
                      }}
                      sx={{ width: '100%' }}
                    >
                      나의 펀딩 내역
                    </Typography>
                  </MenuItem>
                  <MenuItem onClick={handleCloseUserMenu}>
                    <Typography
                      textAlign="center"
                      onClick={() => {
                        navigateTo('/myDonates');
                      }}
                      sx={{ width: '100%' }}
                    >
                      도네이션 내역
                    </Typography>
                  </MenuItem>
                  <MenuItem onClick={handleCloseUserMenu}>
                    <Typography
                      textAlign="center"
                      onClick={() => {
                        navigateTo('/myBadges');
                      }}
                      sx={{ width: '100%' }}
                    >
                      1:1 문의 내역
                    </Typography>
                  </MenuItem>
                  <MenuItem onClick={handleCloseUserMenu}>
                    <Typography textAlign="center" sx={{ color: 'red', width: '100%' }} onClick={logout}>
                      로그아웃
                    </Typography>
                  </MenuItem>
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
