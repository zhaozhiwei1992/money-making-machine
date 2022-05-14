import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { IDataPermission, DataPermission } from '@/shared/model/data-permission.model';
import DataPermissionService from './data-permission.service';

const validations: any = {
  dataPermission: {
    code: {},
    name: {},
    ruleSql: {},
  },
};

@Component({
  validations,
})
export default class DataPermissionUpdate extends Vue {
  @Inject('dataPermissionService') private dataPermissionService: () => DataPermissionService;
  @Inject('alertService') private alertService: () => AlertService;

  public dataPermission: IDataPermission = new DataPermission();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.dataPermissionId) {
        vm.retrieveDataPermission(to.params.dataPermissionId);
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
    if (this.dataPermission.id) {
      this.dataPermissionService()
        .update(this.dataPermission)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.dataPermission.updated', { param: param.id });
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
      this.dataPermissionService()
        .create(this.dataPermission)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.dataPermission.created', { param: param.id });
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

  public retrieveDataPermission(dataPermissionId): void {
    this.dataPermissionService()
      .find(dataPermissionId)
      .then(res => {
        this.dataPermission = res;
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
