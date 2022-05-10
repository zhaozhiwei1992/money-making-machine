<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.uiTable.home.createOrEditLabel"
          data-cy="UiTableCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.uiTable.home.createOrEditLabel')"
        >
          Create or edit a UiTable
        </h2>
        <div>
          <div class="form-group" v-if="uiTable.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="uiTable.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiTable.menuid')" for="ui-table-menuid">Menuid</label>
            <input
              type="number"
              class="form-control"
              name="menuid"
              id="ui-table-menuid"
              data-cy="menuid"
              :class="{ valid: !$v.uiTable.menuid.$invalid, invalid: $v.uiTable.menuid.$invalid }"
              v-model.number="$v.uiTable.menuid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiTable.code')" for="ui-table-code">Code</label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="ui-table-code"
              data-cy="code"
              :class="{ valid: !$v.uiTable.code.$invalid, invalid: $v.uiTable.code.$invalid }"
              v-model="$v.uiTable.code.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiTable.name')" for="ui-table-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="ui-table-name"
              data-cy="name"
              :class="{ valid: !$v.uiTable.name.$invalid, invalid: $v.uiTable.name.$invalid }"
              v-model="$v.uiTable.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiTable.ordernum')" for="ui-table-ordernum">Ordernum</label>
            <input
              type="number"
              class="form-control"
              name="ordernum"
              id="ui-table-ordernum"
              data-cy="ordernum"
              :class="{ valid: !$v.uiTable.ordernum.$invalid, invalid: $v.uiTable.ordernum.$invalid }"
              v-model.number="$v.uiTable.ordernum.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiTable.issource')" for="ui-table-issource">Issource</label>
            <input
              type="checkbox"
              class="form-check"
              name="issource"
              id="ui-table-issource"
              data-cy="issource"
              :class="{ valid: !$v.uiTable.issource.$invalid, invalid: $v.uiTable.issource.$invalid }"
              v-model="$v.uiTable.issource.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiTable.isedit')" for="ui-table-isedit">Isedit</label>
            <input
              type="checkbox"
              class="form-check"
              name="isedit"
              id="ui-table-isedit"
              data-cy="isedit"
              :class="{ valid: !$v.uiTable.isedit.$invalid, invalid: $v.uiTable.isedit.$invalid }"
              v-model="$v.uiTable.isedit.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiTable.requirement')" for="ui-table-requirement"
              >Requirement</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="requirement"
              id="ui-table-requirement"
              data-cy="requirement"
              :class="{ valid: !$v.uiTable.requirement.$invalid, invalid: $v.uiTable.requirement.$invalid }"
              v-model="$v.uiTable.requirement.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiTable.type')" for="ui-table-type">Type</label>
            <!-- <input
              type="text"
              class="form-control"
              name="type"
              id="ui-table-type"
              data-cy="type"
              :class="{ valid: !$v.uiTable.type.$invalid, invalid: $v.uiTable.type.$invalid }"
              v-model="$v.uiTable.type.$model"
            /> -->
            <!-- 手动调整为下拉,默认生成为input -->
            <select class="form-control" id="ui-table-type" name="type" v-model="$v.uiTable.type.$model">
              <option value="input">输入框</option>
              <option value="select">下拉框</option>
              <option value="date">日期</option>
              <option value="cascader">树形选择</option>
              <option value="btn">按钮</option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.uiTable.config')" for="ui-table-config">Config</label>
            <input
              type="text"
              class="form-control"
              name="config"
              id="ui-table-config"
              data-cy="config"
              :class="{ valid: !$v.uiTable.config.$invalid, invalid: $v.uiTable.config.$invalid }"
              v-model="$v.uiTable.config.$model"
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
            :disabled="$v.uiTable.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./ui-table-update.component.ts"></script>
