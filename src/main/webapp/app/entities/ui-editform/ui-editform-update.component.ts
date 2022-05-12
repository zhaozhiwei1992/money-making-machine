import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { IUiEditform, UiEditform } from '@/shared/model/ui-editform.model';
import UiEditformService from './ui-editform.service';

const validations: any = {
  uiEditform: {
    menuid: {},
    code: {},
    name: {},
    ordernum: {},
    issource: {},
    isedit: {},
    requirement: {},
    type: {},
    placeholder: {},
    config: {},
  },
};

@Component({
  validations,
})
export default class UiEditformUpdate extends Vue {
  @Inject('uiEditformService') private uiEditformService: () => UiEditformService;
  @Inject('alertService') private alertService: () => AlertService;

  public uiEditform: IUiEditform = new UiEditform();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.uiEditformId) {
        vm.retrieveUiEditform(to.params.uiEditformId);
      } else {
        vm.uiEditform.config = '{}';
        vm.uiEditform.isedit = true;
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
    if (this.uiEditform.id) {
      this.uiEditformService()
        .update(this.uiEditform)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.uiEditform.updated', { param: param.id });
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
      this.uiEditformService()
        .create(this.uiEditform)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.uiEditform.created', { param: param.id });
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

  public retrieveUiEditform(uiEditformId): void {
    this.uiEditformService()
      .find(uiEditformId)
      .then(res => {
        console.log('编辑区输出', res);
        this.uiEditform = res;
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
