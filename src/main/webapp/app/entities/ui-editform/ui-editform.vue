<template>
  <div>
    <h2 id="page-heading" data-cy="UiEditformHeading">
      <span v-text="$t('moneyMakingMachineApp.uiEditform.home.title')" id="ui-editform-heading">Ui Editforms</span>
      <div class="d-flex justify-content-end">
        <span style="margin-right: 5px">菜单id:</span>
        <input type="text" v-model="menuid" style="margin-right: 5px" />
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.uiEditform.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'UiEditformCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-ui-editform"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.uiEditform.home.createLabel')"> Create a new Ui Editform </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && uiEditforms && uiEditforms.length === 0">
      <span v-text="$t('moneyMakingMachineApp.uiEditform.home.notFound')">No uiEditforms found</span>
    </div>
    <div class="table-responsive" v-if="uiEditforms && uiEditforms.length > 0">
      <table class="table table-striped" aria-describedby="uiEditforms">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiEditform.menuid')">Menuid</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiEditform.code')">Code</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiEditform.name')">Name</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiEditform.ordernum')">Ordernum</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiEditform.issource')">Issource</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiEditform.isedit')">Isedit</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiEditform.requirement')">Requirement</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiEditform.type')">Type</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiEditform.placeholder')">Placeholder</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.uiEditform.config')">Config</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="uiEditform in uiEditforms" :key="uiEditform.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'UiEditformView', params: { uiEditformId: uiEditform.id } }">{{ uiEditform.id }}</router-link>
            </td>
            <td>{{ uiEditform.menuid }}</td>
            <td>{{ uiEditform.code }}</td>
            <td>{{ uiEditform.name }}</td>
            <td>{{ uiEditform.ordernum }}</td>
            <td>{{ uiEditform.issource }}</td>
            <td>{{ uiEditform.isedit }}</td>
            <td>{{ uiEditform.requirement }}</td>
            <td>{{ uiEditform.type }}</td>
            <td>{{ uiEditform.placeholder }}</td>
            <td>{{ uiEditform.config }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'UiEditformView', params: { uiEditformId: uiEditform.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'UiEditformEdit', params: { uiEditformId: uiEditform.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(uiEditform)"
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
          id="moneyMakingMachineApp.uiEditform.delete.question"
          data-cy="uiEditformDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-uiEditform-heading" v-text="$t('moneyMakingMachineApp.uiEditform.delete.question', { id: removeId })">
          Are you sure you want to delete this Ui Editform?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-uiEditform"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeUiEditform()"
        >
          Delete
        </button>
      </div>
    </b-modal>

    <div v-show="uiEditforms && uiEditforms.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./ui-editform.component.ts"></script>
