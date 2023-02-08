<script setup>
import { defineProps } from "vue";

const props = defineProps({
  seq: Number,
  getItemSeq: Function,
});
</script>

<template>
  <li :class="$style.dm_items" @click="getItemSeq(props.seq)">
    <div :class="$style.dm_items_left">
      <div :class="$style.dm_items_left_items">
        <span :class="$style.friend_name">seq : {{ props.seq }}</span>
        <span :class="$style.last_text"></span>
      </div>
    </div>
    <div :class="$style.dm_items_right">
      <span :class="$style.time">11:26</span>
    </div>
  </li>
</template>

<style lang="css" module>
@import "DMRoomListItem.module.css";
</style>
