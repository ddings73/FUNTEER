import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { BrowserRouter, createBrowserRouter, RouterProvider } from 'react-router-dom';
import { Provider } from 'react-redux';
import { ThemeProvider } from '@emotion/react';
import App from './App';
import reportWebVitals from './reportWebVitals';
import store from './store/store';
/*  */
import { theme } from './theme/theme';
import UserRoot from './roots/UserRoot';
import AdminRoot from './roots/AdminRoot';
import {
  MainPage,
  SignUp,
  MemberSignUp,
  TeamSignUp,
  FindPassword,
  Login,
  TeamPage,
  ServiceDetail,
  FindEmail,
  ResetPassword,
  Charge,
  Donation,
  ErrorPage,
  MyBadges,
  EditProfile,
  MyDonates,
  MyFavors,
  MyFollows,
  MyFunding,
  MyFunteerDonate,
  MyPage,
  AdminMain,
  AdminMember,
  LogOut,
  AdminTeam,
  FundingList,
  CreateFunding,
  AdminTeamDeny,
} from './pages/index';

const router = createBrowserRouter([
  {
    path: '/',
    element: <UserRoot />,
    errorElement: <ErrorPage />,
    children: [
      {
        index: true,
        element: <MainPage />,
      },
      /* Accounts Routes */
      {
        path: 'login',
        element: <Login />,
      },
      {
        path: 'findEmail',
        element: <FindEmail />,
      },
      {
        path: 'findPassword',
        element: <FindPassword />,
      },
      {
        path: 'resetPassword',
        element: <ResetPassword />,
      },
      {
        path: 'signup',
        element: <SignUp />,
      },
      {
        path: 'signup/team',
        element: <TeamSignUp />,
      },
      {
        path: 'signup/member',
        element: <MemberSignUp />,
      },
      {
        path: 'logout',
        element: <LogOut />,
      },
      /* Add-on Routes */
      {
        path: 'donation',
        element: <Donation />,
      },
      {
        path: 'charge',
        element: <Charge />,
      },
      /* Service Routes */
      {
        path: 'service',
        element: <ServiceDetail />,
      },
      {
        path: 'team',
        element: <TeamPage />,
      },
      /* MyPage Routes */
      {
        path: 'myPage',
        element: <MyPage />,
      },
      {
        path: 'editProfile',
        element: <EditProfile />,
      },
      {
        path: 'myFunding',
        element: <MyFunding />,
      },
      {
        path: 'myFunteerDonate',
        element: <MyFunteerDonate />,
      },
      {
        path: 'myDonates',
        element: <MyDonates />,
      },
      {
        path: 'myBadges',
        element: <MyBadges />,
      },
      {
        path: 'myFavors',
        element: <MyFavors />,
      },
      {
        path: 'myFollow',
        element: <MyFollows />,
      },
      {
        path: '/funding',
        element: <FundingList />,
      },
      {
        path: '/funding/create',
        element: <CreateFunding />,
      },
    ],
  },
  {
    path: '/admin',
    element: <AdminRoot />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: 'main',
        element: <AdminMain />,
      },
      {
        path: 'member',
        element: <AdminMember />,
      },
      {
        path: 'team',
        element: <AdminTeam />,
      },
      {
        path: 'team/deny/:vn', // vn: vms 위촉 번호
        element: <AdminTeamDeny />,
      },
    ],
  },
  {
    path: '/admin',
    element: <AdminRoot />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: 'main',
        element: <AdminMain />,
      },
      {
        path: 'member',
        element: <AdminMember />,
      },
    ],
  },
]);

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);
root.render(
  <React.StrictMode>
    <Provider store={store}>
      <RouterProvider router={router} />
    </Provider>
  </React.StrictMode>,
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
