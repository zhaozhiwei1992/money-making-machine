import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISysFormulaTab } from '@/shared/model/sys-formula-tab.model';
import SysFormulaTabService from './sys-formula-tab.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class SysFormulaTabDetails extends Vue {
  @Inject('sysFormulaTabService') private sysFormulaTabService: () => SysFormulaTabService;
  @Inject('alertService') private alertService: () => AlertService;

  public sysFormulaTab: ISysFormulaTab = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysFormulaTabId) {
        vm.retrieveSysFormulaTab(to.params.sysFormulaTabId);
      }
    });
  }

  public retrieveSysFormulaTab(sysFormulaTabId) {
    this.sysFormulaTabService()
      .find(sysFormulaTabId)
      .then(res => {
        this.sysFormulaTab = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
