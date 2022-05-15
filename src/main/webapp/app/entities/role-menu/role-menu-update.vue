<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.roleMenu.home.createOrEditLabel"
          data-cy="RoleMenuCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.roleMenu.home.createOrEditLabel')"
        >
          Create or edit a RoleMenu
        </h2>
        <div>
          <div class="form-group" v-if="roleMenu.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="roleMenu.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.roleMenu.roleId')" for="role-menu-roleId">Role Id</label>
            <input
              type="text"
              class="form-control"
              name="roleId"
              id="role-menu-roleId"
              data-cy="roleId"
              :class="{ valid: !$v.roleMenu.roleId.$invalid, invalid: $v.roleMenu.roleId.$invalid }"
              v-model="$v.roleMenu.roleId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.roleMenu.menuId')" for="role-menu-menuId">Menu Id</label>
            <input
              type="text"
              class="form-control"
              name="menuId"
              id="role-menu-menuId"
              data-cy="menuId"
              :class="{ valid: !$v.roleMenu.menuId.$invalid, invalid: $v.roleMenu.menuId.$invalid }"
              v-model="$v.roleMenu.menuId.$model"
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
            :disabled="$v.roleMenu.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./role-menu-update.component.ts"></script>
