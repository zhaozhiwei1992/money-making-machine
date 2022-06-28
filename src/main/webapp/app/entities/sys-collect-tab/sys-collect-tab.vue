<template>
  <div>
    <h2 id="page-heading" data-cy="SysCollectTabHeading">
      <span v-text="$t('moneyMakingMachineApp.sysCollectTab.home.title')" id="sys-collect-tab-heading">Sys Collect Tabs</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.sysCollectTab.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'SysCollectTabCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-sys-collect-tab"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.sysCollectTab.home.createLabel')"> Create a new Sys Collect Tab </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && sysCollectTabs && sysCollectTabs.length === 0">
      <span v-text="$t('moneyMakingMachineApp.sysCollectTab.home.notFound')">No sysCollectTabs found</span>
    </div>
    <div class="table-responsive" v-if="sysCollectTabs && sysCollectTabs.length > 0">
      <table class="table table-striped" aria-describedby="sysCollectTabs">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysCollectTab.tabCname')">Tab Cname</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysCollectTab.tabEname')">Tab Ename</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysCollectTab.config')">Config</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysCollectTab.enable')">Enable</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="sysCollectTab in sysCollectTabs" :key="sysCollectTab.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SysCollectTabView', params: { sysCollectTabId: sysCollectTab.id } }">{{
                sysCollectTab.id
              }}</router-link>
            </td>
            <td>{{ sysCollectTab.tabCname }}</td>
            <td>{{ sysCollectTab.tabEname }}</td>
            <td>{{ sysCollectTab.config }}</td>
            <td>{{ sysCollectTab.enable }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'SysCollectTabView', params: { sysCollectTabId: sysCollectTab.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'SysCollectTabEdit', params: { sysCollectTabId: sysCollectTab.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(sysCollectTab)"
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
          id="moneyMakingMachineApp.sysCollectTab.delete.question"
          data-cy="sysCollectTabDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-sysCollectTab-heading" v-text="$t('moneyMakingMachineApp.sysCollectTab.delete.question', { id: removeId })">
          Are you sure you want to delete this Sys Collect Tab?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-sysCollectTab"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeSysCollectTab()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./sys-collect-tab.component.ts"></script>
