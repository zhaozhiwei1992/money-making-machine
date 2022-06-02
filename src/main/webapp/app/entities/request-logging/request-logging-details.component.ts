import { Component, Vue, Inject } from 'vue-property-decorator';

import { IRequestLogging } from '@/shared/model/request-logging.model';
import RequestLoggingService from './request-logging.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class RequestLoggingDetails extends Vue {
  @Inject('requestLoggingService') private requestLoggingService: () => RequestLoggingService;
  @Inject('alertService') private alertService: () => AlertService;

  public requestLogging: IRequestLogging = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.requestLoggingId) {
        vm.retrieveRequestLogging(to.params.requestLoggingId);
      }
    });
  }

  public retrieveRequestLogging(requestLoggingId) {
    this.requestLoggingService()
      .find(requestLoggingId)
      .then(res => {
        this.requestLogging = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
