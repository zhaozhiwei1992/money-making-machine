<template>
  <div>
    <h2 id="page-heading" data-cy="UiTableHeading">
      <span v-text="$t('moneyMakingMachineApp.uiTable.home.title')" id="ui-table-heading">Ui Tables</span>
      <div class="d-flex justify-content-end">
        <span style="margin-right: 5px">菜单id:</span>
        <input type="text" v-model="menuid" style="margin-right: 5px" />
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.uiTable.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'UiTableCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-ui-table"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.uiTable.home.createLabel')"> Create a new Ui Table </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && uiTables && uiTables.length === 0">
      <span v-text="$t('moneyMakingMachineApp.uiTable.home.notFound')">No uiTables found</span>
    </div>
    <div class="table-responsive" v-if="uiTables && uiTables.length > 0">
      <table class="table table-striped" aria-describedby="uiTables">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiTable.menuid')">Menuid</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiTable.code')">Code</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiTable.name')">Name</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiTable.ordernum')">Ordernum</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiTable.issource')">Issource</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiTable.isedit')">Isedit</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiTable.requirement')">Requirement</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiTable.type')">Type</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiTable.config')">Config</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="uiTable in uiTables" :key="uiTable.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'UiTableView', params: { uiTableId: uiTable.id } }">{{ uiTable.id }}</router-link>
            </td>
            <td>{{ uiTable.menuid }}</td>
            <td>{{ uiTable.code }}</td>
            <td>{{ uiTable.name }}</td>
            <td>{{ uiTable.ordernum }}</td>
            <td>{{ uiTable.issource }}</td>
            <td>{{ uiTable.isedit }}</td>
            <td>{{ uiTable.requirement }}</td>
            <td>{{ uiTable.type }}</td>
            <td>{{ uiTable.config }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'UiTableView', params: { uiTableId: uiTable.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'UiTableEdit', params: { uiTableId: uiTable.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(uiTable)"
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
        ><span id="moneyMakingMachineApp.uiTable.delete.question" data-cy="uiTableDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-uiTable-heading" v-text="$t('moneyMakingMachineApp.uiTable.delete.question', { id: removeId })">
          Are you sure you want to delete this Ui Table?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-uiTable"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeUiTable()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="uiTables && uiTables.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./ui-table.component.ts"></script>
