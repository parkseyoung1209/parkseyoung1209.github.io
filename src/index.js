import React from 'react';
import ReactDOM from 'react-dom/client';
import { RouterProvider } from 'react-router-dom';
import router from './router';
import "./assets/css/reset.css";
import store from "./store";
import { Provider } from 'react-redux';
import { QueryClient, QueryClientProvider } from 'react-query';

const queryClient = new QueryClient();

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <QueryClientProvider client={queryClient}>
    <Provider store={store}>
        <RouterProvider router={router}/>
    </Provider>
 </QueryClientProvider>
);

