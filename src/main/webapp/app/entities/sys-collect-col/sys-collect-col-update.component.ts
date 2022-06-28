import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { ISysCollectCol, SysCollectCol } from '@/shared/model/sys-collect-col.model';
import SysCollectColService from './sys-collect-col.service';

const validations: any = {
  sysCollectCol: {
    colCname: {},
    colEname: {},
    tabId: {},
    orderNum: {},
    source: {},
    isEdit: {},
    requirement: {},
    type: {},
    config: {},
  },
};

@Component({
  validations,
})
export default class SysCollectColUpdate extends Vue {
  @Inject('sysCollectColService') private sysCollectColService: () => SysCollectColService;
  @Inject('alertService') private alertService: () => AlertService;

  public sysCollectCol: ISysCollectCol = new SysCollectCol();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysCollectColId) {
        vm.retrieveSysCollectCol(to.params.sysCollectColId);
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
    if (this.sysCollectCol.id) {
      this.sysCollectColService()
        .update(this.sysCollectCol)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.sysCollectCol.updated', { param: param.id });
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
      this.sysCollectColService()
        .create(this.sysCollectCol)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.sysCollectCol.created', { param: param.id });
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

  public retrieveSysCollectCol(sysCollectColId): void {
    this.sysCollectColService()
      .find(sysCollectColId)
      .then(res => {
        this.sysCollectCol = res;
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
