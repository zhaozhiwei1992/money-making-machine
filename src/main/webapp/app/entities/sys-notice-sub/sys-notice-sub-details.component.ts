import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISysNoticeSub } from '@/shared/model/sys-notice-sub.model';
import SysNoticeSubService from './sys-notice-sub.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class SysNoticeSubDetails extends Vue {
  @Inject('sysNoticeSubService') private sysNoticeSubService: () => SysNoticeSubService;
  @Inject('alertService') private alertService: () => AlertService;

  public sysNoticeSub: ISysNoticeSub = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysNoticeSubId) {
        vm.retrieveSysNoticeSub(to.params.sysNoticeSubId);
      }
    });
  }

  public retrieveSysNoticeSub(sysNoticeSubId) {
    this.sysNoticeSubService()
      .find(sysNoticeSubId)
      .then(res => {
        this.sysNoticeSub = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
