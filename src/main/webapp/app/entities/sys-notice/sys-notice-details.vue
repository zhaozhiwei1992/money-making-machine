<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="sysNotice">
        <h2 class="jh-entity-heading" data-cy="sysNoticeDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.sysNotice.detail.title')">SysNotice</span> {{ sysNotice.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysNotice.title')">Title</span>
          </dt>
          <dd>
            <span>{{ sysNotice.title }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysNotice.content')">Content</span>
          </dt>
          <dd>
            <span>{{ sysNotice.content }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysNotice.creater')">Creater</span>
          </dt>
          <dd>
            <span>{{ sysNotice.creater }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysNotice.createTime')">Create Time</span>
          </dt>
          <dd>
            <span>{{ sysNotice.createTime }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysNotice.recType')">Rec Type</span>
          </dt>
          <dd>
            <span>{{ sysNotice.recType }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysNotice.receiver')">Receiver</span>
          </dt>
          <dd>
            <span>{{ sysNotice.receiver }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysNotice.urgent')">Urgent</span>
          </dt>
          <dd>
            <span>{{ sysNotice.urgent }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysNotice.notiType')">Noti Type</span>
          </dt>
          <dd>
            <span>{{ sysNotice.notiType }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysNotice.status')">Status</span>
          </dt>
          <dd>
            <span>{{ sysNotice.status }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="sysNotice.id"
          :to="{ name: 'SysNoticeEdit', params: { sysNoticeId: sysNotice.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./sys-notice-details.component.ts"></script>
