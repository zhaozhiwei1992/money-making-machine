<template>
  <div>
    <h2 id="page-heading" data-cy="DataPermissionsRelHeading">
      <span v-text="$t('moneyMakingMachineApp.dataPermissionsRel.home.title')" id="data-permissions-rel-heading"
        >Data Permissions Rels</span
      >
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.dataPermissionsRel.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'DataPermissionsRelCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-data-permissions-rel"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.dataPermissionsRel.home.createLabel')"> Create a new Data Permissions Rel </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && dataPermissionsRels && dataPermissionsRels.length === 0">
      <span v-text="$t('moneyMakingMachineApp.dataPermissionsRel.home.notFound')">No dataPermissionsRels found</span>
    </div>
    <div class="table-responsive" v-if="dataPermissionsRels && dataPermissionsRels.length > 0">
      <table class="table table-striped" aria-describedby="dataPermissionsRels">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('ruleId')">
              <span v-text="$t('moneyMakingMachineApp.dataPermissionsRel.ruleId')">Rule Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ruleId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('roleId')">
              <span v-text="$t('moneyMakingMachineApp.dataPermissionsRel.roleId')">Role Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'roleId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('menuId')">
              <span v-text="$t('moneyMakingMachineApp.dataPermissionsRel.menuId')">Menu Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'menuId'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="dataPermissionsRel in dataPermissionsRels" :key="dataPermissionsRel.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'DataPermissionsRelView', params: { dataPermissionsRelId: dataPermissionsRel.id } }">{{
                dataPermissionsRel.id
              }}</router-link>
            </td>
            <td>{{ dataPermissionsRel.ruleId }}</td>
            <td>{{ dataPermissionsRel.roleId }}</td>
            <td>{{ dataPermissionsRel.menuId }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'DataPermissionsRelView', params: { dataPermissionsRelId: dataPermissionsRel.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'DataPermissionsRelEdit', params: { dataPermissionsRelId: dataPermissionsRel.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(dataPermissionsRel)"
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
          id="moneyMakingMachineApp.dataPermissionsRel.delete.question"
          data-cy="dataPermissionsRelDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p
          id="jhi-delete-dataPermissionsRel-heading"
          v-text="$t('moneyMakingMachineApp.dataPermissionsRel.delete.question', { id: removeId })"
        >
          Are you sure you want to delete this Data Permissions Rel?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-dataPermissionsRel"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeDataPermissionsRel()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="dataPermissionsRels && dataPermissionsRels.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./data-permissions-rel.component.ts"></script>
