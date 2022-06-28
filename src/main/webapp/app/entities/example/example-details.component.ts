import { Component, Vue, Inject } from 'vue-property-decorator';

import { IExample } from '@/shared/model/example.model';
import ExampleService from './example.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ExampleDetails extends Vue {
  @Inject('exampleService') private exampleService: () => ExampleService;
  @Inject('alertService') private alertService: () => AlertService;

  public example: IExample = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.exampleId) {
        vm.retrieveExample(to.params.exampleId);
      }
    });
  }

  public retrieveExample(exampleId) {
    this.exampleService()
      .find(exampleId)
      .then(res => {
        this.example = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
