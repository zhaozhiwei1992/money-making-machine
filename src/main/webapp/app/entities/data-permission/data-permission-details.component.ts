import { Component, Vue, Inject } from 'vue-property-decorator';

import { IDataPermission } from '@/shared/model/data-permission.model';
import DataPermissionService from './data-permission.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class DataPermissionDetails extends Vue {
  @Inject('dataPermissionService') private dataPermissionService: () => DataPermissionService;
  @Inject('alertService') private alertService: () => AlertService;

  public dataPermission: IDataPermission = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.dataPermissionId) {
        vm.retrieveDataPermission(to.params.dataPermissionId);
      }
    });
  }

  public retrieveDataPermission(dataPermissionId) {
    this.dataPermissionService()
      .find(dataPermissionId)
      .then(res => {
        this.dataPermission = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
