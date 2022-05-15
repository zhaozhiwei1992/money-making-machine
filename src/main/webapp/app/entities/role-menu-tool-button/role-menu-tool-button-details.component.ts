import { Component, Vue, Inject } from 'vue-property-decorator';

import { IRoleMenuToolButton } from '@/shared/model/role-menu-tool-button.model';
import RoleMenuToolButtonService from './role-menu-tool-button.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class RoleMenuToolButtonDetails extends Vue {
  @Inject('roleMenuToolButtonService') private roleMenuToolButtonService: () => RoleMenuToolButtonService;
  @Inject('alertService') private alertService: () => AlertService;

  public roleMenuToolButton: IRoleMenuToolButton = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.roleMenuToolButtonId) {
        vm.retrieveRoleMenuToolButton(to.params.roleMenuToolButtonId);
      }
    });
  }

  public retrieveRoleMenuToolButton(roleMenuToolButtonId) {
    this.roleMenuToolButtonService()
      .find(roleMenuToolButtonId)
      .then(res => {
        this.roleMenuToolButton = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
