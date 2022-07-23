import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { ISysNoticeSub, SysNoticeSub } from '@/shared/model/sys-notice-sub.model';
import SysNoticeSubService from './sys-notice-sub.service';

const validations: any = {
  sysNoticeSub: {
    sysNoticeId: {},
    recipientId: {},
    updateTime: {},
    status: {},
  },
};

@Component({
  validations,
})
export default class SysNoticeSubUpdate extends Vue {
  @Inject('sysNoticeSubService') private sysNoticeSubService: () => SysNoticeSubService;
  @Inject('alertService') private alertService: () => AlertService;

  public sysNoticeSub: ISysNoticeSub = new SysNoticeSub();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysNoticeSubId) {
        vm.retrieveSysNoticeSub(to.params.sysNoticeSubId);
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
    if (this.sysNoticeSub.id) {
      this.sysNoticeSubService()
        .update(this.sysNoticeSub)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.sysNoticeSub.updated', { param: param.id });
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
      this.sysNoticeSubService()
        .create(this.sysNoticeSub)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.sysNoticeSub.created', { param: param.id });
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

  public retrieveSysNoticeSub(sysNoticeSubId): void {
    this.sysNoticeSubService()
      .find(sysNoticeSubId)
      .then(res => {
        this.sysNoticeSub = res;
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
