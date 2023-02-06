<script setup>
import { storeToRefs } from "pinia"
import ChatForm from "./ChatForm.vue";
import ChatListItem from "./ChatListItem.vue";
import { useChatStore } from '@/stores/chat';
import { useUserStore } from '@/stores/user';
import { ref } from "vue";
// import Stomp from 'webstomp-client';
// import SockJS from 'sockjs-client';

const chatStore = useChatStore();
const userStore = useUserStore();

const { loginUser } = storeToRefs(userStore);
const { chatLogs } = storeToRefs(chatStore);

const child = ref(null);

// connect();

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
      message:String,
      MessageType:"",
      chatRoomType:""
    };

    // 일단 배열에 추가 (하면 안되겠다 불러와야할듯 request랑 response dto가 다름)
    chatLogs.value.push(chatData);

    // DB에 저장
    // chatStore.api.postChat(chatData);
  }
};


function liftMessage(){
  setTimeout(() => {
    const element = document.getElementById("chatbox_body");
    element.scrollTop = element.scrollHeight;
  }, 300);
}


// function connect(){
//   this.isSocketConnected = true;
//   var socket = new SockJS('/socket-open/chat');  // WebSocketConfig랑 통일할 주소 , 소켓 열 주소
//   stompClient = Stomp.over(socket);
//   stompClient.connect({}, onConnected, onError);
// }

// function onConnected(){
//   var postIdList = this.$store.state.postIdList;
//   for(var i = 0; i < postIdList.length; i++){
//     stompClient.subscribe(`/sub/send/${postIdList[i]}`, onMessageReceived);
//   }
// }

// function onError(){
//   console.log("소켓 연결 실패");
// }

// function onMessageReceived(res){
//   setTimeout(() => {
//     // console.log('구독으로 받은 메시지', res.body);
//     const post_id = this.$store.state.postIdList[this.$store.state.roomIndex];
//     this.$store.dispatch('FIND_CHAT_LOGS', post_id);
//     this.liftMessage();
//     // 라스트 메시지 갱신
//     let message = "";
//     if(res.body.length < 20){
//         message = res.body;
//     } 
//     else{
//         message = res.body.slice(0,10);
//         message = message + "..."
//     }
//     const changeLastChat = {
//       post_id: res.headers.destination.split("/")[3],
//       message: message
//     };
//     this.$store.commit('SET_LAST_CHAT', changeLastChat);
//   }, 500);
// }


</script>

<template>
  <div :class="$style.chatbox_wrapper">
    <div :class="$style.chatbox_body_size" id="chatbox_body">
      <ChatListItem
        v-for="chatLog of chatLogs"
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
