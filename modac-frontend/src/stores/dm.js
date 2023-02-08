import dm from "@/api/dm";
import { ref } from "vue";
import { defineStore } from "pinia";

export const useDmStore = defineStore("dm", () => {

  
  //status (변수)
  const directMessageRoomList = ref([
    {
      seq: 1,
      lastMessage:"last message 1",
      lastMessageTime:"2023-02-07 09:22:30",
      talker:{
        seq: 2,                         
        id: "test2",                         
        nickname: "nick2",              
        email: "test2@naver.com",  
        catSkin: 5,                  
        singleTheme: null,     
        totalSecond: 0,
        membershipLevel: "BRONZE_1",
        point: 20,
        maxPoint: 50,    
        token: "sdflkjas;dlkfjaksdljfklsdjfklsj"
      }
    },
    {
      seq: 2,
      lastMessage:"last message 2",
      lastMessageTime:"2023-02-07 09:22:30",
      talker:{
        seq: 3,                         
        id: "test3",                         
        nickname: "nick3",              
        email: "test3@naver.com",  
        catSkin: 8,                  
        singleTheme: null,     
        totalSecond: 0,
        membershipLevel: "BRONZE_1",
        point: 20,
        maxPoint: 50,    
        token: "sdflkjas;dlkfjaksdljfklsdjfklsj"
      }
    },
    {
      seq: 3,
      lastMessage:"last message 3",
      lastMessageTime:"2023-02-07 09:22:30",
      talker:{
        seq: 4,                         
        id: "test4",                         
        nickname: "nick4",              
        email: "test4@naver.com",  
        catSkin: 9,                  
        singleTheme: null,     
        totalSecond: 0,
        membershipLevel: "BRONZE_1",
        point: 20,
        maxPoint: 50,    
        token: "sdflkjas;dlkfjaksdljfklsdjfklsj"
      }
    },
    {
      seq: 4,
      lastMessage:"last message 4",
      lastMessageTime:"2023-02-07 09:22:30",
      talker:{
        seq: 5,                         
        id: "test5",                         
        nickname: "nick5",              
        email: "test5@naver.com",  
        catSkin: 5,                  
        singleTheme: null,     
        totalSecond: 0,
        membershipLevel: "BRONZE_1",
        point: 20,
        maxPoint: 50,    
        token: "sdflkjas;dlkfjaksdljfklsdjfklsj"
      }
    },
  ]);

  const liveDirectMessages = ref([]);


  //action (API)
  const api = dm; // axois 비동기 함수들

  return { 
    directMessageRoomList, 
    liveDirectMessages, 
    api
  };
});
