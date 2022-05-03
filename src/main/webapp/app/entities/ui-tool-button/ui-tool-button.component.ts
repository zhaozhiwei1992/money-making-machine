import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IUiToolButton } from '@/shared/model/ui-tool-button.model';

import UiToolButtonService from './ui-tool-button.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class UiToolButton extends Vue {
  @Inject('uiToolButtonService') private uiToolButtonService: () => UiToolButtonService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public uiToolButtons: IUiToolButton[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllUiToolButtons();
  }

  public clear(): void {
    this.retrieveAllUiToolButtons();
  }

  public retrieveAllUiToolButtons(): void {
    this.isFetching = true;
    this.uiToolButtonService()
      .retrieve()
      .then(
        res => {
          this.uiToolButtons = res.data;
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

  public prepareRemove(instance: IUiToolButton): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeUiToolButton(): void {
    this.uiToolButtonService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('moneyMakingMachineApp.uiToolButton.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllUiToolButtons();
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
