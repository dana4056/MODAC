<script setup>
import { ref, onMounted, onUpdated, computed } from "vue";

// const categoryStyle = ref("");
const categoryStyleMap = {
  알고리즘: "study_category_style_algorithm",
  CS: "study_category_style_cs",
  개발: "study_category_style_develop",
  면접: "study_category_style_planning",
  공통: "study_category_style_etc",
};

const slotTag = ref(undefined);
const categoryStyle = computed(() => {
  console.log("computed"); // 왜 실행되지 않지?
  // slotTag.value === undefined 의 결과가 false인 것이 변함없기 때문?
  return slotTag.value === undefined
    ? ""
    : categoryStyleMap[slotTag.value.innerText];
});

onMounted(() => {
  slotTag.value = document.querySelector("#slot");
});

onUpdated(() => {
  console.log("updated");
  console.log(slotTag.value);
  console.log(slotTag.value.innerText);
  console.log(categoryStyleMap[slotTag.value.innerText]);
});
</script>

<template>
  <span id="slot" :class="$style[categoryStyle]">
    <slot></slot>
  </span>
</template>

<style lang="css" module>
@import "./CategoryTag.module.css";
</style>
