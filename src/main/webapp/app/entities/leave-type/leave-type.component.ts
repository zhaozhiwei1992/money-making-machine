import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ILeaveType } from '@/shared/model/leave-type.model';

import LeaveTypeService from './leave-type.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class LeaveType extends Vue {
  @Inject('leaveTypeService') private leaveTypeService: () => LeaveTypeService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public leaveTypes: ILeaveType[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllLeaveTypes();
  }

  public clear(): void {
    this.retrieveAllLeaveTypes();
  }

  public retrieveAllLeaveTypes(): void {
    this.isFetching = true;
    this.leaveTypeService()
      .retrieve()
      .then(
        res => {
          this.leaveTypes = res.data;
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

  public prepareRemove(instance: ILeaveType): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeLeaveType(): void {
    this.leaveTypeService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('moneyMakingMachineApp.leaveType.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllLeaveTypes();
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
