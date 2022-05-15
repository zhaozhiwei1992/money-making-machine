import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { IRoleMenuToolButton, RoleMenuToolButton } from '@/shared/model/role-menu-tool-button.model';
import RoleMenuToolButtonService from './role-menu-tool-button.service';

const validations: any = {
  roleMenuToolButton: {
    roleId: {},
    menuId: {},
    toolButtonId: {},
  },
};

@Component({
  validations,
})
export default class RoleMenuToolButtonUpdate extends Vue {
  @Inject('roleMenuToolButtonService') private roleMenuToolButtonService: () => RoleMenuToolButtonService;
  @Inject('alertService') private alertService: () => AlertService;

  public roleMenuToolButton: IRoleMenuToolButton = new RoleMenuToolButton();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.roleMenuToolButtonId) {
        vm.retrieveRoleMenuToolButton(to.params.roleMenuToolButtonId);
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
    if (this.roleMenuToolButton.id) {
      this.roleMenuToolButtonService()
        .update(this.roleMenuToolButton)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.roleMenuToolButton.updated', { param: param.id });
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
      this.roleMenuToolButtonService()
        .create(this.roleMenuToolButton)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('moneyMakingMachineApp.roleMenuToolButton.created', { param: param.id });
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

  public retrieveRoleMenuToolButton(roleMenuToolButtonId): void {
    this.roleMenuToolButtonService()
      .find(roleMenuToolButtonId)
      .then(res => {
        this.roleMenuToolButton = res;
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
