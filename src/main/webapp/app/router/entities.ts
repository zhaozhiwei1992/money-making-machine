import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

// prettier-ignore
const Menu = () => import('@/entities/menu/menu.vue');
// prettier-ignore
const MenuUpdate = () => import('@/entities/menu/menu-update.vue');
// prettier-ignore
const MenuDetails = () => import('@/entities/menu/menu-details.vue');
// prettier-ignore
const UiComponent = () => import('@/entities/ui-component/ui-component.vue');
// prettier-ignore
const UiComponentUpdate = () => import('@/entities/ui-component/ui-component-update.vue');
// prettier-ignore
const UiComponentDetails = () => import('@/entities/ui-component/ui-component-details.vue');
// prettier-ignore
const UiToolButton = () => import('@/entities/ui-tool-button/ui-tool-button.vue');
// prettier-ignore
const UiToolButtonUpdate = () => import('@/entities/ui-tool-button/ui-tool-button-update.vue');
// prettier-ignore
const UiToolButtonDetails = () => import('@/entities/ui-tool-button/ui-tool-button-details.vue');
// prettier-ignore
const UiTable = () => import('@/entities/ui-table/ui-table.vue');
// prettier-ignore
const UiTableUpdate = () => import('@/entities/ui-table/ui-table-update.vue');
// prettier-ignore
const UiTableDetails = () => import('@/entities/ui-table/ui-table-details.vue');
// prettier-ignore
const UiEditform = () => import('@/entities/ui-editform/ui-editform.vue');
// prettier-ignore
const UiEditformUpdate = () => import('@/entities/ui-editform/ui-editform-update.vue');
// prettier-ignore
const UiEditformDetails = () => import('@/entities/ui-editform/ui-editform-details.vue');
// prettier-ignore
const UiQueryform = () => import('@/entities/ui-queryform/ui-queryform.vue');
// prettier-ignore
const UiQueryformUpdate = () => import('@/entities/ui-queryform/ui-queryform-update.vue');
// prettier-ignore
const UiQueryformDetails = () => import('@/entities/ui-queryform/ui-queryform-details.vue');
// prettier-ignore
const LeaveType = () => import('@/entities/leave-type/leave-type.vue');
// prettier-ignore
const LeaveTypeUpdate = () => import('@/entities/leave-type/leave-type-update.vue');
// prettier-ignore
const LeaveTypeDetails = () => import('@/entities/leave-type/leave-type-details.vue');
// prettier-ignore
const UiTab = () => import('@/entities/ui-tab/ui-tab.vue');
// prettier-ignore
const UiTabUpdate = () => import('@/entities/ui-tab/ui-tab-update.vue');
// prettier-ignore
const UiTabDetails = () => import('@/entities/ui-tab/ui-tab-details.vue');
// prettier-ignore
const DataPermission = () => import('@/entities/data-permission/data-permission.vue');
// prettier-ignore
const DataPermissionUpdate = () => import('@/entities/data-permission/data-permission-update.vue');
// prettier-ignore
const DataPermissionDetails = () => import('@/entities/data-permission/data-permission-details.vue');
// prettier-ignore
const DataPermissionDetails = () => import('@/entities/data-permission-details/data-permission-details.vue');
// prettier-ignore
const DataPermissionDetailsUpdate = () => import('@/entities/data-permission-details/data-permission-details-update.vue');
// prettier-ignore
const DataPermissionDetailsDetails = () => import('@/entities/data-permission-details/data-permission-details-details.vue');
// prettier-ignore
const DataPermissionsRel = () => import('@/entities/data-permissions-rel/data-permissions-rel.vue');
// prettier-ignore
const DataPermissionsRelUpdate = () => import('@/entities/data-permissions-rel/data-permissions-rel-update.vue');
// prettier-ignore
const DataPermissionsRelDetails = () => import('@/entities/data-permissions-rel/data-permissions-rel-details.vue');
// prettier-ignore
const RoleMenu = () => import('@/entities/role-menu/role-menu.vue');
// prettier-ignore
const RoleMenuUpdate = () => import('@/entities/role-menu/role-menu-update.vue');
// prettier-ignore
const RoleMenuDetails = () => import('@/entities/role-menu/role-menu-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'menu',
      name: 'Menu',
      component: Menu,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'menu/new',
      name: 'MenuCreate',
      component: MenuUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'menu/:menuId/edit',
      name: 'MenuEdit',
      component: MenuUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'menu/:menuId/view',
      name: 'MenuView',
      component: MenuDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-component',
      name: 'UiComponent',
      component: UiComponent,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-component/new',
      name: 'UiComponentCreate',
      component: UiComponentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-component/:uiComponentId/edit',
      name: 'UiComponentEdit',
      component: UiComponentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-component/:uiComponentId/view',
      name: 'UiComponentView',
      component: UiComponentDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-tool-button',
      name: 'UiToolButton',
      component: UiToolButton,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-tool-button/new',
      name: 'UiToolButtonCreate',
      component: UiToolButtonUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-tool-button/:uiToolButtonId/edit',
      name: 'UiToolButtonEdit',
      component: UiToolButtonUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-tool-button/:uiToolButtonId/view',
      name: 'UiToolButtonView',
      component: UiToolButtonDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-table',
      name: 'UiTable',
      component: UiTable,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-table/new',
      name: 'UiTableCreate',
      component: UiTableUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-table/:uiTableId/edit',
      name: 'UiTableEdit',
      component: UiTableUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-table/:uiTableId/view',
      name: 'UiTableView',
      component: UiTableDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-editform',
      name: 'UiEditform',
      component: UiEditform,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-editform/new',
      name: 'UiEditformCreate',
      component: UiEditformUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-editform/:uiEditformId/edit',
      name: 'UiEditformEdit',
      component: UiEditformUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-editform/:uiEditformId/view',
      name: 'UiEditformView',
      component: UiEditformDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-queryform',
      name: 'UiQueryform',
      component: UiQueryform,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-queryform/new',
      name: 'UiQueryformCreate',
      component: UiQueryformUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-queryform/:uiQueryformId/edit',
      name: 'UiQueryformEdit',
      component: UiQueryformUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-queryform/:uiQueryformId/view',
      name: 'UiQueryformView',
      component: UiQueryformDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'leave-type',
      name: 'LeaveType',
      component: LeaveType,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'leave-type/new',
      name: 'LeaveTypeCreate',
      component: LeaveTypeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'leave-type/:leaveTypeId/edit',
      name: 'LeaveTypeEdit',
      component: LeaveTypeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'leave-type/:leaveTypeId/view',
      name: 'LeaveTypeView',
      component: LeaveTypeDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-tab',
      name: 'UiTab',
      component: UiTab,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-tab/new',
      name: 'UiTabCreate',
      component: UiTabUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-tab/:uiTabId/edit',
      name: 'UiTabEdit',
      component: UiTabUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'ui-tab/:uiTabId/view',
      name: 'UiTabView',
      component: UiTabDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'data-permission',
      name: 'DataPermission',
      component: DataPermission,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'data-permission/new',
      name: 'DataPermissionCreate',
      component: DataPermissionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'data-permission/:dataPermissionId/edit',
      name: 'DataPermissionEdit',
      component: DataPermissionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'data-permission/:dataPermissionId/view',
      name: 'DataPermissionView',
      component: DataPermissionDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'data-permission-details',
      name: 'DataPermissionDetails',
      component: DataPermissionDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'data-permission-details/new',
      name: 'DataPermissionDetailsCreate',
      component: DataPermissionDetailsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'data-permission-details/:dataPermissionDetailsId/edit',
      name: 'DataPermissionDetailsEdit',
      component: DataPermissionDetailsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'data-permission-details/:dataPermissionDetailsId/view',
      name: 'DataPermissionDetailsView',
      component: DataPermissionDetailsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'data-permissions-rel',
      name: 'DataPermissionsRel',
      component: DataPermissionsRel,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'data-permissions-rel/new',
      name: 'DataPermissionsRelCreate',
      component: DataPermissionsRelUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'data-permissions-rel/:dataPermissionsRelId/edit',
      name: 'DataPermissionsRelEdit',
      component: DataPermissionsRelUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'data-permissions-rel/:dataPermissionsRelId/view',
      name: 'DataPermissionsRelView',
      component: DataPermissionsRelDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'role-menu',
      name: 'RoleMenu',
      component: RoleMenu,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'role-menu/new',
      name: 'RoleMenuCreate',
      component: RoleMenuUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'role-menu/:roleMenuId/edit',
      name: 'RoleMenuEdit',
      component: RoleMenuUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'role-menu/:roleMenuId/view',
      name: 'RoleMenuView',
      component: RoleMenuDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
