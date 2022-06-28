<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.sysCollectTab.home.createOrEditLabel"
          data-cy="SysCollectTabCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.sysCollectTab.home.createOrEditLabel')"
        >
          Create or edit a SysCollectTab
        </h2>
        <div>
          <div class="form-group" v-if="sysCollectTab.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="sysCollectTab.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysCollectTab.tabCname')" for="sys-collect-tab-tabCname"
              >Tab Cname</label
            >
            <input
              type="text"
              class="form-control"
              name="tabCname"
              id="sys-collect-tab-tabCname"
              data-cy="tabCname"
              :class="{ valid: !$v.sysCollectTab.tabCname.$invalid, invalid: $v.sysCollectTab.tabCname.$invalid }"
              v-model="$v.sysCollectTab.tabCname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysCollectTab.tabEname')" for="sys-collect-tab-tabEname"
              >Tab Ename</label
            >
            <input
              type="text"
              class="form-control"
              name="tabEname"
              id="sys-collect-tab-tabEname"
              data-cy="tabEname"
              :class="{ valid: !$v.sysCollectTab.tabEname.$invalid, invalid: $v.sysCollectTab.tabEname.$invalid }"
              v-model="$v.sysCollectTab.tabEname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysCollectTab.config')" for="sys-collect-tab-config"
              >Config</label
            >
            <input
              type="text"
              class="form-control"
              name="config"
              id="sys-collect-tab-config"
              data-cy="config"
              :class="{ valid: !$v.sysCollectTab.config.$invalid, invalid: $v.sysCollectTab.config.$invalid }"
              v-model="$v.sysCollectTab.config.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysCollectTab.enable')" for="sys-collect-tab-enable"
              >Enable</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="enable"
              id="sys-collect-tab-enable"
              data-cy="enable"
              :class="{ valid: !$v.sysCollectTab.enable.$invalid, invalid: $v.sysCollectTab.enable.$invalid }"
              v-model="$v.sysCollectTab.enable.$model"
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
            :disabled="$v.sysCollectTab.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./sys-collect-tab-update.component.ts"></script>
