import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IUiTab } from '@/shared/model/ui-tab.model';

import UiTabService from './ui-tab.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class UiTab extends Vue {
  @Inject('uiTabService') private uiTabService: () => UiTabService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public uiTabs: IUiTab[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllUiTabs();
  }

  public clear(): void {
    this.retrieveAllUiTabs();
  }

  public retrieveAllUiTabs(): void {
    this.isFetching = true;
    this.uiTabService()
      .retrieve()
      .then(
        res => {
          this.uiTabs = res.data;
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

  public prepareRemove(instance: IUiTab): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeUiTab(): void {
    this.uiTabService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('moneyMakingMachineApp.uiTab.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllUiTabs();
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
