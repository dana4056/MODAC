<script setup>
import StudyRoomList from "@/domains/Room/RoomList/StudyRoomList.vue";
import StudyRoom from "@/domains/Room/StudyRoom.vue";
import { useRoomStore } from "@/stores/room";
import { storeToRefs } from "pinia";

const store = useRoomStore();
const { isEnteredRoom } = storeToRefs(store);
</script>

<template>
  <router-link to="/ApiTest">dddddddddd</router-link>
  <StudyRoomList v-if="!isEnteredRoom" />
  <StudyRoom v-if="isEnteredRoom" />
</template>

<style></style>
