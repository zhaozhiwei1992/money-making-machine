import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ISysCollectTab } from '@/shared/model/sys-collect-tab.model';

import SysCollectTabService from './sys-collect-tab.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class SysCollectTab extends Vue {
  @Inject('sysCollectTabService') private sysCollectTabService: () => SysCollectTabService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public sysCollectTabs: ISysCollectTab[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllSysCollectTabs();
  }

  public clear(): void {
    this.retrieveAllSysCollectTabs();
  }

  public retrieveAllSysCollectTabs(): void {
    this.isFetching = true;
    this.sysCollectTabService()
      .retrieve()
      .then(
        res => {
          this.sysCollectTabs = res.data;
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

  public prepareRemove(instance: ISysCollectTab): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeSysCollectTab(): void {
    this.sysCollectTabService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('moneyMakingMachineApp.sysCollectTab.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllSysCollectTabs();
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
