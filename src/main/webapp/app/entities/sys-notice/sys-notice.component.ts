import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ISysNotice } from '@/shared/model/sys-notice.model';

import SysNoticeService from './sys-notice.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class SysNotice extends Vue {
  @Inject('sysNoticeService') private sysNoticeService: () => SysNoticeService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public sysNotices: ISysNotice[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllSysNotices();
  }

  public clear(): void {
    this.retrieveAllSysNotices();
  }

  public retrieveAllSysNotices(): void {
    this.isFetching = true;
    this.sysNoticeService()
      .retrieve()
      .then(
        res => {
          this.sysNotices = res.data;
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

  public prepareRemove(instance: ISysNotice): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeSysNotice(): void {
    this.sysNoticeService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('moneyMakingMachineApp.sysNotice.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllSysNotices();
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
