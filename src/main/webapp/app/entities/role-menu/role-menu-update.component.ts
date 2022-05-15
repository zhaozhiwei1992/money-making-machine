import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { IRoleMenu, RoleMenu } from '@/shared/model/role-menu.model';
import RoleMenuService from './role-menu.service';

const validations: any = {
  roleMenu: {
    roleId: {},
    menuId: {},
  },
};

@Component({
  validations,
})
export default class RoleMenuUpdate extends Vue {
  @Inject('roleMenuService') private roleMenuService: () => RoleMenuService;
  @Inject('alertService') private alertService: () => AlertService;

  public roleMenu: IRoleMenu = new RoleMenu();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.roleMenuId) {
        vm.retrieveRoleMenu(to.params.roleMenuId);
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
    if (this.roleMenu.id) {
      this.roleMenuService()
        .update(this.roleMenu)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.roleMenu.updated', { param: param.id });
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
      this.roleMenuService()
        .create(this.roleMenu)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.roleMenu.created', { param: param.id });
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

  public retrieveRoleMenu(roleMenuId): void {
    this.roleMenuService()
      .find(roleMenuId)
      .then(res => {
        this.roleMenu = res;
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
