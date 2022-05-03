<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="uiQueryform">
        <h2 class="jh-entity-heading" data-cy="uiQueryformDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.uiQueryform.detail.title')">UiQueryform</span> {{ uiQueryform.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiQueryform.menuid')">Menuid</span>
          </dt>
          <dd>
            <span>{{ uiQueryform.menuid }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiQueryform.code')">Code</span>
          </dt>
          <dd>
            <span>{{ uiQueryform.code }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiQueryform.name')">Name</span>
          </dt>
          <dd>
            <span>{{ uiQueryform.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiQueryform.ordernum')">Ordernum</span>
          </dt>
          <dd>
            <span>{{ uiQueryform.ordernum }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiQueryform.issource')">Issource</span>
          </dt>
          <dd>
            <span>{{ uiQueryform.issource }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiQueryform.requirement')">Requirement</span>
          </dt>
          <dd>
            <span>{{ uiQueryform.requirement }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiQueryform.type')">Type</span>
          </dt>
          <dd>
            <span>{{ uiQueryform.type }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiQueryform.placeholder')">Placeholder</span>
          </dt>
          <dd>
            <span>{{ uiQueryform.placeholder }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiQueryform.config')">Config</span>
          </dt>
          <dd>
            <span>{{ uiQueryform.config }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="uiQueryform.id"
          :to="{ name: 'UiQueryformEdit', params: { uiQueryformId: uiQueryform.id } }"
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

<script lang="ts" src="./ui-queryform-details.component.ts"></script>
