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

  const loginUser = ref({
    // seq:1,
    // id:"test1",
    // nickname:"nick1",
    // email:"test1@naver.com",
    // catSkin:2,
    // singleTheme:null,
    // totalSecond:0,
    // membershipLevel:"BRONZE_LV1",
    // point:0,
    // maxPoint:50,
    // token:"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MSIsInVzZXJTZXEiOjEsIm5pY2tuYW1lIjoibmljazEiLCJyb2xlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpYXQiOjE2NzU2OTE5MzAsImV4cCI6MTcwNzIyNzkzMH0.d-nWBIKVboVlnjVX3FD5h5OCIFYApyy1NQnwlndTYCY",
  });

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
