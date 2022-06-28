<template>
  <div>
    <h2 id="page-heading" data-cy="ExampleHeading">
      <span v-text="$t('moneyMakingMachineApp.example.home.title')" id="example-heading">Examples</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.example.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'ExampleCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-example"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.example.home.createLabel')"> Create a new Example </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && examples && examples.length === 0">
      <span v-text="$t('moneyMakingMachineApp.example.home.notFound')">No examples found</span>
    </div>
    <div class="table-responsive" v-if="examples && examples.length > 0">
      <table class="table table-striped" aria-describedby="examples">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.example.name')">Name</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.example.age')">Age</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.example.sex')">Sex</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="example in examples" :key="example.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ExampleView', params: { exampleId: example.id } }">{{ example.id }}</router-link>
            </td>
            <td>{{ example.name }}</td>
            <td>{{ example.age }}</td>
            <td>{{ example.sex }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ExampleView', params: { exampleId: example.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ExampleEdit', params: { exampleId: example.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(example)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="moneyMakingMachineApp.example.delete.question" data-cy="exampleDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-example-heading" v-text="$t('moneyMakingMachineApp.example.delete.question', { id: removeId })">
          Are you sure you want to delete this Example?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-example"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeExample()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./example.component.ts"></script>
