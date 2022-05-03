<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.uiQueryform.home.createOrEditLabel"
          data-cy="UiQueryformCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.uiQueryform.home.createOrEditLabel')"
        >
          Create or edit a UiQueryform
        </h2>
        <div>
          <div class="form-group" v-if="uiQueryform.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="uiQueryform.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiQueryform.menuid')" for="ui-queryform-menuid"
              >Menuid</label
            >
            <input
              type="number"
              class="form-control"
              name="menuid"
              id="ui-queryform-menuid"
              data-cy="menuid"
              :class="{ valid: !$v.uiQueryform.menuid.$invalid, invalid: $v.uiQueryform.menuid.$invalid }"
              v-model.number="$v.uiQueryform.menuid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiQueryform.code')" for="ui-queryform-code">Code</label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="ui-queryform-code"
              data-cy="code"
              :class="{ valid: !$v.uiQueryform.code.$invalid, invalid: $v.uiQueryform.code.$invalid }"
              v-model="$v.uiQueryform.code.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiQueryform.name')" for="ui-queryform-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="ui-queryform-name"
              data-cy="name"
              :class="{ valid: !$v.uiQueryform.name.$invalid, invalid: $v.uiQueryform.name.$invalid }"
              v-model="$v.uiQueryform.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiQueryform.ordernum')" for="ui-queryform-ordernum"
              >Ordernum</label
            >
            <input
              type="number"
              class="form-control"
              name="ordernum"
              id="ui-queryform-ordernum"
              data-cy="ordernum"
              :class="{ valid: !$v.uiQueryform.ordernum.$invalid, invalid: $v.uiQueryform.ordernum.$invalid }"
              v-model.number="$v.uiQueryform.ordernum.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiQueryform.issource')" for="ui-queryform-issource"
              >Issource</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="issource"
              id="ui-queryform-issource"
              data-cy="issource"
              :class="{ valid: !$v.uiQueryform.issource.$invalid, invalid: $v.uiQueryform.issource.$invalid }"
              v-model="$v.uiQueryform.issource.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiQueryform.requirement')" for="ui-queryform-requirement"
              >Requirement</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="requirement"
              id="ui-queryform-requirement"
              data-cy="requirement"
              :class="{ valid: !$v.uiQueryform.requirement.$invalid, invalid: $v.uiQueryform.requirement.$invalid }"
              v-model="$v.uiQueryform.requirement.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiQueryform.type')" for="ui-queryform-type">Type</label>
            <input
              type="text"
              class="form-control"
              name="type"
              id="ui-queryform-type"
              data-cy="type"
              :class="{ valid: !$v.uiQueryform.type.$invalid, invalid: $v.uiQueryform.type.$invalid }"
              v-model="$v.uiQueryform.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiQueryform.placeholder')" for="ui-queryform-placeholder"
              >Placeholder</label
            >
            <input
              type="text"
              class="form-control"
              name="placeholder"
              id="ui-queryform-placeholder"
              data-cy="placeholder"
              :class="{ valid: !$v.uiQueryform.placeholder.$invalid, invalid: $v.uiQueryform.placeholder.$invalid }"
              v-model="$v.uiQueryform.placeholder.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiQueryform.config')" for="ui-queryform-config"
              >Config</label
            >
            <input
              type="text"
              class="form-control"
              name="config"
              id="ui-queryform-config"
              data-cy="config"
              :class="{ valid: !$v.uiQueryform.config.$invalid, invalid: $v.uiQueryform.config.$invalid }"
              v-model="$v.uiQueryform.config.$model"
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
            :disabled="$v.uiQueryform.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./ui-queryform-update.component.ts"></script>
