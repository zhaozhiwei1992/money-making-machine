<template>
  <div>
    <h2 id="page-heading" data-cy="TaskParamHeading">
      <span v-text="$t('moneyMakingMachineApp.taskParam.home.title')" id="task-param-heading">Task Params</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.taskParam.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'TaskParamCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-task-param"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.taskParam.home.createLabel')"> Create a new Task Param </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && taskParams && taskParams.length === 0">
      <span v-text="$t('moneyMakingMachineApp.taskParam.home.notFound')">No taskParams found</span>
    </div>
    <div class="table-responsive" v-if="taskParams && taskParams.length > 0">
      <table class="table table-striped" aria-describedby="taskParams">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span v-text="$t('moneyMakingMachineApp.taskParam.name')">Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('cronExpression')">
              <span v-text="$t('moneyMakingMachineApp.taskParam.cronExpression')">Cron Expression</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'cronExpression'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('startClass')">
              <span v-text="$t('moneyMakingMachineApp.taskParam.startClass')">Start Class</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'startClass'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('enable')">
              <span v-text="$t('moneyMakingMachineApp.taskParam.enable')">Enable</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'enable'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="taskParam in taskParams" :key="taskParam.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TaskParamView', params: { taskParamId: taskParam.id } }">{{ taskParam.id }}</router-link>
            </td>
            <td>{{ taskParam.name }}</td>
            <td>{{ taskParam.cronExpression }}</td>
            <td>{{ taskParam.startClass }}</td>
            <td>{{ taskParam.enable }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TaskParamView', params: { taskParamId: taskParam.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TaskParamEdit', params: { taskParamId: taskParam.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(taskParam)"
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
          id="moneyMakingMachineApp.taskParam.delete.question"
          data-cy="taskParamDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-taskParam-heading" v-text="$t('moneyMakingMachineApp.taskParam.delete.question', { id: removeId })">
          Are you sure you want to delete this Task Param?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-taskParam"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTaskParam()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="taskParams && taskParams.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-param.component.ts"></script>
