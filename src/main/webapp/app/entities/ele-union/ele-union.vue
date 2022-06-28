<template>
  <div>
    <h2 id="page-heading" data-cy="EleUnionHeading">
      <span v-text="$t('moneyMakingMachineApp.eleUnion.home.title')" id="ele-union-heading">Ele Unions</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.eleUnion.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'EleUnionCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-ele-union"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.eleUnion.home.createLabel')"> Create a new Ele Union </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && eleUnions && eleUnions.length === 0">
      <span v-text="$t('moneyMakingMachineApp.eleUnion.home.notFound')">No eleUnions found</span>
    </div>
    <div class="table-responsive" v-if="eleUnions && eleUnions.length > 0">
      <table class="table table-striped" aria-describedby="eleUnions">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.eleUnion.eleCatCode')">Ele Cat Code</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.eleUnion.eleCatName')">Ele Cat Name</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.eleUnion.eleCode')">Ele Code</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.eleUnion.eleName')">Ele Name</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.eleUnion.parentId')">Parent Id</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.eleUnion.levelNo')">Level No</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.eleUnion.isLeaf')">Is Leaf</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.eleUnion.isEnabled')">Is Enabled</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.eleUnion.createTime')">Create Time</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.eleUnion.updateTime')">Update Time</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="eleUnion in eleUnions" :key="eleUnion.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'EleUnionView', params: { eleUnionId: eleUnion.id } }">{{ eleUnion.id }}</router-link>
            </td>
            <td>{{ eleUnion.eleCatCode }}</td>
            <td>{{ eleUnion.eleCatName }}</td>
            <td>{{ eleUnion.eleCode }}</td>
            <td>{{ eleUnion.eleName }}</td>
            <td>{{ eleUnion.parentId }}</td>
            <td>{{ eleUnion.levelNo }}</td>
            <td>{{ eleUnion.isLeaf }}</td>
            <td>{{ eleUnion.isEnabled }}</td>
            <td>{{ eleUnion.createTime }}</td>
            <td>{{ eleUnion.updateTime }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'EleUnionView', params: { eleUnionId: eleUnion.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'EleUnionEdit', params: { eleUnionId: eleUnion.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(eleUnion)"
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
        ><span id="moneyMakingMachineApp.eleUnion.delete.question" data-cy="eleUnionDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-eleUnion-heading" v-text="$t('moneyMakingMachineApp.eleUnion.delete.question', { id: removeId })">
          Are you sure you want to delete this Ele Union?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-eleUnion"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeEleUnion()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./ele-union.component.ts"></script>
