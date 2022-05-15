import { Component, Vue, Inject } from 'vue-property-decorator';

import { IDataPermissionDetails } from '@/shared/model/data-permission-details.model';
import DataPermissionDetailsService from './data-permission-details.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class DataPermissionDetailsDetails extends Vue {
  @Inject('dataPermissionDetailsService') private dataPermissionDetailsService: () => DataPermissionDetailsService;
  @Inject('alertService') private alertService: () => AlertService;

  public dataPermissionDetails: IDataPermissionDetails = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.dataPermissionDetailsId) {
        vm.retrieveDataPermissionDetails(to.params.dataPermissionDetailsId);
      }
    });
  }

  public retrieveDataPermissionDetails(dataPermissionDetailsId) {
    this.dataPermissionDetailsService()
      .find(dataPermissionDetailsId)
      .then(res => {
        this.dataPermissionDetails = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
