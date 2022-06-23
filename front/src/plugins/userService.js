import axios from 'axios'

let config = {
  baseURL: import.meta.env.VITE_USER_API,
  timeout: 10 * 1000, // Timeout 
};

const axiosInstance = axios.create(config);

axiosInstance.interceptors.request.use(
  function (config) {
    config.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token') || '';
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
);

export default axiosInstance;