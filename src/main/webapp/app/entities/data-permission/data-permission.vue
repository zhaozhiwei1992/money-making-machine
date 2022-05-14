<template>
  <div>
    <h2 id="page-heading" data-cy="DataPermissionHeading">
      <span v-text="$t('moneyMakingMachineApp.dataPermission.home.title')" id="data-permission-heading">Data Permissions</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.dataPermission.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'DataPermissionCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-data-permission"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.dataPermission.home.createLabel')"> Create a new Data Permission </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && dataPermissions && dataPermissions.length === 0">
      <span v-text="$t('moneyMakingMachineApp.dataPermission.home.notFound')">No dataPermissions found</span>
    </div>
    <div class="table-responsive" v-if="dataPermissions && dataPermissions.length > 0">
      <table class="table table-striped" aria-describedby="dataPermissions">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('code')">
              <span v-text="$t('moneyMakingMachineApp.dataPermission.code')">Code</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'code'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span v-text="$t('moneyMakingMachineApp.dataPermission.name')">Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('ruleSql')">
              <span v-text="$t('moneyMakingMachineApp.dataPermission.ruleSql')">Rule Sql</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ruleSql'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="dataPermission in dataPermissions" :key="dataPermission.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'DataPermissionView', params: { dataPermissionId: dataPermission.id } }">{{
                dataPermission.id
              }}</router-link>
            </td>
            <td>{{ dataPermission.code }}</td>
            <td>{{ dataPermission.name }}</td>
            <td>{{ dataPermission.ruleSql }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'DataPermissionView', params: { dataPermissionId: dataPermission.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'DataPermissionEdit', params: { dataPermissionId: dataPermission.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(dataPermission)"
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
          id="moneyMakingMachineApp.dataPermission.delete.question"
          data-cy="dataPermissionDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-dataPermission-heading" v-text="$t('moneyMakingMachineApp.dataPermission.delete.question', { id: removeId })">
          Are you sure you want to delete this Data Permission?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-dataPermission"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeDataPermission()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="dataPermissions && dataPermissions.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./data-permission.component.ts"></script>
