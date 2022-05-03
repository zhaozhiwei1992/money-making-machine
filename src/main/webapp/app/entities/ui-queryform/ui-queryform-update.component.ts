import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { IUiQueryform, UiQueryform } from '@/shared/model/ui-queryform.model';
import UiQueryformService from './ui-queryform.service';

const validations: any = {
  uiQueryform: {
    menuid: {},
    code: {},
    name: {},
    ordernum: {},
    issource: {},
    requirement: {},
    type: {},
    placeholder: {},
    config: {},
  },
};

@Component({
  validations,
})
export default class UiQueryformUpdate extends Vue {
  @Inject('uiQueryformService') private uiQueryformService: () => UiQueryformService;
  @Inject('alertService') private alertService: () => AlertService;

  public uiQueryform: IUiQueryform = new UiQueryform();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.uiQueryformId) {
        vm.retrieveUiQueryform(to.params.uiQueryformId);
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
    if (this.uiQueryform.id) {
      this.uiQueryformService()
        .update(this.uiQueryform)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.uiQueryform.updated', { param: param.id });
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
      this.uiQueryformService()
        .create(this.uiQueryform)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.uiQueryform.created', { param: param.id });
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

  public retrieveUiQueryform(uiQueryformId): void {
    this.uiQueryformService()
      .find(uiQueryformId)
      .then(res => {
        this.uiQueryform = res;
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
