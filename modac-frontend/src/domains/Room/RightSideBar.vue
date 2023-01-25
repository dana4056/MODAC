<script setup>
import { ref } from "vue";

const rightSideBarContentMap = {
  ROOM_CHAT: 0,
  STARRED_ROOM_LIST: 1,
  FRIEND_LIST: 2,
  ROOM_SETTING: 3,
};
const rightSideBarContentNumber = ref(rightSideBarContentMap.ROOM_CHAT);
</script>

<template>
  <div :class="$style.right_side_bar">right</div>
</template>

<style lang="css" module>
@import "./RightSideBar.module.css";
</style>
