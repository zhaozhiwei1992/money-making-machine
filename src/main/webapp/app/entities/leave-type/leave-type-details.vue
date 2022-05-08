<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="leaveType">
        <h2 class="jh-entity-heading" data-cy="leaveTypeDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.leaveType.detail.title')">LeaveType</span> {{ leaveType.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.leaveType.code')">Code</span>
          </dt>
          <dd>
            <span>{{ leaveType.code }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.leaveType.name')">Name</span>
          </dt>
          <dd>
            <span>{{ leaveType.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.leaveType.parentid')">Parentid</span>
          </dt>
          <dd>
            <span>{{ leaveType.parentid }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.leaveType.enabled')">Enabled</span>
          </dt>
          <dd>
            <span>{{ leaveType.enabled }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="leaveType.id"
          :to="{ name: 'LeaveTypeEdit', params: { leaveTypeId: leaveType.id } }"
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

<script lang="ts" src="./leave-type-details.component.ts"></script>
