<script setup>
import LeftSideBar from "@/domains/Room/LeftSideBar.vue";
import MainSection from "@/domains/Room/MainSection.vue";
import RightSideBar from "@/domains/Room/RightSideBar.vue";
import { ref } from "vue";

const isOpenedLeftSideBar = ref(true);
const isOpenedRightSideBar = ref(true);
</script>

<template>
  <div :class="$style.study_room_wrapper_flex">
    <LeftSideBar v-if="isOpenedLeftSideBar" />
    <MainSection />
    <RightSideBar v-if="isOpenedRightSideBar" />
  </div>
</template>
<style lang="css" module>
@import "./StudyRoom.module.css";
</style>
