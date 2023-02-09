import { defineStore } from "pinia";
import { ref } from "vue";
import chat from "../api/chat";

export const useChatStore = defineStore("chat", () => {
    //status (변수)

  const chatLogs = ref([ ]);

  // {
  //   user: {
  //     seq: 2,
  //     id: "test2",
  //     nickname: "nick22",
  //     email: "test2@naver.com",
  //     catSkin: 11,
  //     singleTheme: null,
  //     totalSecond: 0,
  //     membershipLevel: "BRONZE_1",
  //     point: 20,
  //     maxPoint: 50,    
  //     token: ""
  //   },
  //   chatRoomSeq: room.chatRoom.seq,
  //   sendTime: dateString + " " + timeString,
  //   message:chatMessage,
  // }

  // usersSeq:null
  // chatRoomsSeq:1
  // sendTime:"2023-02-07 16:19:03"
  // message:"sdf"
// chatRoomType:"GROUP"
// messageType:null
// seq:null


    //action (API)
  const api = chat;   // axois 비동기 함수들

  return {
      chatLogs,
      api,    
  };
});
