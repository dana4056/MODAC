<script setup>
import { onMounted } from "vue";
import { useRoomStore } from "@/stores/room";

let links = null;
const store = useRoomStore();

const rightSideBarContentMap = {
  ROOM_CHAT: 0,
  FAVORITE_ROOM_LIST: 1,
  FRIEND_LIST: 2,
  ROOM_SETTINGS: 3,
};

onMounted(() => {
  links = document.querySelectorAll("button");
  // function called in response to a click event on the anchor link
  function handleClick(e) {
    // prevent the default behavior, but most importantly remove the class of .active from those elements with it
    e.preventDefault();
    links.forEach((link) => {
      if (link.classList.contains("active")) {
        link.classList.remove("active");
      }
    });

    // apply the class of active to animate the svg an show the span element
    this.classList.add("active");
  }

  // listen for a click event on each and every anchor link
  links.forEach((link) => link.addEventListener("click", handleClick));
});
</script>

<template>
  <div class="button_flex">
    <div class="button_item">
      <button
        class="buttons active"
        @click="
          store.changeRightSideBarContent(rightSideBarContentMap.ROOM_CHAT)
        "
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1.5"
          stroke="currentColor"
          class="icons"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M8.625 12a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H8.25m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0H12m4.125 0a.375.375 0 11-.75 0 .375.375 0 01.75 0zm0 0h-.375M21 12c0 4.556-4.03 8.25-9 8.25a9.764 9.764 0 01-2.555-.337A5.972 5.972 0 015.41 20.97a5.969 5.969 0 01-.474-.065 4.48 4.48 0 00.978-2.025c.09-.457-.133-.901-.467-1.226C3.93 16.178 3 14.189 3 12c0-4.556 4.03-8.25 9-8.25s9 3.694 9 8.25z"
          />
        </svg>
        <!-- <g transform="translate(10 5) scale(0.8 0.9)">
          <path d="M 0 30 v 70 h 100 v -70 l -50 -30 z" stroke="currentColor" stroke-width="10" fill="none"
            stroke-linejoin="round" stroke-linecap="round" />
        </g> -->
        <span class="titles"> 채팅하기 </span>
      </button>
    </div>

    <div class="button_item">
      <button
        class="buttons"
        @click="
          store.changeRightSideBarContent(
            rightSideBarContentMap.FAVORITE_ROOM_LIST
          )
        "
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1.5"
          stroke="currentColor"
          class="icons"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M17.593 3.322c1.1.128 1.907 1.077 1.907 2.185V21L12 17.25 4.5 21V5.507c0-1.108.806-2.057 1.907-2.185a48.507 48.507 0 0111.186 0z"
          />
        </svg>

        <span class="titles"> 즐겨찾기 </span>
      </button>
    </div>

    <div class="button_item">
      <button
        class="buttons"
        @click="
          store.changeRightSideBarContent(rightSideBarContentMap.FRIEND_LIST)
        "
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1.5"
          stroke="currentColor"
          class="icons"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M15.75 6a3.75 3.75 0 11-7.5 0 3.75 3.75 0 017.5 0zM4.501 20.118a7.5 7.5 0 0114.998 0A17.933 17.933 0 0112 21.75c-2.676 0-5.216-.584-7.499-1.632z"
          />
        </svg>
        <span class="titles"> 친구목록 </span>
      </button>
    </div>
    <div class="button_item">
      <button
        class="buttons"
        @click="
          store.changeRightSideBarContent(rightSideBarContentMap.ROOM_SETTINGS)
        "
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke-width="1.5"
          stroke="currentColor"
          class="icons"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M9.594 3.94c.09-.542.56-.94 1.11-.94h2.593c.55 0 1.02.398 1.11.94l.213 1.281c.063.374.313.686.645.87.074.04.147.083.22.127.324.196.72.257 1.075.124l1.217-.456a1.125 1.125 0 011.37.49l1.296 2.247a1.125 1.125 0 01-.26 1.431l-1.003.827c-.293.24-.438.613-.431.992a6.759 6.759 0 010 .255c-.007.378.138.75.43.99l1.005.828c.424.35.534.954.26 1.43l-1.298 2.247a1.125 1.125 0 01-1.369.491l-1.217-.456c-.355-.133-.75-.072-1.076.124a6.57 6.57 0 01-.22.128c-.331.183-.581.495-.644.869l-.213 1.28c-.09.543-.56.941-1.11.941h-2.594c-.55 0-1.02-.398-1.11-.94l-.213-1.281c-.062-.374-.312-.686-.644-.87a6.52 6.52 0 01-.22-.127c-.325-.196-.72-.257-1.076-.124l-1.217.456a1.125 1.125 0 01-1.369-.49l-1.297-2.247a1.125 1.125 0 01.26-1.431l1.004-.827c.292-.24.437-.613.43-.992a6.932 6.932 0 010-.255c.007-.378-.138-.75-.43-.99l-1.004-.828a1.125 1.125 0 01-.26-1.43l1.297-2.247a1.125 1.125 0 011.37-.491l1.216.456c.356.133.751.072 1.076-.124.072-.044.146-.087.22-.128.332-.183.582-.495.644-.869l.214-1.281z"
          />
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"
          />
        </svg>

        <span class="titles"> 설정하기 </span>
      </button>
    </div>
  </div>
</template>

<style lang="css">
@import "RightSideBarButton.module.css";
</style>
