import { defineStore } from "pinia";
import { ref } from "vue";
import chat from "../api/chat";

export const useChatStore = defineStore("chat", () => {
    //status (변수)

    //action (API)
    const api = chat;   // axois 비동기 함수들

    return {
        api,    
    };
});
