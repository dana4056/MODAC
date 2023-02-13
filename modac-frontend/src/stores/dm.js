import dm from "@/api/dm";
import { ref } from "vue";
import { defineStore , storeToRefs } from "pinia";
import { useUserStore } from "@/stores/user";
import Stomp from "webstomp-client";
import SockJS from "sockjs-client/dist/sockjs.min.js";


// const userStore = useUserStore();
// const { loginUser } = storeToRefs(userStore);


export const useDmStore = defineStore("dm", () => {

  const userStore = useUserStore();
  const { loginUser } = storeToRefs(userStore);


  //status (변수)
  const directMessageRoomList = ref([]);    // DM 채팅방 목록
  const directMessageRoomSeq = ref(0);      // 선택한 DM 채팅방 목록
  const directChatLogs = ref([]);           // 채팅방 내 채팅 로그 목록
  const isDropdownOpenState = ref(false);   // DM창 드롭다운 여부
  const stompClient = ref(null);
  // [directMessageRoomList 요소 형식] 
  // seq:Integer, 
  // lastMessage:String,
  // lastMessageTime:Integer
  // talker: {
  //        seq: Integer,                         
  //        id: String,                         
  //        nickname: String,             
  //        email: String,  
  //        catSkin: Integer,                  
  //        singleTheme: String,     
  //        totalSecond: Integer
  //        membershipLevel: String,
  //        point: Integer,
  //        maxPoint: Integer,    
  //        token: String
  //  }

//   {
//     seq:Integer,
//     sendTime:String
//     message:String,
//     chatRoomsSeq:Integer
//     user: {
//          seq: Integer,
//          id: String,
//          nickname: String,
//          email: String,
//          password: String,
//          catSkin: Integer,
//          singleTheme: String,
//          totalSecond: Integer,
//          membership: {
//              grade: String
//          }
//  }

  const connect = () => {
    console.log("connet함수 호출  with"+directMessageRoomSeq.value);
    var socket = new SockJS("http://localhost:8080/ws"); // WebSocketConfig랑 통일할 주소 , 소켓 열 주소
    // var socket = new SockJS("http://70.12.247.126:8080/ws"); // WebSocketConfig랑 통일할 주소 , 소켓 열 주소
    // var socket = new SockJS("https://i8a608.p.ssafy.io/ws"); // WebSocketConfig랑 통일할 주소 , 소켓 열 주소
    stompClient.value = Stomp.over(socket);
    stompClient.value.connect({}, function() {
      console.log("채팅룸 seq" + directMessageRoomSeq.value);
      stompClient.value.subscribe(
        `/queue/chat/rooms/enter/direct/${directMessageRoomSeq.value}`,
        onMessageReceived
      );
    }, onError);
  }

  
  const onError = () => {
    console.log("소켓 연결 실패");
  }
  
  const onMessageReceived = (res) => {
    setTimeout(() => {
      var chat = JSON.parse(res.body);
      console.log("구독으로 받은 메시지", chat);
  
      if(chat.user.seq != loginUser.value.seq){
        directChatLogs.value.push(chat);
      }
  
      liftMessage();
    }, 500);
  }

  //action (API)
  const api = dm; // axois 비동기 함수들

  return { 
    directMessageRoomList, 
    directMessageRoomSeq,
    directChatLogs, 
    isDropdownOpenState,
    stompClient,
    connect,
    api
  };
});
