<script setup>
import { ref, computed, onMounted } from "vue";

const isReadyToStyle = ref(false);

onMounted(() => {
  isReadyToStyle.value = true;

  const categoryStyleMap = {
    알고리즘: "study_category_style_algorithm",
    CS: "study_category_style_cs",
    개발: "study_category_style_develop",
    기획: "study_category_style_planning",
    기타: "study_category_style_etc",
  };

  const slotTag = ref(document.querySelector("#slot")); // <span>
  const slotTagInnerText = computed(() => slotTag.value.innerText);
  // console.log(slotTagInnerText.value);
  // console.log(categoryStyleMap[slotTagInnerText.value]);
  const slotTagStyleState = computed(() => {
    return categoryStyleMap[slotTagInnerText.value];
  });
});
</script>

<template>
  <span id="slot" :class="isReadyToStyle && $style[slotTagStyleState]">
    <slot></slot>
  </span>
  <span :class="$style.study_category_style_algorithm">hello</span>
</template>

<style lang="css" module>
@import "./CategoryTag.module.css";
</style>
