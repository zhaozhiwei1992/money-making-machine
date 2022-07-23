import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ISysNoticeSub } from '@/shared/model/sys-notice-sub.model';

import SysNoticeSubService from './sys-notice-sub.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class SysNoticeSub extends Vue {
  @Inject('sysNoticeSubService') private sysNoticeSubService: () => SysNoticeSubService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public sysNoticeSubs: ISysNoticeSub[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllSysNoticeSubs();
  }

  public clear(): void {
    this.retrieveAllSysNoticeSubs();
  }

  public retrieveAllSysNoticeSubs(): void {
    this.isFetching = true;
    this.sysNoticeSubService()
      .retrieve()
      .then(
        res => {
          this.sysNoticeSubs = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: ISysNoticeSub): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeSysNoticeSub(): void {
    this.sysNoticeSubService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('moneyMakingMachineApp.sysNoticeSub.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllSysNoticeSubs();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
