<template>
  <div>
    <h2 id="page-heading" data-cy="SysNoticeSubHeading">
      <span v-text="$t('moneyMakingMachineApp.sysNoticeSub.home.title')" id="sys-notice-sub-heading">Sys Notice Subs</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('moneyMakingMachineApp.sysNoticeSub.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'SysNoticeSubCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-sys-notice-sub"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('moneyMakingMachineApp.sysNoticeSub.home.createLabel')"> Create a new Sys Notice Sub </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && sysNoticeSubs && sysNoticeSubs.length === 0">
      <span v-text="$t('moneyMakingMachineApp.sysNoticeSub.home.notFound')">No sysNoticeSubs found</span>
    </div>
    <div class="table-responsive" v-if="sysNoticeSubs && sysNoticeSubs.length > 0">
      <table class="table table-striped" aria-describedby="sysNoticeSubs">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysNoticeSub.sysNoticeId')">Sys Notice Id</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysNoticeSub.recipientId')">Recipient Id</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysNoticeSub.updateTime')">Update Time</span></th>
            <th scope="row"><span v-text="$t('moneyMakingMachineApp.sysNoticeSub.status')">Status</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="sysNoticeSub in sysNoticeSubs" :key="sysNoticeSub.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SysNoticeSubView', params: { sysNoticeSubId: sysNoticeSub.id } }">{{
                sysNoticeSub.id
              }}</router-link>
            </td>
            <td>{{ sysNoticeSub.sysNoticeId }}</td>
            <td>{{ sysNoticeSub.recipientId }}</td>
            <td>{{ sysNoticeSub.updateTime }}</td>
            <td>{{ sysNoticeSub.status }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'SysNoticeSubView', params: { sysNoticeSubId: sysNoticeSub.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'SysNoticeSubEdit', params: { sysNoticeSubId: sysNoticeSub.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(sysNoticeSub)"
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
          id="moneyMakingMachineApp.sysNoticeSub.delete.question"
          data-cy="sysNoticeSubDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-sysNoticeSub-heading" v-text="$t('moneyMakingMachineApp.sysNoticeSub.delete.question', { id: removeId })">
          Are you sure you want to delete this Sys Notice Sub?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-sysNoticeSub"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeSysNoticeSub()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./sys-notice-sub.component.ts"></script>
