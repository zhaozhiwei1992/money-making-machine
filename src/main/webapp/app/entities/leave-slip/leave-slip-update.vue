<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.leaveSlip.home.createOrEditLabel"
          data-cy="LeaveSlipCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.leaveSlip.home.createOrEditLabel')"
        >
          Create or edit a LeaveSlip
        </h2>
        <div>
          <div class="form-group" v-if="leaveSlip.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="leaveSlip.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.leaveSlip.type')" for="leave-slip-type">Type</label>
            <input
              type="text"
              class="form-control"
              name="type"
              id="leave-slip-type"
              data-cy="type"
              :class="{ valid: !$v.leaveSlip.type.$invalid, invalid: $v.leaveSlip.type.$invalid }"
              v-model="$v.leaveSlip.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.leaveSlip.startTime')" for="leave-slip-startTime"
              >Start Time</label
            >
            <input
              type="text"
              class="form-control"
              name="startTime"
              id="leave-slip-startTime"
              data-cy="startTime"
              :class="{ valid: !$v.leaveSlip.startTime.$invalid, invalid: $v.leaveSlip.startTime.$invalid }"
              v-model="$v.leaveSlip.startTime.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.leaveSlip.endTime')" for="leave-slip-endTime"
              >End Time</label
            >
            <input
              type="text"
              class="form-control"
              name="endTime"
              id="leave-slip-endTime"
              data-cy="endTime"
              :class="{ valid: !$v.leaveSlip.endTime.$invalid, invalid: $v.leaveSlip.endTime.$invalid }"
              v-model="$v.leaveSlip.endTime.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.leaveSlip.reason')" for="leave-slip-reason">Reason</label>
            <input
              type="text"
              class="form-control"
              name="reason"
              id="leave-slip-reason"
              data-cy="reason"
              :class="{ valid: !$v.leaveSlip.reason.$invalid, invalid: $v.leaveSlip.reason.$invalid }"
              v-model="$v.leaveSlip.reason.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.leaveSlip.file')" for="leave-slip-file">File</label>
            <input
              type="text"
              class="form-control"
              name="file"
              id="leave-slip-file"
              data-cy="file"
              :class="{ valid: !$v.leaveSlip.file.$invalid, invalid: $v.leaveSlip.file.$invalid }"
              v-model="$v.leaveSlip.file.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.leaveSlip.superior')" for="leave-slip-superior"
              >Superior</label
            >
            <input
              type="number"
              class="form-control"
              name="superior"
              id="leave-slip-superior"
              data-cy="superior"
              :class="{ valid: !$v.leaveSlip.superior.$invalid, invalid: $v.leaveSlip.superior.$invalid }"
              v-model.number="$v.leaveSlip.superior.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.leaveSlip.wfstatus')" for="leave-slip-wfstatus"
              >Wfstatus</label
            >
            <input
              type="text"
              class="form-control"
              name="wfstatus"
              id="leave-slip-wfstatus"
              data-cy="wfstatus"
              :class="{ valid: !$v.leaveSlip.wfstatus.$invalid, invalid: $v.leaveSlip.wfstatus.$invalid }"
              v-model="$v.leaveSlip.wfstatus.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.leaveSlip.leavePerson')" for="leave-slip-leavePerson"
              >Leave Person</label
            >
            <input
              type="text"
              class="form-control"
              name="leavePerson"
              id="leave-slip-leavePerson"
              data-cy="leavePerson"
              :class="{ valid: !$v.leaveSlip.leavePerson.$invalid, invalid: $v.leaveSlip.leavePerson.$invalid }"
              v-model="$v.leaveSlip.leavePerson.$model"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.leaveSlip.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./leave-slip-update.component.ts"></script>
