<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.example.home.createOrEditLabel"
          data-cy="ExampleCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.example.home.createOrEditLabel')"
        >
          Create or edit a Example
        </h2>
        <div>
          <div class="form-group" v-if="example.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="example.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.example.name')" for="example-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="example-name"
              data-cy="name"
              :class="{ valid: !$v.example.name.$invalid, invalid: $v.example.name.$invalid }"
              v-model="$v.example.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.example.age')" for="example-age">Age</label>
            <input
              type="number"
              class="form-control"
              name="age"
              id="example-age"
              data-cy="age"
              :class="{ valid: !$v.example.age.$invalid, invalid: $v.example.age.$invalid }"
              v-model.number="$v.example.age.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.example.sex')" for="example-sex">Sex</label>
            <input
              type="text"
              class="form-control"
              name="sex"
              id="example-sex"
              data-cy="sex"
              :class="{ valid: !$v.example.sex.$invalid, invalid: $v.example.sex.$invalid }"
              v-model="$v.example.sex.$model"
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
            :disabled="$v.example.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./example-update.component.ts"></script>
