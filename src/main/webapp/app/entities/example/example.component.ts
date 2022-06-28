import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IExample } from '@/shared/model/example.model';

import ExampleService from './example.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Example extends Vue {
  @Inject('exampleService') private exampleService: () => ExampleService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public examples: IExample[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllExamples();
  }

  public clear(): void {
    this.retrieveAllExamples();
  }

  public retrieveAllExamples(): void {
    this.isFetching = true;
    this.exampleService()
      .retrieve()
      .then(
        res => {
          this.examples = res.data;
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

  public prepareRemove(instance: IExample): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeExample(): void {
    this.exampleService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('moneyMakingMachineApp.example.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllExamples();
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
