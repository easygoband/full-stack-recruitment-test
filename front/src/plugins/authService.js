import axios from 'axios'

let config = {
  baseURL: import.meta.env.VITE_AUTH_API,
  apiKey: import.meta.env.VITE_AUTH_API_KEY,
  timeout: 10 * 1000, // Timeout 
  headers: {
    'apiKey': import.meta.env.VITE_AUTH_API_KEY,
  }
};

const axiosInstance = axios.create(config);

axiosInstance.interceptors.request.use(
  function (config) {
    config.headers.common['token'] = localStorage.getItem('token') || '';
    return config;
  },
  function (error) {
    return Promise.reject(error);
  }
);

axiosInstance.interceptors.response.use(
  function (response) {
    return response;
  },
  // function (error) {
  //   if (error.code == "ECONNABORTED") {
  //     // Vue.toasted.global.errorMessage('Connection time exceeded, try later');
  //   }
  //   //Reset session if acces token is expired or unauthorized
  //   if (error.response.status == 401) {
  //     localStorage.removeItem('token');
  //     localStorage.removeItem('user');
  //     if (window.location.pathname != '/login' && window.location.pathname != '/session') {
  //       window.location.replace("/login?session_expired=true");
  //     } else {
  //       // Vue.toasted.global.errorMessage(error.response.data.message);
  //     }
  //   } else if (error.response.status == 422 && router.history.current.path == "/signup") {
  //     // Vue.toasted.global.errorMessage(i18n.t('signup.errorEmail'));
  //   } else {
  //     let message = '';
  //     if (error.response.status == 500) {
  //       message = 'Something is wrong, try later';
  //     } else {
  //       message = error.response.data.message;
  //     }
  //     // Vue.toasted.global.errorMessage(message);
  //   }
  //   // Do something with response error
  //   return Promise.reject(error);
  // }
);



export default axiosInstance;