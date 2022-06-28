import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISysCollectTab } from '@/shared/model/sys-collect-tab.model';
import SysCollectTabService from './sys-collect-tab.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class SysCollectTabDetails extends Vue {
  @Inject('sysCollectTabService') private sysCollectTabService: () => SysCollectTabService;
  @Inject('alertService') private alertService: () => AlertService;

  public sysCollectTab: ISysCollectTab = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysCollectTabId) {
        vm.retrieveSysCollectTab(to.params.sysCollectTabId);
      }
    });
  }

  public retrieveSysCollectTab(sysCollectTabId) {
    this.sysCollectTabService()
      .find(sysCollectTabId)
      .then(res => {
        this.sysCollectTab = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
