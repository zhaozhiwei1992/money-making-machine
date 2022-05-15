<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="dataPermissionsRel">
        <h2 class="jh-entity-heading" data-cy="dataPermissionsRelDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.dataPermissionsRel.detail.title')">DataPermissionsRel</span> {{ dataPermissionsRel.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.dataPermissionsRel.ruleId')">Rule Id</span>
          </dt>
          <dd>
            <span>{{ dataPermissionsRel.ruleId }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.dataPermissionsRel.roleId')">Role Id</span>
          </dt>
          <dd>
            <span>{{ dataPermissionsRel.roleId }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.dataPermissionsRel.menuId')">Menu Id</span>
          </dt>
          <dd>
            <span>{{ dataPermissionsRel.menuId }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="dataPermissionsRel.id"
          :to="{ name: 'DataPermissionsRelEdit', params: { dataPermissionsRelId: dataPermissionsRel.id } }"
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

<script lang="ts" src="./data-permissions-rel-details.component.ts"></script>
