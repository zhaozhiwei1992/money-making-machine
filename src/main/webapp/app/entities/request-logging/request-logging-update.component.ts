import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { IRequestLogging, RequestLogging } from '@/shared/model/request-logging.model';
import RequestLoggingService from './request-logging.service';

const validations: any = {
  requestLogging: {
    traceId: {},
    loginName: {},
    requestURI: {},
    clientIP: {},
    currentTime: {},
  },
};

@Component({
  validations,
})
export default class RequestLoggingUpdate extends Vue {
  @Inject('requestLoggingService') private requestLoggingService: () => RequestLoggingService;
  @Inject('alertService') private alertService: () => AlertService;

  public requestLogging: IRequestLogging = new RequestLogging();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.requestLoggingId) {
        vm.retrieveRequestLogging(to.params.requestLoggingId);
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
    if (this.requestLogging.id) {
      this.requestLoggingService()
        .update(this.requestLogging)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.requestLogging.updated', { param: param.id });
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
      this.requestLoggingService()
        .create(this.requestLogging)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.requestLogging.created', { param: param.id });
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

  public retrieveRequestLogging(requestLoggingId): void {
    this.requestLoggingService()
      .find(requestLoggingId)
      .then(res => {
        this.requestLogging = res;
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
