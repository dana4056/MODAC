import { defineStore } from "pinia";
import { ref } from "vue";
import user from "../api/user";

export const useUserStore = defineStore("user", () => {

    const api = user;   // axois 비동기 함수들

    return {
        api,    
    };
});
