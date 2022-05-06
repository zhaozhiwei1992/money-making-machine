import Vue from 'vue';
import Component from 'vue-class-component';
import Ribbon from '@/core/ribbon/ribbon.vue';
import JhiFooter from '@/core/jhi-footer/jhi-footer.vue';
import JhiNavbar from '@/core/jhi-navbar/jhi-navbar.vue';
import LoginForm from '@/account/login-form/login-form.vue';

import '@/shared/config/dayjs';

import axios from 'axios';

const menuBaseApiUrl = 'api/menus';

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
  public homeMenu = [{ index: '/', title: '导航1', children: [] }];

  // 界面初始化加载数据
  public mounted(): void {
    this.findMenuTree();
  }

  public findMenuTree(): void {
    axios.get(menuBaseApiUrl + '/tree').then(res => {
      const response = res.data;
      console.log('菜单树: {}', response);
      this.homeMenu = response;
    });
  }

  // el-menu选中会传入链接信息 key === index
  public handleSelect(key, keyPath): void {
    console.log('点击了select');
    console.log(key, keyPath);
    // 跳转指定地址
    this.$router.push(key);
  }
}
