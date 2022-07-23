<template>
  <div>
    <h2 id="page-heading" data-cy="SysNoticeHeading">
      <span v-text="$t('moneyMakingMachineApp.sysNotice.home.title')" id="sys-notice-heading">Sys Notices</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.sysNotice.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'SysNoticeCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-sys-notice"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.sysNotice.home.createLabel')"> Create a new Sys Notice </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && sysNotices && sysNotices.length === 0">
      <span v-text="$t('moneyMakingMachineApp.sysNotice.home.notFound')">No sysNotices found</span>
    </div>
    <div class="table-responsive" v-if="sysNotices && sysNotices.length > 0">
      <table class="table table-striped" aria-describedby="sysNotices">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysNotice.title')">Title</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysNotice.content')">Content</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysNotice.creater')">Creater</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysNotice.createTime')">Create Time</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysNotice.recType')">Rec Type</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysNotice.receiver')">Receiver</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysNotice.urgent')">Urgent</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysNotice.notiType')">Noti Type</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysNotice.status')">Status</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="sysNotice in sysNotices" :key="sysNotice.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SysNoticeView', params: { sysNoticeId: sysNotice.id } }">{{ sysNotice.id }}</router-link>
            </td>
            <td>{{ sysNotice.title }}</td>
            <td>{{ sysNotice.content }}</td>
            <td>{{ sysNotice.creater }}</td>
            <td>{{ sysNotice.createTime }}</td>
            <td>{{ sysNotice.recType }}</td>
            <td>{{ sysNotice.receiver }}</td>
            <td>{{ sysNotice.urgent }}</td>
            <td>{{ sysNotice.notiType }}</td>
            <td>{{ sysNotice.status }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'SysNoticeView', params: { sysNoticeId: sysNotice.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'SysNoticeEdit', params: { sysNoticeId: sysNotice.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(sysNotice)"
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
          id="moneyMakingMachineApp.sysNotice.delete.question"
          data-cy="sysNoticeDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-sysNotice-heading" v-text="$t('moneyMakingMachineApp.sysNotice.delete.question', { id: removeId })">
          Are you sure you want to delete this Sys Notice?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-sysNotice"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeSysNotice()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./sys-notice.component.ts"></script>
