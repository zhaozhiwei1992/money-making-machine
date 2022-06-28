<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="eleUnion">
        <h2 class="jh-entity-heading" data-cy="eleUnionDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.eleUnion.detail.title')">EleUnion</span> {{ eleUnion.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.eleUnion.eleCatCode')">Ele Cat Code</span>
          </dt>
          <dd>
            <span>{{ eleUnion.eleCatCode }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.eleUnion.eleCatName')">Ele Cat Name</span>
          </dt>
          <dd>
            <span>{{ eleUnion.eleCatName }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.eleUnion.eleCode')">Ele Code</span>
          </dt>
          <dd>
            <span>{{ eleUnion.eleCode }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.eleUnion.eleName')">Ele Name</span>
          </dt>
          <dd>
            <span>{{ eleUnion.eleName }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.eleUnion.parentId')">Parent Id</span>
          </dt>
          <dd>
            <span>{{ eleUnion.parentId }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.eleUnion.levelNo')">Level No</span>
          </dt>
          <dd>
            <span>{{ eleUnion.levelNo }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.eleUnion.isLeaf')">Is Leaf</span>
          </dt>
          <dd>
            <span>{{ eleUnion.isLeaf }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.eleUnion.isEnabled')">Is Enabled</span>
          </dt>
          <dd>
            <span>{{ eleUnion.isEnabled }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.eleUnion.createTime')">Create Time</span>
          </dt>
          <dd>
            <span>{{ eleUnion.createTime }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.eleUnion.updateTime')">Update Time</span>
          </dt>
          <dd>
            <span>{{ eleUnion.updateTime }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="eleUnion.id" :to="{ name: 'EleUnionEdit', params: { eleUnionId: eleUnion.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./ele-union-details.component.ts"></script>
