<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.uiTab.home.createOrEditLabel"
          data-cy="UiTabCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.uiTab.home.createOrEditLabel')"
        >
          Create or edit a UiTab
        </h2>
        <div>
          <div class="form-group" v-if="uiTab.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="uiTab.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiTab.menuid')" for="ui-tab-menuid">Menuid</label>
            <input
              type="number"
              class="form-control"
              name="menuid"
              id="ui-tab-menuid"
              data-cy="menuid"
              :class="{ valid: !$v.uiTab.menuid.$invalid, invalid: $v.uiTab.menuid.$invalid }"
              v-model.number="$v.uiTab.menuid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiTab.code')" for="ui-tab-code">Code</label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="ui-tab-code"
              data-cy="code"
              :class="{ valid: !$v.uiTab.code.$invalid, invalid: $v.uiTab.code.$invalid }"
              v-model="$v.uiTab.code.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiTab.name')" for="ui-tab-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="ui-tab-name"
              data-cy="name"
              :class="{ valid: !$v.uiTab.name.$invalid, invalid: $v.uiTab.name.$invalid }"
              v-model="$v.uiTab.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiTab.ordernum')" for="ui-tab-ordernum">Ordernum</label>
            <input
              type="number"
              class="form-control"
              name="ordernum"
              id="ui-tab-ordernum"
              data-cy="ordernum"
              :class="{ valid: !$v.uiTab.ordernum.$invalid, invalid: $v.uiTab.ordernum.$invalid }"
              v-model.number="$v.uiTab.ordernum.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiTab.config')" for="ui-tab-config">Config</label>
            <input
              type="text"
              class="form-control"
              name="config"
              id="ui-tab-config"
              data-cy="config"
              :class="{ valid: !$v.uiTab.config.$invalid, invalid: $v.uiTab.config.$invalid }"
              v-model="$v.uiTab.config.$model"
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
            :disabled="$v.uiTab.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./ui-tab-update.component.ts"></script>
