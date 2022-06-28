<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.eleUnion.home.createOrEditLabel"
          data-cy="EleUnionCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.eleUnion.home.createOrEditLabel')"
        >
          Create or edit a EleUnion
        </h2>
        <div>
          <div class="form-group" v-if="eleUnion.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="eleUnion.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.eleUnion.eleCatCode')" for="ele-union-eleCatCode"
              >Ele Cat Code</label
            >
            <input
              type="text"
              class="form-control"
              name="eleCatCode"
              id="ele-union-eleCatCode"
              data-cy="eleCatCode"
              :class="{ valid: !$v.eleUnion.eleCatCode.$invalid, invalid: $v.eleUnion.eleCatCode.$invalid }"
              v-model="$v.eleUnion.eleCatCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.eleUnion.eleCatName')" for="ele-union-eleCatName"
              >Ele Cat Name</label
            >
            <input
              type="text"
              class="form-control"
              name="eleCatName"
              id="ele-union-eleCatName"
              data-cy="eleCatName"
              :class="{ valid: !$v.eleUnion.eleCatName.$invalid, invalid: $v.eleUnion.eleCatName.$invalid }"
              v-model="$v.eleUnion.eleCatName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.eleUnion.eleCode')" for="ele-union-eleCode">Ele Code</label>
            <input
              type="text"
              class="form-control"
              name="eleCode"
              id="ele-union-eleCode"
              data-cy="eleCode"
              :class="{ valid: !$v.eleUnion.eleCode.$invalid, invalid: $v.eleUnion.eleCode.$invalid }"
              v-model="$v.eleUnion.eleCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.eleUnion.eleName')" for="ele-union-eleName">Ele Name</label>
            <input
              type="text"
              class="form-control"
              name="eleName"
              id="ele-union-eleName"
              data-cy="eleName"
              :class="{ valid: !$v.eleUnion.eleName.$invalid, invalid: $v.eleUnion.eleName.$invalid }"
              v-model="$v.eleUnion.eleName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.eleUnion.parentId')" for="ele-union-parentId"
              >Parent Id</label
            >
            <input
              type="text"
              class="form-control"
              name="parentId"
              id="ele-union-parentId"
              data-cy="parentId"
              :class="{ valid: !$v.eleUnion.parentId.$invalid, invalid: $v.eleUnion.parentId.$invalid }"
              v-model="$v.eleUnion.parentId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.eleUnion.levelNo')" for="ele-union-levelNo">Level No</label>
            <input
              type="number"
              class="form-control"
              name="levelNo"
              id="ele-union-levelNo"
              data-cy="levelNo"
              :class="{ valid: !$v.eleUnion.levelNo.$invalid, invalid: $v.eleUnion.levelNo.$invalid }"
              v-model.number="$v.eleUnion.levelNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.eleUnion.isLeaf')" for="ele-union-isLeaf">Is Leaf</label>
            <input
              type="checkbox"
              class="form-check"
              name="isLeaf"
              id="ele-union-isLeaf"
              data-cy="isLeaf"
              :class="{ valid: !$v.eleUnion.isLeaf.$invalid, invalid: $v.eleUnion.isLeaf.$invalid }"
              v-model="$v.eleUnion.isLeaf.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.eleUnion.isEnabled')" for="ele-union-isEnabled"
              >Is Enabled</label
            >
            <input
              type="checkbox"
              class="form-check"
              name="isEnabled"
              id="ele-union-isEnabled"
              data-cy="isEnabled"
              :class="{ valid: !$v.eleUnion.isEnabled.$invalid, invalid: $v.eleUnion.isEnabled.$invalid }"
              v-model="$v.eleUnion.isEnabled.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.eleUnion.createTime')" for="ele-union-createTime"
              >Create Time</label
            >
            <input
              type="text"
              class="form-control"
              name="createTime"
              id="ele-union-createTime"
              data-cy="createTime"
              :class="{ valid: !$v.eleUnion.createTime.$invalid, invalid: $v.eleUnion.createTime.$invalid }"
              v-model="$v.eleUnion.createTime.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.eleUnion.updateTime')" for="ele-union-updateTime"
              >Update Time</label
            >
            <input
              type="text"
              class="form-control"
              name="updateTime"
              id="ele-union-updateTime"
              data-cy="updateTime"
              :class="{ valid: !$v.eleUnion.updateTime.$invalid, invalid: $v.eleUnion.updateTime.$invalid }"
              v-model="$v.eleUnion.updateTime.$model"
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
            :disabled="$v.eleUnion.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./ele-union-update.component.ts"></script>
