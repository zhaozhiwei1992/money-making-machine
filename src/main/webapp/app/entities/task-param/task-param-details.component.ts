import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITaskParam } from '@/shared/model/task-param.model';
import TaskParamService from './task-param.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class TaskParamDetails extends Vue {
  @Inject('taskParamService') private taskParamService: () => TaskParamService;
  @Inject('alertService') private alertService: () => AlertService;

  public taskParam: ITaskParam = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskParamId) {
        vm.retrieveTaskParam(to.params.taskParamId);
      }
    });
  }

  public retrieveTaskParam(taskParamId) {
    this.taskParamService()
      .find(taskParamId)
      .then(res => {
        this.taskParam = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
