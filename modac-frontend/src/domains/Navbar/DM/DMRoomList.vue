<script setup>
import DMRoomListItem from "./DMRoomListItem.vue";
import { useDmStore } from "@/stores/dm";
import { storeToRefs } from "pinia";

const DMstore = useDmStore();
const { directMessageRoomList, liveDirectMessages } = storeToRefs(DMstore);

// DM 채팅방 리스트 불러오기
DMstore.api.fetchRoomList();

const getMessages = (DMRoomSeq) => {
  const payload = {
    roomSeq:DMRoomSeq,
    page:0
  }

  // 클릭한 채팅방 메시지 목록 불러오기
  DMstore.api.fetchMessages(payload);
};

</script>

<template>
  <ul>
    <DMRoomListItem
      v-for="roomItem in directMessageRoomList"
      :key="roomItem.seq"
      :chatRoom="roomItem"
      :seq="roomItem.seq"
      :getItemSeq="getItemSeq"
    />
  </ul>
</template>

<style lang="css" module>
@import "DMRoomList.module.css";
</style>
