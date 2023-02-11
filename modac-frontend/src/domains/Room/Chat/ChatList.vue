<script setup>
import { storeToRefs } from "pinia";
import ChatForm from "./ChatForm.vue";
import ChatListItem from "./ChatListItem.vue";
import { useChatStore } from "@/stores/chat";
import { useUserStore } from "@/stores/user";
import { ref } from "vue";
import Stomp from "webstomp-client";
import SockJS from "sockjs-client/dist/sockjs.min.js";

const chatStore = useChatStore();
const userStore = useUserStore();
var stompClient = null;

const { loginUser } = storeToRefs(userStore);
const { groupChatLogs } = storeToRefs(chatStore);


// 더미
const room = {
  seq: 1,
  title: "room1",
  description: "방1입니다.",
  maxSize: 3,
  currentSize: 1,
  multiTheme: 0,
  publicType: 0,
  invitationCode: "",
  participants: [
    {
      usersSeq: 1,
      nickname: "nick1",
      status: 0,
      attend: 1,
      catSkin: 2,
      categoriesName: "알고리즘",
      registeredTime: "2023-02-07 09:22:30",
    },
  ],
  host: {
    seq: 1,
    id: "test1",
    nickname: "nick1",
    email: "test1@naver.com",
    catSkin: 2,
    singleTheme: null,
    totalSecond: 0,
    membershipLevel: "BRONZE_LV1",
    point: 0,
    maxPoint: 50,
    token:
      "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MSIsInVzZXJTZXEiOjEsIm5pY2tuYW1lIjoibmljazEiLCJyb2xlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpYXQiOjE2NzU2OTE5MzAsImV4cCI6MTcwNzIyNzkzMH0.d-nWBIKVboVlnjVX3FD5h5OCIFYApyy1NQnwlndTYCY",
  },
  chatRoom: {
    seq: 1,
    lastMessageSeq: 1,
    lastMessageTime: "String",
  },
};

const child = ref(null);
const isSocketConnected = ref(false);
connect();

const enterChat = (chatMessage) => {
  if (chatMessage) {
    liftMessage();

    // 채팅 input창 초기화
    child.value.inputChatMessage = "";

    const date = new Date();
    const year = date.getFullYear();
    const month = ("0" + (date.getMonth() + 1)).slice(-2);
    const day = ("0" + date.getDate()).slice(-2);
    const dateString = year + "-" + month + "-" + day;
    const hours = ("0" + date.getHours()).slice(-2);
    const minutes = ("0" + date.getMinutes()).slice(-2);
    const seconds = ("0" + date.getSeconds()).slice(-2);
    const timeString = hours + ":" + minutes + ":" + seconds;

    const chatData = {
      user: loginUser.value,
      chatRoomSeq: room.chatRoom.seq,
      sendTime: dateString + " " + timeString,
      message: chatMessage,
    };

    // 일단 배열에 추가 (하면 안되겠다 불러와야할듯 request랑 response dto가 다름)
    groupChatLogs.value.push(chatData);

    const sendData = {
      usersSeq: chatData.user.seq,
      chatRoomsSeq: chatData.chatRoomSeq,
      sendTime: chatData.sendTime,
      message: chatData.message,
      MessageType: "TALK",
      chatRoomType: "GROUP",
    };

    // 소켓 send
    stompClient.send(`/pub/messages/group`, JSON.stringify(sendData), {});
  }
};

function liftMessage() {
  setTimeout(() => {
    const element = document.getElementById("chatbox_body");
    element.scrollTop = element.scrollHeight;
  }, 300);
}

function connect() {
  isSocketConnected.value = true;
  var socket = new SockJS("http://i8a608.p.ssafy.io/ws"); // WebSocketConfig랑 통일할 주소 , 소켓 열 주소
  stompClient = Stomp.over(socket);
  stompClient.connect({}, onConnected, onError);
}

function onConnected() {
  console.log("채팅룸 seq" + room.chatRoom.seq);
  stompClient.subscribe(
    `/topic/chat/rooms/enter/group/${room.chatRoom.seq}`,
    onMessageReceived
  );
}

function onError() {
  console.log("소켓 연결 실패");
}

function onMessageReceived(res) {
  setTimeout(() => {
    var chat = JSON.parse(res.body);
    console.log("구독으로 받은 메시지", chat);

    groupChatLogs.value.push(chat);

    liftMessage();
  }, 500);
}
</script>

<template>
  <div :class="$style.chatbox_body_size" id="chatbox_body">
    <ChatListItem
      v-for="chatLog in groupChatLogs"
      :key="chatLog.seq"
      :chatLog="chatLog"
      :loginUser="loginUser"
    />
  </div>
  <ChatForm :enterChat="enterChat" ref="child" />
</template>

<style lang="css" module>
@import "ChatList.module.css";
</style>
