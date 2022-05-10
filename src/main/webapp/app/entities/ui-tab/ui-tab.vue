<template>
  <div>
    <h2 id="page-heading" data-cy="UiTabHeading">
      <span v-text="$t('moneyMakingMachineApp.uiTab.home.title')" id="ui-tab-heading">Ui Tabs</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.uiTab.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'UiTabCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-ui-tab"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.uiTab.home.createLabel')"> Create a new Ui Tab </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && uiTabs && uiTabs.length === 0">
      <span v-text="$t('moneyMakingMachineApp.uiTab.home.notFound')">No uiTabs found</span>
    </div>
    <div class="table-responsive" v-if="uiTabs && uiTabs.length > 0">
      <table class="table table-striped" aria-describedby="uiTabs">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiTab.menuid')">Menuid</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiTab.code')">Code</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiTab.name')">Name</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiTab.ordernum')">Ordernum</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiTab.config')">Config</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="uiTab in uiTabs" :key="uiTab.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'UiTabView', params: { uiTabId: uiTab.id } }">{{ uiTab.id }}</router-link>
            </td>
            <td>{{ uiTab.menuid }}</td>
            <td>{{ uiTab.code }}</td>
            <td>{{ uiTab.name }}</td>
            <td>{{ uiTab.ordernum }}</td>
            <td>{{ uiTab.config }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'UiTabView', params: { uiTabId: uiTab.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'UiTabEdit', params: { uiTabId: uiTab.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(uiTab)"
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
        ><span id="moneyMakingMachineApp.uiTab.delete.question" data-cy="uiTabDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-uiTab-heading" v-text="$t('moneyMakingMachineApp.uiTab.delete.question', { id: removeId })">
          Are you sure you want to delete this Ui Tab?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-uiTab"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeUiTab()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./ui-tab.component.ts"></script>
