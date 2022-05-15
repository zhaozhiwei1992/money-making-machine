<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="roleMenu">
        <h2 class="jh-entity-heading" data-cy="roleMenuDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.roleMenu.detail.title')">RoleMenu</span> {{ roleMenu.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.roleMenu.roleId')">Role Id</span>
          </dt>
          <dd>
            <span>{{ roleMenu.roleId }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.roleMenu.menuId')">Menu Id</span>
          </dt>
          <dd>
            <span>{{ roleMenu.menuId }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="roleMenu.id" :to="{ name: 'RoleMenuEdit', params: { roleMenuId: roleMenu.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./role-menu-details.component.ts"></script>
