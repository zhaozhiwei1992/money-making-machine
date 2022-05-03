<template>
  <div>
    <h2 id="page-heading" data-cy="UiToolButtonHeading">
      <span v-text="$t('moneyMakingMachineApp.uiToolButton.home.title')" id="ui-tool-button-heading">Ui Tool Buttons</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.uiToolButton.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'UiToolButtonCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-ui-tool-button"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.uiToolButton.home.createLabel')"> Create a new Ui Tool Button </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && uiToolButtons && uiToolButtons.length === 0">
      <span v-text="$t('moneyMakingMachineApp.uiToolButton.home.notFound')">No uiToolButtons found</span>
    </div>
    <div class="table-responsive" v-if="uiToolButtons && uiToolButtons.length > 0">
      <table class="table table-striped" aria-describedby="uiToolButtons">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiToolButton.menuid')">Menuid</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiToolButton.code')">Code</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiToolButton.name')">Name</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiToolButton.ordernum')">Ordernum</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiToolButton.action')">Action</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiToolButton.config')">Config</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="uiToolButton in uiToolButtons" :key="uiToolButton.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'UiToolButtonView', params: { uiToolButtonId: uiToolButton.id } }">{{
                uiToolButton.id
              }}</router-link>
            </td>
            <td>{{ uiToolButton.menuid }}</td>
            <td>{{ uiToolButton.code }}</td>
            <td>{{ uiToolButton.name }}</td>
            <td>{{ uiToolButton.ordernum }}</td>
            <td>{{ uiToolButton.action }}</td>
            <td>{{ uiToolButton.config }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'UiToolButtonView', params: { uiToolButtonId: uiToolButton.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'UiToolButtonEdit', params: { uiToolButtonId: uiToolButton.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(uiToolButton)"
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
          id="moneyMakingMachineApp.uiToolButton.delete.question"
          data-cy="uiToolButtonDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-uiToolButton-heading" v-text="$t('moneyMakingMachineApp.uiToolButton.delete.question', { id: removeId })">
          Are you sure you want to delete this Ui Tool Button?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-uiToolButton"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeUiToolButton()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./ui-tool-button.component.ts"></script>
