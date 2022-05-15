import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { IDataPermissionsRel, DataPermissionsRel } from '@/shared/model/data-permissions-rel.model';
import DataPermissionsRelService from './data-permissions-rel.service';

const validations: any = {
  dataPermissionsRel: {
    ruleId: {},
    roleId: {},
    menuId: {},
  },
};

@Component({
  validations,
})
export default class DataPermissionsRelUpdate extends Vue {
  @Inject('dataPermissionsRelService') private dataPermissionsRelService: () => DataPermissionsRelService;
  @Inject('alertService') private alertService: () => AlertService;

  public dataPermissionsRel: IDataPermissionsRel = new DataPermissionsRel();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.dataPermissionsRelId) {
        vm.retrieveDataPermissionsRel(to.params.dataPermissionsRelId);
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
    if (this.dataPermissionsRel.id) {
      this.dataPermissionsRelService()
        .update(this.dataPermissionsRel)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.dataPermissionsRel.updated', { param: param.id });
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
      this.dataPermissionsRelService()
        .create(this.dataPermissionsRel)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.dataPermissionsRel.created', { param: param.id });
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

  public retrieveDataPermissionsRel(dataPermissionsRelId): void {
    this.dataPermissionsRelService()
      .find(dataPermissionsRelId)
      .then(res => {
        this.dataPermissionsRel = res;
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
