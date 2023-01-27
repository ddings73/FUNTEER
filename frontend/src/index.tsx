import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { BrowserRouter, createBrowserRouter, RouterProvider } from 'react-router-dom';
import { Provider } from 'react-redux';
import App from './App';
import reportWebVitals from './reportWebVitals';
import store from './store/store';
/*  */
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
        children: [
          {
            path: 'ResetPassword',
            element: <ResetPassword />,
          },
        ],
      },
      {
        path: 'signup',
        element: <SignUp />,
        children: [
          {
            path: 'teamSignUp',
            element: <TeamSignUp />,
          },
          {
            path: 'MemberSignUp',
            element: <MemberSignUp />,
          },
        ],
      },
      {
        path: 'donation',
        element: <Donation />,
      },
      {
        path: 'service',
        element: <ServiceDetail />,
      },
      {
        path: 'team',
        element: <TeamPage />,
      },
      {
        path: 'charge',
        element: <Charge />,
      },
    ],
  },
  {
    path: '/admin',
    element: <AdminRoot />,
    errorElement: <ErrorPage />,
    children: [],
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
