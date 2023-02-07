<script setup>
import RightSideBarHeader from "../Sidebar/RightSideBarHeader.vue";
import RoomSettingsBody from "./RoomSettingsBody.vue";
import RightSideBarBody from "../Sidebar/RightSideBarBody.vue";
</script>

<template>
  <div :class="$style.setting_box">
    <RightSideBarHeader>방 정보 수정</RightSideBarHeader>
    <RightSideBarBody>
      <RoomSettingsBody />
    </RightSideBarBody>
  </div>
</template>

<style module>
@import "./RoomSettings.module.css";
</style>
