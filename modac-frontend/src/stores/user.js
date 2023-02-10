import user from "../api/user";
import { ref } from "vue";
import { defineStore } from "pinia";

export const useUserStore = defineStore("user", () => {
  //status (변수)
  // const loginUser = ref({}); // 로그인한 사용자
  const loginUser = ref({
    seq: 1,
    id: "test1",
    nickname: "nick1",
    email: "test1@naver.com",
    catSkin: 7,
    singleTheme: null,
    totalSecond: 0,
    membershipLevel: "BRONZE_LV1",
    point: 0,
    maxPoint: 50,
    token:
      "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MSIsInVzZXJTZXEiOjEsIm5pY2tuYW1lIjoibmljazEiLCJyb2xlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpYXQiOjE2NzYwMDg3ODEsImV4cCI6MTcwNzU0NDc4MX0.51ZudvK2ObwkaEMF6hwUFQhbjat4Gvh9RyDXotuLLA0",
  });
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
