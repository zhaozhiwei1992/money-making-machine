<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.dataPermissionDetails.home.createOrEditLabel"
          data-cy="DataPermissionDetailsCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.dataPermissionDetails.home.createOrEditLabel')"
        >
          Create or edit a DataPermissionDetails
        </h2>
        <div>
          <div class="form-group" v-if="dataPermissionDetails.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="dataPermissionDetails.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.dataPermissionDetails.ruleId')"
              for="data-permission-details-ruleId"
              >Rule Id</label
            >
            <input
              type="text"
              class="form-control"
              name="ruleId"
              id="data-permission-details-ruleId"
              data-cy="ruleId"
              :class="{ valid: !$v.dataPermissionDetails.ruleId.$invalid, invalid: $v.dataPermissionDetails.ruleId.$invalid }"
              v-model="$v.dataPermissionDetails.ruleId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.dataPermissionDetails.leftBracket')"
              for="data-permission-details-leftBracket"
              >Left Bracket</label
            >
            <input
              type="text"
              class="form-control"
              name="leftBracket"
              id="data-permission-details-leftBracket"
              data-cy="leftBracket"
              :class="{ valid: !$v.dataPermissionDetails.leftBracket.$invalid, invalid: $v.dataPermissionDetails.leftBracket.$invalid }"
              v-model="$v.dataPermissionDetails.leftBracket.$model"
            />
            <div v-if="$v.dataPermissionDetails.leftBracket.$anyDirty && $v.dataPermissionDetails.leftBracket.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.dataPermissionDetails.leftBracket.pattern"
                v-text="$t('entity.validation.pattern', { pattern: 'Left Bracket' })"
              >
                This field should follow pattern for "Left Bracket".
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.dataPermissionDetails.column')"
              for="data-permission-details-column"
              >Column</label
            >
            <input
              type="text"
              class="form-control"
              name="column"
              id="data-permission-details-column"
              data-cy="column"
              :class="{ valid: !$v.dataPermissionDetails.column.$invalid, invalid: $v.dataPermissionDetails.column.$invalid }"
              v-model="$v.dataPermissionDetails.column.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.dataPermissionDetails.op')" for="data-permission-details-op"
              >Op</label
            >
            <input
              type="text"
              class="form-control"
              name="op"
              id="data-permission-details-op"
              data-cy="op"
              :class="{ valid: !$v.dataPermissionDetails.op.$invalid, invalid: $v.dataPermissionDetails.op.$invalid }"
              v-model="$v.dataPermissionDetails.op.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.dataPermissionDetails.value')"
              for="data-permission-details-value"
              >Value</label
            >
            <input
              type="text"
              class="form-control"
              name="value"
              id="data-permission-details-value"
              data-cy="value"
              :class="{ valid: !$v.dataPermissionDetails.value.$invalid, invalid: $v.dataPermissionDetails.value.$invalid }"
              v-model="$v.dataPermissionDetails.value.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.dataPermissionDetails.rightBracket')"
              for="data-permission-details-rightBracket"
              >Right Bracket</label
            >
            <input
              type="text"
              class="form-control"
              name="rightBracket"
              id="data-permission-details-rightBracket"
              data-cy="rightBracket"
              :class="{ valid: !$v.dataPermissionDetails.rightBracket.$invalid, invalid: $v.dataPermissionDetails.rightBracket.$invalid }"
              v-model="$v.dataPermissionDetails.rightBracket.$model"
            />
            <div v-if="$v.dataPermissionDetails.rightBracket.$anyDirty && $v.dataPermissionDetails.rightBracket.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.dataPermissionDetails.rightBracket.pattern"
                v-text="$t('entity.validation.pattern', { pattern: 'Right Bracket' })"
              >
                This field should follow pattern for "Right Bracket".
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.dataPermissionDetails.ordernum')"
              for="data-permission-details-ordernum"
              >Ordernum</label
            >
            <input
              type="number"
              class="form-control"
              name="ordernum"
              id="data-permission-details-ordernum"
              data-cy="ordernum"
              :class="{ valid: !$v.dataPermissionDetails.ordernum.$invalid, invalid: $v.dataPermissionDetails.ordernum.$invalid }"
              v-model.number="$v.dataPermissionDetails.ordernum.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.dataPermissionDetails.logicalRel')"
              for="data-permission-details-logicalRel"
              >Logical Rel</label
            >
            <input
              type="text"
              class="form-control"
              name="logicalRel"
              id="data-permission-details-logicalRel"
              data-cy="logicalRel"
              :class="{ valid: !$v.dataPermissionDetails.logicalRel.$invalid, invalid: $v.dataPermissionDetails.logicalRel.$invalid }"
              v-model="$v.dataPermissionDetails.logicalRel.$model"
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
            :disabled="$v.dataPermissionDetails.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./data-permission-details-update.component.ts"></script>
