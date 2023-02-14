<script setup>
import { defineProps } from "vue";
const props = defineProps({
  time: String,
  status: Number,
});
</script>

<template>
  <div>
    <span v-if="props.status == 0" :class="$style.style0">{{
      props.time
    }}</span>
    <span v-if="props.status == 1" :class="$style.style1">{{
      props.time
    }}</span>
    <span v-if="props.status == 2" :class="$style.style2">{{
      props.time
    }}</span>
  </div>
</template>

<style lang="css" module>
@import "TodoListItemTimer.module.css";
</style>
