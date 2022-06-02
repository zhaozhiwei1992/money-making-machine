<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.systemParam.home.createOrEditLabel"
          data-cy="SystemParamCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.systemParam.home.createOrEditLabel')"
        >
          Create or edit a SystemParam
        </h2>
        <div>
          <div class="form-group" v-if="systemParam.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="systemParam.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.systemParam.code')" for="system-param-code">Code</label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="system-param-code"
              data-cy="code"
              :class="{ valid: !$v.systemParam.code.$invalid, invalid: $v.systemParam.code.$invalid }"
              v-model="$v.systemParam.code.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.systemParam.name')" for="system-param-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="system-param-name"
              data-cy="name"
              :class="{ valid: !$v.systemParam.name.$invalid, invalid: $v.systemParam.name.$invalid }"
              v-model="$v.systemParam.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.systemParam.value')" for="system-param-value">Value</label>
            <input
              type="text"
              class="form-control"
              name="value"
              id="system-param-value"
              data-cy="value"
              :class="{ valid: !$v.systemParam.value.$invalid, invalid: $v.systemParam.value.$invalid }"
              v-model="$v.systemParam.value.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.systemParam.remark')" for="system-param-remark"
              >Remark</label
            >
            <input
              type="text"
              class="form-control"
              name="remark"
              id="system-param-remark"
              data-cy="remark"
              :class="{ valid: !$v.systemParam.remark.$invalid, invalid: $v.systemParam.remark.$invalid }"
              v-model="$v.systemParam.remark.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.systemParam.enable')" for="system-param-enable"
              >Enable</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="enable"
              id="system-param-enable"
              data-cy="enable"
              :class="{ valid: !$v.systemParam.enable.$invalid, invalid: $v.systemParam.enable.$invalid }"
              v-model="$v.systemParam.enable.$model"
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
            :disabled="$v.systemParam.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./system-param-update.component.ts"></script>
