import { Component, Vue, Inject } from 'vue-property-decorator';

import { IUiComponent } from '@/shared/model/ui-component.model';
import UiComponentService from './ui-component.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class UiComponentDetails extends Vue {
  @Inject('uiComponentService') private uiComponentService: () => UiComponentService;
  @Inject('alertService') private alertService: () => AlertService;

  public uiComponent: IUiComponent = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.uiComponentId) {
        vm.retrieveUiComponent(to.params.uiComponentId);
      }
    });
  }

  public retrieveUiComponent(uiComponentId) {
    this.uiComponentService()
      .find(uiComponentId)
      .then(res => {
        this.uiComponent = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
