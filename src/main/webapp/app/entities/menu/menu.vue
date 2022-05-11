<template>
  <div>
    <h2 id="page-heading" data-cy="MenuHeading">
      <span v-text="$t('moneyMakingMachineApp.menu.home.title')" id="menu-heading">Menus</span>
      <div class="d-flex justify-content-end">
        <span style="margin-right: 5px">菜单名称:</span>
        <input type="text" v-model="name" style="margin-right: 5px" />

        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.menu.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'MenuCreate' }" custom v-slot="{ navigate }">
          <button @click="navigate" id="jh-create-entity" data-cy="entityCreateButton" class="btn btn-primary jh-create-entity create-menu">
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.menu.home.createLabel')"> Create a new Menu </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && menus && menus.length === 0">
      <span v-text="$t('moneyMakingMachineApp.menu.home.notFound')">No menus found</span>
    </div>
    <div class="table-responsive" v-if="menus && menus.length > 0">
      <table class="table table-striped" aria-describedby="menus">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.menu.url')">Url</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.menu.name')">Name</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.menu.iconCls')">Icon Cls</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.menu.ordernum')">Ordernum</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.menu.keepAlive')">Keep Alive</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.menu.requireAuth')">Require Auth</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.menu.parentId')">Parent Id</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.menu.enabled')">Enabled</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.menu.config')">Config</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="menu in menus" :key="menu.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'MenuView', params: { menuId: menu.id } }">{{ menu.id }}</router-link>
            </td>
            <td>{{ menu.url }}</td>
            <td>{{ menu.name }}</td>
            <td>{{ menu.iconCls }}</td>
            <td>{{ menu.ordernum }}</td>
            <td>{{ menu.keepAlive }}</td>
            <td>{{ menu.requireAuth }}</td>
            <td>{{ menu.parentId }}</td>
            <td>{{ menu.enabled }}</td>
            <td>{{ menu.config }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'MenuView', params: { menuId: menu.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'MenuEdit', params: { menuId: menu.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(menu)"
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
        ><span id="moneyMakingMachineApp.menu.delete.question" data-cy="menuDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-menu-heading" v-text="$t('moneyMakingMachineApp.menu.delete.question', { id: removeId })">
          Are you sure you want to delete this Menu?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-menu"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeMenu()"
        >
          Delete
        </button>
      </div>
    </b-modal>

    <div v-show="menus && menus.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./menu.component.ts"></script>
