<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.sysNoticeSub.home.createOrEditLabel"
          data-cy="SysNoticeSubCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.sysNoticeSub.home.createOrEditLabel')"
        >
          Create or edit a SysNoticeSub
        </h2>
        <div>
          <div class="form-group" v-if="sysNoticeSub.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="sysNoticeSub.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysNoticeSub.sysNoticeId')" for="sys-notice-sub-sysNoticeId"
              >Sys Notice Id</label
            >
            <input
              type="number"
              class="form-control"
              name="sysNoticeId"
              id="sys-notice-sub-sysNoticeId"
              data-cy="sysNoticeId"
              :class="{ valid: !$v.sysNoticeSub.sysNoticeId.$invalid, invalid: $v.sysNoticeSub.sysNoticeId.$invalid }"
              v-model.number="$v.sysNoticeSub.sysNoticeId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysNoticeSub.recipientId')" for="sys-notice-sub-recipientId"
              >Recipient Id</label
            >
            <input
              type="text"
              class="form-control"
              name="recipientId"
              id="sys-notice-sub-recipientId"
              data-cy="recipientId"
              :class="{ valid: !$v.sysNoticeSub.recipientId.$invalid, invalid: $v.sysNoticeSub.recipientId.$invalid }"
              v-model="$v.sysNoticeSub.recipientId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysNoticeSub.updateTime')" for="sys-notice-sub-updateTime"
              >Update Time</label
            >
            <input
              type="text"
              class="form-control"
              name="updateTime"
              id="sys-notice-sub-updateTime"
              data-cy="updateTime"
              :class="{ valid: !$v.sysNoticeSub.updateTime.$invalid, invalid: $v.sysNoticeSub.updateTime.$invalid }"
              v-model="$v.sysNoticeSub.updateTime.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysNoticeSub.status')" for="sys-notice-sub-status"
              >Status</label
            >
            <input
              type="number"
              class="form-control"
              name="status"
              id="sys-notice-sub-status"
              data-cy="status"
              :class="{ valid: !$v.sysNoticeSub.status.$invalid, invalid: $v.sysNoticeSub.status.$invalid }"
              v-model.number="$v.sysNoticeSub.status.$model"
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
            :disabled="$v.sysNoticeSub.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./sys-notice-sub-update.component.ts"></script>
