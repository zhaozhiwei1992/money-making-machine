import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISystemParam } from '@/shared/model/system-param.model';
import SystemParamService from './system-param.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class SystemParamDetails extends Vue {
  @Inject('systemParamService') private systemParamService: () => SystemParamService;
  @Inject('alertService') private alertService: () => AlertService;

  public systemParam: ISystemParam = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.systemParamId) {
        vm.retrieveSystemParam(to.params.systemParamId);
      }
    });
  }

  public retrieveSystemParam(systemParamId) {
    this.systemParamService()
      .find(systemParamId)
      .then(res => {
        this.systemParam = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
