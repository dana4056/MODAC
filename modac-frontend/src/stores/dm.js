import dm from "@/api/dm";
import { ref } from "vue";
import { defineStore } from "pinia";

export const useDmStore = defineStore("dm", () => {

  
  //status (변수)
  const directMessageRoomList = ref([]);

  const directMessageRoomSeq = ref(0);
  const directChatLogs = ref([]);

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

  //action (API)
  const api = dm; // axois 비동기 함수들

  return { 
    directMessageRoomList, 
    directMessageRoomSeq,
    directChatLogs, 
    api
  };
});
