<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.slowSqlLogging.home.createOrEditLabel"
          data-cy="SlowSqlLoggingCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.slowSqlLogging.home.createOrEditLabel')"
        >
          Create or edit a SlowSqlLogging
        </h2>
        <div>
          <div class="form-group" v-if="slowSqlLogging.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="slowSqlLogging.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.slowSqlLogging.traceId')" for="slow-sql-logging-traceId"
              >Trace Id</label
            >
            <input
              type="text"
              class="form-control"
              name="traceId"
              id="slow-sql-logging-traceId"
              data-cy="traceId"
              :class="{ valid: !$v.slowSqlLogging.traceId.$invalid, invalid: $v.slowSqlLogging.traceId.$invalid }"
              v-model="$v.slowSqlLogging.traceId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.slowSqlLogging.currentTime')"
              for="slow-sql-logging-currentTime"
              >Current Time</label
            >
            <input
              type="text"
              class="form-control"
              name="currentTime"
              id="slow-sql-logging-currentTime"
              data-cy="currentTime"
              :class="{ valid: !$v.slowSqlLogging.currentTime.$invalid, invalid: $v.slowSqlLogging.currentTime.$invalid }"
              v-model="$v.slowSqlLogging.currentTime.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.slowSqlLogging.sql')" for="slow-sql-logging-sql">Sql</label>
            <input
              type="text"
              class="form-control"
              name="sql"
              id="slow-sql-logging-sql"
              data-cy="sql"
              :class="{ valid: !$v.slowSqlLogging.sql.$invalid, invalid: $v.slowSqlLogging.sql.$invalid }"
              v-model="$v.slowSqlLogging.sql.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.slowSqlLogging.executeMillis')"
              for="slow-sql-logging-executeMillis"
              >Execute Millis</label
            >
            <input
              type="text"
              class="form-control"
              name="executeMillis"
              id="slow-sql-logging-executeMillis"
              data-cy="executeMillis"
              :class="{ valid: !$v.slowSqlLogging.executeMillis.$invalid, invalid: $v.slowSqlLogging.executeMillis.$invalid }"
              v-model="$v.slowSqlLogging.executeMillis.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.slowSqlLogging.executeParams')"
              for="slow-sql-logging-executeParams"
              >Execute Params</label
            >
            <input
              type="text"
              class="form-control"
              name="executeParams"
              id="slow-sql-logging-executeParams"
              data-cy="executeParams"
              :class="{ valid: !$v.slowSqlLogging.executeParams.$invalid, invalid: $v.slowSqlLogging.executeParams.$invalid }"
              v-model="$v.slowSqlLogging.executeParams.$model"
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
            :disabled="$v.slowSqlLogging.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./slow-sql-logging-update.component.ts"></script>
