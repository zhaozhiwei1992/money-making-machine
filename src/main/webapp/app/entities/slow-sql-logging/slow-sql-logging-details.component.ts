import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISlowSqlLogging } from '@/shared/model/slow-sql-logging.model';
import SlowSqlLoggingService from './slow-sql-logging.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class SlowSqlLoggingDetails extends Vue {
  @Inject('slowSqlLoggingService') private slowSqlLoggingService: () => SlowSqlLoggingService;
  @Inject('alertService') private alertService: () => AlertService;

  public slowSqlLogging: ISlowSqlLogging = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.slowSqlLoggingId) {
        vm.retrieveSlowSqlLogging(to.params.slowSqlLoggingId);
      }
    });
  }

  public retrieveSlowSqlLogging(slowSqlLoggingId) {
    this.slowSqlLoggingService()
      .find(slowSqlLoggingId)
      .then(res => {
        this.slowSqlLogging = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
