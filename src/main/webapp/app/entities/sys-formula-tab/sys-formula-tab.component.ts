import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ISysFormulaTab } from '@/shared/model/sys-formula-tab.model';

import SysFormulaTabService from './sys-formula-tab.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class SysFormulaTab extends Vue {
  @Inject('sysFormulaTabService') private sysFormulaTabService: () => SysFormulaTabService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public sysFormulaTabs: ISysFormulaTab[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllSysFormulaTabs();
  }

  public clear(): void {
    this.retrieveAllSysFormulaTabs();
  }

  public retrieveAllSysFormulaTabs(): void {
    this.isFetching = true;
    this.sysFormulaTabService()
      .retrieve()
      .then(
        res => {
          this.sysFormulaTabs = res.data;
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

  public prepareRemove(instance: ISysFormulaTab): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeSysFormulaTab(): void {
    this.sysFormulaTabService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('moneyMakingMachineApp.sysFormulaTab.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllSysFormulaTabs();
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
