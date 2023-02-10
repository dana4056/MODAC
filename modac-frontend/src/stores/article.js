import { ref } from "vue";
import { defineStore } from "pinia";
import article from "../api/article";

export const useArticleStore = defineStore("article", () => {
  const api = article; // axois 비동기 함수들

  const articles = ref([]); // 게시글 목록

  const publicType = ref(0);
  let selectedState = ref(0);
  const buttonState = ref(false);

  return {
    api,
    articles,
    publicType,
    selectedState,
    buttonState,
  };
});
