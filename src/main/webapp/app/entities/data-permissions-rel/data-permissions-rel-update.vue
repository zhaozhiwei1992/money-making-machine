<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.dataPermissionsRel.home.createOrEditLabel"
          data-cy="DataPermissionsRelCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.dataPermissionsRel.home.createOrEditLabel')"
        >
          Create or edit a DataPermissionsRel
        </h2>
        <div>
          <div class="form-group" v-if="dataPermissionsRel.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="dataPermissionsRel.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.dataPermissionsRel.ruleId')"
              for="data-permissions-rel-ruleId"
              >Rule Id</label
            >
            <input
              type="text"
              class="form-control"
              name="ruleId"
              id="data-permissions-rel-ruleId"
              data-cy="ruleId"
              :class="{ valid: !$v.dataPermissionsRel.ruleId.$invalid, invalid: $v.dataPermissionsRel.ruleId.$invalid }"
              v-model="$v.dataPermissionsRel.ruleId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.dataPermissionsRel.roleId')"
              for="data-permissions-rel-roleId"
              >Role Id</label
            >
            <input
              type="text"
              class="form-control"
              name="roleId"
              id="data-permissions-rel-roleId"
              data-cy="roleId"
              :class="{ valid: !$v.dataPermissionsRel.roleId.$invalid, invalid: $v.dataPermissionsRel.roleId.$invalid }"
              v-model="$v.dataPermissionsRel.roleId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.dataPermissionsRel.menuId')"
              for="data-permissions-rel-menuId"
              >Menu Id</label
            >
            <input
              type="text"
              class="form-control"
              name="menuId"
              id="data-permissions-rel-menuId"
              data-cy="menuId"
              :class="{ valid: !$v.dataPermissionsRel.menuId.$invalid, invalid: $v.dataPermissionsRel.menuId.$invalid }"
              v-model="$v.dataPermissionsRel.menuId.$model"
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
            :disabled="$v.dataPermissionsRel.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./data-permissions-rel-update.component.ts"></script>
