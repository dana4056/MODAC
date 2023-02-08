import { defineStore } from "pinia";
import { ref } from "vue";
import room from "../api/room";

export const useRoomStore = defineStore("room", () => {
    //status (변수)
    
    //action (API)
    const api = room;   // axois 비동기 함수들

    return {
        api,    
    };
});
