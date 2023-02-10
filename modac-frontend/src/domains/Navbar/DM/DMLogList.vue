<script setup>
import { useDmStore } from "@/stores/dm";
import { useUserStore } from "@/stores/user"
import { ref } from "vue";
import { storeToRefs } from "pinia";
import DMLogListItem from "@/domains/Navbar/DM/DMLogListItem.vue"
import DMForm from "@/domains/Navbar/DM/DMForm.vue"
import Stomp from "webstomp-client";
import SockJS from "sockjs-client/dist/sockjs.min.js";

const DMstore = useDmStore();
const userStore = useUserStore();
var stompClient = null;

const { loginUser } = storeToRefs(userStore);
const { directMessageRoomSeq, directChatLogs } = storeToRefs(DMstore);
// const logs = ref([]);
// console.log(directChatLogs.value);

// 페이지 파라미터
const page = 0;

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
      chatRoomSeq: directMessageRoomSeq.value,
      sendTime: dateString + " " + timeString,
      message: chatMessage,
    };

    const sendData = {
      usersSeq: chatData.user.seq,
      chatRoomsSeq: chatData.chatRoomSeq,
      sendTime: chatData.sendTime,
      message: chatData.message,
      messageType: "TALK",
      chatRoomType: "DIRECT",
    };

    // 일단 배열에 추가 (하면 안되겠다 불러와야할듯 request랑 response dto가 다름)
    directChatLogs.value.push(chatData);
    
    // 소켓 send
    stompClient.send(`/pub/messages/direct`, JSON.stringify(sendData), {});
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
  // var socket = new SockJS("http://70.12.247.126:8080/ws"); // WebSocketConfig랑 통일할 주소 , 소켓 열 주소
  var socket = new SockJS("http://localhost:8080/ws"); // WebSocketConfig랑 통일할 주소 , 소켓 열 주소
  stompClient = Stomp.over(socket);
  stompClient.connect({}, onConnected, onError);
}

function onConnected() {
  console.log("채팅룸 seq" + directMessageRoomSeq.value);
  stompClient.subscribe(
    `/queue/chat/rooms/enter/direct/${directMessageRoomSeq.value}`,
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

    directChatLogs.value.push(chat);

    liftMessage();
  }, 500);
}


</script>

<template>
  <div :class="$style.chatbox_body_size" id="chatbox_body">
    <DMLogListItem
      v-for="chatLog in directChatLogs"
      :key="chatLog.seq"
      :chatLog="chatLog"
      :loginUser="loginUser"
    />
  </div>
  <DMForm :enterChat="enterChat" ref="child" />
</template>


<style lang="css" module>
@import "DMLogList.module.css";
</style>
