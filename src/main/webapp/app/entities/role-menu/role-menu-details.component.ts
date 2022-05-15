import { Component, Vue, Inject } from 'vue-property-decorator';

import { IRoleMenu } from '@/shared/model/role-menu.model';
import RoleMenuService from './role-menu.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class RoleMenuDetails extends Vue {
  @Inject('roleMenuService') private roleMenuService: () => RoleMenuService;
  @Inject('alertService') private alertService: () => AlertService;

  public roleMenu: IRoleMenu = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.roleMenuId) {
        vm.retrieveRoleMenu(to.params.roleMenuId);
      }
    });
  }

  public retrieveRoleMenu(roleMenuId) {
    this.roleMenuService()
      .find(roleMenuId)
      .then(res => {
        this.roleMenu = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
