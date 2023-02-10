<script setup>
import DMRoomListItem from "./DMRoomListItem.vue";
import { useDmStore } from "@/stores/dm";
import { useUserStore } from "@/stores/user";
import { storeToRefs } from "pinia";

const DMstore = useDmStore();
const userStore = useUserStore();
const { directMessageRoomList, directMessageRoomSeq, directChatLogs } = storeToRefs(DMstore);
const { loginUser } = storeToRefs(userStore);

// DM 채팅방 리스트 불러오기
DMstore.api.fetchRoomList(loginUser.value.seq);

const getMessages = (DMRoomSeq) => {
  // 클릭한 채팅방 번호 저장
  directMessageRoomSeq.value = DMRoomSeq;

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
      :getMessages = "getMessages"
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
