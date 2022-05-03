<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="uiTable">
        <h2 class="jh-entity-heading" data-cy="uiTableDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.uiTable.detail.title')">UiTable</span> {{ uiTable.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiTable.menuid')">Menuid</span>
          </dt>
          <dd>
            <span>{{ uiTable.menuid }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiTable.code')">Code</span>
          </dt>
          <dd>
            <span>{{ uiTable.code }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiTable.name')">Name</span>
          </dt>
          <dd>
            <span>{{ uiTable.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiTable.ordernum')">Ordernum</span>
          </dt>
          <dd>
            <span>{{ uiTable.ordernum }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiTable.issource')">Issource</span>
          </dt>
          <dd>
            <span>{{ uiTable.issource }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiTable.isedit')">Isedit</span>
          </dt>
          <dd>
            <span>{{ uiTable.isedit }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiTable.requirement')">Requirement</span>
          </dt>
          <dd>
            <span>{{ uiTable.requirement }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiTable.type')">Type</span>
          </dt>
          <dd>
            <span>{{ uiTable.type }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.uiTable.config')">Config</span>
          </dt>
          <dd>
            <span>{{ uiTable.config }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="uiTable.id" :to="{ name: 'UiTableEdit', params: { uiTableId: uiTable.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./ui-table-details.component.ts"></script>
