<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="taskParam">
        <h2 class="jh-entity-heading" data-cy="taskParamDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.taskParam.detail.title')">TaskParam</span> {{ taskParam.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.taskParam.name')">Name</span>
          </dt>
          <dd>
            <span>{{ taskParam.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.taskParam.cronExpression')">Cron Expression</span>
          </dt>
          <dd>
            <span>{{ taskParam.cronExpression }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.taskParam.startClass')">Start Class</span>
          </dt>
          <dd>
            <span>{{ taskParam.startClass }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.taskParam.enable')">Enable</span>
          </dt>
          <dd>
            <span>{{ taskParam.enable }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="taskParam.id"
          :to="{ name: 'TaskParamEdit', params: { taskParamId: taskParam.id } }"
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

<script lang="ts" src="./task-param-details.component.ts"></script>
