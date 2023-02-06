<script setup>
import ChatList from "@/domains/Room/Chat/ChatList.vue";
import FavoriteRoomList from "@/domains/Room/Favorites/FavoritesRoomList.vue";
import FriendList from "@/domains/Room/Friend/FriendList.vue";
import RoomSettings from "@/domains/Room/Settings/RoomSettings.vue";
import RoundButton from "@/components/RoundButton.vue";
import InfiniteScrollVue from "./Chat/InfiniteScroll.vue";
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
      <ChatList v-if="isRoomChatOpen" />
      <!-- <InfiniteScrollVue v-if="isRoomChatOpen" /> -->
      <FavoriteRoomList v-if="isFavoriteRoomListOpen" />
      <FriendList v-if="isFriendListOpen" />
      <RoomSettings v-if="isRoomSettingsOpen" />
    </div>
    <div :class="$style.right_side_bar_button_wrapper">
      <div :class="$style.right_side_bar_button">
        <RoundButton
          @click="
            store.changeRightSideBarContent(rightSideBarContentMap.ROOM_CHAT)
          "
          >Chat</RoundButton
        >
      </div>
      <div :class="$style.right_side_bar_button">
        <RoundButton
          @click="
            store.changeRightSideBarContent(
              rightSideBarContentMap.FAVORITE_ROOM_LIST
            )
          "
          >Star</RoundButton
        >
      </div>
      <div :class="$style.right_side_bar_button">
        <RoundButton
          @click="
            store.changeRightSideBarContent(rightSideBarContentMap.FRIEND_LIST)
          "
          >Frie</RoundButton
        >
      </div>
      <div :class="$style.right_side_bar_button">
        <RoundButton
          @click="
            store.changeRightSideBarContent(
              rightSideBarContentMap.ROOM_SETTINGS
            )
          "
          >Sett</RoundButton
        >
      </div>
    </div>
  </div>
</template>

<style lang="css" module>
@import "./RightSideBar.module.css";
</style>
