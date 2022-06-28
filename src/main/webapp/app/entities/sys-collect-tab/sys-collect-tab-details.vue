<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="sysCollectTab">
        <h2 class="jh-entity-heading" data-cy="sysCollectTabDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.sysCollectTab.detail.title')">SysCollectTab</span> {{ sysCollectTab.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysCollectTab.tabCname')">Tab Cname</span>
          </dt>
          <dd>
            <span>{{ sysCollectTab.tabCname }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysCollectTab.tabEname')">Tab Ename</span>
          </dt>
          <dd>
            <span>{{ sysCollectTab.tabEname }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysCollectTab.config')">Config</span>
          </dt>
          <dd>
            <span>{{ sysCollectTab.config }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysCollectTab.enable')">Enable</span>
          </dt>
          <dd>
            <span>{{ sysCollectTab.enable }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="sysCollectTab.id"
          :to="{ name: 'SysCollectTabEdit', params: { sysCollectTabId: sysCollectTab.id } }"
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

<script lang="ts" src="./sys-collect-tab-details.component.ts"></script>
