import user from "../api/user";
import { ref } from "vue";
import { defineStore } from "pinia";

export const useUserStore = defineStore("user", () => {
  //status (변수)
  const loginUser = ref({}); // 로그인한 사용자
  const followingList = ref([]); // 팔로잉한 사용자 리스트
  const followerList = ref([]); // 팔로워 사용자 리스트

  const isLoggedIn = ref(true);
  const login = () => {
    isLoggedIn.value = true;
  };
  const logout = () => {
    isLoggedIn.value = false;
  };

  //action (API)
  const api = user; // axois 비동기 함수들

  return {
    loginUser,
    followingList,
    followerList,
    api,
    isLoggedIn,
    login,
    logout,
  };
});
