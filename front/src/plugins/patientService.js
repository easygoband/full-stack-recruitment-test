import axios from 'axios'

let config = {
  baseURL: import.meta.env.VITE_PATIENTS_API,
  apiKey: import.meta.env.VITE_PATIENTS_API_KEY,
  timeout: 10 * 1000, // Timeout 
  headers: {
    'apiKey': import.meta.env.VITE_PATIENTS_API_KEY,
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
);



export default axiosInstance;