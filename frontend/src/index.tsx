import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { BrowserRouter, createBrowserRouter, RouterProvider } from 'react-router-dom';
import { Provider } from 'react-redux';
import App from './App';
import reportWebVitals from './reportWebVitals';
import store from './store/store';
import UserRoot from './UserRoot'
import { Login, MainPage } from './pages';
import AdminRoot from './AdminRoot';


const router = createBrowserRouter([
  {
   path:"/",
   element:<UserRoot />,
   children:[
    {
      index:true,
      element:<MainPage/>
    },
    {
      path:'/login',
      element:<Login/>
    }
   ] 
  },
  {
    path:"/admin",
    element:<AdminRoot/>,
    children:[
    ]
  }
])

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);
root.render(
  <React.StrictMode>
    <Provider store={store}>
      <RouterProvider router={router}/>
    </Provider>
  </React.StrictMode>,
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
