import { defineStore } from "pinia";
import { ref } from "vue";
import user from "../api/user";

export const useUserStore = defineStore("user", () => {
    //status (변수)
    const loginUser = {};   // 로그인한 사용자

    //action (API)
    const api = user;   // axois 비동기 함수들

    return {
        loginUser,
        api,    
    };
});
