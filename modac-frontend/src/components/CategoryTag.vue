<script setup>
import { defineProps, computed } from "vue";

const categoryStyleMap = {
  알고리즘: "study_category_style_algorithm",
  CS: "study_category_style_cs",
  개발: "study_category_style_develop",
  면접: "study_category_style_planning",
  공통: "study_category_style_etc",
};

const props = defineProps({
  categoryName: String,
});

const categoryStyleState = computed(() => {
  return categoryStyleMap[props.categoryName];
});
</script>

<template>
  <span id="slot" :class="$style[categoryStyleState]">
    <slot></slot>
  </span>
</template>

<style lang="css" module>
@import "./CategoryTag.module.css";
</style>
