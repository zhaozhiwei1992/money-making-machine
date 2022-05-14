<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.dataPermission.home.createOrEditLabel"
          data-cy="DataPermissionCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.dataPermission.home.createOrEditLabel')"
        >
          Create or edit a DataPermission
        </h2>
        <div>
          <div class="form-group" v-if="dataPermission.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="dataPermission.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.dataPermission.code')" for="data-permission-code"
              >Code</label
            >
            <input
              type="text"
              class="form-control"
              name="code"
              id="data-permission-code"
              data-cy="code"
              :class="{ valid: !$v.dataPermission.code.$invalid, invalid: $v.dataPermission.code.$invalid }"
              v-model="$v.dataPermission.code.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.dataPermission.name')" for="data-permission-name"
              >Name</label
            >
            <input
              type="text"
              class="form-control"
              name="name"
              id="data-permission-name"
              data-cy="name"
              :class="{ valid: !$v.dataPermission.name.$invalid, invalid: $v.dataPermission.name.$invalid }"
              v-model="$v.dataPermission.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.dataPermission.ruleSql')" for="data-permission-ruleSql"
              >Rule Sql</label
            >
            <input
              type="text"
              class="form-control"
              name="ruleSql"
              id="data-permission-ruleSql"
              data-cy="ruleSql"
              :class="{ valid: !$v.dataPermission.ruleSql.$invalid, invalid: $v.dataPermission.ruleSql.$invalid }"
              v-model="$v.dataPermission.ruleSql.$model"
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
            :disabled="$v.dataPermission.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./data-permission-update.component.ts"></script>
