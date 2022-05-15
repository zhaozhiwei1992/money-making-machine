<template>
  <div>
    <b-dropdown-item to="/menu">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.menu')">Menu</span>
    </b-dropdown-item>
    <b-dropdown-item to="/ui-component">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.uiComponent')">Ui Component</span>
    </b-dropdown-item>
    <b-dropdown-item to="/ui-tool-button">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.uiToolButton')">Ui Tool Button</span>
    </b-dropdown-item>
    <b-dropdown-item to="/ui-table">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.uiTable')">Ui Table</span>
    </b-dropdown-item>
    <b-dropdown-item to="/ui-editform">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.uiEditform')">Ui Editform</span>
    </b-dropdown-item>
    <b-dropdown-item to="/ui-queryform">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.uiQueryform')">Ui Queryform</span>
    </b-dropdown-item>
    <b-dropdown-item to="/leave-type">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.leaveType')">Leave Type</span>
    </b-dropdown-item>
    <b-dropdown-item to="/ui-tab">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.uiTab')">Ui Tab</span>
    </b-dropdown-item>
    <b-dropdown-item to="/data-permission">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.dataPermission')">Data Permission</span>
    </b-dropdown-item>
    <b-dropdown-item to="/data-permission-details">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.dataPermissionDetails')">Data Permission Details</span>
    </b-dropdown-item>
    <b-dropdown-item to="/data-permissions-rel">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.dataPermissionsRel')">Data Permissions Rel</span>
    </b-dropdown-item>
    <b-dropdown-item to="/role-menu">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.roleMenu')">Role Menu</span>
    </b-dropdown-item>
    <b-dropdown-item to="/role-menu-tool-button">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.roleMenuToolButton')">Role Menu Tool Button</span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
