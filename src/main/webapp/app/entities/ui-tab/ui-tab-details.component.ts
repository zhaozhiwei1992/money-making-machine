import { Component, Vue, Inject } from 'vue-property-decorator';

import { IUiTab } from '@/shared/model/ui-tab.model';
import UiTabService from './ui-tab.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class UiTabDetails extends Vue {
  @Inject('uiTabService') private uiTabService: () => UiTabService;
  @Inject('alertService') private alertService: () => AlertService;

  public uiTab: IUiTab = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.uiTabId) {
        vm.retrieveUiTab(to.params.uiTabId);
      }
    });
  }

  public retrieveUiTab(uiTabId) {
    this.uiTabService()
      .find(uiTabId)
      .then(res => {
        this.uiTab = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
