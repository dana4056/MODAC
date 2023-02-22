import user from "../api/user";
import { ref } from "vue";
import { defineStore } from "pinia";
import Message from "vue-m-message"

export const useUserStore = defineStore(
  "user",
  () => {
    const loginUser = ref(null); // 로그인한 사용자

    const followingList = ref([]); // 팔로잉한 사용자 리스트
    const followerList = ref([]); // 팔로워 사용자 리스트
    const isFollowing = ref(false);

    const logout = () => {
      loginUser.value = null; // store의 로그인 유저 삭제
      localStorage.removeItem("jwt"); // 로컬 스토리지 토큰 삭제
      Message.success("로그아웃 되었습니다 :-)",{position:"top-right", closable:true});
    };

    //action (API)
    const api = user; // axois 비동기 함수들

    return {
      loginUser,
      followingList,
      followerList,
      isFollowing,
      logout,
      api,
    };
  },
  {
    persist: true,
  }
);
