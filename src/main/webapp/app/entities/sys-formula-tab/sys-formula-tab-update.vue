<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.sysFormulaTab.home.createOrEditLabel"
          data-cy="SysFormulaTabCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.sysFormulaTab.home.createOrEditLabel')"
        >
          Create or edit a SysFormulaTab
        </h2>
        <div>
          <div class="form-group" v-if="sysFormulaTab.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="sysFormulaTab.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysFormulaTab.tabEname')" for="sys-formula-tab-tabEname"
              >Tab Ename</label
            >
            <input
              type="text"
              class="form-control"
              name="tabEname"
              id="sys-formula-tab-tabEname"
              data-cy="tabEname"
              :class="{ valid: !$v.sysFormulaTab.tabEname.$invalid, invalid: $v.sysFormulaTab.tabEname.$invalid }"
              v-model="$v.sysFormulaTab.tabEname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysFormulaTab.colEname')" for="sys-formula-tab-colEname"
              >Col Ename</label
            >
            <input
              type="text"
              class="form-control"
              name="colEname"
              id="sys-formula-tab-colEname"
              data-cy="colEname"
              :class="{ valid: !$v.sysFormulaTab.colEname.$invalid, invalid: $v.sysFormulaTab.colEname.$invalid }"
              v-model="$v.sysFormulaTab.colEname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysFormulaTab.calFormula')" for="sys-formula-tab-calFormula"
              >Cal Formula</label
            >
            <input
              type="text"
              class="form-control"
              name="calFormula"
              id="sys-formula-tab-calFormula"
              data-cy="calFormula"
              :class="{ valid: !$v.sysFormulaTab.calFormula.$invalid, invalid: $v.sysFormulaTab.calFormula.$invalid }"
              v-model="$v.sysFormulaTab.calFormula.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.sysFormulaTab.calFormulaDes')"
              for="sys-formula-tab-calFormulaDes"
              >Cal Formula Des</label
            >
            <input
              type="text"
              class="form-control"
              name="calFormulaDes"
              id="sys-formula-tab-calFormulaDes"
              data-cy="calFormulaDes"
              :class="{ valid: !$v.sysFormulaTab.calFormulaDes.$invalid, invalid: $v.sysFormulaTab.calFormulaDes.$invalid }"
              v-model="$v.sysFormulaTab.calFormulaDes.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysFormulaTab.roundType')" for="sys-formula-tab-roundType"
              >Round Type</label
            >
            <input
              type="text"
              class="form-control"
              name="roundType"
              id="sys-formula-tab-roundType"
              data-cy="roundType"
              :class="{ valid: !$v.sysFormulaTab.roundType.$invalid, invalid: $v.sysFormulaTab.roundType.$invalid }"
              v-model="$v.sysFormulaTab.roundType.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysFormulaTab.weight')" for="sys-formula-tab-weight"
              >Weight</label
            >
            <input
              type="number"
              class="form-control"
              name="weight"
              id="sys-formula-tab-weight"
              data-cy="weight"
              :class="{ valid: !$v.sysFormulaTab.weight.$invalid, invalid: $v.sysFormulaTab.weight.$invalid }"
              v-model.number="$v.sysFormulaTab.weight.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysFormulaTab.enable')" for="sys-formula-tab-enable"
              >Enable</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="enable"
              id="sys-formula-tab-enable"
              data-cy="enable"
              :class="{ valid: !$v.sysFormulaTab.enable.$invalid, invalid: $v.sysFormulaTab.enable.$invalid }"
              v-model="$v.sysFormulaTab.enable.$model"
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
            :disabled="$v.sysFormulaTab.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./sys-formula-tab-update.component.ts"></script>
