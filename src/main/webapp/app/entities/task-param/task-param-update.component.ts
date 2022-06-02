import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { ITaskParam, TaskParam } from '@/shared/model/task-param.model';
import TaskParamService from './task-param.service';

const validations: any = {
  taskParam: {
    name: {},
    cronExpression: {},
    startClass: {},
    enable: {},
  },
};

@Component({
  validations,
})
export default class TaskParamUpdate extends Vue {
  @Inject('taskParamService') private taskParamService: () => TaskParamService;
  @Inject('alertService') private alertService: () => AlertService;

  public taskParam: ITaskParam = new TaskParam();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskParamId) {
        vm.retrieveTaskParam(to.params.taskParamId);
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
    if (this.taskParam.id) {
      this.taskParamService()
        .update(this.taskParam)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.taskParam.updated', { param: param.id });
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
      this.taskParamService()
        .create(this.taskParam)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.taskParam.created', { param: param.id });
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

  public retrieveTaskParam(taskParamId): void {
    this.taskParamService()
      .find(taskParamId)
      .then(res => {
        this.taskParam = res;
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
