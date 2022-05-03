import { Component, Vue, Inject } from 'vue-property-decorator';

import { IUiToolButton } from '@/shared/model/ui-tool-button.model';
import UiToolButtonService from './ui-tool-button.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class UiToolButtonDetails extends Vue {
  @Inject('uiToolButtonService') private uiToolButtonService: () => UiToolButtonService;
  @Inject('alertService') private alertService: () => AlertService;

  public uiToolButton: IUiToolButton = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.uiToolButtonId) {
        vm.retrieveUiToolButton(to.params.uiToolButtonId);
      }
    });
  }

  public retrieveUiToolButton(uiToolButtonId) {
    this.uiToolButtonService()
      .find(uiToolButtonId)
      .then(res => {
        this.uiToolButton = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
