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
    <span v-if="props.categoriesName == '기획'" :class="$style.plan"
      >#기획</span
    >
    <span v-if="props.categoriesName == '기타'" :class="$style.common"
      >#기타</span
    >
  </div>
</template>

<style lang="css" module>
@import "TodoListItemCategoryTag.module.css";
</style>
