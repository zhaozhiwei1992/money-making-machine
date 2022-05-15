import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { IDataPermissionDetails, DataPermissionDetails } from '@/shared/model/data-permission-details.model';
import DataPermissionDetailsService from './data-permission-details.service';

const validations: any = {
  dataPermissionDetails: {
    ruleId: {},
    leftBracket: {},
    column: {},
    op: {},
    value: {},
    rightBracket: {},
    ordernum: {},
    logicalRel: {},
  },
};

@Component({
  validations,
})
export default class DataPermissionDetailsUpdate extends Vue {
  @Inject('dataPermissionDetailsService') private dataPermissionDetailsService: () => DataPermissionDetailsService;
  @Inject('alertService') private alertService: () => AlertService;

  public dataPermissionDetails: IDataPermissionDetails = new DataPermissionDetails();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.dataPermissionDetailsId) {
        vm.retrieveDataPermissionDetails(to.params.dataPermissionDetailsId);
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
    if (this.dataPermissionDetails.id) {
      this.dataPermissionDetailsService()
        .update(this.dataPermissionDetails)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.dataPermissionDetails.updated', { param: param.id });
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
      this.dataPermissionDetailsService()
        .create(this.dataPermissionDetails)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.dataPermissionDetails.created', { param: param.id });
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

  public retrieveDataPermissionDetails(dataPermissionDetailsId): void {
    this.dataPermissionDetailsService()
      .find(dataPermissionDetailsId)
      .then(res => {
        this.dataPermissionDetails = res;
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
