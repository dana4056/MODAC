<script setup>
import ChatForm from "./ChatForm.vue";
import ChatListItem from "./ChatListItem.vue";

import { ref } from "vue";

const loginUser = ref({
  seq: 1,
  id: "test1",
  nickname: "nick1",
  email: "test1@naver.com",
  catSkin: 1,
  singleTheme: null,
  totalSecond: 0,
  membershipLevel: "BRONZE_LV1",
  point: 0,
  maxPoint: 50,
  token: "",
});

const chatLogs = ref([
  {
    seq: 1,
    usersSeq: 2,
    chatRoomsSeq: 1,
    sendTime: "",
    message: "안녕 남의 편2",
  },
  {
    seq: 2,
    usersSeq: 1,
    chatRoomsSeq: 1,
    sendTime: "",
    message: "안녕 우리 편",
  },
  {
    seq: 3,
    usersSeq: 3,
    chatRoomsSeq: 1,
    sendTime: "",
    message: "안녕 남의 편3",
  },
  {
    seq: 4,
    usersSeq: 1,
    chatRoomsSeq: 1,
    sendTime: "",
    message: "안녕 우리 편",
  },
]);

const enterChat = (chatMessage) => {
  chatLogs.value.push({
    seq: chatLogs.value.length + 1,
    usersSeq: loginUser.value.seq,
    chatRoomSeq: 1,
    sendTime: "",
    message: chatMessage,
  });
};
</script>

<template>
  <div :class="$style.chatbox_wrapper">
    <div :class="$style.chatbox_body_size">
      <ChatListItem
        v-for="chatLog in chatLogs"
        :key="chatLog.seq"
        :chatLog="chatLog"
        :loginUser="loginUser"
      />
    </div>
    <ChatForm :enterChat="enterChat" />
  </div>
</template>

<style lang="css" module>
@import "ChatList.module.css";
</style>
