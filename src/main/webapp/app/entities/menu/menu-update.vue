<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.menu.home.createOrEditLabel"
          data-cy="MenuCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.menu.home.createOrEditLabel')"
        >
          Create or edit a Menu
        </h2>
        <div>
          <div class="form-group" v-if="menu.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="menu.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.menu.url')" for="menu-url">Url</label>
            <input
              type="text"
              class="form-control"
              name="url"
              id="menu-url"
              data-cy="url"
              :class="{ valid: !$v.menu.url.$invalid, invalid: $v.menu.url.$invalid }"
              v-model="$v.menu.url.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.menu.name')" for="menu-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="menu-name"
              data-cy="name"
              :class="{ valid: !$v.menu.name.$invalid, invalid: $v.menu.name.$invalid }"
              v-model="$v.menu.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.menu.iconCls')" for="menu-iconCls">Icon Cls</label>
            <input
              type="text"
              class="form-control"
              name="iconCls"
              id="menu-iconCls"
              data-cy="iconCls"
              :class="{ valid: !$v.menu.iconCls.$invalid, invalid: $v.menu.iconCls.$invalid }"
              v-model="$v.menu.iconCls.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.menu.ordernum')" for="menu-ordernum">Ordernum</label>
            <input
              type="number"
              class="form-control"
              name="ordernum"
              id="menu-ordernum"
              data-cy="ordernum"
              :class="{ valid: !$v.menu.ordernum.$invalid, invalid: $v.menu.ordernum.$invalid }"
              v-model.number="$v.menu.ordernum.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.menu.keepAlive')" for="menu-keepAlive">Keep Alive</label>
            <input
              type="checkbox"
              class="form-check"
              name="keepAlive"
              id="menu-keepAlive"
              data-cy="keepAlive"
              :class="{ valid: !$v.menu.keepAlive.$invalid, invalid: $v.menu.keepAlive.$invalid }"
              v-model="$v.menu.keepAlive.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.menu.requireAuth')" for="menu-requireAuth"
              >Require Auth</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="requireAuth"
              id="menu-requireAuth"
              data-cy="requireAuth"
              :class="{ valid: !$v.menu.requireAuth.$invalid, invalid: $v.menu.requireAuth.$invalid }"
              v-model="$v.menu.requireAuth.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.menu.parentId')" for="menu-parentId">Parent Id</label>
            <input
              type="number"
              class="form-control"
              name="parentId"
              id="menu-parentId"
              data-cy="parentId"
              :class="{ valid: !$v.menu.parentId.$invalid, invalid: $v.menu.parentId.$invalid }"
              v-model.number="$v.menu.parentId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.menu.enabled')" for="menu-enabled">Enabled</label>
            <input
              type="checkbox"
              class="form-check"
              name="enabled"
              id="menu-enabled"
              data-cy="enabled"
              :class="{ valid: !$v.menu.enabled.$invalid, invalid: $v.menu.enabled.$invalid }"
              v-model="$v.menu.enabled.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.menu.config')" for="menu-config">Config</label>
            <input
              type="text"
              class="form-control"
              name="config"
              id="menu-config"
              data-cy="config"
              :class="{ valid: !$v.menu.config.$invalid, invalid: $v.menu.config.$invalid }"
              v-model="$v.menu.config.$model"
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
            :disabled="$v.menu.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./menu-update.component.ts"></script>
