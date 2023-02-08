<script setup>
import Chat from "@/domains/Room/Chat/Chat.vue";
import FavoriteRoomList from "@/domains/Room/Favorites/FavoritesRoomList.vue";
import FriendList from "@/domains/Room/Friend/FriendList.vue";
import RoomSettings from "@/domains/Room/Settings/RoomSettings.vue";
import RightSideBarButton from "./RightSideBarButton.vue";
import { ref, computed } from "vue";
import { useRoomStore } from "@/stores/room";
import { storeToRefs } from "pinia";

const store = useRoomStore();

const { currentRightSideBarContent } = storeToRefs(store);

const rightSideBarContentMap = {
  ROOM_CHAT: 0,
  FAVORITE_ROOM_LIST: 1,
  FRIEND_LIST: 2,
  ROOM_SETTINGS: 3,
};

const isRoomChatOpen = computed(() => {
  return currentRightSideBarContent.value === rightSideBarContentMap.ROOM_CHAT;
});
const isFavoriteRoomListOpen = computed(() => {
  return (
    currentRightSideBarContent.value ===
    rightSideBarContentMap.FAVORITE_ROOM_LIST
  );
});
const isFriendListOpen = computed(() => {
  return (
    currentRightSideBarContent.value === rightSideBarContentMap.FRIEND_LIST
  );
});
const isRoomSettingsOpen = computed(() => {
  return (
    currentRightSideBarContent.value === rightSideBarContentMap.ROOM_SETTINGS
  );
});
</script>

<template>
  <div :class="$style.right_side_bar">
    <div :class="$style.right_side_bar_content">
      <Chat v-if="isRoomChatOpen" />
      <FavoriteRoomList v-if="isFavoriteRoomListOpen" />
      <FriendList v-if="isFriendListOpen" />
      <RoomSettings v-if="isRoomSettingsOpen" />
    </div>
    <RightSideBarButton />
  </div>
</template>

<style lang="css" module>
@import "./RightSideBar.module.css";
</style>
