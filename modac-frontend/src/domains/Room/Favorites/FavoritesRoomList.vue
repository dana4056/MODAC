<script setup>
import FavoritesRoomListItem from "./FavoritesRoomListItem.vue";
import RightSideBarHeader from "../Sidebar/RightSideBarHeader.vue";
import RightSideBarBody from "../Sidebar/RightSideBarBody.vue";
import { useRoomStore } from '@/stores/room';
import { storeToRefs } from "pinia";

const roomStore = useRoomStore();
const { favorite_room_list } = storeToRefs(roomStore);
</script>

<template>
  <RightSideBarHeader>즐겨찾는 스터디룸</RightSideBarHeader>
  <RightSideBarBody>
    <FavoritesRoomListItem
      v-for="favoritesRoom in favorite_room_list"
      :key="favoritesRoom.seq"
      :favoritesRoom="favoritesRoom"
    />
  </RightSideBarBody>
</template>

<style lang="css" module>
@import "./FavoritesRoomList.module.css";
</style>
