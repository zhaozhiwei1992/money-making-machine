<template>
  <div>
    <h2 id="page-heading" data-cy="UiComponentHeading">
      <span v-text="$t('moneyMakingMachineApp.uiComponent.home.title')" id="ui-component-heading">Ui Components</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.uiComponent.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'UiComponentCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-ui-component"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.uiComponent.home.createLabel')"> Create a new Ui Component </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && uiComponents && uiComponents.length === 0">
      <span v-text="$t('moneyMakingMachineApp.uiComponent.home.notFound')">No uiComponents found</span>
    </div>
    <div class="table-responsive" v-if="uiComponents && uiComponents.length > 0">
      <table class="table table-striped" aria-describedby="uiComponents">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiComponent.menuid')">Menuid</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiComponent.ordernum')">Ordernum</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiComponent.componentid')">Componentid</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiComponent.config')">Config</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="uiComponent in uiComponents" :key="uiComponent.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'UiComponentView', params: { uiComponentId: uiComponent.id } }">{{ uiComponent.id }}</router-link>
            </td>
            <td>{{ uiComponent.menuid }}</td>
            <td>{{ uiComponent.ordernum }}</td>
            <td>{{ uiComponent.componentid }}</td>
            <td>{{ uiComponent.config }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'UiComponentView', params: { uiComponentId: uiComponent.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'UiComponentEdit', params: { uiComponentId: uiComponent.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(uiComponent)"
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
          id="moneyMakingMachineApp.uiComponent.delete.question"
          data-cy="uiComponentDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-uiComponent-heading" v-text="$t('moneyMakingMachineApp.uiComponent.delete.question', { id: removeId })">
          Are you sure you want to delete this Ui Component?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-uiComponent"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeUiComponent()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="uiComponents && uiComponents.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./ui-component.component.ts"></script>
