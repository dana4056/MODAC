import dm from "@/api/dm";
import { ref , watch} from "vue";
import { defineStore, storeToRefs } from "pinia";
import { useUserStore } from "@/stores/user";
import Stomp from "webstomp-client";
import SockJS from "sockjs-client/dist/sockjs.min.js";
import BACKEND_API_URL from "@/api/backend";


export const useDmStore = defineStore("dm", () => {
  const userStore = useUserStore();
  const { loginUser } = storeToRefs(userStore);


  
  //status (변수)
  const directMessageRoomList = ref([]); // DM 채팅방 목록
  const directMessageRoomSeq = ref(0); // 선택한 DM 채팅방 목록
  const directChatLogs = ref([]); // 채팅방 내 채팅 로그 목록
  const isDropdownOpenState = ref(false); // DM창 드롭다운 여부
  let stompClient = null;

  let chatListElement = ref(document);
  let chatFormElement = ref(null);


  const liftMessage = () => {
    setTimeout(() => {
      chatListElement.value.scrollTop = chatListElement.value.scrollHeight;
    }, 300);
  }


  const enterChat = (chatMessage) => {
    if (chatMessage) {
      liftMessage();
  
      // 채팅 input창 초기화
      chatFormElement.value.inputChatMessage = "";
  
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
        userNickName: loginUser.value.nickname,
        chatRoomSeq: directMessageRoomSeq.value,
        sendTime: dateString + " " + timeString,
        message: chatMessage,
      };


  
      const sendData = {
        usersSeq: loginUser.value.seq,
        chatRoomsSeq: chatData.chatRoomSeq,
        sendTime: chatData.sendTime,
        message: chatData.message,
        messageType: "TALK",
        chatRoomType: "DIRECT",
      };
  


      directChatLogs.value.push(chatData);
      
      // 소켓 send
      stompClient.send(`/pub/messages/direct`, JSON.stringify(sendData), {});
    }
  };

  const disconnect = () => {
    if (stompClient != null) {
      stompClient.disconnect(function () {
        console.log("DISCONNECT 소켓 연결해제")
      });
    }
  }


  const connect = () => {
    var socket = new SockJS(BACKEND_API_URL + "/ws");
    stompClient = Stomp.over(socket);
    stompClient.connect({},onConnected, onError);
  };

  const onConnected = () => { // async ?
    stompClient.subscribe(
      `/queue/chat/rooms/enter/direct/${directMessageRoomSeq.value}`,
      onMessageReceived
    );
  }

  const onError = () => {
    console.log("소켓 연결 실패");
  };

  const onMessageReceived = (res) => {
    setTimeout(() => {
      var chat = JSON.parse(res.body);
      console.log("구독으로 받은 메시지", chat);

      if (chat.usersSeq != loginUser.value.seq) {
        directChatLogs.value.push(chat);
      }

      liftMessage();
    }, 500);
  };

  //action (API)
  const api = dm; // axois 비동기 함수들

  return {
    directMessageRoomList,
    directMessageRoomSeq,
    directChatLogs,
    isDropdownOpenState,
    stompClient,
    connect,
    disconnect,
    liftMessage,
    enterChat,
    chatListElement,
    chatFormElement,
    api,
  };
},
{
  persist: true,
}
);
