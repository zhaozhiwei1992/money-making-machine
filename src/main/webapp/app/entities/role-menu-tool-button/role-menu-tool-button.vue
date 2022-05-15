<template>
  <div>
    <h2 id="page-heading" data-cy="RoleMenuToolButtonHeading">
      <span v-text="$t('moneyMakingMachineApp.roleMenuToolButton.home.title')" id="role-menu-tool-button-heading"
        >Role Menu Tool Buttons</span
      >
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.roleMenuToolButton.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'RoleMenuToolButtonCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-role-menu-tool-button"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.roleMenuToolButton.home.createLabel')"> Create a new Role Menu Tool Button </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && roleMenuToolButtons && roleMenuToolButtons.length === 0">
      <span v-text="$t('moneyMakingMachineApp.roleMenuToolButton.home.notFound')">No roleMenuToolButtons found</span>
    </div>
    <div class="table-responsive" v-if="roleMenuToolButtons && roleMenuToolButtons.length > 0">
      <table class="table table-striped" aria-describedby="roleMenuToolButtons">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.roleMenuToolButton.roleId')">Role Id</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.roleMenuToolButton.menuId')">Menu Id</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.roleMenuToolButton.toolButtonId')">Tool Button Id</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="roleMenuToolButton in roleMenuToolButtons" :key="roleMenuToolButton.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'RoleMenuToolButtonView', params: { roleMenuToolButtonId: roleMenuToolButton.id } }">{{
                roleMenuToolButton.id
              }}</router-link>
            </td>
            <td>{{ roleMenuToolButton.roleId }}</td>
            <td>{{ roleMenuToolButton.menuId }}</td>
            <td>{{ roleMenuToolButton.toolButtonId }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'RoleMenuToolButtonView', params: { roleMenuToolButtonId: roleMenuToolButton.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'RoleMenuToolButtonEdit', params: { roleMenuToolButtonId: roleMenuToolButton.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(roleMenuToolButton)"
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
        ><span
          id="moneyMakingMachineApp.roleMenuToolButton.delete.question"
          data-cy="roleMenuToolButtonDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p
          id="jhi-delete-roleMenuToolButton-heading"
          v-text="$t('moneyMakingMachineApp.roleMenuToolButton.delete.question', { id: removeId })"
        >
          Are you sure you want to delete this Role Menu Tool Button?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-roleMenuToolButton"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeRoleMenuToolButton()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./role-menu-tool-button.component.ts"></script>
