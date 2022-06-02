<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="systemParam">
        <h2 class="jh-entity-heading" data-cy="systemParamDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.systemParam.detail.title')">SystemParam</span> {{ systemParam.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.systemParam.code')">Code</span>
          </dt>
          <dd>
            <span>{{ systemParam.code }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.systemParam.name')">Name</span>
          </dt>
          <dd>
            <span>{{ systemParam.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.systemParam.value')">Value</span>
          </dt>
          <dd>
            <span>{{ systemParam.value }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.systemParam.remark')">Remark</span>
          </dt>
          <dd>
            <span>{{ systemParam.remark }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.systemParam.enable')">Enable</span>
          </dt>
          <dd>
            <span>{{ systemParam.enable }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="systemParam.id"
          :to="{ name: 'SystemParamEdit', params: { systemParamId: systemParam.id } }"
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

<script lang="ts" src="./system-param-details.component.ts"></script>
