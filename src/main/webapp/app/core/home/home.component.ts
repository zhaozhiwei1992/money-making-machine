import Component from 'vue-class-component';
import { Inject, Vue } from 'vue-property-decorator';
import LoginService from '@/account/login.service';
import statistics from '@/shared/components/statistics.vue';
import echartpies from '@/shared/components/echart-pies.vue';
import echartrecords from '@/shared/components/echart-records.vue';

@Component({
  // 这里@Component, 声明组建, 所以以前2.0里的参数都可以在这里塞, 如components, data等
  components: {
    statistics,
    echartpies,
    echartrecords,
  },
})
export default class Home extends Vue {
  @Inject('loginService')
  private loginService: () => LoginService;

  public openLogin(): void {
    this.loginService().openLogin((<any>this).$root);
  }

  public get authenticated(): boolean {
    return this.$store.getters.authenticated;
  }

  public get username(): string {
    return this.$store.getters.account?.login ?? '';
  }
}
