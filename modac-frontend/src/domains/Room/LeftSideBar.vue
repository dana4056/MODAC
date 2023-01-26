<script setup>
import { useRoomStore } from "@/stores/room";

const store = useRoomStore();
</script>

<template>
  <div :class="$style.left_side_bar">
    <LeftSideBarContent :class="flex_11_12" />
    <SideBarToggleButton
      :class="flex_1_12"
      @click="store.toggleLeftSideBar"
    ></SideBarToggleButton>
  </div>
</template>

<style lang="css" module>
@import "./LeftSideBar.module.css";
</style>
