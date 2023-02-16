<script setup>
import DMRoomListItem from "./DMRoomListItem.vue";
import { useDmStore } from "@/stores/dm";
import { useUserStore } from "@/stores/user";
import { storeToRefs } from "pinia";

const DMstore = useDmStore();
const userStore = useUserStore();
const { directMessageRoomList, directMessageRoomSeq, directChatLogs } = storeToRefs(DMstore);
const { loginUser } = storeToRefs(userStore);


const getMessages = (DMRoomSeq) => {


  // 클릭한 채팅방 번호 저장
  directMessageRoomSeq.value = DMRoomSeq;

  const payload = {
    roomSeq:DMRoomSeq,
    page:0
  }

  // 클릭한 채팅방 메시지 목록 불러오기
  DMstore.api.fetchMessages(payload);
  DMstore.connect(DMRoomSeq);


};


</script>

<template>
  <ul v-if="directMessageRoomList.length != 0">
    <DMRoomListItem
      v-for="roomItem in directMessageRoomList"
      :getMessages="getMessages"
      :key="roomItem.seq"
      :chatRoom="roomItem"
      :seq="roomItem.seq"
      :getItemSeq="getItemSeq"
    />
  </ul>
  <div v-else :class="$style.none_text_box">
    채팅목록이 존재하지 않습니다.
  </div>
</template>

<style lang="css" module>
@import "DMRoomList.module.css";
</style>
