import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { ILeaveType, LeaveType } from '@/shared/model/leave-type.model';
import LeaveTypeService from './leave-type.service';

const validations: any = {
  leaveType: {
    code: {},
    name: {},
    parentid: {},
    enabled: {},
  },
};

@Component({
  validations,
})
export default class LeaveTypeUpdate extends Vue {
  @Inject('leaveTypeService') private leaveTypeService: () => LeaveTypeService;
  @Inject('alertService') private alertService: () => AlertService;

  public leaveType: ILeaveType = new LeaveType();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.leaveTypeId) {
        vm.retrieveLeaveType(to.params.leaveTypeId);
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
    if (this.leaveType.id) {
      this.leaveTypeService()
        .update(this.leaveType)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.leaveType.updated', { param: param.id });
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
      this.leaveTypeService()
        .create(this.leaveType)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.leaveType.created', { param: param.id });
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

  public retrieveLeaveType(leaveTypeId): void {
    this.leaveTypeService()
      .find(leaveTypeId)
      .then(res => {
        this.leaveType = res;
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
