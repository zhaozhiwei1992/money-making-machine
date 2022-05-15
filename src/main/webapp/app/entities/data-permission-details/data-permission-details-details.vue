<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="dataPermissionDetails">
        <h2 class="jh-entity-heading" data-cy="dataPermissionDetailsDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.detail.title')">DataPermissionDetails</span>
          {{ dataPermissionDetails.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.ruleId')">Rule Id</span>
          </dt>
          <dd>
            <span>{{ dataPermissionDetails.ruleId }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.leftBracket')">Left Bracket</span>
          </dt>
          <dd>
            <span>{{ dataPermissionDetails.leftBracket }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.column')">Column</span>
          </dt>
          <dd>
            <span>{{ dataPermissionDetails.column }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.op')">Op</span>
          </dt>
          <dd>
            <span>{{ dataPermissionDetails.op }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.value')">Value</span>
          </dt>
          <dd>
            <span>{{ dataPermissionDetails.value }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.rightBracket')">Right Bracket</span>
          </dt>
          <dd>
            <span>{{ dataPermissionDetails.rightBracket }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.ordernum')">Ordernum</span>
          </dt>
          <dd>
            <span>{{ dataPermissionDetails.ordernum }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.dataPermissionDetails.logicalRel')">Logical Rel</span>
          </dt>
          <dd>
            <span>{{ dataPermissionDetails.logicalRel }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="dataPermissionDetails.id"
          :to="{ name: 'DataPermissionDetailsEdit', params: { dataPermissionDetailsId: dataPermissionDetails.id } }"
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

<script lang="ts" src="./data-permission-details-details.component.ts"></script>
