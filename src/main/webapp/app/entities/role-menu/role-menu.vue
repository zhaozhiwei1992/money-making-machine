<template>
  <div>
    <h2 id="page-heading" data-cy="RoleMenuHeading">
      <span v-text="$t('moneyMakingMachineApp.roleMenu.home.title')" id="role-menu-heading">Role Menus</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.roleMenu.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'RoleMenuCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-role-menu"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.roleMenu.home.createLabel')"> Create a new Role Menu </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && roleMenus && roleMenus.length === 0">
      <span v-text="$t('moneyMakingMachineApp.roleMenu.home.notFound')">No roleMenus found</span>
    </div>
    <div class="table-responsive" v-if="roleMenus && roleMenus.length > 0">
      <table class="table table-striped" aria-describedby="roleMenus">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.roleMenu.roleId')">Role Id</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.roleMenu.menuId')">Menu Id</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="roleMenu in roleMenus" :key="roleMenu.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'RoleMenuView', params: { roleMenuId: roleMenu.id } }">{{ roleMenu.id }}</router-link>
            </td>
            <td>{{ roleMenu.roleId }}</td>
            <td>{{ roleMenu.menuId }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'RoleMenuView', params: { roleMenuId: roleMenu.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'RoleMenuEdit', params: { roleMenuId: roleMenu.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(roleMenu)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="moneyMakingMachineApp.roleMenu.delete.question" data-cy="roleMenuDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-roleMenu-heading" v-text="$t('moneyMakingMachineApp.roleMenu.delete.question', { id: removeId })">
          Are you sure you want to delete this Role Menu?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-roleMenu"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeRoleMenu()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./role-menu.component.ts"></script>
