<template>
  <div>
    <h2 id="page-heading" data-cy="DataPermissionDetailsHeading">
      <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.home.title')" id="data-permission-details-heading"
        >Data Permission Details</span
      >
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'DataPermissionDetailsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-data-permission-details"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.home.createLabel')"> Create a new Data Permission Details </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && dataPermissionDetails && dataPermissionDetails.length === 0">
      <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.home.notFound')">No dataPermissionDetails found</span>
    </div>
    <div class="table-responsive" v-if="dataPermissionDetails && dataPermissionDetails.length > 0">
      <table class="table table-striped" aria-describedby="dataPermissionDetails">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('ruleId')">
              <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.ruleId')">Rule Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ruleId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('leftBracket')">
              <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.leftBracket')">Left Bracket</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'leftBracket'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('column')">
              <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.column')">Column</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'column'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('op')">
              <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.op')">Op</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'op'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('value')">
              <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.value')">Value</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'value'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('rightBracket')">
              <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.rightBracket')">Right Bracket</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'rightBracket'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('ordernum')">
              <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.ordernum')">Ordernum</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ordernum'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('logicalRel')">
              <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.logicalRel')">Logical Rel</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'logicalRel'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="dataPermissionDetails in dataPermissionDetails" :key="dataPermissionDetails.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'DataPermissionDetailsView', params: { dataPermissionDetailsId: dataPermissionDetails.id } }">{{
                dataPermissionDetails.id
              }}</router-link>
            </td>
            <td>{{ dataPermissionDetails.ruleId }}</td>
            <td>{{ dataPermissionDetails.leftBracket }}</td>
            <td>{{ dataPermissionDetails.column }}</td>
            <td>{{ dataPermissionDetails.op }}</td>
            <td>{{ dataPermissionDetails.value }}</td>
            <td>{{ dataPermissionDetails.rightBracket }}</td>
            <td>{{ dataPermissionDetails.ordernum }}</td>
            <td>{{ dataPermissionDetails.logicalRel }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'DataPermissionDetailsView', params: { dataPermissionDetailsId: dataPermissionDetails.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'DataPermissionDetailsEdit', params: { dataPermissionDetailsId: dataPermissionDetails.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(dataPermissionDetails)"
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
          id="moneyMakingMachineApp.dataPermissionDetails.delete.question"
          data-cy="dataPermissionDetailsDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p
          id="jhi-delete-dataPermissionDetails-heading"
          v-text="$t('moneyMakingMachineApp.dataPermissionDetails.delete.question', { id: removeId })"
        >
          Are you sure you want to delete this Data Permission Details?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-dataPermissionDetails"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeDataPermissionDetails()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="dataPermissionDetails && dataPermissionDetails.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./data-permission-details.component.ts"></script>
