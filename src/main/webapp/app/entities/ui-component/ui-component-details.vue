<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="uiComponent">
        <h2 class="jh-entity-heading" data-cy="uiComponentDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.uiComponent.detail.title')">UiComponent</span> {{ uiComponent.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiComponent.menuid')">Menuid</span>
          </dt>
          <dd>
            <span>{{ uiComponent.menuid }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiComponent.ordernum')">Ordernum</span>
          </dt>
          <dd>
            <span>{{ uiComponent.ordernum }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiComponent.componentid')">Componentid</span>
          </dt>
          <dd>
            <span>{{ uiComponent.componentid }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiComponent.config')">Config</span>
          </dt>
          <dd>
            <span>{{ uiComponent.config }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="uiComponent.id"
          :to="{ name: 'UiComponentEdit', params: { uiComponentId: uiComponent.id } }"
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

<script lang="ts" src="./ui-component-details.component.ts"></script>
