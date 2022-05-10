<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="uiTab">
        <h2 class="jh-entity-heading" data-cy="uiTabDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.uiTab.detail.title')">UiTab</span> {{ uiTab.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiTab.menuid')">Menuid</span>
          </dt>
          <dd>
            <span>{{ uiTab.menuid }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiTab.code')">Code</span>
          </dt>
          <dd>
            <span>{{ uiTab.code }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiTab.name')">Name</span>
          </dt>
          <dd>
            <span>{{ uiTab.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiTab.ordernum')">Ordernum</span>
          </dt>
          <dd>
            <span>{{ uiTab.ordernum }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiTab.config')">Config</span>
          </dt>
          <dd>
            <span>{{ uiTab.config }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="uiTab.id" :to="{ name: 'UiTabEdit', params: { uiTabId: uiTab.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./ui-tab-details.component.ts"></script>
