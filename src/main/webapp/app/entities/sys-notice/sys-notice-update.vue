<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="moneyMakingMachineApp.sysNotice.home.createOrEditLabel"
          data-cy="SysNoticeCreateUpdateHeading"
          v-text="$t('moneyMakingMachineApp.sysNotice.home.createOrEditLabel')"
        >
          Create or edit a SysNotice
        </h2>
        <div>
          <div class="form-group" v-if="sysNotice.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="sysNotice.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysNotice.title')" for="sys-notice-title">Title</label>
            <input
              type="text"
              class="form-control"
              name="title"
              id="sys-notice-title"
              data-cy="title"
              :class="{ valid: !$v.sysNotice.title.$invalid, invalid: $v.sysNotice.title.$invalid }"
              v-model="$v.sysNotice.title.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysNotice.content')" for="sys-notice-content"
              >Content</label
            >
            <input
              type="text"
              class="form-control"
              name="content"
              id="sys-notice-content"
              data-cy="content"
              :class="{ valid: !$v.sysNotice.content.$invalid, invalid: $v.sysNotice.content.$invalid }"
              v-model="$v.sysNotice.content.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysNotice.creater')" for="sys-notice-creater"
              >Creater</label
            >
            <input
              type="text"
              class="form-control"
              name="creater"
              id="sys-notice-creater"
              data-cy="creater"
              :class="{ valid: !$v.sysNotice.creater.$invalid, invalid: $v.sysNotice.creater.$invalid }"
              v-model="$v.sysNotice.creater.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysNotice.createTime')" for="sys-notice-createTime"
              >Create Time</label
            >
            <input
              type="text"
              class="form-control"
              name="createTime"
              id="sys-notice-createTime"
              data-cy="createTime"
              :class="{ valid: !$v.sysNotice.createTime.$invalid, invalid: $v.sysNotice.createTime.$invalid }"
              v-model="$v.sysNotice.createTime.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysNotice.recType')" for="sys-notice-recType"
              >Rec Type</label
            >
            <input
              type="text"
              class="form-control"
              name="recType"
              id="sys-notice-recType"
              data-cy="recType"
              :class="{ valid: !$v.sysNotice.recType.$invalid, invalid: $v.sysNotice.recType.$invalid }"
              v-model="$v.sysNotice.recType.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysNotice.receiver')" for="sys-notice-receiver"
              >Receiver</label
            >
            <input
              type="text"
              class="form-control"
              name="receiver"
              id="sys-notice-receiver"
              data-cy="receiver"
              :class="{ valid: !$v.sysNotice.receiver.$invalid, invalid: $v.sysNotice.receiver.$invalid }"
              v-model="$v.sysNotice.receiver.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysNotice.urgent')" for="sys-notice-urgent">Urgent</label>
            <input
              type="checkbox"
              class="form-check"
              name="urgent"
              id="sys-notice-urgent"
              data-cy="urgent"
              :class="{ valid: !$v.sysNotice.urgent.$invalid, invalid: $v.sysNotice.urgent.$invalid }"
              v-model="$v.sysNotice.urgent.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('moneyMakingMachineApp.sysNotice.notiType')" for="sys-notice-notiType"
              >Noti Type</label
            >
            <input
              type="number"
              class="form-control"
              name="notiType"
              id="sys-notice-notiType"
              data-cy="notiType"
              :class="{ valid: !$v.sysNotice.notiType.$invalid, invalid: $v.sysNotice.notiType.$invalid }"
              v-model.number="$v.sysNotice.notiType.$model"
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
            :disabled="$v.sysNotice.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./sys-notice-update.component.ts"></script>
