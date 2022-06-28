import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { ISysCollectTab, SysCollectTab } from '@/shared/model/sys-collect-tab.model';
import SysCollectTabService from './sys-collect-tab.service';

const validations: any = {
  sysCollectTab: {
    tabCname: {},
    tabEname: {},
    config: {},
    enable: {},
  },
};

@Component({
  validations,
})
export default class SysCollectTabUpdate extends Vue {
  @Inject('sysCollectTabService') private sysCollectTabService: () => SysCollectTabService;
  @Inject('alertService') private alertService: () => AlertService;

  public sysCollectTab: ISysCollectTab = new SysCollectTab();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysCollectTabId) {
        vm.retrieveSysCollectTab(to.params.sysCollectTabId);
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
    if (this.sysCollectTab.id) {
      this.sysCollectTabService()
        .update(this.sysCollectTab)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.sysCollectTab.updated', { param: param.id });
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
      this.sysCollectTabService()
        .create(this.sysCollectTab)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.sysCollectTab.created', { param: param.id });
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

  public retrieveSysCollectTab(sysCollectTabId): void {
    this.sysCollectTabService()
      .find(sysCollectTabId)
      .then(res => {
        this.sysCollectTab = res;
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
