<script setup>
import SideBarToggleButton from "@/components/SideBarToggleButton.vue";
import LeftSideBar from "@/domains/Room/Sidebar/LeftSideBar.vue";
import MainSection from "@/domains/Room/MainSection.vue";
import RightSideBar from "@/domains/Room/Sidebar/RightSideBar.vue";
import Wrapper from "@/components/Wrapper.vue";
import OverflowDiv from "@/components/OverflowDiv.vue";

import { useRoomStore } from "@/stores/room";
import { storeToRefs } from "pinia";
import { computed } from "vue";

const store = useRoomStore();
const { isOpenedLeftSideBar, isOpenedRightSideBar } = storeToRefs(store);

const leftSideBarStyleState = computed(() =>
  isOpenedLeftSideBar.value
    ? "move_side_bar_to_default"
    : "move_side_bar_to_right"
);
const rightSideBarStyleState = computed(() =>
  isOpenedRightSideBar.value
    ? "move_side_bar_to_default"
    : "move_side_bar_to_left"
);
</script>

<template>
  <OverflowDiv>
    <Wrapper :class="$style.study_room_wrapper_flex">
      <Wrapper
        :class="`${$style[leftSideBarStyleState]} ${$style.left_side_bar_wrapper} ${$style.side_bar_wrapper_flex}`"
      >
        <LeftSideBar :class="$style.side_bar_content" />
        <SideBarToggleButton
          :class="$style.flex_1_12"
          @click="store.toggleLeftSideBar"
          >></SideBarToggleButton
        >
      </Wrapper>
      <MainSection />
      <Wrapper
        :class="`${$style[rightSideBarStyleState]} ${$style.right_side_bar_wrapper} ${$style.side_bar_wrapper_flex}`"
      >
        <SideBarToggleButton
          :class="`${$style.rotate_180} ${$style.flex_1_12}`"
          @click="store.toggleRightSideBar"
          >></SideBarToggleButton
        >
        <RightSideBar :class="$style.side_bar_content" />
      </Wrapper>
    </Wrapper>
  </OverflowDiv>
</template>
<style lang="css" module>
@import "./StudyRoom.module.css";
</style>
