<template>
  <div>
    <h2 id="page-heading" data-cy="LeaveTypeHeading">
      <span v-text="$t('moneyMakingMachineApp.leaveType.home.title')" id="leave-type-heading">Leave Types</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.leaveType.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'LeaveTypeCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-leave-type"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.leaveType.home.createLabel')"> Create a new Leave Type </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && leaveTypes && leaveTypes.length === 0">
      <span v-text="$t('moneyMakingMachineApp.leaveType.home.notFound')">No leaveTypes found</span>
    </div>
    <div class="table-responsive" v-if="leaveTypes && leaveTypes.length > 0">
      <table class="table table-striped" aria-describedby="leaveTypes">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.leaveType.code')">Code</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.leaveType.name')">Name</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.leaveType.parentid')">Parentid</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.leaveType.enabled')">Enabled</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="leaveType in leaveTypes" :key="leaveType.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'LeaveTypeView', params: { leaveTypeId: leaveType.id } }">{{ leaveType.id }}</router-link>
            </td>
            <td>{{ leaveType.code }}</td>
            <td>{{ leaveType.name }}</td>
            <td>{{ leaveType.parentid }}</td>
            <td>{{ leaveType.enabled }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'LeaveTypeView', params: { leaveTypeId: leaveType.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'LeaveTypeEdit', params: { leaveTypeId: leaveType.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(leaveType)"
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
          id="moneyMakingMachineApp.leaveType.delete.question"
          data-cy="leaveTypeDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-leaveType-heading" v-text="$t('moneyMakingMachineApp.leaveType.delete.question', { id: removeId })">
          Are you sure you want to delete this Leave Type?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-leaveType"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeLeaveType()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./leave-type.component.ts"></script>
