<script setup>
import { defineProps } from "vue";

const props = defineProps({
  categoriesName: String,
});
</script>

<template>
  <div>
    <span v-if="props.categoriesName === '알고리즘'" :class="$style.algo_style"
      >#알고리즘</span
    >
    <span v-if="props.categoriesName === 'CS'" :class="$style.cs_style"
      >#CS</span
    >
    <span v-if="props.categoriesName === '개발'" :class="$style.develop_style"
      >#개발</span
    >
    <span v-if="props.categoriesName === '공통'" :class="$style.common_style"
      >#공통</span
    >
  </div>
</template>

<style lang="css" module>
@import "./CategoryTag.module.css";
</style>
