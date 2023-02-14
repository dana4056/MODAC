<script setup>
import { storeToRefs } from "pinia";
import ChatForm from "./ChatForm.vue";
import ChatListItem from "./ChatListItem.vue";
import { useChatStore } from "@/stores/chat";
import { useUserStore } from "@/stores/user";
import { useRoomStore } from "@/stores/room";
import { ref } from "vue";
import Stomp from "webstomp-client";
import SockJS from "sockjs-client/dist/sockjs.min.js";
import OverflowDiv from "@/components/OverflowDiv.vue";

const chatStore = useChatStore();
const userStore = useUserStore();
const roomStore = useRoomStore();
var stompClient = null;

const { loginUser, groupChatLogs, room_info } = storeToRefs(userStore);


const child = ref(null);
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
      chatRoomSeq: room_info.value.chatRoom.seq,
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
  // var socket = new SockJS("http://localhost:8080/ws"); // WebSocketConfig랑 통일할 주소 , 소켓 열 주소
  var socket = new SockJS("https://i8a608.p.ssafy.io/api/ws"); // WebSocketConfig랑 통일할 주소 , 소켓 열 주소
  stompClient = Stomp.over(socket);
  stompClient.connect({}, onConnected, onError);
}

function onConnected() {
  console.log("채팅룸 seq" + room_info.value.chatRoom.seq);
  stompClient.subscribe(
    `/topic/chat/rooms/enter/group/${room_info.value.chatRoom.seq}`,
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

    if(chat.user.seq != loginUser.value.seq){
      groupChatLogs.value.push(chat);
    }

    liftMessage();
  }, 500);
}
</script>

<template>
  <OverflowDiv :class="$style.chatbox_body_size" id="chatbox_body">
    <ChatListItem
      v-for="chatLog in groupChatLogs"
      :key="chatLog.seq"
      :chatLog="chatLog"
      :loginUser="loginUser"
    />
  </OverflowDiv>
  <ChatForm :enterChat="enterChat" ref="child" />
</template>

<style lang="css" module>
@import "ChatList.module.css";
</style>