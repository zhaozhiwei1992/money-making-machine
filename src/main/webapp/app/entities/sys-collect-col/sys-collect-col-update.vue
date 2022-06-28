<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.sysCollectCol.home.createOrEditLabel"
          data-cy="SysCollectColCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.sysCollectCol.home.createOrEditLabel')"
        >
          Create or edit a SysCollectCol
        </h2>
        <div>
          <div class="form-group" v-if="sysCollectCol.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="sysCollectCol.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysCollectCol.colCname')" for="sys-collect-col-colCname"
              >Col Cname</label
            >
            <input
              type="text"
              class="form-control"
              name="colCname"
              id="sys-collect-col-colCname"
              data-cy="colCname"
              :class="{ valid: !$v.sysCollectCol.colCname.$invalid, invalid: $v.sysCollectCol.colCname.$invalid }"
              v-model="$v.sysCollectCol.colCname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysCollectCol.colEname')" for="sys-collect-col-colEname"
              >Col Ename</label
            >
            <input
              type="text"
              class="form-control"
              name="colEname"
              id="sys-collect-col-colEname"
              data-cy="colEname"
              :class="{ valid: !$v.sysCollectCol.colEname.$invalid, invalid: $v.sysCollectCol.colEname.$invalid }"
              v-model="$v.sysCollectCol.colEname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysCollectCol.tabId')" for="sys-collect-col-tabId"
              >Tab Id</label
            >
            <input
              type="number"
              class="form-control"
              name="tabId"
              id="sys-collect-col-tabId"
              data-cy="tabId"
              :class="{ valid: !$v.sysCollectCol.tabId.$invalid, invalid: $v.sysCollectCol.tabId.$invalid }"
              v-model.number="$v.sysCollectCol.tabId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysCollectCol.orderNum')" for="sys-collect-col-orderNum"
              >Order Num</label
            >
            <input
              type="number"
              class="form-control"
              name="orderNum"
              id="sys-collect-col-orderNum"
              data-cy="orderNum"
              :class="{ valid: !$v.sysCollectCol.orderNum.$invalid, invalid: $v.sysCollectCol.orderNum.$invalid }"
              v-model.number="$v.sysCollectCol.orderNum.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysCollectCol.source')" for="sys-collect-col-source"
              >Source</label
            >
            <input
              type="text"
              class="form-control"
              name="source"
              id="sys-collect-col-source"
              data-cy="source"
              :class="{ valid: !$v.sysCollectCol.source.$invalid, invalid: $v.sysCollectCol.source.$invalid }"
              v-model="$v.sysCollectCol.source.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysCollectCol.isEdit')" for="sys-collect-col-isEdit"
              >Is Edit</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="isEdit"
              id="sys-collect-col-isEdit"
              data-cy="isEdit"
              :class="{ valid: !$v.sysCollectCol.isEdit.$invalid, invalid: $v.sysCollectCol.isEdit.$invalid }"
              v-model="$v.sysCollectCol.isEdit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('moneyMakingMachineApp.sysCollectCol.requirement')"
              for="sys-collect-col-requirement"
              >Requirement</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="requirement"
              id="sys-collect-col-requirement"
              data-cy="requirement"
              :class="{ valid: !$v.sysCollectCol.requirement.$invalid, invalid: $v.sysCollectCol.requirement.$invalid }"
              v-model="$v.sysCollectCol.requirement.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysCollectCol.type')" for="sys-collect-col-type"
              >Type</label
            >
            <input
              type="text"
              class="form-control"
              name="type"
              id="sys-collect-col-type"
              data-cy="type"
              :class="{ valid: !$v.sysCollectCol.type.$invalid, invalid: $v.sysCollectCol.type.$invalid }"
              v-model="$v.sysCollectCol.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysCollectCol.config')" for="sys-collect-col-config"
              >Config</label
            >
            <input
              type="text"
              class="form-control"
              name="config"
              id="sys-collect-col-config"
              data-cy="config"
              :class="{ valid: !$v.sysCollectCol.config.$invalid, invalid: $v.sysCollectCol.config.$invalid }"
              v-model="$v.sysCollectCol.config.$model"
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
            :disabled="$v.sysCollectCol.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./sys-collect-col-update.component.ts"></script>
