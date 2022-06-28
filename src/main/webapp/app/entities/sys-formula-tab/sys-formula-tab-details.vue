<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="sysFormulaTab">
        <h2 class="jh-entity-heading" data-cy="sysFormulaTabDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.sysFormulaTab.detail.title')">SysFormulaTab</span> {{ sysFormulaTab.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysFormulaTab.tabEname')">Tab Ename</span>
          </dt>
          <dd>
            <span>{{ sysFormulaTab.tabEname }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysFormulaTab.colEname')">Col Ename</span>
          </dt>
          <dd>
            <span>{{ sysFormulaTab.colEname }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysFormulaTab.calFormula')">Cal Formula</span>
          </dt>
          <dd>
            <span>{{ sysFormulaTab.calFormula }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysFormulaTab.calFormulaDes')">Cal Formula Des</span>
          </dt>
          <dd>
            <span>{{ sysFormulaTab.calFormulaDes }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysFormulaTab.roundType')">Round Type</span>
          </dt>
          <dd>
            <span>{{ sysFormulaTab.roundType }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysFormulaTab.weight')">Weight</span>
          </dt>
          <dd>
            <span>{{ sysFormulaTab.weight }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysFormulaTab.enable')">Enable</span>
          </dt>
          <dd>
            <span>{{ sysFormulaTab.enable }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="sysFormulaTab.id"
          :to="{ name: 'SysFormulaTabEdit', params: { sysFormulaTabId: sysFormulaTab.id } }"
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

<script lang="ts" src="./sys-formula-tab-details.component.ts"></script>
