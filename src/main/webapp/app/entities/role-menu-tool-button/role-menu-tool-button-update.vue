<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.roleMenuToolButton.home.createOrEditLabel"
          data-cy="RoleMenuToolButtonCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.roleMenuToolButton.home.createOrEditLabel')"
        >
          Create or edit a RoleMenuToolButton
        </h2>
        <div>
          <div class="form-group" v-if="roleMenuToolButton.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="roleMenuToolButton.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.roleMenuToolButton.roleId')"
              for="role-menu-tool-button-roleId"
              >Role Id</label
            >
            <input
              type="text"
              class="form-control"
              name="roleId"
              id="role-menu-tool-button-roleId"
              data-cy="roleId"
              :class="{ valid: !$v.roleMenuToolButton.roleId.$invalid, invalid: $v.roleMenuToolButton.roleId.$invalid }"
              v-model="$v.roleMenuToolButton.roleId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.roleMenuToolButton.menuId')"
              for="role-menu-tool-button-menuId"
              >Menu Id</label
            >
            <input
              type="text"
              class="form-control"
              name="menuId"
              id="role-menu-tool-button-menuId"
              data-cy="menuId"
              :class="{ valid: !$v.roleMenuToolButton.menuId.$invalid, invalid: $v.roleMenuToolButton.menuId.$invalid }"
              v-model="$v.roleMenuToolButton.menuId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.roleMenuToolButton.toolButtonId')"
              for="role-menu-tool-button-toolButtonId"
              >Tool Button Id</label
            >
            <input
              type="number"
              class="form-control"
              name="toolButtonId"
              id="role-menu-tool-button-toolButtonId"
              data-cy="toolButtonId"
              :class="{ valid: !$v.roleMenuToolButton.toolButtonId.$invalid, invalid: $v.roleMenuToolButton.toolButtonId.$invalid }"
              v-model.number="$v.roleMenuToolButton.toolButtonId.$model"
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
            :disabled="$v.roleMenuToolButton.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./role-menu-tool-button-update.component.ts"></script>
