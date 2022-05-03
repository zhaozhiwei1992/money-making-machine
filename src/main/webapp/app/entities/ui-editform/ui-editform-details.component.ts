import { Component, Vue, Inject } from 'vue-property-decorator';

import { IUiEditform } from '@/shared/model/ui-editform.model';
import UiEditformService from './ui-editform.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class UiEditformDetails extends Vue {
  @Inject('uiEditformService') private uiEditformService: () => UiEditformService;
  @Inject('alertService') private alertService: () => AlertService;

  public uiEditform: IUiEditform = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.uiEditformId) {
        vm.retrieveUiEditform(to.params.uiEditformId);
      }
    });
  }

  public retrieveUiEditform(uiEditformId) {
    this.uiEditformService()
      .find(uiEditformId)
      .then(res => {
        this.uiEditform = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
