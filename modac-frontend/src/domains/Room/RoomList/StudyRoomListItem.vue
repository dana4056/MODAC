<script setup>
import { defineProps } from "vue";
import { useRoomStore } from "@/stores/room";

defineProps({
  roomItem: Object,
});

const store = useRoomStore();
</script>

<template>
  <div :class="$style.list_item_style">
    {{ roomItem.seq }}
    {{ roomItem.title }}
    <button @click="store.enterRoom">입장하기</button>
  </div>
</template>

<style lang="css" module>
@import "./StudyRoomListItem.module.css";
</style>
