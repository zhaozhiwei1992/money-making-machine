import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { IUiToolButton, UiToolButton } from '@/shared/model/ui-tool-button.model';
import UiToolButtonService from './ui-tool-button.service';

const validations: any = {
  uiToolButton: {
    menuid: {},
    code: {},
    name: {},
    ordernum: {},
    action: {},
    config: {},
  },
};

@Component({
  validations,
})
export default class UiToolButtonUpdate extends Vue {
  @Inject('uiToolButtonService') private uiToolButtonService: () => UiToolButtonService;
  @Inject('alertService') private alertService: () => AlertService;

  public uiToolButton: IUiToolButton = new UiToolButton();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.uiToolButtonId) {
        vm.retrieveUiToolButton(to.params.uiToolButtonId);
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
    if (this.uiToolButton.id) {
      this.uiToolButtonService()
        .update(this.uiToolButton)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.uiToolButton.updated', { param: param.id });
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
      this.uiToolButtonService()
        .create(this.uiToolButton)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.uiToolButton.created', { param: param.id });
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

  public retrieveUiToolButton(uiToolButtonId): void {
    this.uiToolButtonService()
      .find(uiToolButtonId)
      .then(res => {
        this.uiToolButton = res;
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
