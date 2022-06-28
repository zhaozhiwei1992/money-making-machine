<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="sysCollectCol">
        <h2 class="jh-entity-heading" data-cy="sysCollectColDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.sysCollectCol.detail.title')">SysCollectCol</span> {{ sysCollectCol.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysCollectCol.colCname')">Col Cname</span>
          </dt>
          <dd>
            <span>{{ sysCollectCol.colCname }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysCollectCol.colEname')">Col Ename</span>
          </dt>
          <dd>
            <span>{{ sysCollectCol.colEname }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysCollectCol.tabId')">Tab Id</span>
          </dt>
          <dd>
            <span>{{ sysCollectCol.tabId }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysCollectCol.orderNum')">Order Num</span>
          </dt>
          <dd>
            <span>{{ sysCollectCol.orderNum }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysCollectCol.source')">Source</span>
          </dt>
          <dd>
            <span>{{ sysCollectCol.source }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysCollectCol.isEdit')">Is Edit</span>
          </dt>
          <dd>
            <span>{{ sysCollectCol.isEdit }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysCollectCol.requirement')">Requirement</span>
          </dt>
          <dd>
            <span>{{ sysCollectCol.requirement }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysCollectCol.type')">Type</span>
          </dt>
          <dd>
            <span>{{ sysCollectCol.type }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.sysCollectCol.config')">Config</span>
          </dt>
          <dd>
            <span>{{ sysCollectCol.config }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="sysCollectCol.id"
          :to="{ name: 'SysCollectColEdit', params: { sysCollectColId: sysCollectCol.id } }"
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

<script lang="ts" src="./sys-collect-col-details.component.ts"></script>
