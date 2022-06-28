<template>
  <div>
    <h2 id="page-heading" data-cy="SysCollectColHeading">
      <span v-text="$t('moneyMakingMachineApp.sysCollectCol.home.title')" id="sys-collect-col-heading">Sys Collect Cols</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.sysCollectCol.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'SysCollectColCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-sys-collect-col"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.sysCollectCol.home.createLabel')"> Create a new Sys Collect Col </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && sysCollectCols && sysCollectCols.length === 0">
      <span v-text="$t('moneyMakingMachineApp.sysCollectCol.home.notFound')">No sysCollectCols found</span>
    </div>
    <div class="table-responsive" v-if="sysCollectCols && sysCollectCols.length > 0">
      <table class="table table-striped" aria-describedby="sysCollectCols">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysCollectCol.colCname')">Col Cname</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysCollectCol.colEname')">Col Ename</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysCollectCol.tabId')">Tab Id</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysCollectCol.orderNum')">Order Num</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysCollectCol.source')">Source</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysCollectCol.isEdit')">Is Edit</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysCollectCol.requirement')">Requirement</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysCollectCol.type')">Type</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysCollectCol.config')">Config</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="sysCollectCol in sysCollectCols" :key="sysCollectCol.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SysCollectColView', params: { sysCollectColId: sysCollectCol.id } }">{{
                sysCollectCol.id
              }}</router-link>
            </td>
            <td>{{ sysCollectCol.colCname }}</td>
            <td>{{ sysCollectCol.colEname }}</td>
            <td>{{ sysCollectCol.tabId }}</td>
            <td>{{ sysCollectCol.orderNum }}</td>
            <td>{{ sysCollectCol.source }}</td>
            <td>{{ sysCollectCol.isEdit }}</td>
            <td>{{ sysCollectCol.requirement }}</td>
            <td>{{ sysCollectCol.type }}</td>
            <td>{{ sysCollectCol.config }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'SysCollectColView', params: { sysCollectColId: sysCollectCol.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'SysCollectColEdit', params: { sysCollectColId: sysCollectCol.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(sysCollectCol)"
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
          id="moneyMakingMachineApp.sysCollectCol.delete.question"
          data-cy="sysCollectColDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-sysCollectCol-heading" v-text="$t('moneyMakingMachineApp.sysCollectCol.delete.question', { id: removeId })">
          Are you sure you want to delete this Sys Collect Col?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-sysCollectCol"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeSysCollectCol()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./sys-collect-col.component.ts"></script>
