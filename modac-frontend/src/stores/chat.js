import { defineStore } from "pinia";
import { ref } from "vue";
import chat from "../api/chat";

export const useChatStore = defineStore("chat", () => {
    //status (변수)
  const chatLogs = ref([
    {
      seq: 1,
      user: {
        seq: 2,
        id: "test2",
        nickname: "nick22",
        email: "test2@naver.com",
        catSkin: 11,
        singleTheme: null,
        totalSecond: 0,
        membershipLevel: "BRONZE_1",
        point: 20,
        maxPoint: 50,    
        token: ""
      },
      chatRoom: {
        seq: 1,
        lastMessageSeq: 0,
        lastMessageTime: ""
      },
      sendTime: "2023-02-07 00:31:29",
      message: "안녕 남의 편2",
    },
    {
      seq: 2,
      user: {
        seq: 1,
        id: "test1",
        nickname: "nick1",
        email: "test1@naver.com",
        catSkin: 1,
        singleTheme: null,
        totalSecond: 0,
        membershipLevel: "BRONZE_1",
        point: 20,
        maxPoint: 50,    
        token: ""
      },
      chatRoom: {
        seq: 1,
        lastMessageSeq: 0,
        lastMessageTime: ""
      },
      sendTime: "2023-02-07 00:31:29",
      message: "안녕 우리 편",
    },
    {
      seq: 3,
      user: {
        seq: 3,
        id: "test3",
        nickname: "nick3",
        email: "test3@naver.com",
        catSkin: 3,
        singleTheme: null,
        totalSecond: 0,
        membershipLevel: "BRONZE_1",
        point: 20,
        maxPoint: 50,    
        token: ""
      },
      chatRoom: {
        seq: 1,
        lastMessageSeq: 0,
        lastMessageTime: ""
      },
      sendTime: "2023-02-07 00:31:29",
      message: "안녕 남의 편3",
    },
    {
      seq: 4,
      user: {
        seq: 1,
        id: "test1",
        nickname: "nick1",
        email: "test1@naver.com",
        catSkin: 1,
        singleTheme: null,
        totalSecond: 0,
        membershipLevel: "BRONZE_1",
        point: 20,
        maxPoint: 50,    
        token: ""
      },
      chatRoom: {
        seq: 1,
        lastMessageSeq: 0,
        lastMessageTime: ""
      },
      sendTime: "2023-02-07 00:31:29",
      message: "안녕 우리 편",
    },
  ]);

    //action (API)
  const api = chat;   // axois 비동기 함수들

  return {
      chatLogs,
      api,    
  };
});
