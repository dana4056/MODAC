import { defineStore } from "pinia";
import { ref } from "vue";

export const useFriendStore = defineStore("friend", () => {
  // 더미 데이터
  const friendList = ref([
    { id: 1, name: "김싸피", status: "알고리즘 학습 중" },
    { id: 2, name: "이싸피", status: "개발 학습 중" },
    { id: 3, name: "박싸피", status: "CS 학습 중" },
    { id: 1, name: "김싸피", status: "알고리즘 학습 중" },
    { id: 2, name: "이싸피", status: "개발 학습 중" },
    { id: 3, name: "박싸피", status: "CS 학습 중" },
    { id: 1, name: "김싸피", status: "알고리즘 학습 중" },
    { id: 2, name: "이싸피", status: "개발 학습 중" },
    { id: 3, name: "박싸피", status: "CS 학습 중" },
  ]);

  return { friendList };
});
