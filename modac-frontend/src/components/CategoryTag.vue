<script setup>
import { ref, defineProps, computed, onMounted } from "vue";

onMounted(() => {
  const slotTag = document.querySelector("#slot");
  console.log(slotTag);
  const slotTagInnerText = ref(slotTag.innerText);
});

const categoryStyleMap = {
  algorithm: "study_category_style_algorithm",
  cs: "study_category_style_cs",
  develop: "study_category_style_develop",
  planning: "study_category_style_planning",
  etc: "study_category_style_etc",
};

const props = defineProps({
  categoryName: String,
});

// const categoryStyleState = computed(() => {
//   return categoryStyleMap[props.categoryName];
// });

const categoryStyleState = computed(() => {
  return categoryStyleMap.slotTagInnerText.value;
});
</script>

<template>
  <span :class="$style[categoryStyleState]">
    <slot id="slot"></slot>
  </span>
</template>

<style lang="css" module>
@import "./CategoryTag.module.css";
</style>
