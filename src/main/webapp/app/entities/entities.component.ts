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
import RoleMenuService from './role-menu/role-menu.service';
import RoleMenuToolButtonService from './role-menu-tool-button/role-menu-tool-button.service';
import RequestLoggingService from './request-logging/request-logging.service';
import SlowSqlLoggingService from './slow-sql-logging/slow-sql-logging.service';
import TaskParamService from './task-param/task-param.service';
import SystemParamService from './system-param/system-param.service';
import ExampleService from './example/example.service';
import LeaveSlipService from './leave-slip/leave-slip.service';
import SysCollectTabService from './sys-collect-tab/sys-collect-tab.service';
import SysCollectColService from './sys-collect-col/sys-collect-col.service';
import SysFormulaTabService from './sys-formula-tab/sys-formula-tab.service';
import EleUnionService from './ele-union/ele-union.service';
import SysNoticeService from './sys-notice/sys-notice.service';
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
  @Provide('roleMenuService') private roleMenuService = () => new RoleMenuService();
  @Provide('roleMenuToolButtonService') private roleMenuToolButtonService = () => new RoleMenuToolButtonService();
  @Provide('requestLoggingService') private requestLoggingService = () => new RequestLoggingService();
  @Provide('slowSqlLoggingService') private slowSqlLoggingService = () => new SlowSqlLoggingService();
  @Provide('taskParamService') private taskParamService = () => new TaskParamService();
  @Provide('systemParamService') private systemParamService = () => new SystemParamService();
  @Provide('exampleService') private exampleService = () => new ExampleService();
  @Provide('leaveSlipService') private leaveSlipService = () => new LeaveSlipService();
  @Provide('sysCollectTabService') private sysCollectTabService = () => new SysCollectTabService();
  @Provide('sysCollectColService') private sysCollectColService = () => new SysCollectColService();
  @Provide('sysFormulaTabService') private sysFormulaTabService = () => new SysFormulaTabService();
  @Provide('eleUnionService') private eleUnionService = () => new EleUnionService();
  @Provide('sysNoticeService') private sysNoticeService = () => new SysNoticeService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
}
