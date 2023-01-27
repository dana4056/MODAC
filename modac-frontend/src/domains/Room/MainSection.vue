<script setup>
import RoundButton from "@/components/RoundButton.vue";
import { useRoomStore } from "@/stores/room";

const store = useRoomStore();
</script>

<template>
  <div :class="$style.main_section">main section</div>
  <RoundButton @click="store.exitRoom">방 나가기</RoundButton>
</template>

<style lang="css" module>
@import "./MainSection.module.css";
</style>
