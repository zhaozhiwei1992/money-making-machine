import { Component, Vue, Inject } from 'vue-property-decorator';

import { IUiQueryform } from '@/shared/model/ui-queryform.model';
import UiQueryformService from './ui-queryform.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class UiQueryformDetails extends Vue {
  @Inject('uiQueryformService') private uiQueryformService: () => UiQueryformService;
  @Inject('alertService') private alertService: () => AlertService;

  public uiQueryform: IUiQueryform = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.uiQueryformId) {
        vm.retrieveUiQueryform(to.params.uiQueryformId);
      }
    });
  }

  public retrieveUiQueryform(uiQueryformId) {
    this.uiQueryformService()
      .find(uiQueryformId)
      .then(res => {
        this.uiQueryform = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
