import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ILeaveSlip } from '@/shared/model/leave-slip.model';

import LeaveSlipService from './leave-slip.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class LeaveSlip extends Vue {
  @Inject('leaveSlipService') private leaveSlipService: () => LeaveSlipService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public leaveSlips: ILeaveSlip[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllLeaveSlips();
  }

  public clear(): void {
    this.retrieveAllLeaveSlips();
  }

  public retrieveAllLeaveSlips(): void {
    this.isFetching = true;
    this.leaveSlipService()
      .retrieve()
      .then(
        res => {
          this.leaveSlips = res.data;
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

  public prepareRemove(instance: ILeaveSlip): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeLeaveSlip(): void {
    this.leaveSlipService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('moneyMakingMachineApp.leaveSlip.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllLeaveSlips();
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
