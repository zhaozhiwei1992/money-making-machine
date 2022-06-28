import { Component, Vue, Inject } from 'vue-property-decorator';

import { ILeaveSlip } from '@/shared/model/leave-slip.model';
import LeaveSlipService from './leave-slip.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class LeaveSlipDetails extends Vue {
  @Inject('leaveSlipService') private leaveSlipService: () => LeaveSlipService;
  @Inject('alertService') private alertService: () => AlertService;

  public leaveSlip: ILeaveSlip = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.leaveSlipId) {
        vm.retrieveLeaveSlip(to.params.leaveSlipId);
      }
    });
  }

  public retrieveLeaveSlip(leaveSlipId) {
    this.leaveSlipService()
      .find(leaveSlipId)
      .then(res => {
        this.leaveSlip = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
