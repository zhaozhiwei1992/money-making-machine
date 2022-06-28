import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { ISysNotice, SysNotice } from '@/shared/model/sys-notice.model';
import SysNoticeService from './sys-notice.service';

const validations: any = {
  sysNotice: {
    title: {},
    content: {},
    creater: {},
    createTime: {},
    recType: {},
    receiver: {},
    urgent: {},
    notiType: {},
  },
};

@Component({
  validations,
})
export default class SysNoticeUpdate extends Vue {
  @Inject('sysNoticeService') private sysNoticeService: () => SysNoticeService;
  @Inject('alertService') private alertService: () => AlertService;

  public sysNotice: ISysNotice = new SysNotice();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysNoticeId) {
        vm.retrieveSysNotice(to.params.sysNoticeId);
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
    if (this.sysNotice.id) {
      this.sysNoticeService()
        .update(this.sysNotice)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.sysNotice.updated', { param: param.id });
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
      this.sysNoticeService()
        .create(this.sysNotice)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.sysNotice.created', { param: param.id });
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

  public retrieveSysNotice(sysNoticeId): void {
    this.sysNoticeService()
      .find(sysNoticeId)
      .then(res => {
        this.sysNotice = res;
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
