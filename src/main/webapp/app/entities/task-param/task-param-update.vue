<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.taskParam.home.createOrEditLabel"
          data-cy="TaskParamCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.taskParam.home.createOrEditLabel')"
        >
          Create or edit a TaskParam
        </h2>
        <div>
          <div class="form-group" v-if="taskParam.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="taskParam.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.taskParam.name')" for="task-param-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="task-param-name"
              data-cy="name"
              :class="{ valid: !$v.taskParam.name.$invalid, invalid: $v.taskParam.name.$invalid }"
              v-model="$v.taskParam.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.taskParam.cronExpression')" for="task-param-cronExpression"
              >Cron Expression</label
            >
            <input
              type="text"
              class="form-control"
              name="cronExpression"
              id="task-param-cronExpression"
              data-cy="cronExpression"
              :class="{ valid: !$v.taskParam.cronExpression.$invalid, invalid: $v.taskParam.cronExpression.$invalid }"
              v-model="$v.taskParam.cronExpression.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.taskParam.startClass')" for="task-param-startClass"
              >Start Class</label
            >
            <input
              type="text"
              class="form-control"
              name="startClass"
              id="task-param-startClass"
              data-cy="startClass"
              :class="{ valid: !$v.taskParam.startClass.$invalid, invalid: $v.taskParam.startClass.$invalid }"
              v-model="$v.taskParam.startClass.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.taskParam.enable')" for="task-param-enable">Enable</label>
            <input
              type="checkbox"
              class="form-check"
              name="enable"
              id="task-param-enable"
              data-cy="enable"
              :class="{ valid: !$v.taskParam.enable.$invalid, invalid: $v.taskParam.enable.$invalid }"
              v-model="$v.taskParam.enable.$model"
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
            :disabled="$v.taskParam.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./task-param-update.component.ts"></script>
