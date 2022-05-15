import { Component, Vue, Inject } from 'vue-property-decorator';

import { IDataPermissionsRel } from '@/shared/model/data-permissions-rel.model';
import DataPermissionsRelService from './data-permissions-rel.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class DataPermissionsRelDetails extends Vue {
  @Inject('dataPermissionsRelService') private dataPermissionsRelService: () => DataPermissionsRelService;
  @Inject('alertService') private alertService: () => AlertService;

  public dataPermissionsRel: IDataPermissionsRel = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.dataPermissionsRelId) {
        vm.retrieveDataPermissionsRel(to.params.dataPermissionsRelId);
      }
    });
  }

  public retrieveDataPermissionsRel(dataPermissionsRelId) {
    this.dataPermissionsRelService()
      .find(dataPermissionsRelId)
      .then(res => {
        this.dataPermissionsRel = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
