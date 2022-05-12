import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { IUiTable, UiTable } from '@/shared/model/ui-table.model';
import UiTableService from './ui-table.service';

const validations: any = {
  uiTable: {
    menuid: {},
    code: {},
    name: {},
    ordernum: {},
    issource: {},
    isedit: {},
    requirement: {},
    type: {},
    config: {},
  },
};

@Component({
  validations,
})
export default class UiTableUpdate extends Vue {
  @Inject('uiTableService') private uiTableService: () => UiTableService;
  @Inject('alertService') private alertService: () => AlertService;

  public uiTable: IUiTable = new UiTable();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.uiTableId) {
        vm.retrieveUiTable(to.params.uiTableId);
      } else {
        vm.uiTable.config = '{}';
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
    if (this.uiTable.id) {
      this.uiTableService()
        .update(this.uiTable)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.uiTable.updated', { param: param.id });
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
      this.uiTableService()
        .create(this.uiTable)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.uiTable.created', { param: param.id });
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

  public retrieveUiTable(uiTableId): void {
    this.uiTableService()
      .find(uiTableId)
      .then(res => {
        this.uiTable = res;
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
