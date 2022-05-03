<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="uiEditform">
        <h2 class="jh-entity-heading" data-cy="uiEditformDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.uiEditform.detail.title')">UiEditform</span> {{ uiEditform.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiEditform.menuid')">Menuid</span>
          </dt>
          <dd>
            <span>{{ uiEditform.menuid }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiEditform.code')">Code</span>
          </dt>
          <dd>
            <span>{{ uiEditform.code }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiEditform.name')">Name</span>
          </dt>
          <dd>
            <span>{{ uiEditform.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiEditform.ordernum')">Ordernum</span>
          </dt>
          <dd>
            <span>{{ uiEditform.ordernum }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiEditform.issource')">Issource</span>
          </dt>
          <dd>
            <span>{{ uiEditform.issource }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiEditform.isedit')">Isedit</span>
          </dt>
          <dd>
            <span>{{ uiEditform.isedit }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiEditform.requirement')">Requirement</span>
          </dt>
          <dd>
            <span>{{ uiEditform.requirement }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiEditform.type')">Type</span>
          </dt>
          <dd>
            <span>{{ uiEditform.type }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiEditform.placeholder')">Placeholder</span>
          </dt>
          <dd>
            <span>{{ uiEditform.placeholder }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiEditform.config')">Config</span>
          </dt>
          <dd>
            <span>{{ uiEditform.config }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="uiEditform.id"
          :to="{ name: 'UiEditformEdit', params: { uiEditformId: uiEditform.id } }"
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

<script lang="ts" src="./ui-editform-details.component.ts"></script>
