<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="dataPermission">
        <h2 class="jh-entity-heading" data-cy="dataPermissionDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.dataPermission.detail.title')">DataPermission</span> {{ dataPermission.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.dataPermission.code')">Code</span>
          </dt>
          <dd>
            <span>{{ dataPermission.code }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.dataPermission.name')">Name</span>
          </dt>
          <dd>
            <span>{{ dataPermission.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.dataPermission.ruleSql')">Rule Sql</span>
          </dt>
          <dd>
            <span>{{ dataPermission.ruleSql }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="dataPermission.id"
          :to="{ name: 'DataPermissionEdit', params: { dataPermissionId: dataPermission.id } }"
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

<script lang="ts" src="./data-permission-details.component.ts"></script>
