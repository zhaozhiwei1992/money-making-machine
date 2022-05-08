import { Component, Vue, Inject } from 'vue-property-decorator';

import { ILeaveType } from '@/shared/model/leave-type.model';
import LeaveTypeService from './leave-type.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class LeaveTypeDetails extends Vue {
  @Inject('leaveTypeService') private leaveTypeService: () => LeaveTypeService;
  @Inject('alertService') private alertService: () => AlertService;

  public leaveType: ILeaveType = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.leaveTypeId) {
        vm.retrieveLeaveType(to.params.leaveTypeId);
      }
    });
  }

  public retrieveLeaveType(leaveTypeId) {
    this.leaveTypeService()
      .find(leaveTypeId)
      .then(res => {
        this.leaveType = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
