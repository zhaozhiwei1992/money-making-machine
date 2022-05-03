<template>
  <div>
    <h2 id="page-heading" data-cy="UiQueryformHeading">
      <span v-text="$t('moneyMakingMachineApp.uiQueryform.home.title')" id="ui-queryform-heading">Ui Queryforms</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.uiQueryform.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'UiQueryformCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-ui-queryform"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.uiQueryform.home.createLabel')"> Create a new Ui Queryform </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && uiQueryforms && uiQueryforms.length === 0">
      <span v-text="$t('moneyMakingMachineApp.uiQueryform.home.notFound')">No uiQueryforms found</span>
    </div>
    <div class="table-responsive" v-if="uiQueryforms && uiQueryforms.length > 0">
      <table class="table table-striped" aria-describedby="uiQueryforms">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiQueryform.menuid')">Menuid</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiQueryform.code')">Code</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiQueryform.name')">Name</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiQueryform.ordernum')">Ordernum</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiQueryform.issource')">Issource</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiQueryform.requirement')">Requirement</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiQueryform.type')">Type</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiQueryform.placeholder')">Placeholder</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiQueryform.config')">Config</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="uiQueryform in uiQueryforms" :key="uiQueryform.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'UiQueryformView', params: { uiQueryformId: uiQueryform.id } }">{{ uiQueryform.id }}</router-link>
            </td>
            <td>{{ uiQueryform.menuid }}</td>
            <td>{{ uiQueryform.code }}</td>
            <td>{{ uiQueryform.name }}</td>
            <td>{{ uiQueryform.ordernum }}</td>
            <td>{{ uiQueryform.issource }}</td>
            <td>{{ uiQueryform.requirement }}</td>
            <td>{{ uiQueryform.type }}</td>
            <td>{{ uiQueryform.placeholder }}</td>
            <td>{{ uiQueryform.config }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'UiQueryformView', params: { uiQueryformId: uiQueryform.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'UiQueryformEdit', params: { uiQueryformId: uiQueryform.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(uiQueryform)"
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
          id="moneyMakingMachineApp.uiQueryform.delete.question"
          data-cy="uiQueryformDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-uiQueryform-heading" v-text="$t('moneyMakingMachineApp.uiQueryform.delete.question', { id: removeId })">
          Are you sure you want to delete this Ui Queryform?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-uiQueryform"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeUiQueryform()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./ui-queryform.component.ts"></script>
