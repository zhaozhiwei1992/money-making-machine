<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="leaveSlip">
        <h2 class="jh-entity-heading" data-cy="leaveSlipDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.leaveSlip.detail.title')">LeaveSlip</span> {{ leaveSlip.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.leaveSlip.type')">Type</span>
          </dt>
          <dd>
            <span>{{ leaveSlip.type }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.leaveSlip.startTime')">Start Time</span>
          </dt>
          <dd>
            <span>{{ leaveSlip.startTime }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.leaveSlip.endTime')">End Time</span>
          </dt>
          <dd>
            <span>{{ leaveSlip.endTime }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.leaveSlip.reason')">Reason</span>
          </dt>
          <dd>
            <span>{{ leaveSlip.reason }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.leaveSlip.file')">File</span>
          </dt>
          <dd>
            <span>{{ leaveSlip.file }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.leaveSlip.superior')">Superior</span>
          </dt>
          <dd>
            <span>{{ leaveSlip.superior }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.leaveSlip.wfstatus')">Wfstatus</span>
          </dt>
          <dd>
            <span>{{ leaveSlip.wfstatus }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.leaveSlip.leavePerson')">Leave Person</span>
          </dt>
          <dd>
            <span>{{ leaveSlip.leavePerson }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="leaveSlip.id"
          :to="{ name: 'LeaveSlipEdit', params: { leaveSlipId: leaveSlip.id } }"
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

<script lang="ts" src="./leave-slip-details.component.ts"></script>
