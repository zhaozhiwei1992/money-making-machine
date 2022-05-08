<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.leaveType.home.createOrEditLabel"
          data-cy="LeaveTypeCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.leaveType.home.createOrEditLabel')"
        >
          Create or edit a LeaveType
        </h2>
        <div>
          <div class="form-group" v-if="leaveType.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="leaveType.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.leaveType.code')" for="leave-type-code">Code</label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="leave-type-code"
              data-cy="code"
              :class="{ valid: !$v.leaveType.code.$invalid, invalid: $v.leaveType.code.$invalid }"
              v-model="$v.leaveType.code.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.leaveType.name')" for="leave-type-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="leave-type-name"
              data-cy="name"
              :class="{ valid: !$v.leaveType.name.$invalid, invalid: $v.leaveType.name.$invalid }"
              v-model="$v.leaveType.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.leaveType.parentid')" for="leave-type-parentid"
              >Parentid</label
            >
            <input
              type="number"
              class="form-control"
              name="parentid"
              id="leave-type-parentid"
              data-cy="parentid"
              :class="{ valid: !$v.leaveType.parentid.$invalid, invalid: $v.leaveType.parentid.$invalid }"
              v-model.number="$v.leaveType.parentid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.leaveType.enabled')" for="leave-type-enabled"
              >Enabled</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="enabled"
              id="leave-type-enabled"
              data-cy="enabled"
              :class="{ valid: !$v.leaveType.enabled.$invalid, invalid: $v.leaveType.enabled.$invalid }"
              v-model="$v.leaveType.enabled.$model"
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
            :disabled="$v.leaveType.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./leave-type-update.component.ts"></script>
