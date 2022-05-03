import { Component, Vue, Inject } from 'vue-property-decorator';

import { IUiTable } from '@/shared/model/ui-table.model';
import UiTableService from './ui-table.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class UiTableDetails extends Vue {
  @Inject('uiTableService') private uiTableService: () => UiTableService;
  @Inject('alertService') private alertService: () => AlertService;

  public uiTable: IUiTable = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.uiTableId) {
        vm.retrieveUiTable(to.params.uiTableId);
      }
    });
  }

  public retrieveUiTable(uiTableId) {
    this.uiTableService()
      .find(uiTableId)
      .then(res => {
        this.uiTable = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
