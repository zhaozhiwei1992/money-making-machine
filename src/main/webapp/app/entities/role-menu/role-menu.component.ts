import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IRoleMenu } from '@/shared/model/role-menu.model';

import RoleMenuService from './role-menu.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class RoleMenu extends Vue {
  @Inject('roleMenuService') private roleMenuService: () => RoleMenuService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public roleMenus: IRoleMenu[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllRoleMenus();
  }

  public clear(): void {
    this.retrieveAllRoleMenus();
  }

  public retrieveAllRoleMenus(): void {
    this.isFetching = true;
    this.roleMenuService()
      .retrieve()
      .then(
        res => {
          this.roleMenus = res.data;
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

  public prepareRemove(instance: IRoleMenu): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeRoleMenu(): void {
    this.roleMenuService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('moneyMakingMachineApp.roleMenu.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllRoleMenus();
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
