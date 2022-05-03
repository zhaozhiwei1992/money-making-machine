import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IUiQueryform } from '@/shared/model/ui-queryform.model';

import UiQueryformService from './ui-queryform.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class UiQueryform extends Vue {
  @Inject('uiQueryformService') private uiQueryformService: () => UiQueryformService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public uiQueryforms: IUiQueryform[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllUiQueryforms();
  }

  public clear(): void {
    this.retrieveAllUiQueryforms();
  }

  public retrieveAllUiQueryforms(): void {
    this.isFetching = true;
    this.uiQueryformService()
      .retrieve()
      .then(
        res => {
          this.uiQueryforms = res.data;
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

  public prepareRemove(instance: IUiQueryform): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeUiQueryform(): void {
    this.uiQueryformService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('moneyMakingMachineApp.uiQueryform.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllUiQueryforms();
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
