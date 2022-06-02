<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.requestLogging.home.createOrEditLabel"
          data-cy="RequestLoggingCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.requestLogging.home.createOrEditLabel')"
        >
          Create or edit a RequestLogging
        </h2>
        <div>
          <div class="form-group" v-if="requestLogging.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="requestLogging.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.requestLogging.traceId')" for="request-logging-traceId"
              >Trace Id</label
            >
            <input
              type="text"
              class="form-control"
              name="traceId"
              id="request-logging-traceId"
              data-cy="traceId"
              :class="{ valid: !$v.requestLogging.traceId.$invalid, invalid: $v.requestLogging.traceId.$invalid }"
              v-model="$v.requestLogging.traceId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.requestLogging.loginName')" for="request-logging-loginName"
              >Login Name</label
            >
            <input
              type="text"
              class="form-control"
              name="loginName"
              id="request-logging-loginName"
              data-cy="loginName"
              :class="{ valid: !$v.requestLogging.loginName.$invalid, invalid: $v.requestLogging.loginName.$invalid }"
              v-model="$v.requestLogging.loginName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.requestLogging.requestURI')"
              for="request-logging-requestURI"
              >Request URI</label
            >
            <input
              type="text"
              class="form-control"
              name="requestURI"
              id="request-logging-requestURI"
              data-cy="requestURI"
              :class="{ valid: !$v.requestLogging.requestURI.$invalid, invalid: $v.requestLogging.requestURI.$invalid }"
              v-model="$v.requestLogging.requestURI.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.requestLogging.clientIP')" for="request-logging-clientIP"
              >Client IP</label
            >
            <input
              type="text"
              class="form-control"
              name="clientIP"
              id="request-logging-clientIP"
              data-cy="clientIP"
              :class="{ valid: !$v.requestLogging.clientIP.$invalid, invalid: $v.requestLogging.clientIP.$invalid }"
              v-model="$v.requestLogging.clientIP.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.requestLogging.currentTime')"
              for="request-logging-currentTime"
              >Current Time</label
            >
            <input
              type="text"
              class="form-control"
              name="currentTime"
              id="request-logging-currentTime"
              data-cy="currentTime"
              :class="{ valid: !$v.requestLogging.currentTime.$invalid, invalid: $v.requestLogging.currentTime.$invalid }"
              v-model="$v.requestLogging.currentTime.$model"
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
            :disabled="$v.requestLogging.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./request-logging-update.component.ts"></script>
