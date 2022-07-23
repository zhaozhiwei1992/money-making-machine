import Vue from 'vue';
import Component from 'vue-class-component';
import Ribbon from '@/core/ribbon/ribbon.vue';
import JhiFooter from '@/core/jhi-footer/jhi-footer.vue';
import JhiNavbar from '@/core/jhi-navbar/jhi-navbar.vue';
import LoginForm from '@/account/login-form/login-form.vue';

import '@/shared/config/dayjs';

import axios from 'axios';

const menuBaseApiUrl = 'api/menus';

const sysNoticeSubBaseApiUrl = 'api/sys-notice';

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

  // 创建一个定时器
  public timer = null;

  // 界面初始化加载数据
  public mounted(): void {
    this.findMenuTree();

    // 通知公告定时器
    this.initNoticeTimer();
  }

  public authenticated(): boolean {
    return this.$store.getters.authenticated;
  }

  public get username(): string {
    return this.$store.getters.account?.login ?? '';
  }

  public initNoticeTimer(): void {
    this.timer = setInterval(() => {
      if (this.authenticated()) {
        console.log('通知公告定时拉取, 登录用户', this.username);
        axios.get(sysNoticeSubBaseApiUrl + '/login/' + this.username).then(res => {
          const response = res.data;
          console.log('通知公告信息', response);
          if (response.length > 0) {
            response.forEach(element => {
              // 根据返回信息, 弹出信息notice
              const h = this.$createElement;

              this.$notify({
                title: element.title,
                message: h('i', { style: 'color: teal' }, element.content),
              });
            });
          }
        });
      }
      // 10秒请求一次
    }, 1000 * 10);
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
