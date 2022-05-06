<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="menu">
        <h2 class="jh-entity-heading" data-cy="menuDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.menu.detail.title')">Menu</span> {{ menu.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.menu.url')">Url</span>
          </dt>
          <dd>
            <span>{{ menu.url }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.menu.name')">Name</span>
          </dt>
          <dd>
            <span>{{ menu.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.menu.iconCls')">Icon Cls</span>
          </dt>
          <dd>
            <span>{{ menu.iconCls }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.menu.ordernum')">Ordernum</span>
          </dt>
          <dd>
            <span>{{ menu.ordernum }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.menu.keepAlive')">Keep Alive</span>
          </dt>
          <dd>
            <span>{{ menu.keepAlive }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.menu.requireAuth')">Require Auth</span>
          </dt>
          <dd>
            <span>{{ menu.requireAuth }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.menu.parentId')">Parent Id</span>
          </dt>
          <dd>
            <span>{{ menu.parentId }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.menu.enabled')">Enabled</span>
          </dt>
          <dd>
            <span>{{ menu.enabled }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.menu.config')">Config</span>
          </dt>
          <dd>
            <span>{{ menu.config }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="menu.id" :to="{ name: 'MenuEdit', params: { menuId: menu.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./menu-details.component.ts"></script>
