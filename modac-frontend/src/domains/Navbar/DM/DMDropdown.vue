<script setup>
import OverflowDiv from "@/components/OverflowDiv.vue";
import DMRoomList from "./DMRoomList.vue";
import DMLogList from "./DMLogList.vue";
</script>

<template>
  <div :class="$style.container">
    <OverflowDiv>
      <div :class="$style.dm_container">
        <DMRoomList />
      </div>
    </OverflowDiv>
    <OverflowDiv>
      <DMLogList />
    </OverflowDiv>
  </div>
</template>

<style lang="css" module>
@import "./DMDropdown.module.css";
</style>
