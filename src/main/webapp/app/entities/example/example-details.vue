<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="example">
        <h2 class="jh-entity-heading" data-cy="exampleDetailsHeading">
          <span v-text="$t('moneyMakingMachineApp.example.detail.title')">Example</span> {{ example.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('moneyMakingMachineApp.example.name')">Name</span>
          </dt>
          <dd>
            <span>{{ example.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.example.age')">Age</span>
          </dt>
          <dd>
            <span>{{ example.age }}</span>
          </dd>
          <dt>
            <span v-text="$t('moneyMakingMachineApp.example.sex')">Sex</span>
          </dt>
          <dd>
            <span>{{ example.sex }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="example.id" :to="{ name: 'ExampleEdit', params: { exampleId: example.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./example-details.component.ts"></script>
