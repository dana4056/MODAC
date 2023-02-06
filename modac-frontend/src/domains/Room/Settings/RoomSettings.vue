<script setup>
import RoomSettingsHeader from "./RoomSettingsHeader.vue";
import RoomSettingsBody from "./RoomSettingsBody.vue";
</script>

<template>
  <div :class="$style.setting_box">
    <RoomSettingsHeader />
    <RoomSettingsBody />
  </div>
</template>

<style module>
@import "./RoomSettings.module.css";
</style>
