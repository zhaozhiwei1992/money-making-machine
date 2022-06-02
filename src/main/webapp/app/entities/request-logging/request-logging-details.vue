<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="requestLogging">
        <h2 class="jh-entity-heading" data-cy="requestLoggingDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.requestLogging.detail.title')">RequestLogging</span> {{ requestLogging.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.requestLogging.traceId')">Trace Id</span>
          </dt>
          <dd>
            <span>{{ requestLogging.traceId }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.requestLogging.loginName')">Login Name</span>
          </dt>
          <dd>
            <span>{{ requestLogging.loginName }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.requestLogging.requestURI')">Request URI</span>
          </dt>
          <dd>
            <span>{{ requestLogging.requestURI }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.requestLogging.clientIP')">Client IP</span>
          </dt>
          <dd>
            <span>{{ requestLogging.clientIP }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.requestLogging.currentTime')">Current Time</span>
          </dt>
          <dd>
            <span>{{ requestLogging.currentTime }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="requestLogging.id"
          :to="{ name: 'RequestLoggingEdit', params: { requestLoggingId: requestLogging.id } }"
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

<script lang="ts" src="./request-logging-details.component.ts"></script>
