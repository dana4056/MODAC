<script setup>
import { defineProps } from "vue";
const props = defineProps({
  openState: Boolean,
  status: Number,
  title: String,
});
</script>

<template>
  <div
    v-if="props.openState && props.status == 2"
    :class="$style.style1"
    @click="toggleOpenState"
  >
    {{ props.title }}
  </div>
  <div
    v-if="props.openState && props.status != 2"
    :class="$style.style2"
    @click="toggleOpenState"
  >
    {{ props.title }}
  </div>
  <div v-if="!props.openState" @click="toggleOpenState" :class="$style.style3">
    {{ props.title }}
  </div>
</template>

<style lang="css" module>
@import "TodoItemContent.module.css";
</style>
