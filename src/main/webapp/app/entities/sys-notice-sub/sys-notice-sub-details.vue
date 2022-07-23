<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="sysNoticeSub">
        <h2 class="jh-entity-heading" data-cy="sysNoticeSubDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.sysNoticeSub.detail.title')">SysNoticeSub</span> {{ sysNoticeSub.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysNoticeSub.sysNoticeId')">Sys Notice Id</span>
          </dt>
          <dd>
            <span>{{ sysNoticeSub.sysNoticeId }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysNoticeSub.recipientId')">Recipient Id</span>
          </dt>
          <dd>
            <span>{{ sysNoticeSub.recipientId }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysNoticeSub.updateTime')">Update Time</span>
          </dt>
          <dd>
            <span>{{ sysNoticeSub.updateTime }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysNoticeSub.status')">Status</span>
          </dt>
          <dd>
            <span>{{ sysNoticeSub.status }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="sysNoticeSub.id"
          :to="{ name: 'SysNoticeSubEdit', params: { sysNoticeSubId: sysNoticeSub.id } }"
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

<script lang="ts" src="./sys-notice-sub-details.component.ts"></script>
