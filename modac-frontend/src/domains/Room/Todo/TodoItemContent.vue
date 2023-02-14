<script setup>
import { defineProps } from "vue";
const props = defineProps({
  toggleOpenState: Function,
  openState: Boolean,
  status: Number,
  title: String,
});
</script>

<template>
  <div @click="props.toggleOpenState">
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
    <div
      v-if="!props.openState"
      @click="toggleOpenState"
      :class="$style.style3"
    >
      {{ props.title }}
    </div>
  </div>
</template>

<style lang="css" module>
@import "TodoItemContent.module.css";
</style>
