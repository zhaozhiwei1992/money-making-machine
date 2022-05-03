<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.uiEditform.home.createOrEditLabel"
          data-cy="UiEditformCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.uiEditform.home.createOrEditLabel')"
        >
          Create or edit a UiEditform
        </h2>
        <div>
          <div class="form-group" v-if="uiEditform.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="uiEditform.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiEditform.menuid')" for="ui-editform-menuid">Menuid</label>
            <input
              type="number"
              class="form-control"
              name="menuid"
              id="ui-editform-menuid"
              data-cy="menuid"
              :class="{ valid: !$v.uiEditform.menuid.$invalid, invalid: $v.uiEditform.menuid.$invalid }"
              v-model.number="$v.uiEditform.menuid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiEditform.code')" for="ui-editform-code">Code</label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="ui-editform-code"
              data-cy="code"
              :class="{ valid: !$v.uiEditform.code.$invalid, invalid: $v.uiEditform.code.$invalid }"
              v-model="$v.uiEditform.code.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiEditform.name')" for="ui-editform-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="ui-editform-name"
              data-cy="name"
              :class="{ valid: !$v.uiEditform.name.$invalid, invalid: $v.uiEditform.name.$invalid }"
              v-model="$v.uiEditform.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiEditform.ordernum')" for="ui-editform-ordernum"
              >Ordernum</label
            >
            <input
              type="number"
              class="form-control"
              name="ordernum"
              id="ui-editform-ordernum"
              data-cy="ordernum"
              :class="{ valid: !$v.uiEditform.ordernum.$invalid, invalid: $v.uiEditform.ordernum.$invalid }"
              v-model.number="$v.uiEditform.ordernum.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiEditform.issource')" for="ui-editform-issource"
              >Issource</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="issource"
              id="ui-editform-issource"
              data-cy="issource"
              :class="{ valid: !$v.uiEditform.issource.$invalid, invalid: $v.uiEditform.issource.$invalid }"
              v-model="$v.uiEditform.issource.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiEditform.isedit')" for="ui-editform-isedit">Isedit</label>
            <input
              type="checkbox"
              class="form-check"
              name="isedit"
              id="ui-editform-isedit"
              data-cy="isedit"
              :class="{ valid: !$v.uiEditform.isedit.$invalid, invalid: $v.uiEditform.isedit.$invalid }"
              v-model="$v.uiEditform.isedit.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiEditform.requirement')" for="ui-editform-requirement"
              >Requirement</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="requirement"
              id="ui-editform-requirement"
              data-cy="requirement"
              :class="{ valid: !$v.uiEditform.requirement.$invalid, invalid: $v.uiEditform.requirement.$invalid }"
              v-model="$v.uiEditform.requirement.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiEditform.type')" for="ui-editform-type">Type</label>
            <input
              type="text"
              class="form-control"
              name="type"
              id="ui-editform-type"
              data-cy="type"
              :class="{ valid: !$v.uiEditform.type.$invalid, invalid: $v.uiEditform.type.$invalid }"
              v-model="$v.uiEditform.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiEditform.placeholder')" for="ui-editform-placeholder"
              >Placeholder</label
            >
            <input
              type="text"
              class="form-control"
              name="placeholder"
              id="ui-editform-placeholder"
              data-cy="placeholder"
              :class="{ valid: !$v.uiEditform.placeholder.$invalid, invalid: $v.uiEditform.placeholder.$invalid }"
              v-model="$v.uiEditform.placeholder.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiEditform.config')" for="ui-editform-config">Config</label>
            <input
              type="text"
              class="form-control"
              name="config"
              id="ui-editform-config"
              data-cy="config"
              :class="{ valid: !$v.uiEditform.config.$invalid, invalid: $v.uiEditform.config.$invalid }"
              v-model="$v.uiEditform.config.$model"
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
            :disabled="$v.uiEditform.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./ui-editform-update.component.ts"></script>
