<template>
  <div>
    <h2 id="page-heading" data-cy="LeaveSlipHeading">
      <span v-text="$t('moneyMakingMachineApp.leaveSlip.home.title')" id="leave-slip-heading">Leave Slips</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.leaveSlip.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'LeaveSlipCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-leave-slip"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.leaveSlip.home.createLabel')"> Create a new Leave Slip </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && leaveSlips && leaveSlips.length === 0">
      <span v-text="$t('moneyMakingMachineApp.leaveSlip.home.notFound')">No leaveSlips found</span>
    </div>
    <div class="table-responsive" v-if="leaveSlips && leaveSlips.length > 0">
      <table class="table table-striped" aria-describedby="leaveSlips">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.leaveSlip.type')">Type</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.leaveSlip.startTime')">Start Time</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.leaveSlip.endTime')">End Time</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.leaveSlip.reason')">Reason</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.leaveSlip.file')">File</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.leaveSlip.superior')">Superior</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.leaveSlip.wfstatus')">Wfstatus</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.leaveSlip.leavePerson')">Leave Person</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="leaveSlip in leaveSlips" :key="leaveSlip.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'LeaveSlipView', params: { leaveSlipId: leaveSlip.id } }">{{ leaveSlip.id }}</router-link>
            </td>
            <td>{{ leaveSlip.type }}</td>
            <td>{{ leaveSlip.startTime }}</td>
            <td>{{ leaveSlip.endTime }}</td>
            <td>{{ leaveSlip.reason }}</td>
            <td>{{ leaveSlip.file }}</td>
            <td>{{ leaveSlip.superior }}</td>
            <td>{{ leaveSlip.wfstatus }}</td>
            <td>{{ leaveSlip.leavePerson }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'LeaveSlipView', params: { leaveSlipId: leaveSlip.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'LeaveSlipEdit', params: { leaveSlipId: leaveSlip.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(leaveSlip)"
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
          id="moneyMakingMachineApp.leaveSlip.delete.question"
          data-cy="leaveSlipDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-leaveSlip-heading" v-text="$t('moneyMakingMachineApp.leaveSlip.delete.question', { id: removeId })">
          Are you sure you want to delete this Leave Slip?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-leaveSlip"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeLeaveSlip()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./leave-slip.component.ts"></script>
