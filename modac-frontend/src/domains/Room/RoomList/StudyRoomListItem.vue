<script setup>
import Card from "@/components/Card.vue";
import { defineProps } from "vue";
import { useRoomStore } from "@/stores/room";

defineProps({
  roomItem: Object,
});

const store = useRoomStore();
</script>

<template>
  <Card :class="$style.list_item_style">
      <div :class="$style.list_item_div_col">
        <!-- <div :class="$style.item_seq">{{ roomItem.seq }}</div> -->
        <div :class="$style.item_title">

          <span v-if="roomItem.publicType == 1">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-4 h-4 inline-block">
              <path d="M18 1.5c2.9 0 5.25 2.35 5.25 5.25v3.75a.75.75 0 01-1.5 0V6.75a3.75 3.75 0 10-7.5 0v3a3 3 0 013 3v6.75a3 3 0 01-3 3H3.75a3 3 0 01-3-3v-6.75a3 3 0 013-3h9v-3c0-2.9 2.35-5.25 5.25-5.25z" />
            </svg>
          </span>

          <span v-else>
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-4 h-4 inline-block">
              <path fill-rule="evenodd" d="M12 1.5a5.25 5.25 0 00-5.25 5.25v3a3 3 0 00-3 3v6.75a3 3 0 003 3h10.5a3 3 0 003-3v-6.75a3 3 0 00-3-3v-3c0-2.9-2.35-5.25-5.25-5.25zm3.75 8.25v-3a3.75 3.75 0 10-7.5 0v3h7.5z" clip-rule="evenodd" />
            </svg>
          </span>

          {{ roomItem.title }}
          <span :class="$style.item_size">
            ({{ roomItem.currentSize }} / {{ roomItem.maxSize }})
          </span>
        </div>
        <div :class="$style.item_description">
          {{ roomItem.description }}
        </div>
        <div :class="$style.item_host_theme">
          방장 &nbsp; <span :class="$style.item_host_theme_name">{{ roomItem.host.nickname }}</span>
          &nbsp; | &nbsp; 테마 &nbsp; <span :class="$style.item_host_theme_name">{{ roomItem.multiTheme }}</span>
        </div>
        <button @click="store.enterRoom" :class="$style.item_enter_button">입장하기</button>
      </div>
  </Card>
</template>

<style lang="css" module>
@import "./StudyRoomListItem.module.css";
</style>
