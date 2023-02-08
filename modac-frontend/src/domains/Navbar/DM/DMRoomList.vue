<script setup>
import DMRoomListItem from "./DMRoomListItem.vue";
import { useDmStore } from "@/stores/dm";
import { storeToRefs } from "pinia";

const store = useDmStore();
const { directMessageRoomList, liveDirectMessageRoomNumber } =
  storeToRefs(store);

const getItemSeq = (DMRoomSeq) => {
  console.log(DMRoomSeq);
  liveDirectMessageRoomNumber.value = DMRoomSeq;
};
</script>

<template>
  <ul>
    <DMRoomListItem
      v-for="roomItem in directMessageRoomList"
      :key="roomItem.seq"
      :seq="roomItem.seq"
      :getItemSeq="getItemSeq"
    />
  </ul>
</template>

<style lang="css" module>
@import "DMRoomList.module.css";
</style>
