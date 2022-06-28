import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { ISysFormulaTab, SysFormulaTab } from '@/shared/model/sys-formula-tab.model';
import SysFormulaTabService from './sys-formula-tab.service';

const validations: any = {
  sysFormulaTab: {
    tabEname: {},
    colEname: {},
    calFormula: {},
    calFormulaDes: {},
    roundType: {},
    weight: {},
    enable: {},
  },
};

@Component({
  validations,
})
export default class SysFormulaTabUpdate extends Vue {
  @Inject('sysFormulaTabService') private sysFormulaTabService: () => SysFormulaTabService;
  @Inject('alertService') private alertService: () => AlertService;

  public sysFormulaTab: ISysFormulaTab = new SysFormulaTab();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysFormulaTabId) {
        vm.retrieveSysFormulaTab(to.params.sysFormulaTabId);
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
    if (this.sysFormulaTab.id) {
      this.sysFormulaTabService()
        .update(this.sysFormulaTab)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.sysFormulaTab.updated', { param: param.id });
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
      this.sysFormulaTabService()
        .create(this.sysFormulaTab)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.sysFormulaTab.created', { param: param.id });
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

  public retrieveSysFormulaTab(sysFormulaTabId): void {
    this.sysFormulaTabService()
      .find(sysFormulaTabId)
      .then(res => {
        this.sysFormulaTab = res;
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
