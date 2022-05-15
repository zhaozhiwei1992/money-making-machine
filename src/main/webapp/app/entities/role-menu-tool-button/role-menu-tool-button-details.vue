<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="roleMenuToolButton">
        <h2 class="jh-entity-heading" data-cy="roleMenuToolButtonDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.roleMenuToolButton.detail.title')">RoleMenuToolButton</span> {{ roleMenuToolButton.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.roleMenuToolButton.roleId')">Role Id</span>
          </dt>
          <dd>
            <span>{{ roleMenuToolButton.roleId }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.roleMenuToolButton.menuId')">Menu Id</span>
          </dt>
          <dd>
            <span>{{ roleMenuToolButton.menuId }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.roleMenuToolButton.toolButtonId')">Tool Button Id</span>
          </dt>
          <dd>
            <span>{{ roleMenuToolButton.toolButtonId }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="roleMenuToolButton.id"
          :to="{ name: 'RoleMenuToolButtonEdit', params: { roleMenuToolButtonId: roleMenuToolButton.id } }"
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

<script lang="ts" src="./role-menu-tool-button-details.component.ts"></script>
