<script setup>
import LeftSideBar from "@/domains/Room/LeftSideBar.vue";
import MainSection from "@/domains/Room/MainSection.vue";
import RightSideBar from "@/domains/Room/RightSideBar.vue";
import { useRoomStore } from "@/stores/room";
import { storeToRefs } from "pinia";

const store = useRoomStore();
const { isOpenedLeftSideBar, isOpenedRightSideBar } = storeToRefs(store);
</script>

<template>
  <div :class="$style.study_room_wrapper_flex">
    <LeftSideBar
      :class="isOpenedLeftSideBar ? $style.block_ : $style.hidden_"
    />
    <MainSection />
    <RightSideBar
      :class="isOpenedRightSideBar ? $style.block_ : $style.hidden_"
    />
  </div>
</template>
<style lang="css" module>
@import "./StudyRoom.module.css";
</style>
