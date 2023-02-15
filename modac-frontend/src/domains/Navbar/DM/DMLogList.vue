<script setup>
import { useDmStore } from "@/stores/dm";
import { useUserStore } from "@/stores/user"
import { ref , onMounted} from "vue";
import { storeToRefs } from "pinia";
import DMLogListItem from "@/domains/Navbar/DM/DMLogListItem.vue"
import DMForm from "@/domains/Navbar/DM/DMForm.vue"
// import Stomp from "webstomp-client";
// import SockJS from "sockjs-client/dist/sockjs.min.js";
import OverflowDiv from "@/components/OverflowDiv.vue";

const DMstore = useDmStore();
const userStore = useUserStore();

const { loginUser } = storeToRefs(userStore);
const { directChatLogs, chatListElement, chatFormElement } = storeToRefs(DMstore);

onMounted(() => {
  chatListElement.value = document.getElementById("chatbox_body");
  DMstore.liftMessage();
});

// const enterChat = (chatMessage) => {
//   if (chatMessage) {
//     liftMessage();

//     // 채팅 input창 초기화
//     child.value.inputChatMessage = "";

//     const date = new Date();
//     const year = date.getFullYear();
//     const month = ("0" + (date.getMonth() + 1)).slice(-2);
//     const day = ("0" + date.getDate()).slice(-2);
//     const dateString = year + "-" + month + "-" + day;
//     const hours = ("0" + date.getHours()).slice(-2);
//     const minutes = ("0" + date.getMinutes()).slice(-2);
//     const seconds = ("0" + date.getSeconds()).slice(-2);
//     const timeString = hours + ":" + minutes + ":" + seconds;

//     const chatData = {
//       user: loginUser.value,
//       chatRoomSeq: directMessageRoomSeq.value,
//       sendTime: dateString + " " + timeString,
//       message: chatMessage,
//     };

//     const sendData = {
//       usersSeq: chatData.user.seq,
//       chatRoomsSeq: chatData.chatRoomSeq,
//       sendTime: chatData.sendTime,
//       message: chatData.message,
//       messageType: "TALK",
//       chatRoomType: "DIRECT",
//     };

//     // 일단 배열에 추가 (하면 안되겠다 불러와야할듯 request랑 response dto가 다름)
//     directChatLogs.value.push(chatData);
    
//     // 소켓 send
//     console.log(stompClient);
//     stompClient.value.send(`/pub/messages/direct`, JSON.stringify(sendData), {});
//   }
// };


// function liftMessage() {
//   setTimeout(() => {
//     const element = document.getElementById("chatbox_body");
//     element.scrollTop = element.scrollHeight;
//   }, 300);
// }


</script>

<template>
  <overflow-div id="chatbox_body" :class="$style.chatbox_content">
    <div :class="$style.chatbox_content">
      <DMLogListItem
        v-for="chatLog in directChatLogs"
        :key="chatLog.seq"
        :chatLog="chatLog"
        :loginUser="loginUser"
      />
    </div>
  </overflow-div>
  <DMForm :enterChat="enterChat" ref="chatFormElement" :class="$style.form_box"/>
</template>


<style lang="css" module>
@import "DMLogList.module.css";
</style>
