import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import MenuService from './menu/menu.service';
import UiComponentService from './ui-component/ui-component.service';
import UiToolButtonService from './ui-tool-button/ui-tool-button.service';
import UiTableService from './ui-table/ui-table.service';
import UiEditformService from './ui-editform/ui-editform.service';
import UiQueryformService from './ui-queryform/ui-queryform.service';
import LeaveTypeService from './leave-type/leave-type.service';
import UiTabService from './ui-tab/ui-tab.service';
import DataPermissionService from './data-permission/data-permission.service';
import DataPermissionDetailsService from './data-permission-details/data-permission-details.service';
import DataPermissionsRelService from './data-permissions-rel/data-permissions-rel.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

@Component
export default class Entities extends Vue {
  @Provide('userService') private userService = () => new UserService();
  @Provide('menuService') private menuService = () => new MenuService();
  @Provide('uiComponentService') private uiComponentService = () => new UiComponentService();
  @Provide('uiToolButtonService') private uiToolButtonService = () => new UiToolButtonService();
  @Provide('uiTableService') private uiTableService = () => new UiTableService();
  @Provide('uiEditformService') private uiEditformService = () => new UiEditformService();
  @Provide('uiQueryformService') private uiQueryformService = () => new UiQueryformService();
  @Provide('leaveTypeService') private leaveTypeService = () => new LeaveTypeService();
  @Provide('uiTabService') private uiTabService = () => new UiTabService();
  @Provide('dataPermissionService') private dataPermissionService = () => new DataPermissionService();
  @Provide('dataPermissionDetailsService') private dataPermissionDetailsService = () => new DataPermissionDetailsService();
  @Provide('dataPermissionsRelService') private dataPermissionsRelService = () => new DataPermissionsRelService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
}
