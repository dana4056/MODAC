<script setup>
import StudyRoomList from "@/domains/Room/RoomList/StudyRoomList.vue";
import StudyRoom from "@/domains/Room/StudyRoom.vue";
import { ref } from "vue";

const isEnteredRoom = ref(true);
</script>

<template>
  <StudyRoomList v-if="!isEnteredRoom" />
  <StudyRoom v-if="isEnteredRoom" />
</template>

<style></style>
