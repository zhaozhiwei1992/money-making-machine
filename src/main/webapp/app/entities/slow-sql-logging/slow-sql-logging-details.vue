<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="slowSqlLogging">
        <h2 class="jh-entity-heading" data-cy="slowSqlLoggingDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.slowSqlLogging.detail.title')">SlowSqlLogging</span> {{ slowSqlLogging.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.slowSqlLogging.traceId')">Trace Id</span>
          </dt>
          <dd>
            <span>{{ slowSqlLogging.traceId }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.slowSqlLogging.currentTime')">Current Time</span>
          </dt>
          <dd>
            <span>{{ slowSqlLogging.currentTime }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.slowSqlLogging.sql')">Sql</span>
          </dt>
          <dd>
            <span>{{ slowSqlLogging.sql }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.slowSqlLogging.executeMillis')">Execute Millis</span>
          </dt>
          <dd>
            <span>{{ slowSqlLogging.executeMillis }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.slowSqlLogging.executeParams')">Execute Params</span>
          </dt>
          <dd>
            <span>{{ slowSqlLogging.executeParams }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="slowSqlLogging.id"
          :to="{ name: 'SlowSqlLoggingEdit', params: { slowSqlLoggingId: slowSqlLogging.id } }"
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

<script lang="ts" src="./slow-sql-logging-details.component.ts"></script>
