import { ref } from "vue";
import axios from "axios";
import { defineStore } from "pinia";

export const useFriendStore = defineStore("friend", () => {
  // User Class 선언 및 인스턴스 만들기
  class User {
    constructor(id, name) {
      this.id = id;
      this.name = name;
    }
  }
  const user1 = new User(1, "성수");
  const user2 = new User(2, "용재");
  const user3 = new User(3, "지영");

  // 친구 목록 확인용 변수
  const friendList = ref([]);
  const getFriendList = () => {
    // 친구 리스트가 axios에 대한 응답으로 전달됐다고 가정
    const axiosReturnValue = ref([user1, user2, user3]);
    friendList.value = axiosReturnValue.value;
    return axiosReturnValue.value;
  };

  return { friendList, getFriendList };
});
