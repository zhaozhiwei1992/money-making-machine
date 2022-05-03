<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="uiToolButton">
        <h2 class="jh-entity-heading" data-cy="uiToolButtonDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.uiToolButton.detail.title')">UiToolButton</span> {{ uiToolButton.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiToolButton.menuid')">Menuid</span>
          </dt>
          <dd>
            <span>{{ uiToolButton.menuid }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiToolButton.code')">Code</span>
          </dt>
          <dd>
            <span>{{ uiToolButton.code }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiToolButton.name')">Name</span>
          </dt>
          <dd>
            <span>{{ uiToolButton.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiToolButton.ordernum')">Ordernum</span>
          </dt>
          <dd>
            <span>{{ uiToolButton.ordernum }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiToolButton.action')">Action</span>
          </dt>
          <dd>
            <span>{{ uiToolButton.action }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiToolButton.config')">Config</span>
          </dt>
          <dd>
            <span>{{ uiToolButton.config }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="uiToolButton.id"
          :to="{ name: 'UiToolButtonEdit', params: { uiToolButtonId: uiToolButton.id } }"
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

<script lang="ts" src="./ui-tool-button-details.component.ts"></script>
