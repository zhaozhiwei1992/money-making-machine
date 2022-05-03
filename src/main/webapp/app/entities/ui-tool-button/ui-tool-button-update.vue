<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.uiToolButton.home.createOrEditLabel"
          data-cy="UiToolButtonCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.uiToolButton.home.createOrEditLabel')"
        >
          Create or edit a UiToolButton
        </h2>
        <div>
          <div class="form-group" v-if="uiToolButton.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="uiToolButton.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiToolButton.menuid')" for="ui-tool-button-menuid"
              >Menuid</label
            >
            <input
              type="number"
              class="form-control"
              name="menuid"
              id="ui-tool-button-menuid"
              data-cy="menuid"
              :class="{ valid: !$v.uiToolButton.menuid.$invalid, invalid: $v.uiToolButton.menuid.$invalid }"
              v-model.number="$v.uiToolButton.menuid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiToolButton.code')" for="ui-tool-button-code">Code</label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="ui-tool-button-code"
              data-cy="code"
              :class="{ valid: !$v.uiToolButton.code.$invalid, invalid: $v.uiToolButton.code.$invalid }"
              v-model="$v.uiToolButton.code.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiToolButton.name')" for="ui-tool-button-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="ui-tool-button-name"
              data-cy="name"
              :class="{ valid: !$v.uiToolButton.name.$invalid, invalid: $v.uiToolButton.name.$invalid }"
              v-model="$v.uiToolButton.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiToolButton.ordernum')" for="ui-tool-button-ordernum"
              >Ordernum</label
            >
            <input
              type="number"
              class="form-control"
              name="ordernum"
              id="ui-tool-button-ordernum"
              data-cy="ordernum"
              :class="{ valid: !$v.uiToolButton.ordernum.$invalid, invalid: $v.uiToolButton.ordernum.$invalid }"
              v-model.number="$v.uiToolButton.ordernum.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiToolButton.action')" for="ui-tool-button-action"
              >Action</label
            >
            <input
              type="text"
              class="form-control"
              name="action"
              id="ui-tool-button-action"
              data-cy="action"
              :class="{ valid: !$v.uiToolButton.action.$invalid, invalid: $v.uiToolButton.action.$invalid }"
              v-model="$v.uiToolButton.action.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiToolButton.config')" for="ui-tool-button-config"
              >Config</label
            >
            <input
              type="text"
              class="form-control"
              name="config"
              id="ui-tool-button-config"
              data-cy="config"
              :class="{ valid: !$v.uiToolButton.config.$invalid, invalid: $v.uiToolButton.config.$invalid }"
              v-model="$v.uiToolButton.config.$model"
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
            :disabled="$v.uiToolButton.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./ui-tool-button-update.component.ts"></script>
