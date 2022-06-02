<template>
  <div>
    <h2 id="page-heading" data-cy="SlowSqlLoggingHeading">
      <span v-text="$t('moneyMakingMachineApp.slowSqlLogging.home.title')" id="slow-sql-logging-heading">Slow Sql Loggings</span>
      <div class="d-flex justify-content-end">
        <span style="margin-right: 5px">搜索条件:</span>
        <input type="text" v-model="condition" style="margin-right: 5px" />

        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.slowSqlLogging.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'SlowSqlLoggingCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-slow-sql-logging"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.slowSqlLogging.home.createLabel')"> Create a new Slow Sql Logging </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && slowSqlLoggings && slowSqlLoggings.length === 0">
      <span v-text="$t('moneyMakingMachineApp.slowSqlLogging.home.notFound')">No slowSqlLoggings found</span>
    </div>
    <div class="table-responsive" v-if="slowSqlLoggings && slowSqlLoggings.length > 0">
      <table class="table table-striped" aria-describedby="slowSqlLoggings">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('traceId')">
              <span v-text="$t('moneyMakingMachineApp.slowSqlLogging.traceId')">Trace Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'traceId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('currentTime')">
              <span v-text="$t('moneyMakingMachineApp.slowSqlLogging.currentTime')">Current Time</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'currentTime'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sql')">
              <span v-text="$t('moneyMakingMachineApp.slowSqlLogging.sql')">Sql</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sql'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('executeMillis')">
              <span v-text="$t('moneyMakingMachineApp.slowSqlLogging.executeMillis')">Execute Millis</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'executeMillis'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('executeParams')">
              <span v-text="$t('moneyMakingMachineApp.slowSqlLogging.executeParams')">Execute Params</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'executeParams'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="slowSqlLogging in slowSqlLoggings" :key="slowSqlLogging.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SlowSqlLoggingView', params: { slowSqlLoggingId: slowSqlLogging.id } }">{{
                slowSqlLogging.id
              }}</router-link>
            </td>
            <td>{{ slowSqlLogging.traceId }}</td>
            <td>{{ slowSqlLogging.currentTime }}</td>
            <td>{{ slowSqlLogging.sql }}</td>
            <td>{{ slowSqlLogging.executeMillis }}</td>
            <td>{{ slowSqlLogging.executeParams }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'SlowSqlLoggingView', params: { slowSqlLoggingId: slowSqlLogging.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'SlowSqlLoggingEdit', params: { slowSqlLoggingId: slowSqlLogging.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(slowSqlLogging)"
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
          id="moneyMakingMachineApp.slowSqlLogging.delete.question"
          data-cy="slowSqlLoggingDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-slowSqlLogging-heading" v-text="$t('moneyMakingMachineApp.slowSqlLogging.delete.question', { id: removeId })">
          Are you sure you want to delete this Slow Sql Logging?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-slowSqlLogging"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeSlowSqlLogging()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="slowSqlLoggings && slowSqlLoggings.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./slow-sql-logging.component.ts"></script>
