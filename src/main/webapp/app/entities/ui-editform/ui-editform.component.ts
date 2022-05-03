import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IUiEditform } from '@/shared/model/ui-editform.model';

import UiEditformService from './ui-editform.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class UiEditform extends Vue {
  @Inject('uiEditformService') private uiEditformService: () => UiEditformService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public uiEditforms: IUiEditform[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllUiEditforms();
  }

  public clear(): void {
    this.retrieveAllUiEditforms();
  }

  public retrieveAllUiEditforms(): void {
    this.isFetching = true;
    this.uiEditformService()
      .retrieve()
      .then(
        res => {
          this.uiEditforms = res.data;
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

  public prepareRemove(instance: IUiEditform): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeUiEditform(): void {
    this.uiEditformService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('moneyMakingMachineApp.uiEditform.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllUiEditforms();
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
