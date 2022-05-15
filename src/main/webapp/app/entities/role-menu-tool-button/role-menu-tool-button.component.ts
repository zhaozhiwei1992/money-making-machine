import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IRoleMenuToolButton } from '@/shared/model/role-menu-tool-button.model';

import RoleMenuToolButtonService from './role-menu-tool-button.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class RoleMenuToolButton extends Vue {
  @Inject('roleMenuToolButtonService') private roleMenuToolButtonService: () => RoleMenuToolButtonService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public roleMenuToolButtons: IRoleMenuToolButton[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllRoleMenuToolButtons();
  }

  public clear(): void {
    this.retrieveAllRoleMenuToolButtons();
  }

  public retrieveAllRoleMenuToolButtons(): void {
    this.isFetching = true;
    this.roleMenuToolButtonService()
      .retrieve()
      .then(
        res => {
          this.roleMenuToolButtons = res.data;
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

  public prepareRemove(instance: IRoleMenuToolButton): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeRoleMenuToolButton(): void {
    this.roleMenuToolButtonService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('moneyMakingMachineApp.roleMenuToolButton.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllRoleMenuToolButtons();
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
