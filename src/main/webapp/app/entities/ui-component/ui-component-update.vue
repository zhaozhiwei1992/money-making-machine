<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.uiComponent.home.createOrEditLabel"
          data-cy="UiComponentCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.uiComponent.home.createOrEditLabel')"
        >
          Create or edit a UiComponent
        </h2>
        <div>
          <div class="form-group" v-if="uiComponent.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="uiComponent.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiComponent.menuid')" for="ui-component-menuid"
              >Menuid</label
            >
            <input
              type="number"
              class="form-control"
              name="menuid"
              id="ui-component-menuid"
              data-cy="menuid"
              :class="{ valid: !$v.uiComponent.menuid.$invalid, invalid: $v.uiComponent.menuid.$invalid }"
              v-model.number="$v.uiComponent.menuid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiComponent.ordernum')" for="ui-component-ordernum"
              >Ordernum</label
            >
            <input
              type="number"
              class="form-control"
              name="ordernum"
              id="ui-component-ordernum"
              data-cy="ordernum"
              :class="{ valid: !$v.uiComponent.ordernum.$invalid, invalid: $v.uiComponent.ordernum.$invalid }"
              v-model.number="$v.uiComponent.ordernum.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiComponent.componentid')" for="ui-component-componentid"
              >Componentid</label
            >
            <input
              type="text"
              class="form-control"
              name="componentid"
              id="ui-component-componentid"
              data-cy="componentid"
              :class="{ valid: !$v.uiComponent.componentid.$invalid, invalid: $v.uiComponent.componentid.$invalid }"
              v-model="$v.uiComponent.componentid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiComponent.config')" for="ui-component-config"
              >Config</label
            >
            <input
              type="text"
              class="form-control"
              name="config"
              id="ui-component-config"
              data-cy="config"
              :class="{ valid: !$v.uiComponent.config.$invalid, invalid: $v.uiComponent.config.$invalid }"
              v-model="$v.uiComponent.config.$model"
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
            :disabled="$v.uiComponent.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./ui-component-update.component.ts"></script>
