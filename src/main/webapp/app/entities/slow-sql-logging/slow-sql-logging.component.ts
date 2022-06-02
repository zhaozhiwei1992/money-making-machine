import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ISlowSqlLogging } from '@/shared/model/slow-sql-logging.model';

import SlowSqlLoggingService from './slow-sql-logging.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class SlowSqlLogging extends Vue {
  @Inject('slowSqlLoggingService') private slowSqlLoggingService: () => SlowSqlLoggingService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;
  public itemsPerPage = 10;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = true;
  public totalItems = 0;
  public condition = '';

  public slowSqlLoggings: ISlowSqlLogging[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllSlowSqlLoggings();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllSlowSqlLoggings();
  }

  public retrieveAllSlowSqlLoggings(): void {
    this.isFetching = true;
    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.slowSqlLoggingService()
      .retrieve(paginationQuery, this.condition)
      .then(
        res => {
          this.slowSqlLoggings = res.data;
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

  public prepareRemove(instance: ISlowSqlLogging): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeSlowSqlLogging(): void {
    this.slowSqlLoggingService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('moneyMakingMachineApp.slowSqlLogging.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllSlowSqlLoggings();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public sort(): Array<any> {
    const result = [this.propOrder + ',' + (this.reverse ? 'desc' : 'asc')];
    if (this.propOrder !== 'id') {
      result.push('id');
    }
    return result;
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public transition(): void {
    this.retrieveAllSlowSqlLoggings();
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
