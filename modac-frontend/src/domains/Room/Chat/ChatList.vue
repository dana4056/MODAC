<script setup>
import { storeToRefs } from "pinia"
import ChatForm from "./ChatForm.vue";
import ChatListItem from "./ChatListItem.vue";
import { useChatStore } from '@/stores/chat';
import { useUserStore } from '@/stores/user';
import { ref } from "vue";

const chatStore = useChatStore();
const userStore = useUserStore();

const { loginUser } = storeToRefs(userStore);


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

const child = ref(null);

const enterChat = (chatMessage) => {
  if(chatMessage){
    liftMessage();
    
    // 채팅 input창 초기화
    child.value.inputChatMessage = "";

    const date = new Date();
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);
    const dateString = year + '-' + month  + '-' + day;
    const hours = ('0' + date.getHours()).slice(-2); 
    const minutes = ('0' + date.getMinutes()).slice(-2);
    const seconds = ('0' + date.getSeconds()).slice(-2); 
    const timeString = hours + ':' + minutes  + ':' + seconds;

    const chatData = {
      usersSeq: loginUser.value.seq,
      chatRoomSeq: 1,
      sendTime: dateString + " " + timeString,
      message: chatMessage,
    };

    // 일단 배열에 추가
    chatLogs.value.push(chatData);

    // DB에 저장
    chatStore.api

  }
};


function liftMessage(){
  setTimeout(() => {
    const element = document.getElementById("chatbox_body");
    element.scrollTop = element.scrollHeight;
  }, 300);
}


</script>

<template>
  <div :class="$style.chatbox_wrapper">
    <div :class="$style.chatbox_body_size" id="chatbox_body">
      <ChatListItem
        v-for="chatLog in chatLogs"
        :key="chatLog.seq"
        :chatLog="chatLog"
        :loginUser="loginUser"
      />
    </div>
    <ChatForm :enterChat="enterChat" ref="child"/>
  </div>
</template>

<style lang="css" module>
@import "ChatList.module.css";
</style>
