import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IUiToolButton } from '@/shared/model/ui-tool-button.model';

import UiToolButtonService from './ui-tool-button.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class UiToolButton extends Vue {
  @Inject('uiToolButtonService') private uiToolButtonService: () => UiToolButtonService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;
  public itemsPerPage = 5;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'menuid';
  public reverse = false;
  public totalItems = 0;
  // 默认菜单0
  public menuid = 0;

  public uiToolButtons: IUiToolButton[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllUiToolButtons();
  }

  public clear(): void {
    this.retrieveAllUiToolButtons();
  }

  public retrieveAllUiToolButtons(): void {
    this.isFetching = true;
    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.uiToolButtonService()
      .retrieve(paginationQuery, this.menuid)
      .then(
        res => {
          this.uiToolButtons = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
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

  public prepareRemove(instance: IUiToolButton): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeUiToolButton(): void {
    this.uiToolButtonService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('moneyMakingMachineApp.uiToolButton.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllUiToolButtons();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  // 根据菜单和 ordernum排序
  public sort(): Array<any> {
    const result = [this.propOrder + ',' + (this.reverse ? 'desc' : 'asc')];
    result.push('ordernum,' + (this.reverse ? 'desc' : 'asc'));
    return result;
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public transition(): void {
    this.retrieveAllUiToolButtons();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
