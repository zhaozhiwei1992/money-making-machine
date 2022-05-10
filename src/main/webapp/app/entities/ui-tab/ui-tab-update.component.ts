import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { IUiTab, UiTab } from '@/shared/model/ui-tab.model';
import UiTabService from './ui-tab.service';

const validations: any = {
  uiTab: {
    menuid: {},
    code: {},
    name: {},
    ordernum: {},
    config: {},
  },
};

@Component({
  validations,
})
export default class UiTabUpdate extends Vue {
  @Inject('uiTabService') private uiTabService: () => UiTabService;
  @Inject('alertService') private alertService: () => AlertService;

  public uiTab: IUiTab = new UiTab();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.uiTabId) {
        vm.retrieveUiTab(to.params.uiTabId);
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
    if (this.uiTab.id) {
      this.uiTabService()
        .update(this.uiTab)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.uiTab.updated', { param: param.id });
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
      this.uiTabService()
        .create(this.uiTab)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.uiTab.created', { param: param.id });
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

  public retrieveUiTab(uiTabId): void {
    this.uiTabService()
      .find(uiTabId)
      .then(res => {
        this.uiTab = res;
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
