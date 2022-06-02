import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { ISystemParam, SystemParam } from '@/shared/model/system-param.model';
import SystemParamService from './system-param.service';

const validations: any = {
  systemParam: {
    code: {},
    name: {},
    value: {},
    remark: {},
    enable: {},
  },
};

@Component({
  validations,
})
export default class SystemParamUpdate extends Vue {
  @Inject('systemParamService') private systemParamService: () => SystemParamService;
  @Inject('alertService') private alertService: () => AlertService;

  public systemParam: ISystemParam = new SystemParam();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.systemParamId) {
        vm.retrieveSystemParam(to.params.systemParamId);
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
    if (this.systemParam.id) {
      this.systemParamService()
        .update(this.systemParam)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.systemParam.updated', { param: param.id });
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
      this.systemParamService()
        .create(this.systemParam)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.systemParam.created', { param: param.id });
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

  public retrieveSystemParam(systemParamId): void {
    this.systemParamService()
      .find(systemParamId)
      .then(res => {
        this.systemParam = res;
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
