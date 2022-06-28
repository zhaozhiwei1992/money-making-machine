import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISysNotice } from '@/shared/model/sys-notice.model';
import SysNoticeService from './sys-notice.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class SysNoticeDetails extends Vue {
  @Inject('sysNoticeService') private sysNoticeService: () => SysNoticeService;
  @Inject('alertService') private alertService: () => AlertService;

  public sysNotice: ISysNotice = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysNoticeId) {
        vm.retrieveSysNotice(to.params.sysNoticeId);
      }
    });
  }

  public retrieveSysNotice(sysNoticeId) {
    this.sysNoticeService()
      .find(sysNoticeId)
      .then(res => {
        this.sysNotice = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
