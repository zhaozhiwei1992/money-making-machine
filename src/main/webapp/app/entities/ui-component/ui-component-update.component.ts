import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { IUiComponent, UiComponent } from '@/shared/model/ui-component.model';
import UiComponentService from './ui-component.service';

const validations: any = {
  uiComponent: {
    menuid: {},
    ordernum: {},
    componentid: {},
    config: {},
  },
};

@Component({
  validations,
})
export default class UiComponentUpdate extends Vue {
  @Inject('uiComponentService') private uiComponentService: () => UiComponentService;
  @Inject('alertService') private alertService: () => AlertService;

  public uiComponent: IUiComponent = new UiComponent();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.uiComponentId) {
        vm.retrieveUiComponent(to.params.uiComponentId);
      } else {
        vm.uiComponent.config = '{}';
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
    if (this.uiComponent.id) {
      this.uiComponentService()
        .update(this.uiComponent)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.uiComponent.updated', { param: param.id });
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
      this.uiComponentService()
        .create(this.uiComponent)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.uiComponent.created', { param: param.id });
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

  public retrieveUiComponent(uiComponentId): void {
    this.uiComponentService()
      .find(uiComponentId)
      .then(res => {
        this.uiComponent = res;
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
