<script setup>
import FavoritesRoomListItem from "./FavoritesRoomListItem.vue";
import RightSideBarHeader from "../Sidebar/RightSideBarHeader.vue";
import RightSideBarBody from "../Sidebar/RightSideBarBody.vue";

import { ref } from "vue";

const favortiesRoomList = ref([
  {
    id: 1,
    name: "즐찾방 1",
    description: "설명 1",
    host: "방장1",
    theme: "모닥불",
  },
]);
</script>

<template>
  <RightSideBarHeader>즐겨찾는 스터디룸</RightSideBarHeader>
  <RightSideBarBody>
    <FavoritesRoomListItem
      v-for="favoritesRoom in favortiesRoomList"
      :key="favoritesRoom.id"
      :favoritesRoom="favoritesRoom"
    />
  </RightSideBarBody>
</template>

<style lang="css" module>
@import "./FavoritesRoomList.module.css";
</style>
