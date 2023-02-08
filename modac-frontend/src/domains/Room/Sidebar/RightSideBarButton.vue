<script setup>
import { useRoomStore } from "@/stores/room";

const store = useRoomStore();

const rightSideBarContentMap = {
  ROOM_CHAT: 0,
  FAVORITE_ROOM_LIST: 1,
  FRIEND_LIST: 2,
  ROOM_SETTINGS: 3,
};
</script>

<template>
  <div :class="$style.button_flex">
    <div :class="$style.button_item">
      <button
        @click="
          store.changeRightSideBarContent(rightSideBarContentMap.ROOM_CHAT)
        "
      >
        Chat
      </button>
    </div>
    <div :class="$style.button_item">
      <button
        @click="
          store.changeRightSideBarContent(
            rightSideBarContentMap.FAVORITE_ROOM_LIST
          )
        "
      >
        Star
      </button>
    </div>
    <div :class="$style.button_item">
      <button
        @click="
          store.changeRightSideBarContent(rightSideBarContentMap.FRIEND_LIST)
        "
      >
        Frie
      </button>
    </div>
    <div :class="$style.button_item">
      <button
        @click="
          store.changeRightSideBarContent(rightSideBarContentMap.ROOM_SETTINGS)
        "
      >
        Sett
      </button>
    </div>
  </div>
</template>

<style lang="css" module>
@import "RightSideBarButton.module.css";
</style>
