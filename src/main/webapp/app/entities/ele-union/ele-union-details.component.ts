import { Component, Vue, Inject } from 'vue-property-decorator';

import { IEleUnion } from '@/shared/model/ele-union.model';
import EleUnionService from './ele-union.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class EleUnionDetails extends Vue {
  @Inject('eleUnionService') private eleUnionService: () => EleUnionService;
  @Inject('alertService') private alertService: () => AlertService;

  public eleUnion: IEleUnion = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.eleUnionId) {
        vm.retrieveEleUnion(to.params.eleUnionId);
      }
    });
  }

  public retrieveEleUnion(eleUnionId) {
    this.eleUnionService()
      .find(eleUnionId)
      .then(res => {
        this.eleUnion = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
