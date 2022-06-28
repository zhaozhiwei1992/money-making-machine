import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISysCollectCol } from '@/shared/model/sys-collect-col.model';
import SysCollectColService from './sys-collect-col.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class SysCollectColDetails extends Vue {
  @Inject('sysCollectColService') private sysCollectColService: () => SysCollectColService;
  @Inject('alertService') private alertService: () => AlertService;

  public sysCollectCol: ISysCollectCol = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysCollectColId) {
        vm.retrieveSysCollectCol(to.params.sysCollectColId);
      }
    });
  }

  public retrieveSysCollectCol(sysCollectColId) {
    this.sysCollectColService()
      .find(sysCollectColId)
      .then(res => {
        this.sysCollectCol = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
