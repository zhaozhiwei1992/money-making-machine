import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { ILeaveSlip, LeaveSlip } from '@/shared/model/leave-slip.model';
import LeaveSlipService from './leave-slip.service';

const validations: any = {
  leaveSlip: {
    type: {},
    startTime: {},
    endTime: {},
    reason: {},
    file: {},
    superior: {},
    wfstatus: {},
    leavePerson: {},
  },
};

@Component({
  validations,
})
export default class LeaveSlipUpdate extends Vue {
  @Inject('leaveSlipService') private leaveSlipService: () => LeaveSlipService;
  @Inject('alertService') private alertService: () => AlertService;

  public leaveSlip: ILeaveSlip = new LeaveSlip();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.leaveSlipId) {
        vm.retrieveLeaveSlip(to.params.leaveSlipId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.leaveSlip.id) {
      this.leaveSlipService()
        .update(this.leaveSlip)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.leaveSlip.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.leaveSlipService()
        .create(this.leaveSlip)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.leaveSlip.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public retrieveLeaveSlip(leaveSlipId): void {
    this.leaveSlipService()
      .find(leaveSlipId)
      .then(res => {
        this.leaveSlip = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
