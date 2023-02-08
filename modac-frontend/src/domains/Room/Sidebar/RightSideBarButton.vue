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
      <button class="active" :class="$style.buttons"
        @click="
          store.changeRightSideBarContent(rightSideBarContentMap.ROOM_CHAT)
        "
      >
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" :class="$style.icons">
          <path stroke-linecap="round" stroke-linejoin="round" d="M8.625 12a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375M21 12c0 4.556-4.03 8.25-9 8.25a9.764 9.764 0 01-2.555-.337A5.972 5.972 0 015.41 20.97a5.969 5.969 0 01-.474-.065 4.48 4.48 0 00.978-2.025c.09-.457-.133-.901-.467-1.226C3.93 16.178 3 14.189 3 12c0-4.556 4.03-8.25 9-8.25s9 3.694 9 8.25z" />
        </svg>
        <g transform="translate(10 5) scale(0.8 0.9)">
          <path d="M 0 30 v 70 h 100 v -70 l -50 -30 z" stroke="currentColor" stroke-width="10" fill="none"
            stroke-linejoin="round" stroke-linecap="round" />
        </g>
        <span :class="$style.titles">
          채팅
        </span>

      </button>
    </div>
    <div :class="$style.button_item">
      <button :class="$style.buttons"
        @click="
          store.changeRightSideBarContent(
            rightSideBarContentMap.FAVORITE_ROOM_LIST
          )
        "
      >
        <svg :class="$style.icons" viewBox="0 0 100 100">
        <g transform="translate(10 5) scale(0.8 0.9)">
          <path d="M 0 30 v 70 h 100 v -70 l -50 -30 z" stroke="currentColor" stroke-width="10" fill="none"
            stroke-linejoin="round" stroke-linecap="round" />
        </g>
        </svg>
        <span :class="$style.titles">
          즐겨찾기
        </span>
      </button>
    </div>
    <div :class="$style.button_item">
      <button :class="$style.buttons"
        @click="
          store.changeRightSideBarContent(rightSideBarContentMap.FRIEND_LIST)
        "
      >
        <span :class="$style.titles">
          친구목록
        </span>
      </button>
    </div>
    <div :class="$style.button_item">
      <button :class="$style.buttons"
        @click="
          store.changeRightSideBarContent(rightSideBarContentMap.ROOM_SETTINGS)
        "
      >
        <span :class="$style.titles">
          방설정
        </span>
      </button>
    </div>
  </div>
</template>

<style lang="css" module>
@import "RightSideBarButton.module.css";
</style>
