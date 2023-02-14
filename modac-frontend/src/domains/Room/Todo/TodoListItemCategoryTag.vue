<script setup>
import { defineProps } from "vue";
const props = defineProps({
  categories_seq: Number,
});
</script>

<template>
  <div>
    <span v-if="props.categories_seq == 0" :class="$style.algo">#알고리즘</span>
    <span v-if="props.categories_seq == 1" :class="$style.cs">#CS</span>
    <span v-if="props.categories_seq == 2" :class="$style.dev">#개발</span>
    <span v-if="props.categories_seq == 3" :class="$style.plan">#기획</span>
    <span v-if="props.categories_seq == 4" :class="$style.common">#기타</span>
  </div>
</template>

<style lang="css" module>
@import "TodoListItemCategoryTag.module.css";
</style>
