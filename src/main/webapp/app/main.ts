// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.common with an alias.
import Vue from 'vue';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import App from './app.vue';
import Vue2Filters from 'vue2-filters';
import { ToastPlugin } from 'bootstrap-vue';
import router from './router';
import * as config from './shared/config/config';
import * as bootstrapVueConfig from './shared/config/config-bootstrap-vue';
import JhiItemCountComponent from './shared/jhi-item-count.vue';
import JhiSortIndicatorComponent from './shared/sort/jhi-sort-indicator.vue';
import InfiniteLoading from 'vue-infinite-loading';
import HealthService from './admin/health/health.service';
import MetricsService from './admin/metrics/metrics.service';
import LogsService from './admin/logs/logs.service';
import ConfigurationService from '@/admin/configuration/configuration.service';
import ActivateService from './account/activate/activate.service';
import RegisterService from './account/register/register.service';
import UserManagementService from './admin/user-management/user-management.service';
import LoginService from './account/login.service';
import AccountService from './account/account.service';
import AlertService from './shared/alert/alert.service';

// 加载 element 组件库
import ElementUI from 'element-ui';
// 加载 element 组件库的样式
import 'element-ui/lib/theme-chalk/index.css';

import axios from 'axios';

import '../content/scss/global.scss';
import '../content/scss/vendor.scss';
import TranslationService from '@/locale/translation.service';
/* tslint:disable */

// jhipster-needle-add-entity-service-to-main-import - JHipster will import entities services here

/* tslint:enable */
Vue.config.productionTip = false;
config.initVueApp(Vue);
config.initFortAwesome(Vue);
bootstrapVueConfig.initBootstrapVue(Vue);
Vue.use(Vue2Filters);
Vue.use(ToastPlugin);
// 全局注册 element 组件库
Vue.use(ElementUI);
Vue.component('font-awesome-icon', FontAwesomeIcon);
Vue.component('jhi-item-count', JhiItemCountComponent);
Vue.component('jhi-sort-indicator', JhiSortIndicatorComponent);
Vue.component('infinite-loading', InfiniteLoading);
const i18n = config.initI18N(Vue);
const store = config.initVueXStore(Vue);

const translationService = new TranslationService(store, i18n);
const loginService = new LoginService();
const accountService = new AccountService(store, translationService, router);

// 动态路由
router.beforeEach(async (to, from, next) => {
  localStorage.setItem('menuid', to.meta.menuid);
  if (!to.matched.length) {
    // 找不到的路由走这里, 前端没有配置的
    // 塞入当前请求的menuid, 数据权限中使用
    // localStorage.removeItem('router');
    const asyncRouter = localStorage.getItem('router');
    console.log('前端缓存路由信息: {}', asyncRouter);
    let pathExist = false;
    if (asyncRouter) {
      // 如果已经缓存则直接从缓存里搞
      const datas = JSON.parse(asyncRouter);
      datas.forEach(v => {
        v.component = routerToComponent(v.component);
        router.addRoute(v);
        if (v.path === to.path) {
          pathExist = true;
          localStorage.setItem('menuid', v.meta.menuid);
        }
      });
      // 如果path不在路由里, 检查菜单是否配置了config.component
    } else {
      axios.get('/api/menus/route').then(resp => {
        const datas = resp.data;
        // console.log('动态路由信息 {}', datas);
        // 保存路由信息
        localStorage.setItem('router', JSON.stringify(datas));
        datas.forEach(v => {
          v.component = routerToComponent(v.component);
          router.addRoute(v);
          if (v.path === to.path) {
            pathExist = true;
            localStorage.setItem('menuid', v.meta.menuid);
          }
        });
      });
    }

    if (pathExist) {
      // next({
      //   path: to.fullPath,
      // });
      next();
    } else {
      next('/not-found');
    }
  } else if (to.meta && to.meta.authorities && to.meta.authorities.length > 0) {
    // 需要认证的走这里
    accountService.hasAnyAuthorityAndCheckAuth(to.meta.authorities).then(value => {
      if (!value) {
        sessionStorage.setItem('requested-url', to.fullPath);
        next('/forbidden');
      } else {
        next();
      }
    });
  } else {
    // no authorities, so just proceed
    // 存在路由, 但是, 不需要认证的走这里
    next();
  }
});

/* tslint:disable */
new Vue({
  el: '#app',
  components: { App },
  template: '<App/>',
  router,
  provide: {
    loginService: () => loginService,
    activateService: () => new ActivateService(),
    registerService: () => new RegisterService(),
    userManagementService: () => new UserManagementService(),
    healthService: () => new HealthService(),
    configurationService: () => new ConfigurationService(),
    logsService: () => new LogsService(),
    metricsService: () => new MetricsService(),

    translationService: () => translationService,
    // jhipster-needle-add-entity-service-to-main - JHipster will import entities services here
    accountService: () => accountService,
    alertService: () => new AlertService(),
  },
  i18n,
  store,
});

// 路径转换为component组件
function routerToComponent(path) {
  // vue 动态加载路由时报错Error: Cannot find module ‘@/views/xxx‘ at webpackEmptyContext
  // 只要 require 和import() 两者的参数如果是全路径，那么会报错，如果是部分参拼接后 那么没问题
  return () => import('@/' + path + '.vue');
}
