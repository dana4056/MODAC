import user from "../api/user";
import { ref } from "vue";
import { defineStore } from "pinia";

export const useUserStore = defineStore("user", () => {
  const isLoggedIn = ref(true);
  const login = () => {
    isLoggedIn.value = true;
  };
  const logout = () => {
    isLoggedIn.value = false;
  };

  //status (변수)
  // const loginUser = ref(null);   // 로그인한 사용자

  const loginUser = ref({});

  //action (API)
  const api = user; // axois 비동기 함수들

  return {
    isLoggedIn,
    login,
    logout,
    loginUser,
    api,
  };
});
