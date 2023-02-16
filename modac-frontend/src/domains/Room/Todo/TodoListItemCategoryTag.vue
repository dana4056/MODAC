<script setup>
import { defineProps } from "vue";
const props = defineProps({
  categoriesName: String,
});
</script>

<template>
  <div>
    <span v-if="props.categoriesName == '알고리즘'" :class="$style.algo"
      >#알고리즘</span
    >
    <span v-if="props.categoriesName == 'CS'" :class="$style.cs">#CS</span>
    <span v-if="props.categoriesName == '개발'" :class="$style.dev">#개발</span>
    <span v-if="props.categoriesName == '면접'" :class="$style.plan"
      >#면접</span
    >
    <span v-if="props.categoriesName == '공통'" :class="$style.common"
      >#공통</span
    >
  </div>
</template>

<style lang="css" module>
@import "TodoListItemCategoryTag.module.css";
</style>
