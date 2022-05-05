import Vue from 'vue';
import Component from 'vue-class-component';
import Ribbon from '@/core/ribbon/ribbon.vue';
import JhiFooter from '@/core/jhi-footer/jhi-footer.vue';
import JhiNavbar from '@/core/jhi-navbar/jhi-navbar.vue';
import LoginForm from '@/account/login-form/login-form.vue';

import '@/shared/config/dayjs';

@Component({
  components: {
    ribbon: Ribbon,
    'jhi-navbar': JhiNavbar,
    'login-form': LoginForm,

    'jhi-footer': JhiFooter,
  },
})
export default class App extends Vue {
  public activeIndex = '/';
  // 左侧导航是否为鼠标划入显示二级菜单
  public isCollapse = false;

  // 动态加载菜单
  public homeMenu = [
    { index: '/', title: '导航1', children: [] },
    {
      index: '/example/uiexample',
      title: '导航2',
      children: [
        { index: '/xx', title: '导航2-1', children: [] },
        { index: '/xx2', title: '导航2-2', children: [] },
      ],
    },
  ];

  // el-menu选中会传入链接信息 key === index
  public handleSelect(key, keyPath): void {
    console.log('点击了select');
    console.log(key, keyPath);
    // 跳转指定地址
    this.$router.push(key);
  }
}
