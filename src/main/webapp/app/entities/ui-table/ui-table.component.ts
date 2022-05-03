import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IUiTable } from '@/shared/model/ui-table.model';

import UiTableService from './ui-table.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class UiTable extends Vue {
  @Inject('uiTableService') private uiTableService: () => UiTableService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public uiTables: IUiTable[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllUiTables();
  }

  public clear(): void {
    this.retrieveAllUiTables();
  }

  public retrieveAllUiTables(): void {
    this.isFetching = true;
    this.uiTableService()
      .retrieve()
      .then(
        res => {
          this.uiTables = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IUiTable): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeUiTable(): void {
    this.uiTableService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('moneyMakingMachineApp.uiTable.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllUiTables();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
