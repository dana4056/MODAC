import { defineStore } from "pinia";
import { ref } from "vue";
import user from "../api/user";

export const useUserStore = defineStore("user", () => {
    //status (변수)
    const loginUser = ref({});      // 로그인한 사용자   
    const followingList = ref([]);  // 팔로잉한 사용자 리스트  
    const followerList = ref([]);   // 팔로워 사용자 리스트


    //action (API)
    const api = user;   // axois 비동기 함수들


    return {
        loginUser,
        followingList,
        followerList,
        api,    
    };
});
