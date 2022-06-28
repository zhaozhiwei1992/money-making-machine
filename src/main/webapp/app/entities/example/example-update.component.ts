import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { IExample, Example } from '@/shared/model/example.model';
import ExampleService from './example.service';

const validations: any = {
  example: {
    name: {},
    age: {},
    sex: {},
  },
};

@Component({
  validations,
})
export default class ExampleUpdate extends Vue {
  @Inject('exampleService') private exampleService: () => ExampleService;
  @Inject('alertService') private alertService: () => AlertService;

  public example: IExample = new Example();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.exampleId) {
        vm.retrieveExample(to.params.exampleId);
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
    if (this.example.id) {
      this.exampleService()
        .update(this.example)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.example.updated', { param: param.id });
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
      this.exampleService()
        .create(this.example)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.example.created', { param: param.id });
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

  public retrieveExample(exampleId): void {
    this.exampleService()
      .find(exampleId)
      .then(res => {
        this.example = res;
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
