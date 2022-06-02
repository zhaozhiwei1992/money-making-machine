<template>
  <div>
    <h2 id="page-heading" data-cy="RequestLoggingHeading">
      <span v-text="$t('moneyMakingMachineApp.requestLogging.home.title')" id="request-logging-heading">Request Loggings</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.requestLogging.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'RequestLoggingCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-request-logging"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.requestLogging.home.createLabel')"> Create a new Request Logging </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && requestLoggings && requestLoggings.length === 0">
      <span v-text="$t('moneyMakingMachineApp.requestLogging.home.notFound')">No requestLoggings found</span>
    </div>
    <div class="table-responsive" v-if="requestLoggings && requestLoggings.length > 0">
      <table class="table table-striped" aria-describedby="requestLoggings">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('traceId')">
              <span v-text="$t('moneyMakingMachineApp.requestLogging.traceId')">Trace Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'traceId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('loginName')">
              <span v-text="$t('moneyMakingMachineApp.requestLogging.loginName')">Login Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'loginName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('requestURI')">
              <span v-text="$t('moneyMakingMachineApp.requestLogging.requestURI')">Request URI</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'requestURI'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('clientIP')">
              <span v-text="$t('moneyMakingMachineApp.requestLogging.clientIP')">Client IP</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'clientIP'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('currentTime')">
              <span v-text="$t('moneyMakingMachineApp.requestLogging.currentTime')">Current Time</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'currentTime'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="requestLogging in requestLoggings" :key="requestLogging.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'RequestLoggingView', params: { requestLoggingId: requestLogging.id } }">{{
                requestLogging.id
              }}</router-link>
            </td>
            <td>{{ requestLogging.traceId }}</td>
            <td>{{ requestLogging.loginName }}</td>
            <td>{{ requestLogging.requestURI }}</td>
            <td>{{ requestLogging.clientIP }}</td>
            <td>{{ requestLogging.currentTime }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'RequestLoggingView', params: { requestLoggingId: requestLogging.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'RequestLoggingEdit', params: { requestLoggingId: requestLogging.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(requestLogging)"
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
          id="moneyMakingMachineApp.requestLogging.delete.question"
          data-cy="requestLoggingDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-requestLogging-heading" v-text="$t('moneyMakingMachineApp.requestLogging.delete.question', { id: removeId })">
          Are you sure you want to delete this Request Logging?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-requestLogging"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeRequestLogging()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="requestLoggings && requestLoggings.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./request-logging.component.ts"></script>
