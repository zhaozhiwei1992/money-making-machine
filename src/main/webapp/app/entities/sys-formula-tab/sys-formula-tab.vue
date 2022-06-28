<template>
  <div>
    <h2 id="page-heading" data-cy="SysFormulaTabHeading">
      <span v-text="$t('moneyMakingMachineApp.sysFormulaTab.home.title')" id="sys-formula-tab-heading">Sys Formula Tabs</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.sysFormulaTab.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'SysFormulaTabCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-sys-formula-tab"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.sysFormulaTab.home.createLabel')"> Create a new Sys Formula Tab </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && sysFormulaTabs && sysFormulaTabs.length === 0">
      <span v-text="$t('moneyMakingMachineApp.sysFormulaTab.home.notFound')">No sysFormulaTabs found</span>
    </div>
    <div class="table-responsive" v-if="sysFormulaTabs && sysFormulaTabs.length > 0">
      <table class="table table-striped" aria-describedby="sysFormulaTabs">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysFormulaTab.tabEname')">Tab Ename</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysFormulaTab.colEname')">Col Ename</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysFormulaTab.calFormula')">Cal Formula</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysFormulaTab.calFormulaDes')">Cal Formula Des</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysFormulaTab.roundType')">Round Type</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysFormulaTab.weight')">Weight</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysFormulaTab.enable')">Enable</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="sysFormulaTab in sysFormulaTabs" :key="sysFormulaTab.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SysFormulaTabView', params: { sysFormulaTabId: sysFormulaTab.id } }">{{
                sysFormulaTab.id
              }}</router-link>
            </td>
            <td>{{ sysFormulaTab.tabEname }}</td>
            <td>{{ sysFormulaTab.colEname }}</td>
            <td>{{ sysFormulaTab.calFormula }}</td>
            <td>{{ sysFormulaTab.calFormulaDes }}</td>
            <td>{{ sysFormulaTab.roundType }}</td>
            <td>{{ sysFormulaTab.weight }}</td>
            <td>{{ sysFormulaTab.enable }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'SysFormulaTabView', params: { sysFormulaTabId: sysFormulaTab.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'SysFormulaTabEdit', params: { sysFormulaTabId: sysFormulaTab.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(sysFormulaTab)"
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
        ><span
          id="moneyMakingMachineApp.sysFormulaTab.delete.question"
          data-cy="sysFormulaTabDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-sysFormulaTab-heading" v-text="$t('moneyMakingMachineApp.sysFormulaTab.delete.question', { id: removeId })">
          Are you sure you want to delete this Sys Formula Tab?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-sysFormulaTab"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeSysFormulaTab()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./sys-formula-tab.component.ts"></script>
