import axios from 'axios';

const TIMEOUT = 1000000;
const onRequestSuccess = config => {
  const token = localStorage.getItem('jhi-authenticationToken') || sessionStorage.getItem('jhi-authenticationToken');
  if (token) {
    if (!config.headers) {
      config.headers = {};
    }
    config.headers.Authorization = `Bearer ${token}`;
  }
  // 获取路由中的menuid信息
  const menuid = localStorage.getItem('menuid');
  if (menuid) {
    console.log('菜单信息, ', menuid);
    config.headers.menuid = `${menuid}`;
  }
  config.timeout = TIMEOUT;
  config.url = `${SERVER_API_URL}${config.url}`;
  return config;
};
const setupAxiosInterceptors = (onUnauthenticated, onServerError) => {
  const onResponseError = err => {
    const status = err.status || err.response.status;
    if (status === 403 || status === 401) {
      onUnauthenticated();
    }
    if (status >= 500) {
      onServerError();
    }
    return Promise.reject(err);
  };

  if (axios.interceptors) {
    axios.interceptors.request.use(onRequestSuccess);
    axios.interceptors.response.use(res => res, onResponseError);
  }
};

export { onRequestSuccess, setupAxiosInterceptors };
